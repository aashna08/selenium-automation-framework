package tests;

import base.BaseTest;
import dataprovider.TestDataProvider;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;
import pages.SignupLoginPage;

public class ProductsTest extends BaseTest {

    private HomePage homepage;
    private ProductsPage productspage;

    @BeforeMethod
    public void initPages()
    {
        homepage=new HomePage(driver);
        productspage=new ProductsPage(driver);
    }

    @Test
    public void viewAllProducts()
    {
        Allure.step("========== START: View All Products ==========");

        Allure.step("Navigate to Products page");
        homepage.clickProducts();

        Allure.step("Handle google ad");
        productspage.getProductslink();

        Allure.step("Verify All Products Header");
        productspage.verifyAllProductsHeader();

        Allure.step("Click View Product btn");
        productspage.clickViewProductBtn();

        Allure.step("Handle google ad");
        productspage.getFirstProductLink();

        Allure.step("Verify product name is present");
        String productName=productspage.getProductName();
        Assert.assertTrue(
                productName != null && !productName.trim().isEmpty(),
                "Product name is null or empty"
        );

        Allure.step("Verify product availability is present");
        String productAvailability=productspage.getProductAvailability();
        Assert.assertTrue(
                productAvailability != null && !productAvailability.trim().isEmpty(),
                "Product availability is null or empty"
        );

        Allure.step("Verify product condition is present");
        String productCondition=productspage.getProductCondition();
        Assert.assertTrue(
                productCondition != null && !productCondition.trim().isEmpty(),
                "Product condition is null or empty"
        );

        Allure.step("Verify product brand is present");
        String productBrand=productspage.getProductBrand();
        Assert.assertTrue(
                productBrand != null && !productBrand.trim().isEmpty(),
                "Product Brand is null or empty"
        );
        Allure.step("========== END: View All Products ==========");
    }

    @Test
    public void SearchProducts()
    {
        Allure.step("========== START: View All Products ==========");

        Allure.step("Navigate to Products page");
        homepage.clickProducts();

        Allure.step("Handle google ad");
        productspage.getProductslink();

        Allure.step("Verify All Products Header");
        productspage.verifyAllProductsHeader();

        Allure.step("Search Product");
        productspage.searchProduct("dress");

        Allure.step("Verifying search results");
        productspage.AreSearchResultsRelevant("dress");

        Allure.step("========== END: View All Products ==========");
    }

    @Test(dataProvider = "viewCategoryData",
            dataProviderClass = TestDataProvider.class)
    public void viewCategoryProducts(String category,String subCategory)
    {
        Allure.step("========== START: viewCategoryData ==========");

        Allure.step("Verify Home Page");
        homepage.verifyHomePage();

        Allure.step("Verify Category Header");
        Assert.assertTrue(homepage.isCategoryHeaderPresent(),
                "Category header not present"
                );

        Allure.step("Select Category");
        homepage.selectCategory(category);

        Allure.step("Select SubCategory");
        homepage.selectSubCategory(category,subCategory);

        Allure.step("Verify Category Results");
        Assert.assertTrue(homepage.isCategoryResultDisplayed(category,subCategory),
                "Results not displayed"
        );
        Allure.step("========== END: viewCategoryData ==========");
    }


    @Test(dataProvider = "viewBrandData",
            dataProviderClass = TestDataProvider.class)
    public void viewBrandProducts(String brand)
    {
        Allure.step("========== START: viewBrandProducts ==========");

        Allure.step("Verify Home Page");
        homepage.verifyHomePage();

        Allure.step("Verify Brand Header");
        Assert.assertTrue(homepage.isBrandHeaderPresent(),
                "Brands header not present"
        );
        Allure.step("Select Brand");
        homepage.selectBrand(brand);

        Allure.step("Verify Brand Results");
        Assert.assertTrue(homepage.isBrandResultDisplayed(brand),
                "Results not displayed"
        );
        Allure.step("========== END: viewBrandProducts ==========");
    }

    @Test
    public void addReviewOnProduct() {
        Allure.step("========== START: addReviewOnProduct ==========");

        Allure.step("Navigate to Products page");
        homepage.clickProducts();

        Allure.step("Handle google ad");
        productspage.getProductslink();

        Allure.step("Verify All Products Header");
        productspage.verifyAllProductsHeader();

        Allure.step("Click View Product btn");
        productspage.clickViewProductBtn();

        Allure.step("Handle google ad");
        productspage.getFirstProductLink();

        Allure.step("Verify product name is present");
        String productName = productspage.getProductName();
        Assert.assertTrue(
                productName != null && !productName.trim().isEmpty(),
                "Product name is null or empty"
        );

        Assert.assertTrue(productspage.verifyReviewHeader(),
                "Review not displayed"
        );
        productspage.writeReview();

        Assert.assertTrue(productspage.verifyReviewSuccessMsg(),
                "Review Success Msg not displayed"
        );
        Allure.step("========== START: addReviewOnProduct ==========");

    }
}
