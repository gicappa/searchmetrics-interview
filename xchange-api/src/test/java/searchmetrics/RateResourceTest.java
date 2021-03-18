package searchmetrics;

import java.time.LocalDateTime;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static searchmetrics.FakeRateRepository.rate1;
import static searchmetrics.FakeRateRepository.rate2;
import static searchmetrics.FakeRateRepository.rate3;

@QuarkusTest
@DisplayName("The rate resource")
class RateResourceTest {

    /**
     * Acceptance Test for the get of the latest exchange rate of BTC-USD.
     * <p>
     * The timestamps are expressed in the format ISO-8601 in the UTC timezone.
     *
     * <pre>
     * {
     *   "BTC": 1
     *   "USD": 60000.15,
     *   "timestamp": "2021-03-14T13:01:59Z"
     * }
     * </pre>
     */
    @Test
    @DisplayName("returns a JSON with a single rate")
    void it_returns_a_200_response_JSON_with_the_latest_rate() {
        given()
            .when()
            .get("/rates/btc-usd/latest")
            .then()
            .statusCode(200)
            .contentType(JSON)
            .body("btc", equalTo(1F))
            .body("usd", equalTo(60000.15F))
            .body("timestamp", equalTo("2021-03-14T13:01:59Z"));
    }

    /**
     * Checking that the apis are returning a JSON with a list of words. The number
     * of words is by default 20 and they must comply with the fizzbuzz
     * specification.
     *
     * <pre>
     * [{
     *   "BTC": 1
     *   "USD": 60000.15,
     *   "timestamp": "2021-03-14T13:01:59Z"
     * },
     * {
     *   "BTC": 1
     *   "USD": 60001.23,
     *   "timestamp": "2021-03-10T23:44:05Z"
     * },
     * {
     *   "BTC": 1
     *   "USD": 60001.23,
     *   "timestamp": "2021-03-07T10:27:00Z"
     * }]
     * </pre>
     */
    @Test
    @DisplayName("returns a JSON with an array of rates")
    void it_returns_a_200_response_JSON_with_the_list_of_rates() {

        BtcUsdRate[] rates = given()
            .when()
            .get("/rates/btc-usd")
            .then()
            .statusCode(200)
            .contentType(JSON)
            .extract()
            .as(BtcUsdRate[].class);

        assertThat(rates).hasSize(3)
            .containsExactly(rate1(), rate2(), rate3());
    }

    /**
     * To better express the error that occurred we may expect a response Similar to
     * this one.
     *
     * <pre>
     * {
     *   "error": {
     *     "type": "XC003",
     *     "code": "400",
     *     "message": "The query parameter limit must be a number.",
     *   }
     * }
     * </pre>
     */
    @Test
    @DisplayName("returns a JSON reporting an error for wrong parameters")
    void it_returns_an_error_resource_with_a_wrong_parameter() {
        System.out.println(
            given()
                .queryParam("startDate", "foo")
                .queryParam("endDate", "bar")
                .when()
                .get("/rates/btc-usd")
                .then()
                .statusCode(400)
                .contentType(JSON)
                .body("type", is("XC003"))
                .body("code", is("400"))
                .body("message", containsString("startDate and endDate")));
    }
}