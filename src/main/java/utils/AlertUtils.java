package utils;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertUtils {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(AlertUtils.class);

    public AlertUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Accept browser alert")
    public void acceptAlert() {
        logger.info("Waiting for alert to be present");
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        logger.info("Alert found. Accepting alert");
        alert.accept();
    }

    @Step("Dismiss browser alert")
    public void dismissAlert() {
        logger.info("Waiting for alert to be present");
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        logger.info("Dismissing alert");
        alert.dismiss();
    }

    @Step("Get alert text")
    public String getAlertText() {
        logger.info("Fetching alert text");
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        logger.debug("Alert text: {}", text);
        return text;
    }
}

