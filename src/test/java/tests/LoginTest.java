package tests;

import base.BaseTest;
import dataprovider.TestDataProvider;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignupLoginPage;

import java.time.Duration;

public class LoginTest extends BaseTest {
    private HomePage homepage;
    private SignupLoginPage signuploginpage;

    @BeforeMethod
    public void initPages()
    {
        homepage=new HomePage(driver);
        signuploginpage=new SignupLoginPage(driver);
    }
    @Test(dataProvider = "loginValidData",
            dataProviderClass = TestDataProvider.class)
    public void validLogin(String email,String password,String name)
    {
        Allure.step("========== START: Login Valid Test ==========");

        Allure.step("Navigate to Signup / Login page");
        homepage.clickSignupLogin();

        Allure.step("Verify Login section is visible");
        Assert.assertTrue(
                signuploginpage.isLoginHeaderVisible(),
                "Login header section is not visible"
        );
        Allure.step("Login using valid email and password");
        signuploginpage.login(email,password);
        logger.info("Login steps completed successfully");

        Allure.step("Verify user is logged in as " + name);
        homepage.isUserLoggedIn(name);

        Allure.step("========== END: Login Valid Test ==========");
    }

    @Test(dataProvider = "invalidLoginData",
            dataProviderClass = TestDataProvider.class)
    public void inValidLogin(String email,String password)
    {
        logger.info("========== START: Login InValid Test ==========");

        logger.info("Navigating to Signup/Login page");
        homepage.clickSignupLogin();

        logger.info("Verifying 'Login' section visibility");
        Assert.assertTrue(
                signuploginpage.isLoginHeaderVisible(),
                "Login header section is not visible"
        );
        logger.info("'Login' section is visible");

        logger.info("Calling login method");
        logger.debug("Trying to login with email :{}"+email+ "and password: {}"+password);
        signuploginpage.login(email,password);
        logger.info("Login steps completed successfully");

        logger.info("Verifying incorrect email or password message");
        Assert.assertTrue(
                signuploginpage.isInvalidLoginMsgVisible(),
                "Incorrect email or password message not displayed"
        );
        logger.info("Incorrect email or password message displayed successfully");

        logger.info("========== END: Login InValid Test ==========");
    }
    @Test(dataProvider = "loginValidData",
            dataProviderClass = TestDataProvider.class)
    public void logOut(String email,String password,String name)
    {
        logger.info("========== START: Log Out Test ==========");

        logger.info("Navigating to Signup/Login page");
        homepage.clickSignupLogin();

        logger.info("Verifying 'Login' section visibility");
        Assert.assertTrue(
                signuploginpage.isLoginHeaderVisible(),
                "Login header section is not visible"
        );
        logger.info("'Login' section is visible");

        logger.info("Calling login method");
        logger.debug("Trying to login with email :{}"+email+ "and password: {}"+password);
        signuploginpage.login(email,password);
        logger.info("Login steps completed successfully");

        logger.info("Verifying user is logged in as: {}", name);
        homepage.isUserLoggedIn(name);
        logger.info("User login verified");

        logger.info("Logging out the user");
        homepage.clickLogout();
        Assert.assertTrue(
                signuploginpage.isLoginHeaderVisible(),
                "Login header section is not visible"
        );
        logger.info("User logged out successfully");
        logger.info("========== END Log Out Test ==========");
    }
}
