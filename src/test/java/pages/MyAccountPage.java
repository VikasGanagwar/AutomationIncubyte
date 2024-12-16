package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage {
    private WebDriver driver;

    private By profileNameArrowDownBtn = By.xpath("(//button[@data-action=\"customer-menu-toggle\"])[1]");
    private By signOutBtn = By.xpath("(//li[@class ='authorization-link']/a[contains(text(),'Sign Out')])[1]");

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void signOut() {
    	driver.findElement(profileNameArrowDownBtn).click();
    	driver.findElement(signOutBtn).click();
    }
}
