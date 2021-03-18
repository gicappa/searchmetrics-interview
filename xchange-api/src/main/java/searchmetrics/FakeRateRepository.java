package searchmetrics;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FakeRateRepository implements RateRepository {
    @Override
    public BtcUsdRate getLatest() {
        return new BtcUsdRate(1, 60000.15, LocalDateTime.of(2021, 3, 14, 21, 9, 0));
    }

    @Override
    public List<BtcUsdRate> getRateByPeriod(LocalDate parse, LocalDate localDate) {
        return null;
    }
}
