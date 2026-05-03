package pages.QABrains;

import org.openqa.selenium.By;
import utils.ApiUtils;
import utils.AuthUtils;
import utils.CommonUtils;
import utils.WaitUtils;

public class LoginPage {
    private CommonUtils utils;
    private WaitUtils wait;

    private ApiUtils api;
    private By email = By.xpath("//input[@type='email']");
    private By password = By.xpath("//input[@type='password']");
    private By loginBtn = By.xpath("//button[contains(.,'Login')]");
    private By errorMsg = By.xpath("//*[contains(text(),'Invalid') or contains(text(),'error')]");
    private By googleBtn = By.xpath("//button[contains(.,'Google')]");

    // 🔹 UI Login
    public void login(String mail, String pass) {
        utils.type(email, mail);
        utils.type(password, pass);
        utils.click(loginBtn);
    }

    public void clickGoogleLogin() {
        utils.click(googleBtn);
    }

    public boolean isOnGooglePage() {
        return wait.waitForUrlContains("accounts.google.com");
    }

    /*public void loginWithToken(String email,String password)
    {
     String token = ApiUtils.getAuthToken(email,password);
     AuthUtils.injectToken(driver,token);
    }*/



}
