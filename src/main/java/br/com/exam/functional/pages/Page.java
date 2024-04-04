package br.com.exam.functional.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import br.com.exam.functional.config.DriverSelector;

public interface Page {

    int DEFAULT_TIMEOUT = 10;
    int MEDIUM_TIMEOUT = 20;
    int LONG_TIMEOUT = 30;
    default WebDriver createNewDriver(){
        return DriverSelector.getCurrentDriver();
    }
    default void waitUntilBeLoaded(WebDriver driver){
        waitUntilBeLoaded(driver, DEFAULT_TIMEOUT);
    }
    default void waitUntilBeLoaded(WebDriver driver, int timeOut) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        String currentState = "";
        int limit = timeOut;
        try {
            do {
                currentState = (String) javascriptExecutor.executeScript("return document.readyState");
                Thread.sleep(DEFAULT_TIMEOUT * 50);
            } while (!"complete".equals(currentState) && --limit > 0);

            if(!"complete".equals(currentState)){
                throw new WebDriverException("Page did not load or took too long");
            }
        } catch (InterruptedException ex){
            throw new WebDriverException("Unable to wait for ready state");
        }
    }

}
