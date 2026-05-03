package pages.Amazon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.CommonUtils;

import java.util.List;

public class HomePage {

    private CommonUtils utils;
    private static final Logger logger =
            LogManager.getLogger(HomePage.class);

    private By searchInput= By.id("twotabsearchtextbox");
    private By searchBtn=By.xpath("//input[@id='nav-search-submit-button']");

    private By searchResults=By.xpath("//div[@data-component-type='s-search-result']");

    private static final String PRODUCT_STRING="//h2[contains(@aria-label,'%s')]";

    public HomePage(WebDriver driver)
    {
        this.utils=new CommonUtils(driver);
    }

    public void searchproduct(String searchString)
    {
        utils.type(searchInput,searchString);
        utils.click(searchBtn);
    }

    public Boolean hasSearchResults()
    {
      List<WebElement> searchResult=utils.getAllElements(searchResults);
      if(searchResult.size()>0)
      {
          return true;
      }
      return false;
    }

    private By getProductLocator(String productName)
    {
        return By.xpath((String.format(PRODUCT_STRING,productName)));
    }

    public void selectProduct(String productName)
    {
      utils.click(getProductLocator(productName));
    }
}
