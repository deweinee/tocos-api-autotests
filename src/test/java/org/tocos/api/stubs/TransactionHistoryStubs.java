package org.tocos.api.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.matchingJsonPath;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

public class TransactionHistoryStubs {

    public static void setupStubs() {
        stubFor(get(urlPathMatching("/transactions/1"))
                        .withRequestBody(matchingJsonPath("$.userId", equalToJson("1")))
                        .willReturn(aResponse()
                                            .withStatus(200)
                                            .withHeader("Content-Type", "application/json")
                                            .withBody("{ \"message\": \"History is displayed\" }")));

        stubFor(get(urlPathMatching("/transactions/2"))
                        .withRequestBody(matchingJsonPath("$.userId", equalToJson("1")))
                        .willReturn(aResponse()
                                            .withStatus(403)
                                            .withHeader("Content-Type", "application/json")
                                            .withBody("{ \"message\": \"Unauthorized access\" }")));

        stubFor(get(urlPathMatching("/transactions/3"))
                        .withRequestBody(matchingJsonPath("$.userId", equalToJson("1")))
                        .willReturn(aResponse()
                                            .withStatus(400)
                                            .withHeader("Content-Type", "application/json")
                                            .withBody("{ \"message\": \"User not found\" }")));

        stubFor(get(urlPathMatching("/transactions/1"))
                        .withRequestBody(matchingJsonPath("$.userId", equalToJson("3")))
                        .willReturn(aResponse()
                                            .withStatus(400)
                                            .withHeader("Content-Type", "application/json")
                                            .withBody("{ \"message\": \"User not found\" }")));
    }
}

