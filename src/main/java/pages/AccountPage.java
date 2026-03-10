package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.CommonUtils;

public class AccountPage {

    private CommonUtils utils;
    private static final Logger logger =
            LogManager.getLogger(AccountPage.class);

    private By accountCreated = By.xpath("//b[text()='Account Created!']");
    private By accountDeleted = By.xpath("//b[text()='Account Deleted!']");
    private By continueBtn = By.xpath("//a[text()='Continue']");
    //private By deleteAccount = By.xpath("//a[contains(text(),'Delete Account')]");

    public AccountPage(WebDriver driver) {
        this.utils = new CommonUtils(driver);
    }

    public boolean isAccountCreated() {
        logger.info("Verifying Account created message");
        logger.debug("Account Created Message: "+utils.getText(accountCreated));
        return utils.getText(accountCreated).toLowerCase().contains("account created");
    }

    public void clickContinue() {
        logger.info("Clicking Continue button");
        utils.click(continueBtn);
    }



    public boolean isAccountDeleted() {
        logger.info("Verifying Account deleted message");
        logger.debug("Account Deleted Message: "+utils.getText(accountDeleted));
        return utils.getText(accountDeleted).toLowerCase().contains("account deleted");
    }
}

