package utils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class AuthUtils {


    public static void injectToken(WebDriver driver, String token) {
        ((JavascriptExecutor) driver).executeScript(
                "window.localStorage.setItem('token', arguments[0]);", token
        );
    }
}