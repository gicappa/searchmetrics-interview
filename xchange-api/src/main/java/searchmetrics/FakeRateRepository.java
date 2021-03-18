package searchmetrics;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.time.LocalDateTime;

@Default
@ApplicationScoped
public class FakeRateRepository implements RateRepository {
    @Override
    public BtcUsdRate getLatest() {
        return new BtcUsdRate(1, 60000.15, LocalDateTime.of(2021, 3, 14, 21, 9, 0));
    }
}
