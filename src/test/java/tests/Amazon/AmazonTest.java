package tests.Amazon;

import base.BaseTest;
import dataprovider.TestDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Amazon.CartPage;
import pages.Amazon.HomePage;
import pages.Amazon.ProductPage;

public class AmazonTest extends BaseTest {
    private HomePage homepage;
    private ProductPage productpage;

    private CartPage cartpage;
    @BeforeMethod
    public void initPages()
    {
        homepage=new HomePage(driver);
        productpage=new ProductPage(driver);
        cartpage=new CartPage(driver);
    }

    @Test(dataProvider = "amazonData",
            dataProviderClass = TestDataProvider.class)
    public void amazonTest(String productName,int quantity) {
        homepage.searchproduct("hp smart tank");

        Assert.assertTrue(homepage.hasSearchResults(), "Search results present");

        homepage.selectProduct(productName);

        productpage.verifyProductName(productName);

        productpage.selectQuantity(quantity);

        productpage.clickAddToCartBtn();

        Assert.assertTrue(productpage.verifyAddedToCartMsg(), "Added to cart not present");

        productpage.clickGoToCart();

        Assert.assertTrue(cartpage.verifyProductName(productName),"Product not present");

        Assert.assertTrue(cartpage.verifyQuantity(quantity), "quantity not present");

    }
}
