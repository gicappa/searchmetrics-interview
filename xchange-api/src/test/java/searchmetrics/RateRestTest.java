package searchmetrics;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RateRestTest {

    private RateService mockService;
    private RateResource rateResource;

    @BeforeEach
    void beforeEach() {
        mockService = mock(RateService.class);
        rateResource = new RateResource(mockService);

        when(mockService.getLatestRate()).thenReturn(btcUsdRate14032021_210900());
    }

    @Test
    @DisplayName("It responds with a status code 200")
    void it_returns_200() {
        var actual = rateResource.getLatest();

        assertThat(actual.getStatus()).isEqualTo(200);
    }

    @Test
    @DisplayName("It invokes the rate service")
    void it_calls_the_rate_service() {
        rateResource.getLatest();

        verify(mockService).getLatestRate();
    }

    private BtcUsdRate btcUsdRate14032021_210900() {
        var ts14032021_210900 = LocalDateTime.of(2021, 3, 14, 21, 9, 0);
        return new BtcUsdRate(1, 60000.15, ts14032021_210900);
    }

}
