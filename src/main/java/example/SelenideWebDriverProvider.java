package example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class SelenideWebDriverProvider implements WebDriverProvider {
    ChromeOptions chromeOptions = new ChromeOptions();

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull DesiredCapabilities desiredCapabilities) {
        Configuration.timeout = 10000;     // 10 seconds
        Configuration.startMaximized = true;
        setMobileEmulation();
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptions);
    }

    public void setMobileEmulation() {
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone X");
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
    }

}
