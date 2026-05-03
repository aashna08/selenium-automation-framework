package tests.Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class MakeMyTrip {

    @Test
    public void makeMyTrip() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.makemytrip.com/");
        WebDriverWait wh = new WebDriverWait(driver, Duration.ofSeconds(10));
        wh.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-cy='closeModal']")));
        driver.findElement(By.xpath("//span[@data-cy='closeModal']")).click();
        WebElement source = driver.findElement(By.id("fromCity"));
        WebElement destination = driver.findElement(By.id("toCity"));
        source.click();
        source.sendKeys("IXC");
        wh.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@role='listbox']/li")));
        List<WebElement> source_places = driver.findElements(By.xpath("//ul[@role='listbox']/li//span[@class='revampedCityName']"));
        for (WebElement element : source_places) {
            if (element.getText().contains("Chandigarh")) {
                element.click();
                break;
            }
        }
        destination.click();
        destination.sendKeys("BLR");
        wh.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@role='listbox']/li")));
        List<WebElement> destination_places = driver.findElements(By.xpath("//ul[@role='listbox']/li//span[@class='revampedCityName']"));
        for (WebElement element : source_places) {
            if (element.getText().contains("Bangalore")) {
                element.click();
                break;
            }
        }
    }
}
