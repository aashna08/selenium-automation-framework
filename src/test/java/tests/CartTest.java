package tests;

import base.BaseTest;
import dataprovider.TestDataProvider;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.util.List;

public class CartTest extends BaseTest {
    private HomePage homepage;
    private ProductsPage productspage;
    private CartPage cartpage;
    private AccountInfoPage accountinfopage;
    private SignupLoginPage signuploginpage;
    private AccountPage accountpage;
    private CheckOutPage checkoutpage;
    private PaymentPage paymentpage;

    @BeforeMethod
    public void initPages()
    {
        homepage=new HomePage(driver);
        productspage=new ProductsPage(driver);
        cartpage=new CartPage(driver);
        signuploginpage=new SignupLoginPage(driver);
        accountinfopage=new AccountInfoPage(driver);
        accountpage=new AccountPage(driver);
        checkoutpage=new CheckOutPage(driver);
        paymentpage=new PaymentPage(driver);
    }

    @Test
    public void addProductsInCart()
    {
        Allure.step("========== START: Add Products in Cart ==========");
        Allure.step("Navigate to Products page");
        homepage.clickProducts();

        Allure.step("Handle google ad");
        productspage.getProductslink();

        Allure.step("Verify All Products Header");
        productspage.verifyAllProductsHeader();

        Allure.step("Fetch first two products names");
        String firstProductName = productspage.getProductName(0);
        String secondProductName = productspage.getProductName(1);

        Allure.step("Add first product to cart");
        productspage.addProductToCart(0);
        Assert.assertTrue(productspage.verifyProductAddedToCartSuccessMsg(),
                "Product not added to cart"
        );

        Allure.step("Click continue shopping button");
        productspage.clickContinueShoppingBtn();
        Allure.step("Add first product to cart");
        productspage.addProductToCart(1);
        Assert.assertTrue(productspage.verifyProductAddedToCartSuccessMsg(),
                "Product not added to cart"
        );
        Allure.step("Click view cart button");
        productspage.clickViewCartBtn();

        Allure.step("Verify selected products added in the cart");
        List<String> productsInCart=cartpage.getProductNamesInCart();
        Assert.assertTrue(productsInCart.contains(firstProductName),
                "First product not present in the cart"
        );
        Assert.assertTrue(productsInCart.contains(secondProductName),
                "Second product not present in the cart"
        );
        Allure.step("========== END: Add Products in Cart ==========");
    }

    @Test
    public void verifyQuantity()
    {
            Allure.step("========== START: Verify Quantity ==========");

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

        Allure.step("Add quantity");
            productspage.addQuantity("4");
        Allure.step("Click Add to Cart button");
            productspage.clickAddToCartProductsView();
        Assert.assertTrue(productspage.verifyProductAddedToCartSuccessMsg(),
                "Product not added to cart"
        );
        Allure.step("Click View Cart button");
        productspage.clickViewCartBtn();
        Allure.step("Verify selected products added in the cart");
        List<String> productsInCart=cartpage.getProductNamesInCart();
        Assert.assertTrue(productsInCart.contains(productName),
                "Product not present in the cart"
        );
        Allure.step("Verify quantity added in the cart");
        String productQuantityInCart= cartpage.getProductQuantityInCart(productName);
        Assert.assertTrue(productQuantityInCart.equals("4"),
                "Quantity is correct"
        );
        Allure.step("========== END: Verify Quantity ==========");
    }

    @Test(dataProvider = "registerUserData",
            dataProviderClass = TestDataProvider.class)
    public void registerAfterAddToCart( String name,
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
                                        String mobile)
    {
        Allure.step("========== START:registerAfterAddToCart ==========");
        Allure.step("Navigate to Products page");
        homepage.clickProducts();

        Allure.step("Handle google ad");
        productspage.getProductslink();

        Allure.step("Verify All Products Header");
        productspage.verifyAllProductsHeader();

        Allure.step("Fetch first two products names");
        String firstProductName = productspage.getProductName(0);
        String secondProductName = productspage.getProductName(1);

        Allure.step("Add first product to cart");
        productspage.addProductToCart(0);
        Assert.assertTrue(productspage.verifyProductAddedToCartSuccessMsg(),
                "Product not added to cart"
        );

        Allure.step("Click continue shopping button");
        productspage.clickContinueShoppingBtn();

        Allure.step("Add second product to cart");
        productspage.addProductToCart(1);
        Assert.assertTrue(productspage.verifyProductAddedToCartSuccessMsg(),
                "Product not added to cart"
        );
        Allure.step("Click view cart button");
        productspage.clickViewCartBtn();

        Allure.step("Verify selected products added in the cart");
        List<String> productsInCart=cartpage.getProductNamesInCart();
        Assert.assertTrue(productsInCart.contains(firstProductName),
                "First product not present in the cart"
        );
        Assert.assertTrue(productsInCart.contains(secondProductName),
                "Second product not present in the cart"
        );

        Allure.step("Click Proceed to Check out button");
        cartpage.clickProceedToCheckOutBtn();
        Assert.assertTrue(cartpage.IsCheckOutHeaderVisible(),
                "Check out header is not visible"
        );

        Allure.step("Click Register/Signin button");
        cartpage.clickRegisterLoginBtn();

        Allure.step("Verify 'New User Signup' section visibility");
        Assert.assertTrue(
                signuploginpage.isNewUserVisible(),
                "New User Signup section is not visible"
        );

        String email =
                emailPrefix + System.currentTimeMillis() + "@test.com";
        logger.debug("Generated unique email: {}", email);
        logger.info("Submitting signup form with Name: {}", name);
        signuploginpage.signup(name,email);

        Allure.step("Verify Account Information page is displayed");
        Assert.assertTrue(
                accountinfopage.isAccountInfoHeaderVisible(),
                "Account Information page not displayed"
        );


        Allure.step("Fill account information details");
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

        Allure.step("Verify account creation success message");
        Assert.assertTrue(
                accountpage.isAccountCreated(),
                "Account was not created successfully"
        );

        Allure.step("Click Continue button after account creation");
        accountpage.clickContinue();

        Allure.step("Verify user is logged in as: " + name);
        homepage.isUserLoggedIn(name);

        Allure.step("Click Cart button");
        homepage.clickCart();

        Allure.step("Click Proceed to checkout button");
        cartpage.clickProceedToCheckOutBtn();

        Allure.step("Verify Header Details");
        Assert.assertTrue(checkoutpage.IsaddressdetailsheaderPresent(),
                "Address detail header not present"
                );

        Allure.step("Fill checkout form");
        checkoutpage.fillCheckOutForm();

        Allure.step("Verify Payments Header");
        Assert.assertTrue(paymentpage.isPaymentHeaderPresent(),
                "Payment header not visible"
                );
        Allure.step("Fill Card Details");
        paymentpage.fillCardDetails();

        Allure.step("Verify Order Placed Success Message");
        Assert.assertTrue(paymentpage.isOrderSuccess(),
                "Order not placed"
                );
        Allure.step("Delete Account");
        homepage.deleteAccount();

        Allure.step("Verify account deletion confirmation");
        Assert.assertTrue(
                accountpage.isAccountDeleted(),
                "Account was not deleted successfully"
        );
        Allure.step("Account deleted successfully");
        Allure.step("========== END:registerAfterAddToCart ==========");
    }

