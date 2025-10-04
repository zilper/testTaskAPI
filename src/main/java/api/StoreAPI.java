package api;

import io.restassured.response.Response;

import static java.lang.String.format;

public class StoreAPI extends BaseAPI {

    public Response sendGetStoreRequest(String endpoint) {
        return sendGetRequest(format("/store%s", endpoint));
    }

}
