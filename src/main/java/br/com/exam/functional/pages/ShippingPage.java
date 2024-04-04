package br.com.exam.functional.pages;

import static br.com.exam.functional.util.WaitUtils.click;
import static br.com.exam.functional.util.WaitUtils.sendKeys;
import static br.com.exam.functional.util.WaitUtils.waitElementToBeClickable;
import static br.com.exam.functional.util.WaitUtils.waitElementsToBeClickable;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.exam.functional.component.Environment;
import br.com.exam.functional.config.DriverSelector;
import br.com.exam.functional.pages.widget.OfferItem;
import br.com.exam.functional.util.WaitUtils;
import io.qameta.allure.Step;

public class ShippingPage implements Page {

    private WebDriver driver;

    public ShippingPage(){
        waitUntilBeLoaded();
    }

    @Step("Wait until the page loads")
    public WebDriver waitUntilBeLoaded(){
        driver = DriverSelector.getCurrentDriver();
        waitUntilBeLoaded(driver);
        WaitUtils.waitUrlTohave(Environment.getEnviroment().getProperty("web.path.shipping"));
        return driver;
    }

    private By email = By.xpath("//*[@id='customer-email-fieldset']//*[@id='customer-email']");
    private By firstName = By.xpath("//*[@name='firstname']");
    private By lastName = By.xpath("//*[@name='lastname']");
    private By streetAddress = By.xpath("//*[@name='street[0]']");
    private By city = By.xpath("//*[@name='city']");
    private By region = By.xpath("//*[@name='region_id']");
    private By postcode = By.xpath("//*[@name='postcode']");
    private By country = By.xpath("//*[@name='country_id']");
    private By telephone = By.xpath("//*[@name='telephone']");
    private By shippingMethod = By.xpath("//*[@id='checkout-shipping-method-load']//input");
    private By nextButton = By.xpath("//*[@id='shipping-method-buttons-container']//button");

    @Step
    public ShippingPage fillEmail(String value){
        sendKeys(email, value);
        return this;
    }
    public ShippingPage fillFirstName(String value){
        sendKeys(firstName, value);
        return this;
    }
    public ShippingPage fillLastName(String value){
        sendKeys(lastName, value);
        return this;
    }
    public ShippingPage fillStreetAddress(String value){
        sendKeys(streetAddress, value);
        return this;
    }
    public ShippingPage fillCity(String value){
        sendKeys(city, value);
        return this;
    }
    public ShippingPage fillRegion(String value) throws InterruptedException {
//        Thread.sleep(2500);
        Select regionDropdown = new Select(driver.findElement(region));
//        Thread.sleep(2500);
//        driver.findElement(region).click();
//        Thread.sleep(2500);
//        regionDropdown.selectByVisibleText(value);
        regionDropdown.selectByValue("12");
//        regionDropdown.getOptions().stream().parallel().filter(item -> item.getText().equalsIgnoreCase(value)).findFirst().get().click();
        Thread.sleep(2500);
        return this;
    }
    public ShippingPage fillPostcode(String value){
        sendKeys(postcode, value);
        return this;
    }
    public ShippingPage fillCountry(String value){
        sendKeys(country, value);
        return this;
    }
    public ShippingPage fillTelephone(String value){
        sendKeys(telephone, value);
        return this;
    }
    public ShippingPage fillShippingMethod(int index) throws InterruptedException {
        waitElementsToBeClickable(shippingMethod).get(index).click();
        Thread.sleep(2000);
        return this;
    }
    public ShippingPage clickNextButton() throws InterruptedException {
        click(nextButton);
        Thread.sleep(2000);
        return this;
    }
}
