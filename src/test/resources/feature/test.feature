Feature: End to end test for ToolsQA's Book Store API
  Description: The purpose of these tests are to cover End to End happy flows for customer

  Book Store Swagger URL: http://bookstore.toolsqa.com/swagger/index.html

  @apiTest
  Scenario: Authorized user is able to Add and Remove a book
    Given I add new pet into the store with post request to url "/pet"
    And I perform check if one of the type of dog is "pitbull" with url "/pet"
    And I update pet information with put request to url "/pet"
    Then I delete the pet information with url "/pet"

  @webTest
  Scenario: Proceed to checkout without making any payments
    Given I navigate to amazon login page
    And I verify if navigation to username page is successful
    When I enter username
    And I click on continue button for password page
    And I verify if navigation to password page is successful
    And I enter password
    And I click on login button
    Then I should verify successful login