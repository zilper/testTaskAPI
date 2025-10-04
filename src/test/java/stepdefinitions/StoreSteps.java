package stepdefinitions;

import api.StoreAPI;
import context.SharedContext;
import io.cucumber.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StoreSteps {

    public static final Logger log = LoggerFactory.getLogger(StoreSteps.class);

    private final SharedContext context;
    private final StoreAPI storeAPI;

    public StoreSteps(SharedContext context, StoreAPI storeAPI) {
        this.context = context;
        this.storeAPI = storeAPI;
    }

    @Given("send a GET request to {string}")
    public void sendGETRequestToEndpoint(String endpoint) {
        context.setResponse(storeAPI.sendGetStoreRequest(endpoint));
    }

}
