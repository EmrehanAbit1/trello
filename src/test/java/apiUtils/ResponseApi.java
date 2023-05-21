package apiUtils;


import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import utility.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResponseApi extends RequestApi {

    private static String boardId;
    private static List<String> idLists;
    private static final List<String> cardLists = new ArrayList<String>();
    private final String encodedKey = decodeBase64(Config.getInstance().getEncodedKey());
    private final String encodedToken = decodeBase64(Config.getInstance().getEncodedToken());

    /**
     * Adds new board to trello
     *
     * @param url
     * @throws IOException
     */
    public void addNewBoardToTrello(String url) throws IOException {
        response = apiRequestPost(url, Config.getInstance().boardAddJsonPath(), encodedKey, encodedToken);
        String jsonString = response.asString();
        boardId = JsonPath.from(jsonString).get("id");
        assertIfStatusCodeTrue(response.getStatusCode());
    }

    /**
     * Gets the lists in board for adding new cards to them
     *
     * @param url
     */
    public void getListsInTheBoard(String url) {
        response = apiRequestGet(url + "/" + "" + boardId + "" + "/lists", encodedKey, encodedToken);
        String jsonString = response.asString();
        JsonPath js = new JsonPath(jsonString);
        idLists = js.getList("id");
        assertIfStatusCodeTrue(response.getStatusCode());
    }

    /**
     * Adds 3 new cards to lists
     *
     * @param url
     * @throws IOException
     */
    public void addNewCardsInTheLists(String url) throws IOException {
        int count = 0;
        while (count < 3) {
            response = apiRequestAddCardPost(url, Config.getInstance().cardAddJsonPath(), idLists.get(count), encodedKey, encodedToken);
            String jsonString = response.asString();
            String cards = JsonPath.from(jsonString).get("id");
            cardLists.add(cards);
            count++;
        }
        assertIfStatusCodeTrue(response.getStatusCode());
    }

    /**
     * Updates one of the cards
     *
     * @param url
     * @throws IOException
     */
    public void editCardContent(String url) throws IOException {
        response = apiRequestPut(url + "/" + "" + cardLists.get(0) + "", Config.getInstance().cardEditJsonPath(), encodedKey, encodedToken);
        assertIfStatusCodeTrue(response.getStatusCode());
    }

    /**
     * Deletes one of the cards
     *
     * @param url
     */
    public void deleteACard(String url) {
        response = apiRequestDelete(url + "/" + "" + cardLists.get(1) + "", encodedKey, encodedToken);
        assertIfStatusCodeTrue(response.getStatusCode());
    }

    /**
     * Adds comment to one of the cards
     *
     * @param url
     * @throws IOException
     */
    public void addCommentToACard(String url) throws IOException {
        response = apiRequestPost(url + "/" + "" + cardLists.get(2) + "" + "/actions/comments", Config.getInstance().commentCardJsonPath(), encodedKey, encodedToken);
        assertIfStatusCodeTrue(response.getStatusCode());
    }

    /**
     * Checks if the response status code is successful
     *
     * @param statusCode
     */
    public void assertIfStatusCodeTrue(int statusCode) {
        Assert.assertEquals(200, statusCode);
    }
}
