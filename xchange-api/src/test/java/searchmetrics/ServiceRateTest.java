package searchmetrics;

import javax.enterprise.context.ApplicationScoped;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("The service XChange rate")
class ServiceRateTest {

    private RateService rateService;
    private RateRepository rateRepository;

    @BeforeEach
    void beforeEach() {
        rateRepository = mock(RateRepository.class);

        rateService = new RateService(rateRepository);
    }

    @Test
    @DisplayName("invokes the rates repository for the latest rate")
    void it_request_the_latest_rate() {
        rateService.getLatestRate();

        verify(rateRepository).getLatest();
    }

}
