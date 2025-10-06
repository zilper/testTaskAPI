package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import config.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static context.CacheKey.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static java.lang.String.format;
import static java.lang.Thread.sleep;

public class StoreAPI extends BaseAPI {

    private static final Logger log = LoggerFactory.getLogger(StoreAPI.class);

    public static void sendGetStoreRequest(final String endpoint) {
         Response response = sendGetRequest(format("/store%s", endpoint));
         putData(RESPONSE, response);
         log.info("Response from /store{} has been saved", endpoint);
    }

    public static boolean statusCodeShouldBe(final int statusCode) {
        Response response = (Response) getCacheData(RESPONSE);
        return response.getStatusCode() == statusCode;
    }

    public static boolean responseShouldMatchSchema(final String schema) {
        Response response = (Response) getCacheData(RESPONSE);
        try {
            response.then().assertThat()
                    .body(matchesJsonSchemaInClasspath(format("files/%s.json", schema)));
            return true;
        } catch (AssertionError e) {
            System.err.println("Schema validation failed: " + e.getMessage());
            return false;
        }
    }

    public static void sendPostOrderRequest(final String endpoint, Map<String, Object> requestBody) {
        Response response = sendPostRequest(format("/store%s", endpoint), requestBody);
        putData(RESPONSE, response);
        log.info("Order created with /store{} has been saved", endpoint);
        log.info(String.valueOf(response.getStatusCode()));
        log.info(response.getBody().asPrettyString());
    }

    public static void sendPostRequestWithInvalidBody(final String endpoint) {
        Response response = given()
                .baseUri(ConfigLoader.getProperty("base.url"))
                .basePath("/store" + endpoint)
                .body("{ bad_json, , , }")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .post();
        putData(RESPONSE, response);
        log.info("Response created with request with raw body by /store{} has been saved", endpoint);
        log.info(response.getBody().asPrettyString());
    }

    public static boolean responseMessageShouldBe(final String message) {
        Response response = (Response) getCacheData(RESPONSE);
        JsonObject jsonObject = new Gson().fromJson(response.getBody().asString(), JsonObject.class);
        return jsonObject.get("message").getAsString().equals(message);
    }

    public static void sendGetOrderRequestById(final String endpoint, final String id) {
        Response response = sendGetRequest(format("/store%s/%s", endpoint, id));
        putData(RESPONSE, response);
        log.info("Response from /store{}/{} has been saved", endpoint, id);
        log.info(response.getBody().asPrettyString());
    }

    public static void sendDeleteOrderRequestById(final String endpoint, final String id) {
        Response response = sendDeleteRequest(format("/store%s", endpoint), id);
        putData(RESPONSE, response);
        log.info("Response from DELETE store request has been saved");
        log.info(response.getBody().asPrettyString());
    }

}
