package searchmetrics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("The service XChange rate")
class ServiceRateTest {

    @Test
    @DisplayName("invokes the rates repository for the latest rate")
    void it_request_the_latest_rate() {
        var rateRepository = mock(RateRepository.class);

        var rateService = new RateService(rateRepository);

        rateService.getLatestRate();

        verify(rateRepository).getLatest();
    }
}
