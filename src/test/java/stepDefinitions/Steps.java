package stepDefinitions;


import apiUtils.ResponseApi;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.io.IOException;

public class Steps extends ResponseApi {
    @Given("I add new pet into the store with post request to url {string}")
    public void i_add_new_pet_into_the_store_with_post_request_to_url(String url) throws IOException {
        addNewPetToStore(url);
    }

    @When("I check if added pet exists in {string} url with get request")
    public void iCheckIfAddedPetExistsInUrlWithGetRequest(String arg0) {
        checkIfPetAddedSuccessfully(arg0);
    }

    @And("I update pet information with put request to url {string}")
    public void iUpdatePetInformationWithPutRequestToUrl(String url) throws IOException {
        updatePetInformation(url);
    }

    @Then("I delete the pet information with url {string}")
    public void i_delete_the_pet_information_with_url(String url) {
        deleteCreatedPet(url);
    }
}
