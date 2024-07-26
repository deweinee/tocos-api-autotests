package org.tocos.api.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.matchingJsonPath;
import static com.github.tomakehurst.wiremock.client.WireMock.or;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class SendTocosStubs {

    public static void setupStubs() {
        stubFor(post(urlEqualTo("/send"))
                        .withRequestBody(matchingJsonPath("$", or(
                                matchingJsonPath("$.userId", equalToJson("1"))
                                        .and((matchingJsonPath("$.amount", equalToJson("0.001")))
                                        .or(matchingJsonPath("$.amount", equalToJson("200.0")))),
                                matchingJsonPath("$.userId", equalToJson("2"))
                                        .and((matchingJsonPath("$.targetUserId", equalToJson("1")))
                                        .and(matchingJsonPath("$.amount", equalToJson("100.0"))))
                        )))
                        .willReturn(aResponse()
                                            .withStatus(200)
                                            .withHeader("Content-Type", "application/json")
                                            .withBody("{ \"message\": \"Transaction successful\" }")));

        stubFor(post(urlEqualTo("/send"))
                        .withRequestBody(matchingJsonPath("$.userId", equalToJson("1")))
                        .withRequestBody(matchingJsonPath("$.targetUserId", equalToJson("2")))
                        .withRequestBody(matchingJsonPath("$.amount", equalToJson("200.001")))
                        .willReturn(aResponse()
                                            .withStatus(400)
                                            .withHeader("Content-Type", "application/json")
                                            .withBody("{ \"message\": \"Insufficient fiat balance\" }")));

        stubFor(post(urlEqualTo("/send"))
                        .withRequestBody(matchingJsonPath("$.userId", equalToJson("1")))
                        .withRequestBody(matchingJsonPath("$.targetUserId", equalToJson("2")))
                        .withRequestBody(matchingJsonPath("$.amount", equalToJson("0.0")))
                        .willReturn(aResponse()
                                            .withStatus(400)
                                            .withHeader("Content-Type", "application/json")
                                            .withBody("{ \"message\": \"Invalid Tocos amount\" }")));

        stubFor(post(urlEqualTo("/send"))
                        .withRequestBody(matchingJsonPath("$.userId", equalToJson("2")))
                        .withRequestBody(matchingJsonPath("$.targetUserId", equalToJson("1")))
                        .withRequestBody(matchingJsonPath("$.amount", equalToJson("100.001")))
                        .willReturn(aResponse()
                                            .withStatus(400)
                                            .withHeader("Content-Type", "application/json")
                                            .withBody("{ \"message\": \"Transaction limit exceeded\" }")));

        stubFor(post(urlEqualTo("/send"))
                        .withRequestBody(matchingJsonPath("$", or(
                                matchingJsonPath("$.userId", equalToJson("1"))
                                        .and(matchingJsonPath("$.targetUserId", equalToJson("3"))),
                                matchingJsonPath("$.userId", equalToJson("3"))
                                        .and(matchingJsonPath("$.targetUserId", equalToJson("1")))
                        )))
                        .willReturn(aResponse()
                                            .withStatus(400)
                                            .withHeader("Content-Type", "application/json")
                                            .withBody("{ \"message\": \"User not found\" }")));


    }

}
