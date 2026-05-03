package pages.Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.CommonUtils;

public class CartPage {

    private CommonUtils utils;

    public CartPage(WebDriver driver)
    {
        this.utils=new CommonUtils(driver);
    }

    private By productNameLocator= By.xpath("//span[contains(@class,'sc-product-title')]");

    private By totalPriceCart=By.xpath("//span[@id='sc-subtotal-amount-activecart']/span");
    private By productQuantity=By.xpath("//fieldset[@name='sc-quantity']//div[@class='a-declarative']//span[@data-a-selector='inner-value']");

    public Boolean verifyProductName(String productName)
    {
        System.out.println("Product name in cart"+utils.getText(productNameLocator));
        return utils.getText(productNameLocator).contains(productName);
    }

    public Boolean verifyQuantity(int quantity)
    {
        String str = String.valueOf(quantity);
        return utils.getText(productQuantity).equalsIgnoreCase(str);
    }

    public Boolean verifyPrice(int totalPrice)
    {
        String price = utils.getText(totalPriceCart);

        price = price.replace("₹", "")
                .replace(",", "")
                .split("\\.")[0];

        int priceInt = Integer.parseInt(price);

        return priceInt == totalPrice;
    }
}
