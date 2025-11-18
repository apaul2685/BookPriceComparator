package tests;

import Models.Books;
import Pages.AmazonPage;
import Pages.BuyBooksIndiaPage;
import Pages.FlipkartPage;
import Utils.BookReportUtil;
import Utils.CsvReaderUtil;
import base.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class BookPriceComparatorTest {

    private WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = DriverFactory.getDriver();
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testBookComparisonReport() throws IOException, InterruptedException {
        List<Books> books = CsvReaderUtil.readBooks("resources/bookList.csv");

        // Scrape prices and availability from all platforms
        for (Books book : books) {
            new AmazonPage(driver).searchBook(book.getName());
            book.getPrices().put("Amazon", new AmazonPage(driver).getPrice());
            book.getAvailability().put("Amazon", new AmazonPage(driver).isAvailable());

            new FlipkartPage(driver).searchBook(book.getName());
            book.getPrices().put("Flipkart", new FlipkartPage(driver).getPrice());
            book.getAvailability().put("Flipkart", new FlipkartPage(driver).isAvailable());

            new BuyBooksIndiaPage(driver).searchBook(book.getName());
            book.getPrices().put("BuyBooksIndia", new BuyBooksIndiaPage(driver).getPrice());
            book.getAvailability().put("BuyBooksIndia", new BuyBooksIndiaPage(driver).isAvailable());
        }

        // Generate JSON report
        String jsonReport = BookReportUtil.generateCustomJsonReport(books);
        System.out.println(jsonReport);

        BookReportUtil.comparePlatformCosts(books);

    }





}

