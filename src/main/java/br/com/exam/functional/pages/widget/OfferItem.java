package br.com.exam.functional.pages.widget;

import static br.com.exam.functional.util.WaitUtils.click;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import br.com.exam.functional.config.DriverSelector;
import br.com.exam.functional.util.WaitUtils;
import io.qameta.allure.Step;

public class OfferItem {

    public WebElement currentItem;
    public final String name;
    public final String price;
    public static By productSelector = By.cssSelector(".product-item");
    private static By nameSelector = By.cssSelector(".product-item-name");
    private static By priceSelector = By.cssSelector(".price");
    private static By sizeListSelector = By.cssSelector(".size");
    private static By colorListSelector = By.cssSelector(".color");
    private static By itemSelector = By.cssSelector(".swatch-option");
    private static By addToCartButtonSelector = By.cssSelector(".action.primary");

    public OfferItem(WebElement item){
        new Actions(DriverSelector.getCurrentDriver()).moveToElement(item);
        currentItem = item;
        name = item.findElement(nameSelector).getText();
        price = item.findElement(priceSelector).getText();
    }

    @Step("Select size")
    public OfferItem selectSize(String size) {
        currentItem.findElement(sizeListSelector).findElements(itemSelector).stream().parallel().forEach(
            item -> {
                if(item.getText().equalsIgnoreCase(size))
                    item.click();
            }
        );
        return this;
    }

    @Step("Select color")
    public OfferItem selectColor(String color) {
        currentItem.findElement(colorListSelector).findElements(itemSelector).stream().parallel().forEach(
            item -> {
                if(item.getAttribute("aria-label").equalsIgnoreCase(color))
                    item.click();
            }
        );
        return this;
    }

    @Step("Add to card")
    public OfferItem addToCart() {
//        new Actions(DriverSelector.getCurrentDriver()).moveToElement(currentItem);
        click(currentItem.findElement(addToCartButtonSelector));
        return this;
    }
}
