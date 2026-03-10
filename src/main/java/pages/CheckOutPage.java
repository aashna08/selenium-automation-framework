package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.CommonUtils;

public class CheckOutPage {

    private CommonUtils utils;
    private By addressdetailsheader=By.xpath("//h2[text()='Address Details']");
    private By commentInput=By.xpath("//textarea[@class='form-control']");
    private By placeOrderBtn=By.xpath("//a[text()='Place Order']");

    public CheckOutPage(WebDriver driver)
    {
        this.utils=new CommonUtils(driver);
    }

    public Boolean IsaddressdetailsheaderPresent()
    {
        return utils.getText(addressdetailsheader).contains(("Address Details"));
    }

    public void fillCheckOutForm()
    {
        utils.type(commentInput,"Test Input");
        utils.click(placeOrderBtn);
    }

}
