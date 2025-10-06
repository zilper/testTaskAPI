package api;

import config.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseAPI {

    protected static Response sendGetRequest(final String endpoint) {
        return given()
                .baseUri(ConfigLoader.getProperty("base.url"))
                .basePath(endpoint)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .get();
    }

    protected static Response sendPostRequest(final String endpoint, Map<String, Object> requestBody) {
        return given()
                .baseUri(ConfigLoader.getProperty("base.url"))
                .basePath(endpoint)
                .body(requestBody)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .post();
    }

    protected static Response sendDeleteRequest(final String endpoint, final String id) {
        return given()
                .baseUri(ConfigLoader.getProperty("base.url"))
                .basePath(endpoint + "/{id}")
                .pathParam("id", id)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .delete();
    }

}
