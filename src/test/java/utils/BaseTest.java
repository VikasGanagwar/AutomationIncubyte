package utils;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    public static WebDriver driver;

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

	public static void setUp() {
		WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();	
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}
}
