package utility;

import cucumber.api.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepDefinitions.Hooks;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Utils {

    protected static WebDriver driver;

    private static final Logger logger = Logger.getLogger(String.valueOf(Hooks.class.getClass()));

    /**
     * Checks if test is in whether GitHub Actions or in local machine and starts browser depending on that condition
     *
     * @return driver paramter to get browser
     */
    public WebDriver launchBrowser() {
        try {
            if (null == driver) {
                String osName = System.getProperty("os.name");
                if (osName.equals("Linux")) {
                    System.setProperty("webdriver.chrome.driver", Config.CHROME_DRIVER_PATH_UBUNTU);
                    headlessBrowser();
                } else if (Config.getInstance().getBrowser().equals("headless")) {
                    headlessBrowser();
                } else {
                    //System.setProperty("webdriver.chrome.driver", Context.CHROME_DRIVER_PATH);
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                }
            }
        } catch (Exception e) {
            logger.info("Unable to load browser: " + e.getMessage());
        } finally {
            driver.manage().window().maximize();
            driver.switchTo().window(driver.getWindowHandle());
            driver.manage().getCookies();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        }
        return driver;
    }

    /**
     * After tests are done, takes screenshot if failed, closes browser and ends test.
     *
     * @param scenario library to get screenshot and reach to feature tags
     */
    public static WebDriver endTest(Scenario scenario) {
        try {
            if (driver != null && scenario.isFailed()) {
                String filePath = System.getProperty("user.dir") + Config.SCREENSHOTS_ON_FAILURE_PATH;
                File srcFile = ((TakesScreenshot) driver).getScreenshotAs((OutputType.FILE));
                // "Try" below will save the screenshot in d drive with test method name
                try {
                    FileUtils.copyFile(srcFile, new File(filePath + "result" + ".png"));
                    logger.info("*** Screenshot is in " + filePath + " ***");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Deleting cookies and closing browser before ending test
                driver.manage().deleteAllCookies();
                driver.quit();
                driver = null;
            }
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        } catch (Exception e) {
            logger.info("Methods failed: tearDownAndScreenshotOnFailure, Exception is: " + e.getMessage());
        }
        return driver;
    }

    /**
     * Navigating to given page
     *
     * @param url given url to navigate
     */
    public void navigateTo(String url) {
        logger.info("Opening page " + url);
        driver.get(url);
    }

    /**
     * Implicit wait when necessary
     *
     * @param time duration to wait implicitly
     */
    public static void sleep(long time) {
        logger.info("Waiting " + time + " seconds");
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Explicit wait until the existence of the element
     *
     * @param driver  Webdriver parameter
     * @param locator Parameter to reach to element
     * @throws Exception
     */
    public static void waitForElementExists(WebDriver driver, By locator) throws Exception {
        logger.info("Waiting for " + locator + " to exist in page");
        long DEFAULT_TIMEOUT = 20000L;
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT / 1000L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Clicks on buttons, fields
     *
     * @param locator Parameter to reach to element
     * @throws InterruptedException
     */
    public void clickLocator(By locator) throws InterruptedException {
        boolean clicked = false;
        int attempts = 0;
        while (attempts < 10 && !clicked) {
            try {
                new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(locator)).click();
                logger.info("Successfully clicked on the element using by locator: " + "<" + locator.toString() + ">");
                clicked = true;
            } catch (Exception e) {
                logger.info("Unable to click on element, Exception: " + e.getMessage());
                Assert.fail("Unable to click on element: " + "<" + locator.toString() + ">");
            }
            attempts++;
        }
    }

    /**
     * Sends keys to fileds or pushes keyboard buttons
     *
     * @param locator Parameter to reach to element
     * @param text    Text to write in the field
     * @throws Exception
     */
    public void sendKeysToLocator(By locator, String text) throws Exception {
        long DEFAULT_TIMEOUT = 20000L;
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT / 1000L);
        try {
            // Condition below is for pressing keyboard keys.
            if (text.contains("Press-")) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(Keys.valueOf(text.split("-")[1]));
            } else {
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).clear();
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
            }
            logger.info("Successfully sent the text: " + text);
        } catch (Exception e) {
            logger.info("Unable to send keys: " + text);
            Assert.fail("Unable to send keys to locator, Exception: " + e.getMessage());
        }
    }

    /**
     * This is mainly for asserting the existence of navigated page
     *
     * @param locator Parameter to reach to element
     * @throws Exception
     */
    public void assertIfElementExists(By locator) throws Exception {
        waitForElementExists(driver, locator);
        Assert.assertEquals(true, driver.findElement(locator).isDisplayed());
        logger.info("Successfully approved the element existence " + locator);
    }

    /**
     * gets the number of elements existed in page depending on DOM
     *
     * @param locator Parameter to reach to element
     * @return boolean depending on the element existence
     * @throws Exception
     */
    public boolean getValueOfElement(By locator) throws Exception {
        waitForElementExists(driver, locator);
        boolean result = driver.findElements(locator).size() != 0;
        return result;

    }

    /**
     * Getting information and selecting elements in dropdowns
     *
     * @param locator Parameter to reach to element
     * @param text    Text in dropdown to select
     */
    public void handleDropDown(By locator, String text) {
        try {
            //Using JavascriptExecuter in order to make element visible
            JavascriptExecutor j = (JavascriptExecutor) driver;
            j.executeScript("document.getElementById('address-ui-widgets-countryCode-dropdown-nativeId').style.display='block';");
            Select drpDown = new Select(driver.findElement(locator));
            drpDown.selectByVisibleText(text);
            //Had to sleep till the dropdown is closed and click to next element
            sleep(2);
            logger.info("Successfully selected the " + text + " from dropdown.");
        } catch (Exception e) {
            logger.info("Unable to select " + text);
            Assert.fail("Unable to select value from dropdown, Exception: " + e.getMessage());
        }
    }

    /**
     * Starting headless browser mainly for GitHub Actions but also applicable for local execution.
     */
    public void headlessBrowser() {
        ChromeOptions chOptions = new ChromeOptions();
        chOptions.addArguments("--headless");
        chOptions.addArguments("no-sandbox");
        chOptions.addArguments("ignore-certificate-errors");
        driver = new ChromeDriver(chOptions);
    }

    /**
     * Created this method because sometimes it opens the wrong website. I needed to refresh when it does.
     *
     * @param locator Parameter to reach to element
     */
    public void refreshPageIfElementExists(By locator) {
        //Waiting for 1 sec to load page appropriately
        sleep(1);
        int count = 0;
        while (count < 20) {
            if (driver.findElements(locator).size() == 0) {
                break;
            } else {
                driver.navigate().refresh();
                logger.info("refreshed page successfully");
                count++;
            }
        }
    }

    /**
     * Accepting cookies in the page
     *
     * @param locator Parameter to reach to element
     * @throws InterruptedException
     */
    public void acceptCookies(By locator) throws Exception {
        if (getValueOfElement(locator)) {
            clickLocator(locator);
        }
    }
}
