package apiUtils;


import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import utility.Config;

import java.io.IOException;
import java.util.List;

public class ResponseApi extends RequestApi {

    private static int petId;

    /**
     * API request to add a new pet to store.
     *
     * @param url api URI extension
     * @throws IOException
     */
    public void addNewPetToStore(String url) throws IOException {
        String jsonString = stringifyPostPutResponse(url, Config.getInstance().getPetAdditionJsonPath());
        petId = JsonPath.from(jsonString).get("id");
    }

    /**
     * API request to assert if dog type exists
     *
     * @param type type of dog to search
     * @param url  api URI extension
     */
    public void checkDogType(String type, String url) {
        String jsonString = stringifyGetResponse(url + "/" + String.valueOf(petId));
        JsonPath js = new JsonPath(jsonString);
        List<String> tags = js.getList("tags.name");
        for (String tag : tags) {
            if (tag.equals(type)) {
                System.out.println("You got it right!! Dog type is correct");
                break;
            } else {
                throw new java.lang.Error("Dog type does not match with any!!");
            }
        }
    }

    /**
     * API request to update the pet name
     *
     * @param url api URI extension
     * @throws IOException
     */
    public void updatePetInformation(String url) throws IOException {
        String updatePetJsonPath = Config.getInstance().getPetUpdateJsonPath();
        String jsonString = stringifyPostPutResponse(url, updatePetJsonPath);
        Assert.assertTrue(JsonPath.from(jsonString).get("name").equals("lilly"));
    }

    /**
     * API request to delete lately created pet
     *
     * @param url api URI extension
     */
    public void deleteCreatedPet(String url) {
        String jsonString = stringifyDeleteResponse(url + "/" + String.valueOf(petId));
        Assert.assertTrue(JsonPath.from(jsonString).get("message").equals(String.valueOf(petId)));
    }
}
