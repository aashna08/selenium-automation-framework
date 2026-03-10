package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.CommonUtils;
import utils.JavaScriptUtils;

import java.util.List;

public class ProductsPage {

    private CommonUtils utils;
    private JavaScriptUtils jsutils;
    private static final Logger logger =
            LogManager.getLogger(ProductsPage.class);

    private By allProductsHeader=By.xpath("//div//h2[text()='All Products']");
    private By viewProductsBtns=By.xpath("//a[text()='View Product']");
    private By productName=By.xpath("//div//h2/following-sibling::p/preceding-sibling::h2");
    private By productAvailablity=By.xpath("//b[text()='Availability:']/parent::p");
    private By productCondition=By.xpath("//b[text()='Condition:']/parent::p");
    private By productBrand=By.xpath("//b[text()='Brand:']/parent::p");
    private By searchInput=By.id("search_product");
    private By searchBtn=By.id("submit_search");
    private By productNames=By.xpath(".//p");
    private By allProducts=By.xpath("//div[@class='productinfo text-center']");
    private By productPrices = By.xpath(".//h2");
    private By addToCartBtn = By.xpath(".//*[text()='Add to cart']");
    private By viewCartBtn=By.xpath("//a//u[text()='View Cart']");
    private By ContinueShoppingBtn=By.xpath("//button[text()='Continue Shopping']");
    private By productAddedToCartSuccessMsg=By.xpath("//div[@class='modal-body']//p[text()='Your product has been added to cart.']");
    private By quantityInput=By.id("quantity");
    private By addToCartBtnProductView=By.xpath("//button[@type='button']");
    private By reviewHeader=By.xpath("//a[text()='Write Your Review']");
    private By reviewNameInput=By.id("name");
    private By reviewEmailInput=By.id("email");
    private By reviewDetails=By.xpath("//textarea[@name='review']");
    private By reviewBtn=By.id("button-review");
    private By reviewSuccessMsg=By.xpath("//*[contains(text(),'Thank you for your review')]");
    public ProductsPage(WebDriver driver)
    {
        this.utils=new CommonUtils(driver);
        this.jsutils=new JavaScriptUtils(driver);
    }

    public Boolean verifyAllProductsHeader()
    {
        logger.info("Verifying All Products Header");
        logger.debug("All Products Header: "+utils.getText(allProductsHeader));
        return utils.getText(allProductsHeader).contains("All Products");
    }

    public void clickViewProductBtn()
    {
        logger.info("Clicking View Product button for the first product");
        WebElement element=utils.getElementFromList(viewProductsBtns,0);
        utils.safeClick(viewProductsBtns);
    }

    public String getProductName()
    {
        logger.info("Checking product name");
        logger.debug("Product Name is: "+utils.getText(productName));
        return utils.getText(productName);
    }

    public String getProductAvailability()
    {
        logger.info("Checking product availability");
        logger.debug("Product Name is: "+utils.getText(productAvailablity));
        return utils.getText(productAvailablity);
    }

    public String getProductCondition()
    {
        logger.info("Checking product condition");
        logger.debug("Product Name is: "+utils.getText(productCondition));
        return utils.getText(productCondition);
    }

    public String getProductBrand()
    {
        logger.info("Checking product Brand");
        logger.debug("Product Name is: "+utils.getText(productBrand));
        return utils.getText(productBrand);
    }

    public void getProductslink()
    {
        utils.handleGoogleVignette("products");
    }

    public void getFirstProductLink()
    {
        utils.handleGoogleVignette("product_details/1");
    }

    public void searchProduct(String keyword)
    {
        logger.info("Searching Product");
        logger.debug("Product for search: "+keyword);
        utils.type(searchInput,keyword);
        utils.click(searchBtn);
    }

    public List<String> getProductNames()
    {
        logger.info("Getting product names");
        return utils.getTexts(productNames);
    }

    public Boolean AreSearchResultsRelevant(String keyword)
    {
        logger.info("Checking search result");
        List<String> productNames=getProductNames();
        for(String name:productNames)
        {
            if(!(name.toLowerCase().contains(keyword.toLowerCase())))
            {
                return false;
            }
        }
        return true;
    }

    public void getFirstProduct()
    {
        logger.info("Get First Product");
        WebElement element=utils.getElementFromList(allProducts,0);
    }

    public String getProductName(int index) {
        return utils
                .getAllElements(allProducts)
                .get(index)
                .findElement(productNames)
                .getText();
    }

    public String getProductPrice(int index) {
        return utils
                .getAllElements(allProducts)
                .get(index)
                .findElement(productPrices)
                .getText();
    }

    public void addProductToCart(int index) {
        var product = utils.getAllElements(allProducts).get(index);
        jsutils.hover(product);
        WebElement element=product.findElement(addToCartBtn);
        utils.safeClick(element);
    }

    public void clickContinueShoppingBtn()
    {
        logger.info("Click Continue Shopping");
        utils.click(ContinueShoppingBtn);
    }

    public Boolean verifyProductAddedToCartSuccessMsg()
    {
        logger.debug("Add to cart success msg: "+utils.getText(productAddedToCartSuccessMsg));
        return utils.getText(productAddedToCartSuccessMsg).contains("Your product has been added to cart");
    }

    public void clickViewCartBtn()
    {
        logger.info("Click View Cart Button");
        utils.click(viewCartBtn);
    }

    public void addQuantity(String number)
    {
        logger.info("Add Quantity");
        utils.type(quantityInput,number);
    }

    public void clickAddToCartProductsView()
    {
        utils.click(addToCartBtnProductView);
    }

    public Boolean verifyReviewHeader()
    {
        logger.info("Verifying Review Header");
        logger.debug("Review Header: "+utils.getText(reviewHeader));
        return utils.getText(reviewHeader).equalsIgnoreCase("Write Your Review");
    }

    public void writeReview()
    {
        logger.info("writing review");
        utils.type(reviewNameInput,"Test One");
        utils.type(reviewEmailInput,"test828@test.com");
        utils.type(reviewDetails,"Test review");
        utils.click(reviewBtn);
    }

    public Boolean verifyReviewSuccessMsg()
    {
        logger.info("Verifying Review Success Message");
        logger.debug("Review Success Message: "+utils.getText(reviewSuccessMsg));
        return utils.getText(reviewSuccessMsg).contains("Thank you for your review");
    }


}
