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

public class SellTocosTests extends ApiTestSetup {

    @ParameterizedTest
    @MethodSource("sellTocosDataProvider")
    public void testSellTocos(int userId, float amount, int expectedStatusCode, String expectedMessage) {
        Response response = apiClient.sellTocos(userId, amount);

        response.then().statusCode(expectedStatusCode)
                .body("message", equalTo(expectedMessage));
    }

    static Stream<Arguments> sellTocosDataProvider() throws IOException {
        return CsvUtils.parseCsv("src/test/resources/sellTocos_testdata.csv").map(SellTocosTests::convertCsvRecordToArguments);
    }

    private static Arguments convertCsvRecordToArguments(CSVRecord record) {
        int userId = Integer.parseInt(record.get("userId"));
        float amount = Float.parseFloat(record.get("amount"));
        int expectedStatusCode = Integer.parseInt(record.get("expectedStatusCode"));
        String expectedMessage = record.get("expectedMessage");
        return Arguments.of(userId, amount, expectedStatusCode, expectedMessage);
    }
}
