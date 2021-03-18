package searchmetrics;

import javax.ws.rs.core.GenericType;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static searchmetrics.Scenario.rate20210301;
import static searchmetrics.Scenario.rate20210314;

@DisplayName("The historical exchange rate BTC-USD")
class RestGetHistoricalRatesTest {

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
            when(mockService.getRatesByPeriod(
                LocalDate.parse("2021-03-04"),
                LocalDate.parse("2021-03-18")))
                .thenReturn(List.of(rate20210314()));

            when(mockService.getRatesByDefaultPeriod())
                .thenReturn(List.of(rate20210301(), rate20210314()));
        }

        @AfterEach
        void afterEach() {
            reset(mockService);
        }

        @Test
        @DisplayName("it responds with a status code 200")
        void it_returns_200() {
            var actual = rateResource.index("2021-03-04", "2021-03-18");

            assertThat(actual.getStatus()).isEqualTo(200);
        }

        @Test
        @DisplayName("it responds using a Content-Type: application/json")
        void it_returns_content_type_application_json() {
            var actual = rateResource.index("2021-03-04", "2021-03-18");

            assertThat(actual.getMediaType()).isEqualTo(APPLICATION_JSON_TYPE);
        }

        @Test
        @DisplayName("it invokes the historical rate service")
        void it_calls_the_rate_service() {
            rateResource.index("2021-03-04", "2021-03-18");

            verify(mockService).getRatesByPeriod(
                LocalDate.parse("2021-03-04"),
                LocalDate.parse("2021-03-18"));
        }

        @Test
        @DisplayName("it responds with a default range list of BtcUsdRate entity")
        void it_returns_a_list_of_rates_for_a_call_with_null_params() {
            var actual = rateResource.index(null, null);

            assertThat(actual.readEntity(listRateClass()))
                .containsExactly(rate20210301(), rate20210314());
        }

        @Test
        @DisplayName("it responds with a default range list of BtcUsdRate entity")
        void it_returns_a_list_of_rates_for_a_call_with_empty_params() {
            var actual = rateResource.index("", "");

            assertThat(actual.readEntity(listRateClass()))
                .containsExactly(rate20210301(), rate20210314());
        }

        @Test
        @DisplayName("it responds with a default range list of BtcUsdRate entity")
        void it_returns_a_list_of_rate_entity_even_() {
            rateResource.index(null, null);

            verify(mockService).getRatesByDefaultPeriod();
        }

        @Test
        @DisplayName("it responds with a default range list of BtcUsdRate entity")
        void it_returns_a_list_of_rate_entity() {
            var actual = rateResource.index(null, null);

            assertThat(actual.readEntity(listRateClass()))
                .containsExactly(rate20210301(), rate20210314());
        }

        @Test
        @DisplayName("it responds with a list of BtcUsdRate entity in a date range")
        void it_returns_a_list_of_rate_entity_in_a_time_range() {
            var actual = rateResource.index("2021-03-04", "2021-03-18");

            assertThat(actual.readEntity(listRateClass()))
                .containsExactly(rate20210314());
        }
    }

    @Nested
    @DisplayName("when a unsuccessful GET request is performed")
    class WhenUnsuccessful {
        @BeforeEach
        void beforeEach() {
            when(mockService.getRatesByPeriod(LocalDate.parse("2021-03-04"), LocalDate.parse("2021-03-18")))
                .thenThrow(new XChangeRateEx("Internal Server Error"));
        }

        @Test
        @DisplayName("it responds with a status code 400 when a parameter different from a date is passed")
        void it_returns_400() {
            var actual = rateResource.index("foo", "bar");

            assertThat(actual.getStatus()).isEqualTo(400);
        }

        @Test
        @DisplayName("it responds with a payload containing the error")
        void it_returns_en_error_payload() {
            var actual = rateResource.index("foo", "bar");

            final var expectedError =
                new RateError(
                    "XC003",
                    "400",
                    "parameters startDate and endDate must be expressed in the format yyyy-MM-dd");

            assertThat(actual.getEntity()).isEqualTo(expectedError);
        }

        @Test
        @DisplayName("it responds with a status code 500 when an internal exception is thrown")
        void it_returns_500() {
            var actual = rateResource.index("2021-03-04", "2021-03-18");

            assertThat(actual.getStatus()).isEqualTo(500);
        }

        @Test
        @DisplayName("it responds using a Content-Type: application/json")
        void it_returns_content_type_application_json() {
            var actual = rateResource.index("2021-03-04", "2021-03-18");

            assertThat(actual.getMediaType()).isEqualTo(APPLICATION_JSON_TYPE);
        }

        @Test
        @DisplayName("it responds with a payload containing the error")
        void it_returns_an_error_payload() {
            var actual = rateResource.index("2021-03-04", "2021-03-18");

            final var expectedError =
                new RateError(
                    "XC002",
                    "500",
                    "A unexpected exception occurred. Please contact the administrator");

            assertThat(actual.getEntity()).isEqualTo(expectedError);
        }
    }

    public GenericType<List<BtcUsdRate>> listRateClass() {
        return new GenericType<>() {
        };
    }
}
