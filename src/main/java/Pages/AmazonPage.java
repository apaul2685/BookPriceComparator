package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AmazonPage {

    private WebDriver driver;

    public AmazonPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchBook(String bookName) {
        driver.get("https://www.amazon.in/");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
        name.sendKeys(bookName + Keys.ENTER);
    }

    public String getPrice() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement priceElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.a-price-whole"))
            );
            return priceElement.getText();
        } catch (Exception e) {
            return "N/A";
        }
    }

    public boolean isAvailable() {
        return !getPrice().equals("N/A");
    }

}

