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

    private static Logger logger = Logger.getLogger(String.valueOf(Hooks.class.getClass()));

    @Before()
    public void setup(Scenario scenario) {
        if (tags.contains("api")) {
            logger.info("Starting api tests");
        } else {
            launchBrowser();
            navigateTo(config.getInstance().getUrl());
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
