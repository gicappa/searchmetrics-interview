package searchmetrics;

import javax.enterprise.context.ApplicationScoped;

/**
 * RateService has the responsibility of decoupling the APIs
 * layer from the one managing the business logic.
 * <p>
 * It adapts the input and output if necessary and orchestrates
 * different services when necessary.
 */
@ApplicationScoped
public class RateService {
    private final RateRepository rateRepository;

    public RateService(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    /**
     * @return the latest rate from the persistent repository
     */
    public BtcUsdRate getLatestRate() {
        return rateRepository.getLatest();
    }
}
