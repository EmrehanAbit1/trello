package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/feature",
        glue = {"stepDefinitions"},
        monochrome = false,
        strict = true,
        tags = "",
        plugin = {"pretty",
                "html:target/TestResults/TestReport",
                "json:target/TestResults/TestReport.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)

public class TestRunner {
}
