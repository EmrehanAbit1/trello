package pageObjects;

import org.openqa.selenium.By;
import utility.Config;
import utility.Utils;

public class LoginPageObject extends Utils {
    private By signInLink = By.xpath("//*[@data-uuid='MJFtCCgVhXrVl7v9HA7EH_login']");
    private By usernameField = By.id("user");
    private By signInContinueButton = By.id("login");
    private By passwordField = By.id("password");
    private By signInButton = By.id("login-submit");
    private By nameAppearanceOnLogin = By.xpath("//*[@data-testid='home-team-tab-name']");

    /**
     * Navigation to login page of Trello
     *
     * @throws Exception
     */
    public void navigateToLoginPage() throws Exception {
        clickLocator(signInLink);
        assertIfElementExists(usernameField);
    }

    /**
     * Fills the username field
     *
     * @throws Exception
     */
    public void enterUserName() throws Exception {
        sendKeysToLocator(usernameField, Config.getInstance().getUserName());
    }

    /**
     * Clicks on continue button to navigate to password page
     *
     * @throws Exception
     */
    public void clickOnContinueButton() throws Exception {
        clickLocator(signInContinueButton);
    }

    /**
     * Checking if navigation to password entrance page is successful
     *
     * @throws Exception
     */
    public void verifyPasswordPage() throws Exception {
        assertIfElementExists(passwordField);
    }

    /**
     * Fills the password field
     *
     * @throws Exception
     */
    public void enterPassword() throws Exception {
        sendKeysToLocator(passwordField, Config.getInstance().getPassword());
    }

    /**
     * Clicks on signin button to complete signin operation
     *
     * @throws Exception
     */
    public void clickSignInButton() throws Exception {
        clickLocator(signInButton);
    }

    /**
     * Verifies if login to Trello page is successful
     *
     * @throws Exception
     */
    public void checkIfTrelloLoginSuccessful() throws Exception {
        assertIfElementExists(nameAppearanceOnLogin);
    }
}
