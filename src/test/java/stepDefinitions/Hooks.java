package stepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import utility.Config;
import utility.Utils;

import java.util.logging.Logger;

public class Hooks extends Utils {

    private static String tags = "";

    private static final Logger logger = Logger.getLogger(String.valueOf(Hooks.class.getClass()));

    @Before()
    public void setup(Scenario scenario) {
        tags = scenario.getSourceTagNames().toString();
        if (tags.contains("api")) {
            logger.info("Starting api tests");
            RestAssured.baseURI = Config.getInstance().getBaseUri();
        } else {
            launchBrowser();
            navigateTo(Config.getInstance().getUrl());
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (tags.contains("api")) {
            logger.info("Finishing api tests");
        } else {
            endTest(scenario);
        }
    }
}
