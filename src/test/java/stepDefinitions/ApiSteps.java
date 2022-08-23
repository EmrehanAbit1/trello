package stepDefinitions;


import apiUtils.ResponseApi;
import cucumber.api.java.en.*;

public class ApiSteps extends ResponseApi {

    @Given("^I add new pet into the store with post request to url \"([^\"]*)\"$")
    public void i_add_new_pet_into_the_store_with_post_request_to_url(String url) throws Throwable {
        addNewPetToStore(url);
    }

    @When("^I check if added pet exists in \"([^\"]*)\" url with get request$")
    public void i_check_if_added_pet_exists_in_url_with_get_request(String arg0) throws Throwable {
        checkIfPetAddedSuccessfully(arg0);
    }

    @When("^I update pet information with put request to url \"([^\"]*)\"$")
    public void i_update_pet_information_with_put_request_to_url(String url) throws Throwable {
        updatePetInformation(url);
    }

    @Then("^I delete the pet information with url \"([^\"]*)\"$")
    public void i_delete_the_pet_information_with_url(String url) throws Throwable {
        deleteCreatedPet(url);
    }

}
