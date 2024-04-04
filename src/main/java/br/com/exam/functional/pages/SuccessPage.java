package br.com.exam.functional.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.exam.functional.component.Environment;
import br.com.exam.functional.config.DriverSelector;
import br.com.exam.functional.pages.widget.OfferItem;
import br.com.exam.functional.util.WaitUtils;
import io.qameta.allure.Step;

public class SuccessPage implements Page {

    private WebDriver driver;
    public SuccessPage(){
        waitUntilBeLoaded();
    }

    @Step("Wait until the page loads")
    public WebDriver waitUntilBeLoaded(){
        driver = DriverSelector.getCurrentDriver();
        waitUntilBeLoaded(driver);
        WaitUtils.waitUrlTohave(Environment.getEnviroment().getProperty("web.path.success"));
        return driver;
    }

    private By title = By.xpath("//*[@class='page-title']//*[@class='base']");

    public String getTitle() {
        return driver.findElement(title).getText();
    }
}
