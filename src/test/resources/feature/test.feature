Feature: End to end test for ToolsQA's Book Store API
  Description: The purpose of these tests are to cover End to End happy flows for customer

  Book Store Swagger URL: http://bookstore.toolsqa.com/swagger/index.html

  Background: I set base url for the api test
    Given I set "baseURI" for api tests

  Scenario: Authorized user is able to Add and Remove a book
    Given I add new pet into the store with post request to url "/pet"
    When I check if added pet exists in "/pet" url with get request
    And I update pet information with put request to url "/pet"
    Then I delete the pet information with url "/pet"
