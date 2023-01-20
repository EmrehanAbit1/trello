package apiUtils;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utility.Config;

import java.io.IOException;
import java.util.List;

public class ResponseApi extends RequestApi {

    private static int petId;

    public void addNewPetToStore(String url) throws IOException {
        Response response = apiRequestPost(url, Config.getInstance().getPetAdditionJsonPath());
        String jsonString = response.asString();
        petId = JsonPath.from(jsonString).get("id");
    }

    public void checkDogType(String type, String url) {
        Response response = apiRequestGet(url + "/" + String.valueOf(petId));
        String jsonString = response.asString();
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

    public void updatePetInformation(String url) throws IOException {
        String updatePetJsonPath = Config.getInstance().getPetUpdateJsonPath();
        Response response = apiRequestPut(url, updatePetJsonPath);
        String jsonString = response.asString();
        Assert.assertTrue(JsonPath.from(jsonString).get("name").equals("lilly"));
    }

    public void deleteCreatedPet(String url) {
        Response response = apiRequestDelete(url + "/" + String.valueOf(petId));
        String jsonString = response.asString();
        Assert.assertTrue(JsonPath.from(jsonString).get("message").equals(String.valueOf(petId)));
    }
}
