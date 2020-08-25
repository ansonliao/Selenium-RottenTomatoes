package example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.sskorol.core.DataSupplier;
import io.github.sskorol.data.YamlReader;
import io.qameta.allure.selenide.AllureSelenide;
import one.util.streamex.StreamEx;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.github.sskorol.data.TestDataReader.use;

public class GoogleTest {
    String url = "https://www.google.com";

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        Configuration.browser = SelenideWebDriverProvider.class.getName();
        Configuration.timeout = 10000;     // 10 seconds
        Configuration.startMaximized = true;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @Test(groups = "google")
    public void f1() {
        Assert.assertEquals(Configuration.timeout, 10_000);
        open(url);
        System.out.println(getWebDriver().getTitle());
        $(byName("q")).shouldNotBe(Condition.enabled);
    }

    @Test(groups = "debug", dataProvider = "getUsers")
    public void f2(User user) {
        System.out.println(user.getName());
        System.out.println(user.getAge());
    }

    @DataSupplier
    public StreamEx<User> getUsers() {
        return use(YamlReader.class).withTarget(User.class).read();
    }
}
