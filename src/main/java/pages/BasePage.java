package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;


public class BasePage extends PageFactory {

    protected WebDriver driver;
    private WebDriverWait jsWait;
    private JavascriptExecutor js;
    private final int timeOut = 60;

    @FindBy(id = "firstHeading")
    WebElement heading;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.jsWait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;

    }

    public boolean isElementPresent(WebElement element) {
        /*
         * Verify that element is present or not.
         */
        try {
            jsWait.until(ExpectedConditions.visibilityOf(element));
            jsWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            return true;
        } catch (Throwable e) {
            takeScreenshot();
            return false;
        }
    }

    public void findAndScrollElement(WebElement element, int scrollAmount) {
        /*
         * Scroll to the element.
         * param : scrollAmount :  How much the user want scrolling to the element.
         */

        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/" + scrollAmount + "));";
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(scrollElementIntoMiddle, element);
    }

    public void waitUntilClickableAndClick(WebElement element) {
        /*
         * Wait element is clickable and click.
         */

        try {
            Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeOut))
                    .pollingEvery(Duration.ofMillis(10)).ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            takeScreenshot();
        }
    }
    public void waitUntilVisible(WebElement element) {
        /*
         * Wait element is visible.
         */
        Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofMillis(10)).ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilVisibilityOfAllElements(List<WebElement> element) {
        /*
         * Wait Until All elements are visible.
         */
        Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofMillis(10)).ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public Boolean waitUntilElementIsDisplayed(WebElement element) {
        /*
         * Wait element is displayed.
         */

        Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofMillis(10)).ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        return wait.until(ExpectedConditions.elementToBeClickable(element)).isDisplayed();
    }

    public void waitForLoad() {

        try {
            ExpectedCondition<Boolean> pageLoadCondition = driver -> {
                assert driver != null;
                return ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").equals("complete");
            };

            jsWait.until(pageLoadCondition);
        } catch (Exception e) {
            takeScreenshot();
        }

    }

    public void waitForJQueryLoad() {
        try {
            ExpectedCondition<Boolean> jQueryLoad = driver -> ((Long) ((JavascriptExecutor) this.driver)
                    .executeScript("return jQuery.active") == 0);
            boolean jqueryReady = (Boolean) js.executeScript("return jQuery.active==0");
            if (!jqueryReady) {
                jsWait.until(jQueryLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }

    public void scrollInTheMiddleOfElement(WebElement element) {

        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", element);
    }

    private String getCurrentTime() {
        /*
         * The method returns current time
         */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        return simpleDateFormat.format(new Date());
    }

    private void takeScreenshot() {
        /*
         * Take a screenshot method and save to the screenshot folder
         * param: methodName : give the method name
         */
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {

            String path = "src/test/java/screenshot/" + getCurrentTime() + ".png";
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            System.out.println("ScreenShot fail." + e.getMessage());
        }
    }

}
