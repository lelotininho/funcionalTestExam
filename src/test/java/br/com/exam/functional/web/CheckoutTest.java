package br.com.exam.functional.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.primitives.Bytes;

import br.com.exam.functional.config.DriverSelector;
import br.com.exam.functional.pages.MainPage;
import br.com.exam.functional.pages.PaymentPage;
import br.com.exam.functional.pages.ShippingPage;
import br.com.exam.functional.pages.SuccessPage;
import br.com.exam.functional.pages.widget.OfferItem;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class CheckoutTest {

    @BeforeEach
    public void SetupTest()
    {
        DriverSelector.getCurrentDriver();
    }

    @AfterEach
    public void TearDown()
    {
        WebDriver driver = DriverSelector.getCurrentDriver();
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Image", new ByteArrayInputStream(screenshot));

        driver.quit();
    }

    @Test
    public void chooseItemsAndCheckoutTest() throws InterruptedException {
        MainPage mainPage = new MainPage();

        List<String> itemName = mainPage.getOfferItemsNames();

        OfferItem offerItem1 = putItemInCart(mainPage.getOfferItemByName(itemName.get(0)), "M", "Purple");

        mainPage.waitCartItemHave("1");

        OfferItem offerItem2 = putItemInCart(mainPage.getOfferItemByName(itemName.get(1)), "L", "White");

        mainPage.waitCartItemHave("2");

        OfferItem offerItem3 = putItemInCart(mainPage.getOfferItemByName(itemName.get(2)), "M", "Gray");

        mainPage.waitCartItemHave("3");

        int amountOfItens = 2;

        mainPage.openCart()
            .changeAmountOfItemsFromSecondItem(amountOfItens)
            .proceedToCheckout();

        new ShippingPage()
            .fillEmail("abc123def@dummy.com")
            .fillFirstName("dummy")
            .fillLastName("boy")
            .fillStreetAddress("Park Avenue")
            .fillCity("Guatemala")
            .fillRegion("Kansas")
            .fillPostcode("12345-6789")
            .fillTelephone("999-999-999")
            .fillShippingMethod(0)
            .clickNextButton();

        PaymentPage paymentPage = new PaymentPage();
        String totalOrder = paymentPage.getTotalOrderPrice();

        paymentPage.clickPlaceOrderButton();

        String successPageTitle = new SuccessPage().getTitle();

        Assertions.assertAll(
            () -> assertEquals("Thank you for your purchase!", successPageTitle),
            () -> assertEquals((convertsPriceInDouble(offerItem1.price) * amountOfItens +
                convertsPriceInDouble(offerItem2.price) +
                convertsPriceInDouble(offerItem3.price)),
                convertsPriceInDouble(totalOrder),
                "Validating total order price")
        );
    }

    private double convertsPriceInDouble(String price){
        return Double.parseDouble(price.replace("$", ""));
    }

    @Step("Select Item")
    private static OfferItem putItemInCart(WebElement item, String size, String color) {
        OfferItem offerItem = new OfferItem(item);
        return offerItem.selectSize(size)
            .selectColor(color)
            .addToCart();
    }
}
