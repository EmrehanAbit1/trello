package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/feature",
        glue = {"stepDefinitions"},
        tags = {"@webTest"},
        plugin = {"pretty",
                "html:target/TestResults/TestReport",
                "json:target/TestResults/TestReport.json",
                "com.cucumber.listener.ExtentCucumberFormatter:target/TestResults/TestReport/ExtentReport.html"}
)

public class TestRunner {
}
