package pages.Amazon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.CommonUtils;

public class ProductPage {
    private CommonUtils utils;
    private static final Logger logger =
            LogManager.getLogger(ProductPage.class);

    private By quantityDropdown= By.xpath("//span[contains(@class,'a-dropdown-label')]");
    private By addToCartBtn=By.id("add-to-cart-button");
    private By productNameLocator=By.id("productTitle");
    private By productPrice=By.xpath("//div[@id='corePriceDisplay_desktop_feature_div']//span[@class='a-price-whole']");

    private By addedToCartMsg=By.xpath("//h1[contains(text(),'Added to cart')]");

    private By goToCartBtn=By.xpath("//a[contains(text(),'Go to Cart')]");

   // private By productPrize=By.xpath("//div[@data-cel-widget='corePriceDisplay_desktop_feature_div']//span[@class='a-price-whole']");
   private By productPrize =
           By.cssSelector("#corePriceDisplay_desktop_feature_div .a-price-whole");


    private static final String QUANTITY_VALUE="//a[@id='quantity_%d']";


    private By getQuantityValueLocator(int quantity)
    {
        return By.xpath((String.format(QUANTITY_VALUE,quantity)));
    }

    public ProductPage(WebDriver driver)
    {
        this.utils=new CommonUtils(driver);
    }

    public Boolean verifyProductName(String productName)
    {
        utils.switchToNewWindow();
        return utils.getText(productNameLocator).contains(productName);
    }

    public void selectQuantity(int quantity)
    {
        quantity=quantity-1;
     utils.click(quantityDropdown);
     utils.click(getQuantityValueLocator(quantity));
    }

    public void clickAddToCartBtn()
    {
        utils.click(addToCartBtn);
    }

    public Boolean verifyAddedToCartMsg()
    {
        return utils.getText(addedToCartMsg).contains("Added to cart");
    }

    public void clickGoToCart()
    {
        utils.click(goToCartBtn);
    }

    public int calculatePrice(int quantity)
    {
        String prize = utils.getText(productPrize);

        // Remove ₹ and decimals
        prize = prize.replace("₹","").split("\\.")[0];

        // Remove comma
        prize = prize.replace(",", "");

        int priceInt = Integer.parseInt(prize);

        int totalPrize = priceInt * quantity;

        System.out.println("Total price is " + totalPrize);

        return totalPrize;
    }



}
