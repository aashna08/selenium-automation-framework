package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.CommonUtils;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage {

    private static final Logger logger =
            LogManager.getLogger(ContactUsPage.class);
    private By cartProductNames =
            By.xpath("//table//tbody/tr/td[@class='cart_description']//a");
    private By productName;

    private static final String PRODUCT_QUANTITY_XPATH =
            "//a[normalize-space()='%s']/ancestor::td/following-sibling::td[@class='cart_quantity']//button";

    private static final String PRODUCT_DELETE_XPATH="//table//tbody/tr/td[@class='cart_description']//a[normalize-space()='%s']/ancestor::td/following-sibling::td[@class='cart_delete']//a";
    private By proccedToCheckoutBtn=By.xpath("//a[text()='Proceed To Checkout']");
    private By checkOutHeader=By.xpath("//div[@class='modal-content']//h4[text()='Checkout']");
    private By registerLoginBtn=By.xpath("//a//u[text()='Register / Login']");
    private By productQuantityInCart = By.xpath("//table//tbody/tr/td[@class='cart_description']//a[text()='" + productName + "']//ancestor::td//following-sibling::td[@class='cart_quantity']//button");
    private CommonUtils utils;

    public CartPage(WebDriver driver) {
        this.utils = new CommonUtils(driver);
    }

    public List<String> getProductNamesInCart() {
        return utils.getAllElements(cartProductNames)
                .stream()
                .map(e -> e.getText())
                .collect(Collectors.toList());
    }

    private By getProductQuantityLocator(String productName) {
        return By.xpath(String.format(PRODUCT_QUANTITY_XPATH, productName));
    }

    private By getDeleteProductLocator(String productName)
    {
        return By.xpath(String.format(PRODUCT_DELETE_XPATH,productName));
    }

    public String getProductQuantityInCart(String productName) {
        logger.info("Fetching quantity for product: {}", productName);
        String quantity = utils.getText(getProductQuantityLocator(productName));
        logger.debug("Quantity for {} is {}", productName, quantity);
        return quantity;
    }

    public void deleteProductFromCart(String productName) {
        utils.click(getDeleteProductLocator(productName));
        utils.refreshPage();
    }

    public void clickProceedToCheckOutBtn()
    {
        logger.info("Clicking proceed to checkout button");
        utils.click(proccedToCheckoutBtn);
    }

    public Boolean IsCheckOutHeaderVisible()
    {
        return utils.getText(checkOutHeader).contains("Checkout");
    }

    public void clickRegisterLoginBtn()
    {
        utils.click(registerLoginBtn);
    }



}

