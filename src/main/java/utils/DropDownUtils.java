package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDownUtils {

    private WebDriver driver;
    private WaitUtils waitUtils;

    public DropDownUtils(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, 10);
    }

    public void selectByVisibleText(By locator, String text) {
        WebElement element = waitUtils.waitForVisibility(locator);
        new Select(element).selectByVisibleText(text);
    }

    public void selectByValue(By locator, String value) {
        WebElement element = waitUtils.waitForVisibility(locator);
        new Select(element).selectByValue(value);
    }

    public void selectByIndex(By locator, int index) {
        WebElement element = waitUtils.waitForVisibility(locator);
        new Select(element).selectByIndex(index);
    }

    public String getSelectedOption(By locator) {
        WebElement element = waitUtils.waitForVisibility(locator);
        return new Select(element).getFirstSelectedOption().getText();
    }
}
