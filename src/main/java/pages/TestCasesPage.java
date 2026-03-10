package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.CommonUtils;

public class TestCasesPage {

    private By testCasesHeader = By.xpath("//div//span[contains(text(),'list of test Cases')]");
    private CommonUtils utils;
    private static final Logger logger =
            LogManager.getLogger(TestCasesPage.class);

    public TestCasesPage(WebDriver driver)
    {
        this.utils=new CommonUtils(driver);
    }

    public Boolean verifyTestCasesHeader()
    {
        logger.debug("Test Cases Header text: "+utils.getText(testCasesHeader));
        return utils.getText(testCasesHeader).contains("list of test Cases");
    }

    public void getTestCaseslink()
    {
        utils.handleGoogleVignette("test_cases");
    }
}