    @Test(dataProvider = "loginValidData",
            dataProviderClass = TestDataProvider.class)
    public void loginBeforeCheckOut(String email,String password,String name)
    {
        Allure.step("========== START: loginBeforeCheckOut ==========");

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

        Allure.step("Navigate to Products page");
        homepage.clickProducts();

        Allure.step("Handle google ad");
        productspage.getProductslink();

        Allure.step("Verify All Products Header");
        productspage.verifyAllProductsHeader();

        Allure.step("Fetch first two products names");
        String firstProductName = productspage.getProductName(0);
        String secondProductName = productspage.getProductName(1);

        Allure.step("Add first product to cart");
        productspage.addProductToCart(0);
        Assert.assertTrue(productspage.verifyProductAddedToCartSuccessMsg(),
                "Product not added to cart"
        );

        Allure.step("Click continue shopping button");
        productspage.clickContinueShoppingBtn();

        Allure.step("Add second product to cart");
        productspage.addProductToCart(1);
        Assert.assertTrue(productspage.verifyProductAddedToCartSuccessMsg(),
                "Product not added to cart"
        );
        Allure.step("Click view cart button");
        productspage.clickViewCartBtn();

        Allure.step("Verify selected products added in the cart");
        List<String> productsInCart=cartpage.getProductNamesInCart();
        Assert.assertTrue(productsInCart.contains(firstProductName),
                "First product not present in the cart"
        );
        Assert.assertTrue(productsInCart.contains(secondProductName),
                "Second product not present in the cart"
        );

        Allure.step("Click Proceed to Check out button");
        cartpage.clickProceedToCheckOutBtn();

        Allure.step("Verify Header Details");
        Assert.assertTrue(checkoutpage.IsaddressdetailsheaderPresent(),
                "Address detail header not present"
        );

        Allure.step("Fill checkout form");
        checkoutpage.fillCheckOutForm();

        Allure.step("Verify Payments Header");
        Assert.assertTrue(paymentpage.isPaymentHeaderPresent(),
                "Payment header not visible"
        );
        Allure.step("Fill Card Details");
        paymentpage.fillCardDetails();

        Allure.step("Verify Order Placed Success Message");
        Assert.assertTrue(paymentpage.isOrderSuccess(),
                "Order not placed"
        );
        Allure.step("========== START: loginBeforeCheckOut ==========");
    }

    @Test
    public void removeProductsFromCart()
    {
        Allure.step("========== START: removeProductsFromCart ==========");
        Allure.step("Navigate to Products page");
        homepage.clickProducts();

        Allure.step("Handle google ad");
        productspage.getProductslink();

        Allure.step("Verify All Products Header");
        productspage.verifyAllProductsHeader();

        Allure.step("Fetch first two products names");
        String firstProductName = productspage.getProductName(0);
        String secondProductName = productspage.getProductName(1);

        Allure.step("Add first product to cart");
        productspage.addProductToCart(0);
        Assert.assertTrue(productspage.verifyProductAddedToCartSuccessMsg(),
                "Product not added to cart"
        );
        Allure.step("Click continue shopping button");
        productspage.clickContinueShoppingBtn();
        Allure.step("Add first product to cart");
        productspage.addProductToCart(1);
        Assert.assertTrue(productspage.verifyProductAddedToCartSuccessMsg(),
                "Product not added to cart"
        );
        Allure.step("Click view cart button");
        productspage.clickViewCartBtn();

        Allure.step("Verify selected products added in the cart");
        List<String> productsInCart=cartpage.getProductNamesInCart();
        Assert.assertTrue(productsInCart.contains(firstProductName),
                "First product not present in the cart"
        );
        Assert.assertTrue(productsInCart.contains(secondProductName),
                "Second product not present in the cart"
        );

        Allure.step("Delete Product");
        cartpage.deleteProductFromCart(firstProductName);

        Allure.step("Verify deleted product not present in cart");
        productsInCart=cartpage.getProductNamesInCart();
        Assert.assertFalse(productsInCart.contains(firstProductName),
                "First product present in the cart"
        );
        Allure.step("========== END: removeProductsFromCart ==========");
    }
}
