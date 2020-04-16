package example;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DVDPageTest {
    WebDriver driver;

    @Before
    public void Before() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void f1() throws InterruptedException {
        DVDPage page = new DVDPage(driver);
        System.out.println("==========");
        System.out.println("Now movie card number: " + page.movieCards.size());
        Assert.assertEquals(32, page.movieCards.size());
        int count = 1;
        while (page.isDisplayed(page.showMoreBtn)) {
            page.clickShowMore();
            ++count;
            TimeUnit.SECONDS.sleep(1);
            System.out.println("==========");
            System.out.println("Click [show more] " + count + " times:  movie card number:" + page.movieCards.size());
            if (count == 2) {
                Assert.assertEquals(64, page.movieCards.size());
            } else {
                Assert.assertEquals(65, page.movieCards.size());
            }
        }
    }

    @Test
    public void f2() throws InterruptedException {
        DVDPage page = new DVDPage(driver);
        System.out.println("==== Movic count: " + page.movieCards.size() + " ====");
        page.movieCards.forEach(movieCard -> {
            System.out.println("Movie tile: " + movieCard.title.getText());
            System.out.println("Movie score: " + movieCard.score.getText());
            System.out.println("Movie poster url: " + movieCard.poster.getAttribute("src"));
        });
        int count = 1;
        while (page.isDisplayed(page.showMoreBtn)) {
            page.clickShowMore();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("==== Movic count: " + page.movieCards.size() + " ====");
            System.out.println("Movic count: " + page.movieCards.size());
            page.movieCards.forEach(movieCard -> {
                System.out.println("********");
                System.out.println("Movie tile: " + movieCard.title.getText());
                System.out.println("Movie score: " + (page.isDisplayed(movieCard.score) ? movieCard.score.getText() : "N/A"));
                System.out.println("Movie poster url: " + movieCard.poster.getAttribute("src"));
            });
            ++count;
        }
    }

    @After
    public void after() {
        System.out.println("<====== Test Ended");
        driver.quit();
    }
}
