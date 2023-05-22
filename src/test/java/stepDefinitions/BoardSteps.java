package stepDefinitions;

import cucumber.api.java.en.*;
import pageObjects.BoardPageObject;

public class BoardSteps extends BoardPageObject {

    @When("^I navigate to Test Trello Board$")
    public void i_navigate_to_Test_Trello_Board() throws Throwable {
        navigateToBoard();
    }

    @When("^I verify if navigation to board is successful$")
    public void i_verify_if_navigation_to_board_is_successful() throws Throwable {
        verifyBoardNavigation();
    }

    @When("^I verify that there are (\\d+) cards on the board$")
    public void i_verify_that_there_are_cards_on_the_board(int boarNum) throws Throwable {
        verifyThereAreTwoCards(boarNum);
    }

    @When("^I verify that there is a card with a comment$")
    public void i_verify_that_there_is_a_card_with_a_comment() throws Throwable {
        verifyThereIsAComment();
    }

    @When("^I add a new comment to that card$")
    public void i_add_a_new_comment_to_that_card() throws Throwable {
        addCommentToCard();
    }

    @Then("^I set the card as DONE$")
    public void i_set_the_card_as_DONE() throws Throwable {
        setCardAsDone();
    }
}
