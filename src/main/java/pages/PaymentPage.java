package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.CommonUtils;

public class PaymentPage {

    private CommonUtils utils;
    private By paymentHeader=By.xpath("//h2[text()='Payment']");
    private By nameInput=By.xpath("//input[@name='name_on_card']");
    private By cardNumberInput=By.xpath("//input[@name='card_number']");
    private By cvcInput=By.xpath("//input[@name='cvc']");
    private By expiryMonthInput=By.xpath("//input[@name='expiry_month']");
    private By expiryYearInput=By.xpath("//input[@name='expiry_year']");
    private By submitBtn=By.xpath("//button[@id='submit']");
    private By orderSuccess=By.xpath("//p[text()='Congratulations! Your order has been confirmed!']");
    public PaymentPage(WebDriver driver)
    {
        this.utils=new CommonUtils(driver);
    }
    public Boolean isPaymentHeaderPresent()
    {
        return utils.getText(paymentHeader).contains("Payment");
    }
    public void fillCardDetails()
    {
        utils.type(nameInput,"Test name");
        utils.type(cardNumberInput,"123456789");
        utils.type(cvcInput,"999");
        utils.type(expiryMonthInput,"12");
        utils.type(expiryYearInput,"2027");
        utils.click(submitBtn);
    }
    public Boolean isOrderSuccess()
    {
        return utils.getText(orderSuccess).contains("Congratulations! Your order has been confirmed");
    }
}
