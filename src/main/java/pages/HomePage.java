package pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.CommonUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.JavaScriptUtils;

public class HomePage {

    private CommonUtils utils;
    private JavaScriptUtils jsUtils;
    private static final Logger logger =
            LogManager.getLogger(HomePage.class);
    private By signupLoginLink = By.xpath("//a[contains(text(),'Signup')]");
    private By productsLink = By.xpath("//a[contains(text(),'Products')]");
    private By cartLink = By.xpath("//a[contains(text(),'Cart')]");
    private By contactLink = By.xpath("//a[contains(text(),'Contact')]");
    private By testCasesLink = By.xpath("//a[contains(text(),'Test Cases')]");
    private By homeLink = By.xpath("//a[contains(text(),'Home')]");
    private By loggedInUser = By.xpath("//a[contains(text(),'Logged in as')]");
    private By logoutBtn = By.xpath("//a[contains(text(),'Logout')]");
    private By deleteAccount = By.xpath("//a[contains(text(),'Delete Account')]");
    private By SubscriptionHeader=By.xpath("//div//h2[text()='Subscription']");
    private By subscribeEmailInput=By.id("susbscribe_email");
    private By subscribeBtn=By.id("subscribe");
    private By subscriptionSuccessText=By.xpath("//*[text()='You have been successfully subscribed!']");
    private By CategoryHeader=By.xpath("//div[@class='left-sidebar']//h2[text()='Category']");
    private By BrandsHeader=By.xpath("//div[@class='left-sidebar']//h2[text()='Brands']");
    private static final String PRODUCT_SUBCATEGORY="//div[contains(@class,'left-sidebar')]//a[normalize-space()='%s']";
    private static final String PRODUCT_SUBCATEGORY_TYPE="//div[@id='%s']//ul//li//a[normalize-space()='%s']";
    private static final String CATEGORY_RESULT="//div[@class='features_items']//h2[text()='%s']";
    private static final String PRODUCT_BRAND_NAME="//div[@class='brands-name']//a[text()='%s']";



    public HomePage(WebDriver driver)
    {
        this.utils = new CommonUtils(driver);
        this.jsUtils=new JavaScriptUtils(driver);
    }

    private By subCategoryLocator(String subCategory) {
        return By.xpath(String.format(PRODUCT_SUBCATEGORY, subCategory));
    }
    private By subCategoryTypeLocator(String subCategory,String subCategoryType) {
        return By.xpath(String.format(PRODUCT_SUBCATEGORY_TYPE, subCategory,subCategoryType));
    }

    private By getBrandResultLocator(String brand) {
        String expectedText = "Brand" + " - " + brand + " Products";
        return By.xpath(String.format(CATEGORY_RESULT, expectedText));
    }

    private By brandLocator(String brand) {
        return By.xpath(String.format(PRODUCT_BRAND_NAME,brand));
    }

    private By getCategoryResultLocator(String category, String subCategory) {
        String expectedText = category + " - " + subCategory + " Products";
        return By.xpath(String.format(CATEGORY_RESULT, expectedText));
    }



    public void clickSignupLogin() {
        logger.info("Clicking Signup/Login link");
        utils.click(signupLoginLink);
    }

    public void clickProducts() {
        logger.info("Clicking on Products link");
        utils.click(productsLink);
    }

    public void clickCart() {
        logger.info("Clicking on Cart link");
        utils.click(cartLink);
    }

    public void clickContactUs() {
        logger.info("Clicking Contact Us link");
        utils.click(contactLink);
    }

    public void clickTestCases() {
        logger.info("Clicking Test Cases link");
        utils.click(testCasesLink);
    }

    public Boolean verifyHomePage() {
        logger.info("Verifying Home page is displayed");
        return utils.getText(homeLink).contains("Home");
    }

    public boolean isUserLoggedIn(String userName) {
        logger.info("Verifying user is logged in as: {}", userName);
        return utils.getText(loggedInUser).contains(userName);
    }

    public void clickLogout() {
        logger.info("Logging out current user");
        utils.click(logoutBtn);
    }

    public void deleteAccount() {
        logger.info("Deleting user account");
        utils.click(deleteAccount);
    }

    public Boolean verifySubscriptionHeader()
    {
        logger.info("Verifying Subscription Header");
        jsUtils.scrollToBottom();
        logger.debug("Subscription Header "+utils.getText(SubscriptionHeader));
        return utils.getText(SubscriptionHeader).contains("Subscription");
    }

    public void addSubscription()
    {
        logger.info("Adding Subscription");
        utils.type(subscribeEmailInput,"test767@test.com");
        utils.click(subscribeBtn);
    }

    public Boolean isSubscriptionSuccessMsgVisible()
    {
        logger.info("Verifying Subscription Message");
        logger.debug("Subscription success message: "+utils.getText(subscriptionSuccessText));
        return utils.getText(subscriptionSuccessText).contains("You have been successfully subscribed");
    }

    public Boolean isCategoryHeaderPresent()
    {
        jsUtils.scrollIntoView(CategoryHeader);
        logger.debug("Category header value is "+utils.getText(CategoryHeader));
        return utils.getText(CategoryHeader).equalsIgnoreCase("Category");
    }

    public void selectCategory(String category)
    {
        utils.click(subCategoryLocator(category));
    }

    public void selectSubCategory(String category,String subCategory)
    {
        utils.click(subCategoryTypeLocator(category,subCategory));
    }

    public boolean isCategoryResultDisplayed(String category, String subCategory) {
        return utils.getText(getCategoryResultLocator(category, subCategory))
                .equalsIgnoreCase(category + " - " + subCategory + " Products");
    }

    public Boolean isBrandHeaderPresent()
    {
        jsUtils.scrollIntoView(BrandsHeader);
        logger.debug("Category header value is "+utils.getText(BrandsHeader));
        return utils.getText(BrandsHeader).equalsIgnoreCase("Brands");
    }

    public void selectBrand(String brand)
    {
        utils.click(brandLocator(brand));
    }

    public boolean isBrandResultDisplayed(String brand) {
        return utils.getText(getBrandResultLocator(brand))
                .equalsIgnoreCase("Brands" + " - " + brand + " Products");
    }


}

