package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    private static WebDriver driver;

    // Initialize driver
    public static WebDriver getDriver() {
            System.out.println("Setting up ChromeDriver...");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            System.out.println("Driver initialized.");

        return driver;
    }

    // Quit driver
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
