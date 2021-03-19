package searchmetrics;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import searchmetrics.api.RateError;
import searchmetrics.api.RateResource;
import searchmetrics.domain.BtcUsdRate;
import searchmetrics.domain.RateService;
import searchmetrics.domain.XChangeRateEx;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("The latest exchange rate BTC-USD")
class RestGetLatestRateTest {

    private RateService mockService;
    private RateResource rateResource;

    @BeforeEach
    void beforeEach() {
        mockService = mock(RateService.class);
        rateResource = new RateResource(mockService);
    }

    @Nested
    @DisplayName("when a successful GET request is performed")
    class WhenSuccessful {

        @BeforeEach
        void beforeEach() {
            when(mockService.getLatestRate()).thenReturn(btcUsdRate14032021_210900());
        }

        @Test
        @DisplayName("It responds using a Content-Type: application/json")
        void it_returns_content_type_application_json() {
            var actual = rateResource.getLatest();

            assertThat(actual.getMediaType()).isEqualTo(APPLICATION_JSON_TYPE);
        }

        @Test
        @DisplayName("It invokes the rate service")
        void it_calls_the_rate_service() {
            rateResource.getLatest();

            verify(mockService).getLatestRate();
        }

        @Test
        @DisplayName("it responds with a status code 200")
        void it_returns_200() {
            var actual = rateResource.getLatest();

            assertThat(actual.getStatus()).isEqualTo(200);
        }

        @Test
        @DisplayName("It responds with a payload containing the BtcUsdRate entity")
        void it_returns_a_valid_payload() {
            var actual = rateResource.getLatest();

            assertThat(actual.getEntity()).isEqualTo(btcUsdRate14032021_210900());
        }

    }

    @Nested
    @DisplayName("when a unsuccessful GET request is performed")
    class WhenUnsuccessful {
        @BeforeEach
        void beforeEach() {
            when(mockService.getLatestRate()).thenThrow(new XChangeRateEx("Internal Server Error"));
        }

        @Test
        @DisplayName("It responds using a Content-Type: application/json")
        void it_returns_content_type_application_json() {
            var actual = rateResource.getLatest();

            assertThat(actual.getMediaType()).isEqualTo(APPLICATION_JSON_TYPE);
        }

        @Test
        @DisplayName("It responds with a status code 500 when an internal exception is thrown")
        void it_returns_500() {
            var actual = rateResource.getLatest();

            assertThat(actual.getStatus()).isEqualTo(500);
        }

        @Test
        @DisplayName("It responds with a payload containing the error")
        void it_returns_a_valid_payload() {
            var actual = rateResource.getLatest();

            final var expectedError =
                new RateError(
                    "XC001",
                    "500",
                    "A unexpected exception occurred. Please contact the administrator");

            assertThat(actual.getEntity()).isEqualTo(expectedError);
        }
    }

    private BtcUsdRate btcUsdRate14032021_210900() {
        var ts14032021_210900 = LocalDateTime.of(2021, 3, 14, 21, 9, 0);
        return new BtcUsdRate(1, 60000.15, ts14032021_210900);
    }

}
