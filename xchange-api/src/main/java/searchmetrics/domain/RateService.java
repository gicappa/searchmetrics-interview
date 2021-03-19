package searchmetrics.domain;

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

    /**
     * RateService.getRatesByDefaultPeriod() is invoked when
     * start date and end date of the historical query are not
     * specified. By default end date is calculated to now
     * while start date is one week back in time from now.
     *
     * @return the exchange rate BTC-USD of the past week
     */
    public List<BtcUsdRate> getRatesByDefaultPeriod() {
        var endDate = LocalDate.ofInstant(Instant.now(clock), UTC);
        var startDate = endDate.minus(1, WEEKS);

        return getRatesByPeriod(startDate, endDate);
    }

    /**
     * RateService.getRatesByPeriod() to retrieve the historical data
     * of the exchanged rates for the BTC-USD symbol from the startDate
     * to the endDate
     *
     * @param startDate start date of the query
     * @param endDate end date of the query
     * @return list of rates in the query period
     */
    public List<BtcUsdRate> getRatesByPeriod(LocalDate startDate, LocalDate endDate) {
        return rateRepository.getRateByPeriod(startDate, endDate);
    }
}
