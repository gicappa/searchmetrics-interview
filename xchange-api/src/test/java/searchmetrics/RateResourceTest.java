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
     *   "timestamp": "2021-03-14T21:09:00Z"
     * }
     * </pre>
     */
    @Test
    @Disabled("To be implemented")
    @DisplayName("returns a JSON with a single rate")
    void it_returns_a_200_response_JSON_with_the_latest_rate() {
        given()
            .when()
            .get("/rates/btc-usd/latest")
            .then()
            .statusCode(200)
            .contentType(JSON)
            .body("BTC", equalTo(1))
            .body("USD", equalTo(60000.15))
            .body("timestamp", equalTo("2021-03-14T21:09:00Z"));
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
     *   "timestamp": "2021-03-14T21:09:00Z"
     * },
     * {
     *   "BTC": 1
     *   "USD": 60001.23,
     *   "timestamp": "2021-03-14T21:07:00Z"
     * }]
     * </pre>
     */
    @Test
    @Disabled("To be implemented")
    @DisplayName("returns a JSON with an array of rates")
    void it_returns_a_200_response_JSON_with_the_list_of_rates() {
        var ts14032021_210900 = LocalDateTime.of(2021, 3, 14, 21, 9, 0);
        var ts14032021_210700 = LocalDateTime.of(2021, 3, 14, 21, 7, 0);

        BtcUsdRate[] rates = given()
            .when()
            .get("/rates/btc-usd")
            .then()
            .statusCode(200)
            .contentType(JSON)
            .extract()
            .as(BtcUsdRate[].class);

        assertThat(rates).hasSize(2)
            .contains(new BtcUsdRate(1, 60000.15, ts14032021_210900))
            .contains(new BtcUsdRate(1, 60001.23, ts14032021_210700));
    }

    /**
     * To better express the error that occurred we may expect a response Similar to
     * this one.
     *
     * <pre>
     * {
     *   "error": {
     *     "type": "ValidationError",
     *     "code": "FB001",
     *     "message": "The query parameter limit must be a number.",
     *   }
     * }
     * </pre>
     */
    @Test
    @Disabled("To be implemented")
    @DisplayName("returns a JSON reporting an error for wrong parameters")
    void it_returns_an_error_resource_with_a_wrong_parameter() {
        System.out.println(
            given()
                .queryParam("limit", "foobar")
                .when()
                .get("/rates/btc-usd")
                .then()
                .statusCode(400)
                .contentType(JSON)
                .body("any { it.key == 'error' }", is(true))
                .body("error.type", is("ValidationError"))
                .body("error.code", is("FB001"))
                .body("error.message", containsString("must be a number")));
    }
}