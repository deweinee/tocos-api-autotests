package org.tocos.api.testing;

import org.tocos.api.utils.CsvUtils;
import io.restassured.response.Response;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.io.IOException;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;

public class TransactionHistoryTests extends ApiTestSetup {

    @ParameterizedTest
    @MethodSource("transactionHistoryDataProvider")
    public void testTransactionHistoryAccess(int userId, int targetUserId, int expectedStatusCode, String expectedMessage) {
        Response response = apiClient.getTransactionHistory(userId, targetUserId);

        response.then().statusCode(expectedStatusCode)
                .body("message", equalTo(expectedMessage));
    }

    static Stream<Arguments> transactionHistoryDataProvider() throws IOException {
        return CsvUtils.parseCsv("src/test/resources/transactionHistory_testdata.csv").map(TransactionHistoryTests::convertCsvRecordToArguments);
    }

    private static Arguments convertCsvRecordToArguments(CSVRecord record) {
        int userId = Integer.parseInt(record.get("userId"));
        int targetUserId = Integer.parseInt(record.get("targetUserId"));
        int expectedStatusCode = Integer.parseInt(record.get("expectedStatusCode"));
        String expectedMessage = record.get("expectedMessage");
        return Arguments.of(userId, targetUserId, expectedStatusCode, expectedMessage);
    }
}
