package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BuyBooksIndiaPage {

    private WebDriver driver;

    public BuyBooksIndiaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchBook(String bookName) throws InterruptedException {
        driver.get("https://www.buybooksindia.com/");
        WebElement searchBox = driver.findElement(By.name("keyword"));
        searchBox.sendKeys(bookName + Keys.ENTER);
        Thread.sleep(5000);
    }



    public String getPrice() {
        try {
            WebElement priceElement = driver.findElement(By.cssSelector("span.price"));
            return priceElement.getText();
        } catch (Exception e) {
            return "N/A";
        }
    }

    public boolean isAvailable() {
        return !getPrice().equals("N/A");
    }

}
