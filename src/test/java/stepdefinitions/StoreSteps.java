package stepdefinitions;

import api.StoreAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static context.CacheKey.RESPONSE;
import static context.CacheKey.getCacheData;

public class StoreSteps {

    public static final Logger log = LoggerFactory.getLogger(StoreSteps.class);

    @Given("send a GET request to {string} endpoint")
    public void sendGETRequestToEndpoint(final String endpoint) {
        StoreAPI.sendGetStoreRequest(endpoint);
    }

    @Given("send a POST request to {string} endpoint:")
    public void sendPOSTRequestToEndpoint(final String endpoint, DataTable dataTable) {
        Map<String, Object> requestBody = new HashMap<>();
        for (Map<String, String> row : dataTable.asMaps()) {
            String fieldName = row.get("Field");
            String value = row.get("Value");
            requestBody.put(fieldName, value);
        }
        StoreAPI.sendPostOrderRequest(endpoint, requestBody);
    }

    @Given("send a GET request to {string} endpoint with id {}")
    public void sendGETRequestToEndpointWithId(final String endpoint, final String id) {
        StoreAPI.sendGetOrderRequestById(endpoint, id);
    }

    @Given("send a POST request with invalid body to {string} endpoint")
    public void sendPOSTRequestWithInvalidBody(final String endpoint) {
        StoreAPI.sendPostRequestWithInvalidBody(endpoint);
    }

    @When("send a DELETE request to {string} endpoint with id {}")
    public void sendDELETERequestToEndpointWithId(final String endpoint, final String id) {
        StoreAPI.sendDeleteOrderRequestById(endpoint, id);
    }

    @Then("status code should be {int}")
    public void statusCodeShouldBe(final int statusCode) {
        Assert.assertTrue(StoreAPI.statusCodeShouldBe(statusCode));
    }

    @Then("message should be {string}")
    public void messageShouldBe(final String message) {
        Assert.assertTrue(StoreAPI.responseMessageShouldBe(message));
    }

    @Then("response should match to {string} schema")
    public void responseShouldMatchToSchema(final String schema) {
        Assert.assertTrue(StoreAPI.responseShouldMatchSchema(schema));
    }


}
