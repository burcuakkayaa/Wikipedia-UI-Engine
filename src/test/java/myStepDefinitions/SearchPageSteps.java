package myStepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class SearchPageSteps implements BaseSteps {

    private String search;

    @Given("user is on the Search Page")
    public void user_is_on_the_search_page() {
         searchPage.getSearchPage();
    }

    @When("user writes {string} to the search bar")
    public void user_writes_to_the_search_bar(String search) {
       this.search = search;
       searchPage.writeTheSearchValue(search);
    }


    @Then("user should see the most search results")
    public void user_should_see_the_most_search_results() {

    }

    @Then("the Advanced Search bar should be present")
    public void theAdvancedSearchBarShouldBePresent() {
        searchPage.verifyAdvancedSearchBarIsPresent();
    }

    @And("the Search In bar should be present")
    public void theSearchInBarShouldBePresent() {
        searchPage.verifySearchInBarIsPresent();
    }

    @And("the Search Result title should be present")
    public void theSearchResultTitleShouldBePresent() {
        searchPage.verifySearchResultTitleIsPresent();
    }

    @And("the Wikipedia Logo should be displayed")
    public void theWikipediaLogoShouldBeDisplayed() {
        searchPage.verifyLogoIsPresent();
    }

    @And("the Search button should be present")
    public void theSearchButtonShouldBePresent() {
        searchPage.verifySearchButtonIsPresent();
    }

    @And("user clicks the Search button")
    public void userClicksTheSearchButton() {
        searchPage.clickSearchButton();
    }

    @Then("user should see the search result info message")
    public void userShouldSeeTheSearchResultInfoMessage() {
        String actualText = searchPage.getActualSearchInfo().replaceAll("\\p{Punct}", "");
        String expectedText = String.format("There is a page named %s on Wikipedia", search);
        Assert.assertEquals(actualText, expectedText);
        Assert.assertTrue(actualText.contains(search));
    }

    @And("All results should be displayed")
    public void allResultsShouldBeDisplayed() {
        searchPage.verifySearchResultsAreDisplayed();
    }

    @And("The url should contain the search value")
    public void theUrlShouldContainTheSearchValue() {
       String actualUrl = driver.getCurrentUrl();
       Assert.assertTrue(actualUrl.contains("search=" + search));
    }
}
