package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JavaScriptUtils {

    private JavascriptExecutor js;
    private Actions actions;
    private WebDriver driver;

    public JavaScriptUtils(WebDriver driver) {

        this.js = (JavascriptExecutor) driver;
        this.actions=new Actions(driver);
        this.driver=driver;
    }

    public void scrollToTop() {
        js.executeScript("window.scrollTo(0, 0)");
    }

    public void scrollIntoView(By locator) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollIntoView(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void scrollToElement(WebElement element) {

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    public void clickByJS(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    public void hover(WebElement element) {
        try {
            actions.moveToElement(element).perform();
        } catch (MoveTargetOutOfBoundsException e) {
            scrollToElement(element);
            actions.moveToElement(element).perform();
        }
    }

    public void hoverAndClick(WebElement parent, By childLocator) {
        hover(parent);

        try {
            WebElement child = parent.findElement(childLocator);
            child.click();
        } catch (ElementClickInterceptedException e) {
            clickByJS(parent.findElement(childLocator));
        }
    }
}
