package searchmetrics.storage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import searchmetrics.domain.BtcUsdRate;
import searchmetrics.domain.RateRepository;

/**
 * Used from the tests to stub results
 */
public class FakeRateRepository implements RateRepository {

    @Override
    public BtcUsdRate getLatest() {
        return rate1();
    }

    @Override
    public List<BtcUsdRate> findRateByPeriod(LocalDate startDate, LocalDate endDate) {
        return List.of(rate1(), rate2(), rate3());
    }

    @Override
    public void createBtcUsdRate(BtcUsdRate btcUsdRate) {
        // nope
    }

    // mock btc usd rates
    public static BtcUsdRate rate1() {
        return new BtcUsdRate(1, 60000.15,
            LocalDateTime.of(2021, 3, 14, 13, 1, 59));
    }

    public static BtcUsdRate rate2() {
        return new BtcUsdRate(1, 60003.57,
            LocalDateTime.of(2021, 3, 10, 23, 44, 5));
    }

    public static BtcUsdRate rate3() {
        return new BtcUsdRate(1, 60005.28,
            LocalDateTime.of(2021, 3, 7, 10, 27, 0));
    }
}
