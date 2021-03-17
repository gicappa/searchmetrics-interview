package searchmetrics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class RateRestTest {

    @Test
    @DisplayName("It responds with a status code 200")
    void it_returns_200() {
        var rateResource = new RateResource(mock(RateService.class));

        var actual = rateResource.getLatest();

        assertThat(actual.getStatus()).isEqualTo(200);
    }

    @Test
    @DisplayName("It invokes the rate service")
    void it_calls_the_rate_service() {
        var mockService = mock(RateService.class);

        var rateResource = new RateResource(mockService);

        var actual = rateResource.getLatest();

        verify(mockService).getLatestRate();
    }
}
