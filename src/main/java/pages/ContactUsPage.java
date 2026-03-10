package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AlertUtils;
import utils.CommonUtils;

import java.time.Duration;

public class ContactUsPage {

    private static final Logger logger =
            LogManager.getLogger(ContactUsPage.class);

    private CommonUtils utils;
    private AlertUtils alertUtils;
    private By getInTouchHeader=By.xpath("//div[@class='contact-form']/h2[text()='Get In Touch']");
    private By nameInput=By.xpath("//input[@placeholder='Name']");
    private By emailInput=By.xpath("//input[@placeholder='Email']");
    private By subjectInput=By.xpath("//input[@placeholder='Subject']");
    private By messageInput=By.id("message");
    private By clickBtn=By.xpath("//input[@name='submit']");
    private By successMsg=By.xpath("//div[@class='contact-form']//div[text()='Success! Your details have been submitted successfully.']");
    private By successBtn=By.xpath("//div[@class='contact-form']//div//a");

    public ContactUsPage(WebDriver driver)
    {
        this.utils=new CommonUtils(driver);
        this.alertUtils=new AlertUtils(driver);
    }

    public Boolean isgetInTouchHeaderPresent()
    {
        logger.info("Verifying Get in touch header");
        logger.debug("Get in Touch header text: "+utils.getText(getInTouchHeader));
        return utils.getText(getInTouchHeader).contains("GET IN TOUCH");
    }

    public void fillContactUsForm(String name,String email,String subject,String message)
    {
        logger.info("Submitting contact us form");
        logger.debug("Input details: Name: {} "+name+" Email: {} "+email+" Subject: {} "+subject+" Message: {} "+message);
        utils.type(nameInput,name);
        utils.type(emailInput,email);
        utils.type(subjectInput,subject);
        utils.type(messageInput,message);
        logger.info("Submitting contact us form");
        utils.click(clickBtn);
    }

    public void acceptAlertMsg()
    {
        logger.info("Accepting alert box");
        alertUtils.acceptAlert();
    }

    public Boolean verifySuccessMsg()
    {
        logger.info("Verifying Success Message");
        logger.debug("Success Message text: "+utils.getText(successMsg));
        return utils.getText(successMsg).contains("Success");
    }

    public void  clickSuccessBtn()
    {
        logger.info("Clicking success button");
        utils.click(successBtn);

    }
}
