package example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class GoogleTest {
    String url = "https://www.google.com";

    @BeforeMethod
    public void beforeMethod() {
        Configuration.browser = SelenideWebDriverProvider.class.getName();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @Test(groups = "debug")
    public void f1() {
        System.out.println(Configuration.timeout);
        open(url);
        System.out.println(getWebDriver().getTitle());
        $(byName("q")).shouldBe(Condition.enabled);
    }
}
