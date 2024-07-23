package org.tocos.api.testing;

import io.restassured.response.Response;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.tocos.api.utils.CsvUtils;

import java.io.IOException;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;

public class SendTocosTests extends ApiTestSetup {

    @ParameterizedTest
    @MethodSource("sendTocosDataProvider")
    public void testTransaction(int userId, int targetUserId, float amount, int expectedStatusCode, String expectedMessage) {
        Response response = apiClient.sendTocos(userId, targetUserId, amount);

        response.then().statusCode(expectedStatusCode)
                .body("message", equalTo(expectedMessage));
    }

    static Stream<Arguments> sendTocosDataProvider() throws IOException {
        return CsvUtils.parseCsv("src/test/resources/sendTocos_testdata.csv").map(SendTocosTests::convertCsvRecordToArguments);
    }

    private static Arguments convertCsvRecordToArguments(CSVRecord record) {
        int userId = Integer.parseInt(record.get("userId"));
        int targetUserId = Integer.parseInt(record.get("targetUserId"));
        float amount = Float.parseFloat(record.get("amount"));
        int expectedStatusCode = Integer.parseInt(record.get("expectedStatusCode"));
        String expectedMessage = record.get("expectedMessage");
        return Arguments.of(userId, targetUserId, amount, expectedStatusCode, expectedMessage);
    }
}
