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
import pages.*;
import org.testng.annotations.Listeners;
import Listeners.AllureFailureListener;

import java.time.Duration;
@Listeners({
        io.qameta.allure.testng.AllureTestNg.class,
        AllureFailureListener.class
})

public class RegisterUser extends BaseTest {

    private HomePage homepage;
    private SignupLoginPage signuploginpage;
    private AccountPage accountpage;
    private AccountInfoPage accountinfopage;

    private TestCasesPage testcasespage;

    private ContactUsPage contactuspage;

    @BeforeMethod
    public void initPages() {
        homepage = new HomePage(driver);
        signuploginpage = new SignupLoginPage(driver);
        accountinfopage = new AccountInfoPage(driver);
        accountpage = new AccountPage(driver);
        contactuspage=new ContactUsPage(driver);
        testcasespage=new TestCasesPage(driver);
    }

    @Test(dataProvider = "registerUserData",
            dataProviderClass = TestDataProvider.class)
    public void registerUserTest( String name,
                                         String emailPrefix,
                                         String password,
                                         int day,
                                         int month,
                                         int year,
                                         String firstName,
                                         String lastName,
                                         String company,
                                         String address1,
                                         String address2,
                                         String country,
                                         String state,
                                         String city,
                                         String zipcode,
                                         String mobile) {

        logger.info("========== START: Register User Test ==========");

        logger.info("Navigating to Signup/Login page");
        homepage.clickSignupLogin();

        logger.info("Verifying 'New User Signup' section visibility");
        Assert.assertTrue(
                signuploginpage.isNewUserVisible(),
                "New User Signup section is not visible"
        );
        logger.info("'New User Signup' section is visible");

        String email =
                emailPrefix + System.currentTimeMillis() + "@test.com";
        logger.debug("Generated unique email: {}", email);
        logger.info("Submitting signup form with Name: {}", name);
        signuploginpage.signup(name,email);

        logger.info("Verifying Account Information page is displayed");
        Assert.assertTrue(
                accountinfopage.isAccountInfoHeaderVisible(),
                "Account Information page not displayed"
        );
        logger.info("Account Information page displayed successfully");

        logger.info("Filling account information details");
        logger.debug(
                "DOB: {}/{}/{}, Country: {}, State: {}, City: {}",
                day, month, year, country, state, city
        );
        accountinfopage.fillAccountInformation(
                password,
                day, month, year,
                firstName, lastName,
                company,
                address1, address2,
                country,
                state, city,
                zipcode, mobile
        );
        logger.info("Account information submitted");

        logger.info("Verifying account creation success message");
        Assert.assertTrue(
                accountpage.isAccountCreated(),
                "Account was not created successfully"
        );
        logger.info("Account created successfully");

        logger.info("Clicking Continue button after account creation");
        accountpage.clickContinue();

        logger.info("Verifying user is logged in as: {}", name);
        homepage.isUserLoggedIn(name);
        logger.info("User login verified");

        logger.info("Deleting the created account");
        homepage.deleteAccount();

        logger.info("Verifying account deletion confirmation");
        Assert.assertTrue(
                accountpage.isAccountDeleted(),
                "Account was not deleted successfully"
        );
        logger.info("Account deleted successfully");

        logger.info("========== END: Register User Test ==========");

    }



    @Test(dataProvider = "existingUserData",
            dataProviderClass = TestDataProvider.class)
    public void registerWithExistingEmail(String name,String email)
    {
        logger.info("========== START: Register User with existing email ==========");

        logger.info("Navigating to Signup/Login page");
        homepage.clickSignupLogin();

        logger.info("Verifying 'New User Signup' section visibility");
        Assert.assertTrue(
                signuploginpage.isNewUserVisible(),
                "New User Signup section is not visible"
        );
        logger.info("'New User Signup' section is visible");

        logger.info("Submitting signup form with Name: {} ", name +" and email: {} "+email);
        signuploginpage.signup(name,email);
        logger.info("'New User Signup' section details entered successfully");

        logger.info("Verifying email id already exists message");
        Assert.assertTrue(
                signuploginpage.isExistingEmailErrorMessageVisible(),
                "Existing Email Error Message not visible"
        );
        logger.info("Existing email error message is visible");

        logger.info("========== END: Register User with existing email ==========");
    }

    @Test(dataProvider = "ContactUsData",
            dataProviderClass = TestDataProvider.class)
    public void contactUs(String name,String email,String subject,String message)
    {
        Allure.step("========== START: Contact US Test ==========");

        Allure.step("Navigate to Contact Us Page");
        homepage.clickContactUs();

        Allure.step("Verify Get in Touch Message");
        Assert.assertTrue(
                contactuspage.isgetInTouchHeaderPresent(),
                "GET IN TOUCH Header not visible"
        );

        Allure.step("Fill contact us form");
        contactuspage.fillContactUsForm(name,email,subject,message);

        Allure.step("Accept Alert box");
        contactuspage.acceptAlertMsg();

        Allure.step("Verify Success Message");
        Assert.assertTrue(
                contactuspage.verifySuccessMsg(),
                "Success Message not visible"
        );

        Allure.step("Navigate to Home");
        contactuspage.clickSuccessBtn();

        Allure.step("Verify  Home");
        Assert.assertTrue(
                homepage.verifyHomePage(),
                "Home Page not present"
        );

        Allure.step("========== END: Contact US Test ==========");
    }

    @Test
    public void viewTestCases()
    {
        Allure.step("========== START: View Test Cases ==========");

        Allure.step("Navigate to test cases page");
        homepage.clickTestCases();

        testcasespage.getTestCaseslink();

        Allure.step("Verify test cases header");
        Assert.assertTrue(testcasespage.verifyTestCasesHeader(),
                "Test Cases header not present"
        );
        Allure.step("========== END: View Test Cases ==========");
    }

    @Test
    public void addSubscription()
    {
        Allure.step("========== START: ADD Subscription ==========");
        Allure.step("Navigate to bottom and verify subscription header");
        homepage.verifySubscriptionHeader();
        Allure.step("Add Subscription");
        homepage.addSubscription();
        Assert.assertTrue(homepage.isSubscriptionSuccessMsgVisible(),
                "Subscription success message not visible"
                );
        Assert.fail("Failing test case");
        Allure.step("========== END: ADD Subscription ==========");
    }
}













