package pageObjects;

import org.openqa.selenium.By;
import utility.Config;
import utility.Utils;

public class LoginPageObject extends Utils {
    private By departmentsTab = By.xpath("//*[text()='Departments']");
    private By signInLink = By.xpath("//*[@data-nav-ref='nav_ya_signin']");
    private By signInPage = By.name("signIn");
    private By usernameField = By.id("ap_email");
    private By signInContinueButton = By.xpath("//*[@type='submit']");
    private By passwordField = By.id("ap_password");
    private By signInButton = By.id("signInSubmit");
    private By messageWarning = By.xpath("//*[text()='Important Message!']");
    private By nameAppearanceOnLogin = By.xpath("//span[contains(text(),'TestUser')]");
    private By userNamePage = By.xpath("//*[contains(text(),'E-posta adresi veya telefon numarası')]");
    private By passwordPage = By.xpath("//label[contains(text(),'Şifre')]");
    private By acceptCookieButton = By.id("sp-cc-accept");

    /**
     * Navigation to login page of Amazon
     *
     * @throws Exception
     */
    public void navigateToLoginPage() throws Exception {
        refreshPageIfElementExists(departmentsTab);
        acceptCookies(acceptCookieButton);
        clickLocator(signInLink);
        assertIfElementExists(signInPage);
    }

    /**
     * Checking if navigation to username entrance page is successful
     *
     * @throws Exception
     */
    public void verifyUserNamePage() throws Exception {
        assertIfElementExists(userNamePage);
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
        assertIfElementExists(passwordPage);
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
     * Verifies if login to Amazon page is successful
     *
     * @throws Exception
     */
    public void checkIfAmazonLoginSuccessful() throws Exception {
        refreshPageIfElementExists(messageWarning);
        assertIfElementExists(nameAppearanceOnLogin);
    }
}
