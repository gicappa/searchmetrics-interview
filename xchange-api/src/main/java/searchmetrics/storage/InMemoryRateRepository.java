package searchmetrics.storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import searchmetrics.domain.BtcUsdRate;
import searchmetrics.domain.RateRepository;

import static java.time.ZoneOffset.UTC;
import static java.util.stream.Collectors.toList;

/**
 * InMemoryRateRepository records in memory the xchange rate data
 */
public class InMemoryRateRepository implements RateRepository {

    /**
     * The in memory list containing the rates
     */
    private final List<BtcUsdRate> xchangeRates;

    public InMemoryRateRepository() {
        this.xchangeRates = new ArrayList<>();
    }

    /**
     * @return the latest xchange rate from memory
     */
    @Override
    public BtcUsdRate getLatest() {
        return xchangeRates.get(xchangeRates.size() - 1);
    }

    /**
     * Search in memory the rates between start and end period
     * in a closed range.
     *
     * @param startDate start of the period
     * @param endDate end of the period
     * @return a list of rates
     */
    @Override
    public List<BtcUsdRate> findRateByPeriod(LocalDate startDate, LocalDate endDate) {
        return xchangeRates.stream()
            .filter(xcr -> xcr.getInstant().isAfter(startDate.minusDays(1).atTime(LocalTime.MAX).toInstant(UTC)))
            .filter(xcr -> xcr.getInstant().isBefore(endDate.atTime(LocalTime.MAX).toInstant(UTC)))
            .collect(toList());
    }

    /**
     * Saving a rate into memory
     *
     * @param btcUsdRate the entity to be stored
     */
    @Override
    public void createBtcUsdRate(BtcUsdRate btcUsdRate) {
        xchangeRates.add(btcUsdRate);
    }
}
