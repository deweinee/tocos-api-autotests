package org.tocos.api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class ApiClient {

    public ApiClient() {
        RestAssured.baseURI = ApiConfig.getBaseUri();
    }

    protected RequestSpecification request() {
        return RestAssured.given().header("Content-Type", "application/json");
    }
}
