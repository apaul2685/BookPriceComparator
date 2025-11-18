package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FlipkartPage {
    private WebDriver driver;

    public FlipkartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchBook(String bookName) {
        driver.get("https://www.flipkart.com/");
        try {
            WebElement closeBtn = driver.findElement(By.cssSelector("button._2KpZ6l._2doB4z"));
            closeBtn.click();
        } catch (Exception ignored) {}

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(bookName + Keys.ENTER);
    }

    public String getPrice() {
        try {
            WebElement priceElement = driver.findElement(By.xpath("(//div[contains(@class,'Nx9bqj')])[1]"));
            return priceElement.getText();
        } catch (Exception e) {
            return "N/A";
        }
    }

    public boolean isAvailable() {
        return !getPrice().equals("N/A");
    }

}
