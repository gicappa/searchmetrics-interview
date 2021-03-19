package searchmetrics.domain;

import java.time.LocalDate;
import java.util.List;

/**
 * RateRepository has the responsibility to provide
 * data that are downloaded from the bitcoin rate provider
 */
public interface RateRepository {

    /**
     * @return the latest BTC-USD rate exchange collected
     */
    BtcUsdRate getLatest();

    /**
     * RateRepository.findRateByPeriod returns the persisted
     * historical data of the exchange rates
     *
     * @param startDate start of the period
     * @param endDate end of the period
     * @return list of rates
     */
    List<BtcUsdRate> findRateByPeriod(LocalDate startDate, LocalDate endDate);

    /**
     * RateRepository.createBtcUsdRate() persists a new exchange rate
     * in the storage
     *
     * @param btcUsdRate the entity to be stored
     */
    void createBtcUsdRate(BtcUsdRate btcUsdRate);
}
