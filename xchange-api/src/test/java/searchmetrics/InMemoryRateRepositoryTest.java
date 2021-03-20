package searchmetrics;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import searchmetrics.domain.BtcUsdRate;
import searchmetrics.storage.InMemoryRateRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("The InMemoryRateRepositoryTest")
class InMemoryRateRepositoryTest {

    private InMemoryRateRepository rateRepository;

    @BeforeEach
    void beforeEach() {
        rateRepository = new InMemoryRateRepository();

        List.of(rate20210101(), rate20210201(), rate20210301())
            .forEach(rateRepository::createBtcUsdRate);
    }

    @Test
    @DisplayName("returns a list of rates between a closed range start end date")
    void it_returns_the_list_of_rates_between_a_closed_range() {

        assertThat(rateRepository.findRateByPeriod(
            LocalDate.parse("2021-01-15"),
            LocalDate.parse("2021-02-15")))
            .containsExactly(rate20210201());

        assertThat(rateRepository.findRateByPeriod(
            LocalDate.parse("2021-01-01"),
            LocalDate.parse("2021-03-01")))
            .containsExactly(rate20210101(), rate20210201(), rate20210301());
    }

    private BtcUsdRate rate20210101() {
        return new BtcUsdRate(1, 1, LocalDateTime.parse("2021-01-01T00:00:00.000"));
    }

    private BtcUsdRate rate20210201() {
        return new BtcUsdRate(1, 2, LocalDateTime.parse("2021-02-01T00:00:00.000"));
    }

    private BtcUsdRate rate20210301() {
        return new BtcUsdRate(1, 3, LocalDateTime.parse("2021-03-01T00:00:00.000"));
    }
}
