package stepDefinitions;

import cucumber.api.java.en.*;
import pageObjects.LoginPageObject;

public class AmazonSteps extends LoginPageObject {

    @Given("^I navigate to amazon login page$")
    public void i_navigate_to_amazon_login_page() throws Throwable {
        navigateToLoginPage();
    }

    @Given("^I verify if navigation to username page is successful$")
    public void i_verify_if_navigation_to_username_page_is_successful() throws Throwable {
        verifyUserNamePage();
    }

    @When("^I enter username$")
    public void i_enter_username() throws Throwable {
        enterUserName();
    }

    @When("^I click on continue button for password page$")
    public void i_click_on_continue_button_for_password_page() throws Throwable {
        clickOnContinueButton();
    }

    @When("^I verify if navigation to password page is successful$")
    public void i_verify_if_navigation_to_password_page_is_successful() throws Throwable {
        verifyPasswordPage();
    }

    @When("^I enter password$")
    public void i_enter_password() throws Throwable {
        enterPassword();
    }

    @When("^I click on login button$")
    public void i_click_on_login_button() throws Throwable {
        clickSignInButton();
    }

    @Then("^I should verify successful login$")
    public void i_should_verify_successful_login() throws Throwable {
        checkIfAmazonLoginSuccessful();
    }
}
