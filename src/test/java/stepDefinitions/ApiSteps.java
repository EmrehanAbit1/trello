package stepDefinitions;


import apiUtils.ResponseApi;
import cucumber.api.java.en.*;

public class ApiSteps extends ResponseApi {

    @Given("^I add new board with post request to url \"([^\"]*)\"$")
    public void i_add_new_board_with_post_request_to_url(String url) throws Throwable {
        addNewBoardToTrello(url);
    }

    @Given("^I get the list with url \"([^\"]*)\"$")
    public void i_get_the_list_with_url(String url) throws Throwable {
        getListsInTheBoard(url);
    }

    @Given("^I create new cards into the created board with url \"([^\"]*)\"$")
    public void i_create_new_cards_into_the_created_board_with_url(String url) throws Throwable {
        addNewCardsInTheLists(url);
    }

    @Given("^I update one of the cards with put request to url \"([^\"]*)\"$")
    public void i_update_one_of_the_cards_with_put_request_to_url(String url) throws Throwable {
        editCardContent(url);
    }

    @Given("^I delete one of the cards with url \"([^\"]*)\"$")
    public void i_delete_one_of_the_cards_with_url(String url) throws Throwable {
        deleteACard(url);
    }

    @Then("^I add comment to one of the cards with url \"([^\"]*)\"$")
    public void i_add_comment_to_one_of_the_cards_with_url(String url) throws Throwable {
        addCommentToACard(url);
    }
}
