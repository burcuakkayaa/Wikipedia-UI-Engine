package myHooks;

import factory.CreateChromeDriver;
import factory.CreateFirefoxDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class BaseHook {

    public static WebDriver driver = null;

    @Before("@chrome")
    public void launchBrowserAsChrome() {
        System.out.println("Chrome STARTED");
        CreateChromeDriver chrome = new CreateChromeDriver();
        driver = chrome.createChromeDriver();

    }

    @Before("@firefox")
    public void launchBrowserAsFirefox() {
        System.out.println("Firefox STARTED");
        CreateFirefoxDriver firefox = new CreateFirefoxDriver();
        driver = firefox.createFirefoxDriver();
    }

    @After()
    public void afterScenario(Scenario scenario) {

        if (scenario.isFailed()) {
            //take screenshot
            String screenshotName = scenario.getName().replaceAll(" ", "_");

            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            scenario.attach(sourcePath, "image/png", screenshotName);

        }
        driver.quit();
    }
}
