package apiUtils;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utility.Config;

import java.io.IOException;

public class ResponseApi extends RequestApi {

    public static Config config;
    private static int petId;

    public void addNewPetToStore(String url) throws IOException {
        Response response = apiRequestPost(url, config.getInstance().getPetAdditionJsonPath());
        String jsonString = response.asString();
        petId = JsonPath.from(jsonString).get("id");
    }

    public void checkIfPetAddedSuccessfully(String url) {
        Response response = apiRequestGet(url + "/" + String.valueOf(petId));
        String jsonString = response.asString();
        Assert.assertTrue(JsonPath.from(jsonString).get("id").equals(petId));
    }

    public void updatePetInformation(String url) throws IOException {
        String updatePetJsonPath = config.getInstance().getPetUpdateJsonPath();
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
