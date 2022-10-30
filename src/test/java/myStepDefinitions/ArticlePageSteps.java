package myStepDefinitions;

import io.cucumber.java.en.Then;

public class ArticlePageSteps implements BaseSteps{

    @Then("user should see the Article Page")
    public void userShouldSeeTheArticlePage() {
        articlePage.verifyArticlePage();
    }
}
