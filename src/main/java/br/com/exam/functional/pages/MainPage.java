package br.com.exam.functional.pages;

import static br.com.exam.functional.util.WaitUtils.click;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.com.exam.functional.component.Environment;
import br.com.exam.functional.config.DriverSelector;
import br.com.exam.functional.pages.widget.OfferItem;
import io.qameta.allure.Step;

public class MainPage implements Page {

    private WebDriver driver;

    private By cart = By.cssSelector(".showcart");
    private By itemsInCart = By.cssSelector(".counter.qty .counter-number");
    public MainPage(){
        waitUntilBeLoaded();
    }

    @Step("Wait until the page loads")
    public WebDriver waitUntilBeLoaded(){
        driver = DriverSelector.getCurrentDriver();
        driver.get(Environment.getEnviroment().getProperty("web.path.main"));
        waitUntilBeLoaded(driver);
        return driver;
    }

    public List<WebElement> getOfferItems(){
        return driver.findElements(OfferItem.productSelector);
    }

    public List<String> getOfferItemsNames(){
        return getOfferItems().stream().map(item -> new OfferItem(item).name).toList();
    }

    public WebElement getOfferItemByName(String name){
        return driver.findElements(OfferItem.productSelector).stream().filter(item -> new OfferItem(item).name.equalsIgnoreCase(name)).findFirst().get();
    }

    @Step
    public Cart openCart() throws InterruptedException {
        driver.findElement(cart).click();
        return new Cart();
    }

    public void waitCartItemHave(String value) {
        DriverSelector.getWebDriverWait().until(ExpectedConditions.textToBePresentInElementLocated(itemsInCart, value));
    }

    public class Cart{
        private By items = By.cssSelector("#mini-cart .item");
        private By qtyInput = By.cssSelector(".cart-item-qty");
        private By updateInput = By.cssSelector(".update-cart-item");
        private By checkoutButton = By.cssSelector("#top-cart-btn-checkout");

        @Step
        public Cart changeAmountOfItemsFromSecondItem(int amount) throws InterruptedException {
            WebElement item = driver.findElements(items).get(2);
            WebElement input = item.findElement(qtyInput);
            input.click();
            input.clear();
            input.sendKeys(Keys.BACK_SPACE);
            input.sendKeys(amount + "");
            item.findElement(updateInput).click();
            return this;
        }

        @Step
        public Cart proceedToCheckout(){
            click(checkoutButton);
            return this;
        }
    }
}
