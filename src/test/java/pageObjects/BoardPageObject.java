package pageObjects;

import org.junit.Assert;
import utility.Utils;
import org.openqa.selenium.By;


public class BoardPageObject extends Utils {

    private By boardButton = By.xpath("//*[text()='Test Trello Board']");
    private By boardPage = By.xpath("//*[text()='Test Trello Card updated content']");
    private By numOfBoards = By.xpath("//*[@class='list-card-details js-card-details']");
    private By numOfComments = By.xpath("//*[@data-testid='badge-card-comments']");
    private By cardProperties = By.xpath("//*[text()='Test Trello Card updated content']");
    private By commentField = By.xpath("//*[@data-testid='card-back-new-comment-input-skeleton']");
    private By commentText = By.id("ak-editor-textarea");
    private By saveButton = By.xpath("//*[@data-testid='card-back-comment-save-button']");
    private By moveButton = By.xpath("//*[@title='Move']");
    private By defaultCommentMessage = By.xpath("//*[text()='Write a comment…']");
    private By listButton = By.xpath("//*[@data-testid='move-card-popover-select-list-destination']");
    private By submitMoveButton = By.xpath("//*[@data-testid='move-card-popover-move-button']");
    private By closeLabel = By.xpath("//*[@aria-label='Close dialog']");
    private By cardsInDone = By.xpath("//*[@class='list js-list-content']//*[@class='list-card js-member-droppable ui-droppable ui-sortable-handle']");

    /**
     * navigates to board page
     *
     * @throws InterruptedException
     */
    public void navigateToBoard() throws InterruptedException {
        clickLocator(boardButton);
    }

    /**
     * verifies if navigation to board page is successful
     *
     * @throws Exception
     */
    public void verifyBoardNavigation() throws Exception {
        assertIfElementExists(boardPage);
    }

    /**
     * @param boardNum
     * @throws Exception
     */
    public void verifyThereAreTwoCards(int boardNum) throws Exception {
        Assert.assertEquals(boardNum, getNumberOfElements(numOfBoards));
    }

    /**
     * verifies if comment exists
     *
     * @throws Exception
     */
    public void verifyThereIsAComment() throws Exception {
        Assert.assertEquals(1, getNumberOfElements(numOfComments));
    }

    /**
     * adds comment to a card
     *
     * @throws Exception
     */
    public void addCommentToCard() throws Exception {
        clickLocator(cardProperties);
        clickLocator(commentField);
        waitForElementExists(driver, defaultCommentMessage);
        sendKeyToOneOfTheElements(commentText, 1, "New comment for testing");
        clickLocator(saveButton);
    }

    /**
     * sets card to Done list
     *
     * @throws Exception
     */
    public void setCardAsDone() throws Exception {
        waitForElementExists(driver, moveButton);
        clickLocator(moveButton);
        selectListItem(listButton, "Done");
        clickLocator(submitMoveButton);
        clickLocator(closeLabel);
        Assert.assertTrue(getValueOfElement(cardsInDone));
    }
}
