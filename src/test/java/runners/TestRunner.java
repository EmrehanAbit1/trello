package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/resources/feature",
        glue = {"stepDefinitions"},
        monochrome = false,
        strict = true,
        tags = {}
)

public class TestRunner {
}
