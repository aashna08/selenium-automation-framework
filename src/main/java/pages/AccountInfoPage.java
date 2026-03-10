package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.CommonUtils;
import utils.DropDownUtils;

public class AccountInfoPage {
    private CommonUtils utils;
    private static final Logger logger =
            LogManager.getLogger(AccountInfoPage.class);
    private DropDownUtils dropdowns;
    // ----------- Text Fields -----------

    private By accountInfoheader = By.xpath("//*[text()='Enter Account Information']");
    private By password = By.id("password");
    private By firstName = By.id("first_name");
    private By lastName = By.id("last_name");
    private By company = By.id("company");
    private By address1 = By.id("address1");
    private By address2 = By.id("address2");
    private By state = By.id("state");
    private By city = By.id("city");
    private By zipcode = By.id("zipcode");
    private By mobile = By.id("mobile_number");

    // ----------- Dropdowns -----------
    private By days = By.id("days");
    private By months = By.id("months");
    private By years = By.id("years");
    private By country = By.id("country");

    // ----------- Button -----------
    private By createAccountBtn =
            By.xpath("//button[text()='Create Account']");

    public AccountInfoPage(WebDriver driver) {
        this.utils = new CommonUtils(driver);
        this.dropdowns = new DropDownUtils(driver);
    }

    public Boolean isAccountInfoHeaderVisible()
    {
        logger.info("Verifying Account Information Header");
        logger.debug("Account Information header: "+utils.getText(accountInfoheader));
        return utils.getText(accountInfoheader).toLowerCase().contains("enter account information");
    }

    public void fillAccountInformation(
            String passwordValue,
            int day,
            int month,
            int year,
            String firstNameValue,
            String lastNameValue,
            String companyValue,
            String address1Value,
            String address2Value,
            String countryValue,
            String stateValue,
            String cityValue,
            String zipValue,
            String mobileValue
    ) {
        logger.info("Filling account information form");

        // Password
        utils.type(password, passwordValue);

        // Date of Birth
        dropdowns.selectByIndex(days, day);
        dropdowns.selectByIndex(months, month);
        dropdowns.selectByIndex(years, year);

        // Personal Details
        utils.type(firstName, firstNameValue);
        utils.type(lastName, lastNameValue);
        utils.type(company, companyValue);

        // Address Details
        utils.type(address1, address1Value);
        utils.type(address2, address2Value);
        dropdowns.selectByVisibleText(country, countryValue);

        // Location & Contact
        utils.type(state, stateValue);
        utils.type(city, cityValue);
        utils.type(zipcode, zipValue);
        utils.type(mobile, mobileValue);
        utils.click(createAccountBtn);
    }



}
