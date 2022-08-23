package stepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import utility.Config;
import utility.Utils;

import java.util.logging.Logger;

public class Hooks extends Utils {

    public static Config config;
    private static String tags = "";

    @Before()
    public void setup(Scenario scenario) {
        tags = scenario.getSourceTagNames().toString();
        if (tags.contains("api")) {
            RestAssured.baseURI = config.getInstance().getBaseUri();
        } else {
            launchBrowser();
            navigateTo(config.getInstance().getUrl());
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (tags.contains("api")) {
            Logger logger = Logger.getLogger(Hooks.class.getName());
            logger.info("Finishing api tests");
        } else {
            endTest(scenario);
        }
    }
}
