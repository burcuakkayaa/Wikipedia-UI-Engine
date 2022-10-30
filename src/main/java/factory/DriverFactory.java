package factory;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static utils.Constants.IMPLICIT_WAIT_DURATION;
import static utils.Constants.PAGE_LOAD_TIMEOUT;

public class DriverFactory {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public WebDriver getDriverManager(String browser) {
        /*
         * The method returns Webdriver
         */

        if (browser.equalsIgnoreCase("firefox")) {
            CreateFirefoxDriver firefox = new CreateFirefoxDriver();
            tlDriver = firefox.createFirefoxDriver();
        } else {
            CreateChromeDriver chrome = new CreateChromeDriver();
            tlDriver = chrome.createChromeDriver();
        }

        WebDriver driver = tlDriver.get();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(IMPLICIT_WAIT_DURATION)));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(Long.parseLong(PAGE_LOAD_TIMEOUT)));

        return driver;

    }

}
