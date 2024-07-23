package org.tocos.api.testing;

import org.junit.jupiter.api.BeforeAll;
import org.tocos.api.TocoApiClient;

public class ApiTestSetup {

    protected static TocoApiClient apiClient;

    @BeforeAll
    public static void setup() {
        apiClient = new TocoApiClient();
    }
}
