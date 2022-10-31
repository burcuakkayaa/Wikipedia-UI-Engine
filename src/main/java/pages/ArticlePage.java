package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ArticlePage extends BasePage {

    @FindBy(xpath = "//*[text() = 'Article']")
    WebElement articleLabel;

    public ArticlePage(WebDriver driver) {
        super(driver);
    }

    public void verifyArticlePage() {
        waitForJQueryLoad();
        waitUntilElementIsDisplayed(articleLabel);
        waitUntilVisible(heading);
        String expectedHeading = heading.getText().replaceAll("\\s+", "_");
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedHeading));
    }
}
