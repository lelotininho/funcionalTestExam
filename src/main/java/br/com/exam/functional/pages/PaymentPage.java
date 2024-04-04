package br.com.exam.functional.pages;

import static br.com.exam.functional.util.WaitUtils.click;
import static br.com.exam.functional.util.WaitUtils.waitVisibilityOfElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.exam.functional.component.Environment;
import br.com.exam.functional.config.DriverSelector;
import br.com.exam.functional.util.WaitUtils;
import io.qameta.allure.Step;

public class PaymentPage implements Page {

    private WebDriver driver;
    public PaymentPage(){
        waitUntilBeLoaded();
    }
    @Step("Wait until the page loads")
    public WebDriver waitUntilBeLoaded(){
        driver = DriverSelector.getCurrentDriver();
        waitUntilBeLoaded(driver);
        WaitUtils.waitUrlTohave(Environment.getEnviroment().getProperty("web.path.payment"));
        return driver;
    }

    private By placeOrder = By.xpath("//div[@class='actions-toolbar']//button[contains(@class,'checkout')]");
    private By totalOrderPrice = By.xpath("//*[@class='grand totals']//*[@class='price']");

    @Step
    public String getTotalOrderPrice(){
        return waitVisibilityOfElement(driver.findElement(totalOrderPrice)).getText();
    }
    @Step
    public PaymentPage clickPlaceOrderButton(){
        click(placeOrder);
        return this;
    }
}
