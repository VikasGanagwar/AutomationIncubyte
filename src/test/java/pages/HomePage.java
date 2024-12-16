package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    private By createAccountLink = By.linkText("Create an Account");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCreateAccount() {
        driver.findElement(createAccountLink).click();
    }
}
