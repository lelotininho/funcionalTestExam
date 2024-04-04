package br.com.exam.functional.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.com.exam.functional.config.DriverSelector;

public class WaitUtils {
    public static WebElement click(By path){
        WebElement element = waitElementToBeClickable(path);
        element.click();
        return element;
    }
    public static WebElement click(WebElement element){
        waitElementToBeClickable(element).click();
        return element;
    }

    public static WebElement sendKeys(By path, String text){
        WebElement element = DriverSelector.getCurrentDriver().findElement(path);
        element.sendKeys(text);
        return element;
    }

    public static WebElement waitElementToBeClickable(WebElement element){
        DriverSelector.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    public static WebElement waitElementToBeClickable(By path){
        DriverSelector.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(path));
        return DriverSelector.getCurrentDriver().findElement(path);
    }

    public static List<WebElement> waitElementsToBeClickable(By path){
        DriverSelector.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(path));
        return DriverSelector.getCurrentDriver().findElements(path);
    }

    public static WebElement waitElementToBeSelected(By path){
        DriverSelector.getWebDriverWait().until(ExpectedConditions.elementToBeSelected(path));
        return DriverSelector.getCurrentDriver().findElement(path);
    }
    public static WebElement waitVisibilityOfElement(WebElement element){
        DriverSelector.getWebDriverWait().until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public static void waitUrlTohave(String url) {
        DriverSelector.getWebDriverWait().until(ExpectedConditions.urlContains(url));
    }
}
