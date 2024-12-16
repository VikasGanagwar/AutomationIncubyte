package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private By emailField = By.id("email");
    private By passwordField = By.id("pass");
    private By loginButton = By.id("send2");
    private By accountDashboard = By.xpath("//span[@data-ui-id=\"page-title-wrapper\"]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public boolean isAccountDashboardVisible() {
        return driver.findElement(accountDashboard).isDisplayed();
    }
}
