package tests;

import base.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class test {
    @Test
    public void testDriverFactory() {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://www.amazon.in/");
        System.out.println("Page title: " + driver.getTitle());
        DriverFactory.quitDriver();
    }
}


