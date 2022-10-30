package myHooks;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class BaseHooks {

    protected static DriverFactory factory;
    public static WebDriver driver;

    public BaseHooks() {
        factory = new DriverFactory();
    }

    @Before("@chrome")
    public void launchBrowserAsChrome() {
        driver = factory.getDriverManager("chrome");

    }

    @Before("@firefox")
    public void launchBrowserAsFirefox() {
        driver = factory.getDriverManager("firefox");
    }

    @After(order = 1)
    public void afterScenario(Scenario scenario) {

        if (scenario.isFailed()) {
            //take screenshot
            String screenshotName = scenario.getName().replaceAll(" ", "_");

            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            scenario.attach(sourcePath, "image/png", screenshotName);

        }

    }


    @After(order = 0)
    public void AfterSteps() {
        driver.quit();
    }
}
