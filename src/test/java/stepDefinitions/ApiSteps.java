package stepDefinitions;


import apiUtils.ResponseApi;
import cucumber.api.java.en.*;

public class ApiSteps extends ResponseApi {

    @Given("^I add new pet into the store with post request to url \"([^\"]*)\"$")
    public void i_add_new_pet_into_the_store_with_post_request_to_url(String url) throws Throwable {
        addNewPetToStore(url);
    }

    @Given("^I perform check if one of the type of dog is \"([^\"]*)\" with url \"([^\"]*)\"$")
    public void i_perform_check_if_one_of_the_type_of_dog_is_with_url(String type, String url) throws Throwable {
        checkDogType(type, url);
    }

    @When("^I update pet information with put request to url \"([^\"]*)\"$")
    public void i_update_pet_information_with_put_request_to_url(String url) throws Throwable {
        updatePetInformation(url);
    }

    @Then("^I delete the pet information with url \"([^\"]*)\"$")
    public void i_delete_the_pet_information_with_url(String url) {
        deleteCreatedPet(url);
    }
}
