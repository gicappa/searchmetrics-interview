package searchmetrics;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static searchmetrics.Scenario.rate20210314;

@DisplayName("The service XChange rate")
class ServiceRateTest {

    private RateService rateService;
    private RateRepository rateRepository;

    @BeforeEach
    void beforeEach() {
        rateRepository = mock(RateRepository.class);
        when(rateRepository.getLatest()).thenReturn(rate20210314());
        rateService = new RateService(rateRepository);
    }

    @Test
    @DisplayName("invokes the rates repository for the latest rate")
    void it_request_the_latest_rate() {
        rateService.getLatestRate();

        verify(rateRepository).getLatest();
    }

    @Test
    @DisplayName("returns the latest rate from the repository")
    void it_returns_the_latest_rate() {
        var actual = rateService.getLatestRate();

        assertThat(actual).isEqualTo(rate20210314());
    }

}
