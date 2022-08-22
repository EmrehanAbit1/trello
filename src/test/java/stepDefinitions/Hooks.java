package stepDefinitions;

import context.Context;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

import java.io.IOException;

public class Hooks {

    @Before()
    public void setup() throws IOException {
        RestAssured.baseURI = Context.getRepositoryObject("baseURI");
    }
}
