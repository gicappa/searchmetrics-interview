package searchmetrics;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import searchmetrics.domain.BtcUsdRate;
import searchmetrics.domain.RateRepository;
import searchmetrics.domain.RateService;

import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
        when(rateRepository.findRateByPeriod(any(), any())).thenReturn(List.of(rate20210314()));

        rateService = new RateService(rateRepository, clock2021_02_10());
    }

    private Clock clock2021_02_10() {
        return Clock.fixed(Instant.parse("2021-02-10T00:00:00.00Z"), UTC);
    }

    @Test
    @DisplayName("invokes repository for the latest rate available")
    void it_request_the_latest_rate() {
        rateService.getLatestRate();

        verify(rateRepository).getLatest();
    }

    @Test
    @DisplayName("returns the latest rate available in the the repository")
    void it_returns_the_latest_rate() {
        var actual = rateService.getLatestRate();

        assertThat(actual).isEqualTo(rate20210314());
    }

    @Test
    @DisplayName("invoke the repository for a specified period of time")
    void it_invokes_the_repository_for_time_period() {
        rateService.getRatesByPeriod(
            LocalDate.parse("2021-03-04"),
            LocalDate.parse("2021-03-13"));

        verify(rateRepository).findRateByPeriod(
            LocalDate.parse("2021-03-04"),
            LocalDate.parse("2021-03-13"));
    }

    @Test
    @DisplayName("returns the data in a specified period of time")
    void it_returns_the_data_for_time_period() {
        var actual =
            rateService.getRatesByPeriod(
                LocalDate.parse("2021-03-04"),
                LocalDate.parse("2021-03-13"));

        assertThat(actual).containsExactly(rate20210314());
    }

    @Test
    @DisplayName("returns the data of one week period (from 2021-02-10) when no params are passed")
    void it_returns_the_data_for_the_default_time_period() {
        rateService.getRatesByDefaultPeriod();

        verify(rateRepository).findRateByPeriod(
            LocalDate.parse("2021-02-03"),
            LocalDate.parse("2021-02-10"));
    }

    @Test
    @DisplayName("persists a new exchange rate BTC-USD")
    void it_persists_a_new_xchange_rate() {
        rateService.updateBtcUsdRate(56391.2D, Instant.parse("2021-03-14T00:00:00.00Z"));

        verify(rateRepository).createBtcUsdRate(new BtcUsdRate(1,56391.2D, LocalDateTime.parse("2021-03-14T00:00:00.000")));
    }

}
