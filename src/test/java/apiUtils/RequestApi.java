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

    protected Response response;

    /**
     * Parameters for token
     *
     * @param objects list of obects
     * @param values  list of object values
     * @return
     */
    public static JSONObject requestParams(List<String> objects, List<String> values) {
        JSONObject reqParams = new JSONObject();
        for (int i = 0; i < objects.size(); i++) {
            reqParams.put(objects.get(i), values.get(i));
        }
        return reqParams;
    }

    /**
     * Execute GET Request
     *
     * @param url Request URI extension
     * @return
     */
    public Response apiRequestGet(String url) {
        Response response = given()
                .get(url)
                .then()
                .extract()
                .response();

        return response;
    }

    /**
     * Execute POST Request
     *
     * @param url  Request URI extension
     * @param path Path to json body file
     * @return
     * @throws IOException
     */
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

    /**
     * Execute POST Request for token authentication
     *
     * @param url     Request URI extension
     * @param token   token value
     * @param objects
     * @param values
     * @return
     */
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

    /**
     * Execute PUT Request
     *
     * @param url  Request URI extension
     * @param path Path to json body file
     * @return
     * @throws IOException
     */
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

    /**
     * Execute DELETE Request
     *
     * @param url Request URI extension
     * @return
     */
    public Response apiRequestDelete(String url) {
        Response response = given()
                .delete(url)
                .then()
                .extract()
                .response();

        return response;
    }

    /**
     * Generating  string from json file to be used in body
     *
     * @param path Path to json body file
     * @return
     * @throws IOException
     */
    public String generateStringFromJsonFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    /**
     * Executing POST or PUT Request and stringifying it. Done for reusability and code duplication purposes
     *
     * @param url      Request URI extension
     * @param filePath Path to json body file
     * @return
     * @throws IOException
     */
    public String stringifyPostPutResponse(String url, String filePath) throws IOException {
        if (filePath.contains("Post")) {
            response = apiRequestPost(url, filePath);
        } else if (filePath.contains("Put")) {
            response = apiRequestPut(url, filePath);
        }
        String jsonString = response.asString();
        return jsonString;
    }

    /**
     * Executing GET Request and stringifying it. Done for reusability and code duplication purposes
     *
     * @param url Request URI extension
     * @return
     */
    protected String stringifyGetResponse(String url) {
        response = apiRequestGet(url);
        String jsonString = response.asString();
        return jsonString;
    }

    /**
     * Executing DELETE Request and stringifying it. Done for reusability and code duplication purposes
     *
     * @param url Request URI extension
     * @return
     */
    protected String stringifyDeleteResponse(String url) {
        response = apiRequestDelete(url);
        String jsonString = response.asString();
        return jsonString;
    }
}
