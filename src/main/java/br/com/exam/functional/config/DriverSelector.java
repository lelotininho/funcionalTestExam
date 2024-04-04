package br.com.exam.functional.config;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.exam.functional.component.Environment;
import io.qameta.allure.Allure;

public class DriverSelector {

    private static WebDriver currentDriver;

    private static WebDriverWait webDriverWait;

    public synchronized static WebDriver getCurrentDriver() {
        return currentDriver != null ? currentDriver : createDriver();
    }
    public synchronized static WebDriver getCurrentDriver(String driverName) {
        return currentDriver != null ? currentDriver : createDriver(driverName);
    }

    public synchronized static WebDriverWait getWebDriverWait() {
        if (webDriverWait == null){
            webDriverWait = new WebDriverWait(getCurrentDriver(), Duration.ofSeconds(
                Integer.parseInt(Environment.getEnviroment().getProperty("web.timeout.default"))
            ));
        }
        return webDriverWait;
    }

    private synchronized static WebDriver createDriver() {
        String driverName = Environment.getEnviroment().getProperty("web.driver");
        currentDriver = createDriver(driverName);
        currentDriver.manage().timeouts().implicitlyWait(
            Duration.ofSeconds(
                Integer.parseInt(Environment.getEnviroment().getProperty("web.timeout.default")
                )
            )
        );
        return currentDriver;
    }

    private synchronized static WebDriver createDriver(String driverName) {
        return switch (driverName) {
            case "chrome" -> configChromeDriver();
            case "firefox" -> configFirefoxDriver();
            case "edge" -> configEdgeDriver();
            case "ie" -> configInternetExplorerDriver();
            case "safari" -> configSafariDriver();
            default -> throw new WebDriverException("Invalid Web Driver");
        };
    }

    //Nos métodos abaixo fica um espaço para a inserção de configurações individuais para cada driver
    private static ChromeDriver configChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        logCapabilities(options);
        return new ChromeDriver(options);
    }

    private static FirefoxDriver configFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        logCapabilities(options);
        return new FirefoxDriver(options);
    }

    private static EdgeDriver configEdgeDriver() {
        EdgeOptions options = new EdgeOptions();
        logCapabilities(options);
        return new EdgeDriver(options);
    }

    private static InternetExplorerDriver configInternetExplorerDriver() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        logCapabilities(options);
        return new InternetExplorerDriver(options);
    }

    private static SafariDriver configSafariDriver() {
        SafariOptions options = new SafariOptions();
        logCapabilities(options);
        return new SafariDriver(options);
    }

    private static void logCapabilities(AbstractDriverOptions<?> options) {
        Allure.addAttachment("Environment", ("WebDriver{browserName='%s', browserVersion='%s', platformName='%s'}")
            .formatted(options.getBrowserName(), options.getBrowserVersion(), options.getPlatformName())
        );
    }

}
