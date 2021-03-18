package searchmetrics;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Default
@ApplicationScoped
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
