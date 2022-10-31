package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static utils.Constants.CHROME_OPTIONS;

public class CreateChromeDriver {

    WebDriver driver;

    public WebDriver createChromeDriver() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(getOptions());
        return driver;
    }

    private ChromeOptions getOptions() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(CHROME_OPTIONS);
        return chromeOptions;
    }
}
