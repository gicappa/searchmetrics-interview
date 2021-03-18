package searchmetrics;

/**
 * RateRepository has the responsibility to provide
 * data that are downloaded from the bitcoin rate provider
 */
public interface RateRepository {

    /**
     * @return the latest BTC-USD rate exchange collected
     */
    BtcUsdRate getLatest();
}
