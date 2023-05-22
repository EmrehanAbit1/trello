package apiUtils;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import org.apache.commons.codec.binary.Base64;
import utility.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RequestApi {

    protected Response response;
    protected String APIToken = decodeBase64(Config.getInstance().getEncodedToken());
    protected String APIKey = decodeBase64(Config.getInstance().getEncodedKey());

    /**
     * Execute GET Request
     *
     * @param url Request URI extension
     * @return
     */
    public Response apiRequestGet(String url, String key, String token) {
        Response response = given()
                .queryParam("key", APIKey)
                .queryParam("token", APIToken)
                .get(url)
                .then()
                .extract()
                .response();

        return response;
    }

    /**
     * Execute POST Request to add board
     *
     * @param url  Request URI extension
     * @param path Path to json body file
     * @return response
     * @throws IOException
     */
    public Response apiRequestPost(String url, String path, String key, String token) throws IOException {
        Response response = given()
                .header("Content-type", "application/json")
                .queryParam("key", APIKey)
                .queryParam("token", APIToken)
                .and()
                .body(generateStringFromJsonFile(path))
                .when()
                .post(url)
                .then()
                .extract().response();

        return response;
    }

    /**
     * Execute POST Request to add board
     *
     * @param url  Request URI extension
     * @param path Path to json body file
     * @return response
     * @throws IOException
     */
    public Response apiRequestAddCardPost(String url, String path, String idList, String key, String token) throws IOException {
        Response response = given()
                .header("Content-type", "application/json")
                .queryParam("key", APIKey)
                .queryParam("token", APIToken)
                .queryParam("idList", idList)
                .and()
                .body(generateStringFromJsonFile(path))
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
    public Response apiRequestPut(String url, String path, String key, String token) throws IOException {
        Response response = given()
                .header("Content-type", "application/json")
                .queryParam("key", APIKey)
                .queryParam("token", APIToken)
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
    public Response apiRequestDelete(String url, String key, String token) {
        Response response = given()
                .queryParam("key", APIKey)
                .queryParam("token", APIToken)
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
     * Decodes key and token for security reasons
     *
     * @param decoder
     * @return
     */
    public static String decodeBase64(String decoder) {
        byte[] bytes = Base64.decodeBase64(decoder.getBytes());
        return new String(bytes);
    }
}
