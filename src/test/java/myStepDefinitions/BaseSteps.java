package myStepDefinitions;

import myHooks.BaseHook;
import pages.ArticlePage;
import pages.BasePage;
import pages.SearchPage;

public class BaseSteps {

    BaseHook baseHook = new BaseHook();
    BasePage basePage = new BasePage(baseHook.driver);
    SearchPage searchPage = new SearchPage(baseHook.driver);
    ArticlePage articlePage = new ArticlePage(baseHook.driver);
}
