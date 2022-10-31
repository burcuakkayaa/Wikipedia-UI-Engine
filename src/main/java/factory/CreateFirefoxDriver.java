package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static utils.Constants.FIREFOX_OPTIONS;

public class CreateFirefoxDriver {

    WebDriver driver;

    public WebDriver createFirefoxDriver() {

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(getFirefoxOptions());
        return driver;
    }

    private FirefoxOptions getFirefoxOptions() {

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments(FIREFOX_OPTIONS);
        return firefoxOptions;
    }
}
