package org.tocos.api.testing;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.tocos.api.TocoApiClient;
import org.tocos.api.stubs.BuyTocosStubs;
import org.tocos.api.stubs.SellTocosStubs;
import org.tocos.api.stubs.SendTocosStubs;
import org.tocos.api.stubs.TransactionHistoryStubs;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApiTestSetup {

    private static WireMockServer wireMockServer;
    protected static TocoApiClient apiClient;

    @BeforeAll
    public static void setup() {
        apiClient = new TocoApiClient();

        if (wireMockServer == null) {
            wireMockServer = new WireMockServer(options().port(8080));
            wireMockServer.start();

            BuyTocosStubs.setupStubs();
            SellTocosStubs.setupStubs();
            SendTocosStubs.setupStubs();
            TransactionHistoryStubs.setupStubs();
        }
    }

    @AfterAll
    public static void teardown() {
        if (wireMockServer != null) {
            wireMockServer.stop();
            wireMockServer = null;
        }
    }
}
