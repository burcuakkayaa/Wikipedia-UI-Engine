package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utils.Constants;

import java.time.Duration;
import java.util.List;

import static utils.Constants.IMPLICIT_WAIT_DURATION;
import static utils.Constants.PAGE_LOAD_TIMEOUT;

public class SearchPage extends BasePage {

    @FindBy(id = "ooui-php-1")
    WebElement searchArea;

    @FindBy(css = ".mw-advancedSearch-searchPreview")
    WebElement advancedSearch;

    @FindBy(css = ".mw-advancedSearch-namespacesPreview")
    WebElement searchIn;
    @FindBy(className = "mw-wiki-logo")
    WebElement wikipediaLogo;

    @FindBy(className = "oo-ui-actionFieldLayout-button")
    WebElement searchButton;

    @FindBy(className = "mw-search-exists")
    WebElement searchInfo;

    @FindBy(className = "mw-search-nonefound")
    WebElement noneFoundSearchInfo;
    @FindBy(xpath = "//a[@data-serp-pos and @href]")
    List<WebElement> searchResults;

    @FindBy(css = ".mw-pager-navigation-bar")
    WebElement pageNavigationBar;

    @FindBy(className = "mw-nextlink")
    WebElement nextPageLink;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void getSearchPage() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(IMPLICIT_WAIT_DURATION)));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(Long.parseLong(PAGE_LOAD_TIMEOUT)));
        driver.get(Constants.BASE_URL);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        waitForLoad();
        waitForJQueryLoad();
        waitUntilUrlContains("search");
    }

    public void writeTheSearchValue(String search) {
        isElementPresent(searchArea);
        searchArea.sendKeys(search);
    }

    public void verifyAdvancedSearchBarIsPresent() {
        waitUntilElementIsDisplayed(advancedSearch);
    }

    public void verifySearchInBarIsPresent() {
        waitUntilElementIsDisplayed(searchIn);
    }

    public void verifySearchResultTitleIsPresent() {
        waitUntilElementIsDisplayed(heading);
    }

    public void verifyLogoIsPresent() {
        waitUntilElementIsDisplayed(wikipediaLogo);
    }

    public void verifySearchButtonIsPresent() {
        waitUntilElementIsDisplayed(searchButton);
    }

    public void clickSearchButton() {
        waitUntilClickableAndClick(searchButton);
    }

    public String getActualSearchInfo() {
        waitUntilElementIsDisplayed(searchInfo);
        String actualText = searchInfo.getText();

        return actualText;
    }

    public void verifySearchResultsAreDisplayed() {
        waitUntilVisibilityOfAllElements(searchResults);
    }

    public void clickNextPage() {
        findAndScrollElement(pageNavigationBar, 20);
        if (isElementPresent(nextPageLink))
            waitUntilClickableAndClick(nextPageLink);
        else
            Assert.fail("The next page button doesn't exist. Please change the search value");
    }

    public void clickSearchResult(int count) {
        waitForLoad();
        waitUntilElementIsDisplayed(searchResults.get(count));
        scrollInTheMiddleOfElement(searchResults.get(count));
        waitUntilClickableAndClick(searchResults.get(count));
    }

    public void verifyNoneOfResultIsDisplayed() {
        waitUntilElementIsDisplayed(noneFoundSearchInfo);
        String expectedText = "There were no results matching the query.";
        Assert.assertEquals(noneFoundSearchInfo.getText(), expectedText);
    }
}
