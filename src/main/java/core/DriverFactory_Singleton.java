package core;

import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory_Singleton {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory_Singleton() {
        // private constructor to prevent object creation
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(createDriver());
        }
        return driver.get();
    }

    public static WebDriver getDriver1()
    {
        return driver.get();
    }



    private static WebDriver createDriver() {

        String browser = System.getProperty("browser");
        if (browser == null) {
            browser = ConfigReader.get("browser").toLowerCase();
        }

        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));

        switch (browser) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-infobars");
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--disable-extensions");

                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }

                return new ChromeDriver(chromeOptions);

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("-headless");
                }
                return new FirefoxDriver(firefoxOptions);

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                return new EdgeDriver(edgeOptions);

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); // very important to avoid memory leaks
        }
    }
}