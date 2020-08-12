package example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class GoogleTest {
    String url = "https://www.google.com";

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        Configuration.browser = SelenideWebDriverProvider.class.getName();
        Configuration.timeout = 10000;     // 10 seconds
        Configuration.startMaximized = true;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @Test(groups = "debug")
    public void f1() {
        Assert.assertEquals(Configuration.timeout, 10_000);
        open(url);
        System.out.println(getWebDriver().getTitle());
        $(byName("q")).shouldNotBe(Condition.enabled);
    }
}
