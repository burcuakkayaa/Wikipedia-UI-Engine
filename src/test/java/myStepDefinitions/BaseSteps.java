package myStepDefinitions;

import myHooks.BaseHooks;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.SearchPage;

public interface BaseSteps {

    WebDriver driver = BaseHooks.driver;
    BasePage basePage = new BasePage(driver);
    SearchPage searchPage = new SearchPage(driver);
}
