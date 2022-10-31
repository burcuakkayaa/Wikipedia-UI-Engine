package myTestRunners;

import io.cucumber.testng.*;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.*;

@CucumberOptions(
        features = "src/test/resources/functionalTest",
        glue= {"myStepDefinitions" , "myHooks"},
        tags = "@chrome or @firefox",
        plugin = {"io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm",
                "timeline:test-output-thread/",
                "rerun:src/test/resources/failedrerun.txt",
                "pretty", "json:target/cucumber/report.json"},
        monochrome = true,
        publish = true)
public class TestRunner implements IRetryAnalyzer {

    private TestNGCucumberRunner testNGCucumberRunner;
    private int count = 0;
    private static int maxTry = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {  ;
            if (count < maxTry) {
                count++;
                iTestResult.setStatus(ITestResult.FAILURE);
                return true;
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        System.out.println("Before Scenario ****");
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios",
            dataProvider = "scenarios",retryAnalyzer = TestRunner.class)
    public void scenario(PickleWrapper pickleEvent, FeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runScenario(pickleEvent.getPickle());
    }

    @DataProvider
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        System.out.println("After Scenario ****");
        testNGCucumberRunner.finish();
    }
}
