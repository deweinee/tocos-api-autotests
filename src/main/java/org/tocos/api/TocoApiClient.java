package org.tocos.api;

import io.restassured.response.Response;

public class TocoApiClient extends ApiClient {

    public TocoApiClient() {
        super();
    }

    public Response createUser(int userId) {
        return request()
                .body("{\"userId\": " + userId + " }")
                .post("/users");
    }

    public Response buyTocos(int userId, float amount) {
        return request()
                .body("{ \"userId\": " + userId + ", \"amount\": " + amount + " }")
                .post("/buy");
    }

    public Response sellTocos(int userId, float amount) {
        return request()
                .body("{ \"userId\": " + userId + ", \"amount\": " + amount + " }")
                .post("/sell");
    }

    public Response sendTocos(int userId, int targetUserId, float amount) {
        return request()
                .body("{ \"userId\": " + userId + ", \"targetUserId\": " + targetUserId + ", \"amount\": " + amount + " }")
                .post("/send");
    }

    public Response getTransactionHistory(int userId, int targetUserId) {
        return request()
                .body("{ \"userId\": " + userId + " }")
                .get("/transactions/" + targetUserId);
    }
}
