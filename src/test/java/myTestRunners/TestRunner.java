package myTestRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/functionalTest",
        glue= {"myStepDefinitions" , "myHooks"},
        tags = "@chrome or @firefox",
        plugin = {
                "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm",
                "timeline:test-output-thread/",
                "rerun:src/test/resources/failedrerun.txt",
                "pretty", "json:target/cucumber/report.json"},
        monochrome = true,
        publish = true)
public class TestRunner extends AbstractTestNGCucumberTests {
}
