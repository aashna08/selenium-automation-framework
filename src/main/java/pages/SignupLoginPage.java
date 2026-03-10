package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.CommonUtils;

public class SignupLoginPage {

    private CommonUtils utils;
    private static final Logger logger =
            LogManager.getLogger(SignupLoginPage.class);
    private By newUserHeader = By.xpath("//h2[text()='New User Signup!']");
    private By loginHeader = By.xpath("//h2[text()='Login to your account']");
    private By nameInput = By.xpath("//input[@placeholder='Name']");
    private By signupEmail = By.xpath("//div[@class='signup-form']//input[@placeholder='Email Address']");
    private By signupBtn = By.xpath("//button[text()='Signup']");
    private By loginEmail = By.xpath("//input[@type='email']");
    private By loginPassword = By.xpath("//input[@type='password']");
    private By loginBtn = By.xpath("//button[text()='Login']");
    private By existingEmailErrorMessage = By.xpath("//p[text()='Email Address already exist!']");
    private By invalidLoginMsg = By.xpath("//p[text()='Your email or password is incorrect!']");

    public SignupLoginPage(WebDriver driver) {
        this.utils = new CommonUtils(driver);
    }
    public void signup(String name, String email) {
        logger.info("Filling signup form");
        logger.debug("Input fields -> Name: "+name+" Email: "+email);
        utils.type(nameInput, name);
        utils.type(signupEmail, email);
        utils.click(signupBtn);
    }
    public void login(String email, String password) {
        logger.info("Filling login form");
        logger.debug("Input fields -> Email: "+email+" Password: "+password);
        utils.type(loginEmail, email);
        utils.type(loginPassword, password);
        utils.click(loginBtn);
    }
    public boolean isNewUserVisible() {
        logger.info("Verifying new user header message");
        logger.debug("New User Header Message: "+utils.getText(newUserHeader));
        return utils.getText(newUserHeader).contains("New User Signup");
    }

    public boolean isLoginHeaderVisible() {
        logger.info("Verifying login header message");
        logger.debug("Login Header Message: "+utils.getText(loginHeader));
        return utils.getText(loginHeader).contains("Login");
    }

    public boolean isInvalidLoginMsgVisible()
    {
        logger.info("Verifying invalid login message");
        logger.debug("Invalid login Error Message: "+utils.getText(invalidLoginMsg));
        return utils.getText(invalidLoginMsg).contains("Your email or password is incorrect");
    }

    public boolean isExistingEmailErrorMessageVisible()
    {
        logger.info("Verifying existing email message");
        logger.debug("Existing Email Error Message: "+utils.getText(existingEmailErrorMessage));
        return utils.getText(existingEmailErrorMessage).contains("Email Address already exist");
    }
}

