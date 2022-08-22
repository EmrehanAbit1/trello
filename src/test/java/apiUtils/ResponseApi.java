package apiUtils;


import context.Context;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.IOException;

public class ResponseApi extends RequestApi {

    public void addNewPetToStore(String url) throws IOException {
        Response response = apiRequestPost(url, Context.getRepositoryObject("addPetJsonPath"));
        String jsonString = response.asString();
        Context.setPetId(JsonPath.from(jsonString).get("id"));
    }

    public void checkIfPetAddedSuccessfully(String url) {
        Response response = apiRequestGet(url + "/" + String.valueOf(Context.getPetId()));
        String jsonString = response.asString();
        Assert.assertTrue(JsonPath.from(jsonString).get("id").equals(Context.getPetId()));
    }

    public void updatePetInformation(String url) throws IOException {
        String updatePetJsonPath = Context.getRepositoryObject("updatePetJson");
        Response response = apiRequestPut(url, updatePetJsonPath);
        String jsonString = response.asString();
        Assert.assertTrue(JsonPath.from(jsonString).get("name").equals("lilly"));
    }

    public void deleteCreatedPet(String url) {
        Response response = apiRequestDelete(url + "/" + String.valueOf(Context.getPetId()));
        String jsonString = response.asString();
        Assert.assertTrue(JsonPath.from(jsonString).get("message").equals(String.valueOf(Context.getPetId())));
    }
}
