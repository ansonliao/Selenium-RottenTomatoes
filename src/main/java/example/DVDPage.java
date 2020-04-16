package example;

import java.util.List;

import example.annotations.PageName;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

@PageName("DVD Page")
public class DVDPage {

    @FindBy(css = "#show-more-btn > button")
    @Name("Show More Button")
    public Button showMoreBtn;

    List<MovieCard> movieCards;

    private WebDriver driver;
    private String url = "https://www.rottentomatoes.com/browse/top-dvd-streaming/";

    public DVDPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
        driver.get(url);
        waitForPageLoaded();
    }

    public DVDPage clickShowMore() {
        if (isDisplayed(showMoreBtn)) {
            showMoreBtn.click();
            // Log the testing behavious details to the testing report, here should be implemeted by AOP, only example here
            System.out.println("What I clicked: " + showMoreBtn.getName() + ", Page: " + getCurrentPageName());
        }
        return this;
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private String getCurrentPageName() {
        PageName pageName = this.getClass().getAnnotation(PageName.class);
        return pageName.value();
    }
}
