package searchmetrics.infrastructure;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import searchmetrics.domain.BtcUsdRate;
import searchmetrics.domain.RateRepository;

import static java.util.stream.Collectors.toList;

/**
 * InMemoryRateRepository records in memory the xchange rate data
 */
public class InMemoryRateRepository implements RateRepository {

    private final List<BtcUsdRate> xchangeRates;

    public InMemoryRateRepository() {
        this.xchangeRates = new ArrayList<>();
    }

    @Override
    public BtcUsdRate getLatest() {
        return xchangeRates.get(xchangeRates.size());
    }

    @Override
    public List<BtcUsdRate> findRateByPeriod(LocalDate startDate, LocalDate endDate) {
        return xchangeRates.stream()
            .filter(xcr -> xcr.getInstant().isAfter(Instant.from(startDate)))
            .filter(xcr -> xcr.getInstant().isBefore(Instant.from(endDate)))
            .collect(toList());
    }

    @Override
    public void createBtcUsdRate(BtcUsdRate btcUsdRate) {
        xchangeRates.add(btcUsdRate);
    }
}
