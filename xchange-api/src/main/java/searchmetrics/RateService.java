package searchmetrics;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import static java.time.ZoneOffset.UTC;
import static java.time.temporal.ChronoUnit.WEEKS;

/**
 * RateService has the responsibility of decoupling the APIs
 * layer from the one managing the business logic.
 * <p>
 * It adapts the input and output if necessary and orchestrates
 * different services when necessary.
 */
public class RateService {
    private final RateRepository rateRepository;
    private final Clock clock;

    public RateService(RateRepository rateRepository) {
        this(rateRepository, Clock.systemUTC());
    }

    public RateService(RateRepository rateRepository, Clock clock) {
        this.rateRepository = rateRepository;
        this.clock = clock;
    }

    /**
     * @return the latest rate from the persistent repository
     */
    public BtcUsdRate getLatestRate() {
        return rateRepository.getLatest();
    }

    public List<BtcUsdRate> getRatesByPeriod(LocalDate startDate, LocalDate endDate) {
        return rateRepository.getRateByPeriod(startDate, endDate);
    }

    public List<BtcUsdRate> getRatesByDefaultPeriod() {
        var endDate = LocalDate.ofInstant(Instant.now(clock), UTC);
        var startDate = endDate.minus(1, WEEKS);

        return getRatesByPeriod(startDate, endDate);
    }
}
