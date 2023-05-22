Feature: End to end test for Trello API
  Description: The purpose of these tests are to cover End to End test to add, edit and delete board and cards in Trello


  @apiTest
  Scenario: Create, edit and delete board and cards
    Given I add new board with post request to url "/boards"
    And I get the list with url "/boards"
    And I create new cards into the created board with url "/cards"
    And I update one of the cards with put request to url "/cards"
    And I delete one of the cards with url "/cards"
    Then I add comment to one of the cards with url "/cards"

  @webTest
  Scenario: Login to trello successfully
    Given I navigate to trello login page
    When I enter username
    And I click on continue button for password page
    And I verify if navigation to password page is successful
    And I enter password
    And I click on login button
    Then I should verify successful login
    When I navigate to Test Trello Board
    And I verify if navigation to board is successful
    And I verify that there are 2 cards on the board
    And I verify that there is a card with a comment
    And I add a new comment to that card
    Then I set the card as DONE
