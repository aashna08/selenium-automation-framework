package utils;

import org.openqa.selenium.*;

import java.util.List;

public class CommonUtils {

    private WebDriver driver;
    private WaitUtils waitUtils;

    public CommonUtils(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, 10);
    }

    public void click(By locator) {
        waitUtils.waitForClickable(locator).click();
    }

    public WebElement getElementFromList(By locator, int index) {
        List<WebElement> elements =
                waitUtils.waitForAllElementsVisible(locator);

        if (elements.size() <= index) {
            throw new RuntimeException(
                    "Requested index " + index +
                            " but only " + elements.size() +
                            " elements found for locator: " + locator
            );
        }
        return elements.get(index);
    }

    public void type(By locator, String text) {
        WebElement element = waitUtils.waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {
        return waitUtils.waitForVisibility(locator).getText();
    }

    public void handleGoogleVignette(String appendUrl) {
        if (driver.getCurrentUrl().contains("google_vignette")) {
            String cleanUrl = driver.getCurrentUrl().split("#")[0] + appendUrl;
            driver.get(cleanUrl);
            System.out.println("URL Loaded " + cleanUrl);
        }
    }

    public void safeClick(By locator) {
        try {
            waitUtils.waitForClickable(locator).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();",
                            waitUtils.waitForVisibility(locator));
        }
    }

    public void safeClick(WebElement element) {
        try {
            waitUtils.waitForClickable(element).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }
    }

    public List<String> getTexts(By locator) {
        List<WebElement> elements =
                waitUtils.waitForAllElementsVisible(locator);
        return elements.stream()
                .map(WebElement::getText)
                .toList();
    }

    public void refreshPage() {
        //logger.info("Refreshing the page");
        driver.navigate().refresh();
    }

    public List<WebElement> getAllElements(By locator) {
        return waitUtils.waitForAllElementsVisible(locator);
    }


}
