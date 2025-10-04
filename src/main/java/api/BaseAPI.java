package api;

import config.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseAPI {

    protected static Response sendGetRequest(String endpoint) {
        return given()
                .baseUri(ConfigLoader.getProperty("base.url"))
                .basePath(endpoint)
                .when()
                .get();
    }

    protected static Response sendPostRequest(String endpoint, Map<String, Object> requestBody) {
        return given()
                .baseUri(ConfigLoader.getProperty("base.url"))
                .basePath(endpoint)
                .body(requestBody)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .post();
    }

    protected static Response sendDeleteRequest(String endpoint) {
        return given()
                .baseUri(ConfigLoader.getProperty("base.url"))
                .basePath(endpoint)
                .when()
                .delete();
    }

}
