package stepDefinitions;

import io.cucumber.java.en.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.CreateAccountPage;
import pages.LoginPage;
import pages.MyAccountPage;
import utils.BaseTest;

public class SignupSteps {
    private WebDriver driver;
    private HomePage homePage;
    private CreateAccountPage createAccountPage;
    private MyAccountPage myAccountPage;
    private LoginPage loginPage;
    private String createdEmail;
    private final String createdPassword = "Test@1234";

    @Given("the user is on the Magento homepage")
    public void user_is_on_homepage() {
        BaseTest.setUp();
        driver = BaseTest.driver;
        driver.get("https://magento.softwaretestingboard.com/");
        homePage = new HomePage(driver);
    }

    @When("the user navigates to the signup page")
    public void user_navigates_to_signup_page() {
        homePage.clickCreateAccount();
        createAccountPage = new CreateAccountPage(driver);
    }

    @When("the user enters valid signup details")
    public void user_enters_valid_details() {
        createAccountPage.enterFirstName("Test");
        createAccountPage.enterLastName("User");
        createdEmail = "testuser" + System.currentTimeMillis() + "@example.com";
        createAccountPage.enterEmail(createdEmail);
        createAccountPage.enterPassword(createdPassword);
        createAccountPage.enterConfirmPassword(createdPassword);
    }

    @And("clicks on the {string} button")
    public void clicks_create_account(String buttonName) {
        createAccountPage.clickCreateAccount();
    }

    @Then("the user should be successfully signed up")
    public void user_should_be_successfully_signed_up() {
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("customer/account");
    }

    @Then("the user should see a welcome message")
    public void user_should_see_welcome_message() {
        String welcomeMessage = driver.findElement(By.cssSelector("div.messages")).getText();
        assert welcomeMessage.contains("Thank you for registering");
    }

    @When("the user logs out")
    public void user_logs_out() { 
    	myAccountPage = new MyAccountPage(driver);
        myAccountPage.signOut();
    }

    @When("the user logs in with the newly created account")
    public void user_logs_in_with_new_account() {
        driver.findElement(By.linkText("Sign In")).click();
        loginPage = new LoginPage(driver);
        loginPage.enterEmail(createdEmail);
        loginPage.enterPassword(createdPassword);
        loginPage.clickLoginButton();
    }

    @Then("the user should see their account dashboard")
    public void user_should_see_account_dashboard() {
        assert loginPage.isAccountDashboardVisible();
        BaseTest.tearDown();
    }
    
    @When("the user enters an already registered email")
    public void user_enters_already_registered_email() {
        createAccountPage.enterFirstName("Test");
        createAccountPage.enterLastName("User");
        createAccountPage.enterEmail("existinguser@example.com"); // Replace with an actual existing email
        createAccountPage.enterPassword("Test@1234");
        createAccountPage.enterConfirmPassword("Test@1234");
    }

    @Then("the user should see an error message about the email being already registered")
    public void user_sees_error_message_for_registered_email() {
        String errorMessage = driver.findElement(By.cssSelector("div.messages")).getText();
        assert errorMessage.contains("There is already an account with this email address.");
    }
    
    @When("the user enters mismatched passwords")
    public void user_enters_mismatched_passwords() {
        createAccountPage.enterFirstName("Test");
        createAccountPage.enterLastName("User");
        String uniqueEmail = "testuser" + System.currentTimeMillis() + "@example.com";
        createAccountPage.enterEmail(uniqueEmail);
        createAccountPage.enterPassword("Test@1234");
        createAccountPage.enterConfirmPassword("Mismatch@1234"); // Intentionally mismatched
    }

    @Then("the user should see an error message about password mismatch")
    public void user_sees_error_message_for_password_mismatch() {
        String errorMessage = driver.findElement(By.id("password-confirmation-error")).getText();
        assert errorMessage.contains("Please enter the same value again.");
    }


}
