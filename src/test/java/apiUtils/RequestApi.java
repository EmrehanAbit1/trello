package apiUtils;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class RequestApi {


    public static JSONObject requestParams(List<String> objects, List<String> values) {
        JSONObject reqParams = new JSONObject();
        for (int i = 0; i < objects.size(); i++) {
            reqParams.put(objects.get(i), values.get(i));
        }
        return reqParams;
    }

    public Response apiRequestGet(String url) {
        Response response = given()
                .get(url)
                .then()
                .extract()
                .response();

        return response;
    }

    public Response apiRequestPost(String url, String path) throws IOException {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(generateStringFromJsonFile(path))
                .when()
                .post(url)
                .then()
                .extract().response();

        return response;
    }

    public Response apiRequestPostWithToken(String url, String token, List<String> objects, List<String> values) {
        JSONObject requestParam = requestParams(objects, values);
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer" + token)
                .and()
                .body(requestParam.toJSONString())
                .when()
                .post(url)
                .then()
                .extract().response();

        return response;
    }

    public Response apiRequestPut(String url, String path) throws IOException {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(generateStringFromJsonFile(path))
                .when()
                .put(url)
                .then()
                .extract().response();

        return response;
    }

    public Response apiRequestDelete(String url) {
        //JSONObject requestParam = requestParams(objects, values);
        Response response = given()
                .delete(url)
                .then()
                .extract()
                .response();

        return response;
    }

    public String generateStringFromJsonFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
