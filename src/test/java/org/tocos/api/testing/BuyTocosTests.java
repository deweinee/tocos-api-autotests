package org.tocos.api.testing;

import io.restassured.response.Response;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.tocos.api.utils.CsvUtils;

import java.io.IOException;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;

public class BuyTocosTests extends ApiTestSetup {

    @ParameterizedTest
    @MethodSource("buyTocosDataProvider")
    public void testBuyTocos(int userId, float amount, int expectedStatusCode, String expectedMessage) {
        Response response = apiClient.buyTocos(userId, amount);

        response.then().statusCode(expectedStatusCode)
                .body("message", equalTo(expectedMessage));
    }

    static Stream<Arguments> buyTocosDataProvider() throws IOException {
        return CsvUtils.parseCsv("src/test/resources/buyTocos_testdata.csv").map(BuyTocosTests::convertCsvRecordToArguments);
    }

    private static Arguments convertCsvRecordToArguments(CSVRecord record) {
        int userId = Integer.parseInt(record.get("userId"));
        float amount = Float.parseFloat(record.get("amount"));
        int expectedStatusCode = Integer.parseInt(record.get("expectedStatusCode"));
        String expectedMessage = record.get("expectedMessage");
        return Arguments.of(userId, amount, expectedStatusCode, expectedMessage);
    }
}
