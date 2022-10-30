package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Constants;

import java.util.List;

public class SearchPage extends BasePage {

    @FindBy(id = "ooui-php-1")
    WebElement searchArea;

    @FindBy(css = ".mw-advancedSearch-searchPreview")
    WebElement advancedSearch;

    @FindBy(css = ".mw-advancedSearch-namespacesPreview")
    WebElement searchIn;

    @FindBy(id = "firstHeading")
    WebElement searchResultTitle;

    @FindBy(className = "mw-wiki-logo")
    WebElement wikipediaLogo;

    @FindBy(className = "oo-ui-actionFieldLayout-button")
    WebElement searchButton;

    @FindBy(className = "mw-search-exists")
    WebElement searchInfo;

    @FindBy(css = ".mw-search-result")
    List<WebElement> searchResults;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void getSearchPage() {
        waitForLoad();
        driver.get(Constants.BASE_URL);
        waitForJQueryLoad();
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
        waitUntilElementIsDisplayed(searchResultTitle);
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
}
