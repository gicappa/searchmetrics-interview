package searchmetrics;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RateService {
    private final RateRepository rateRepository;

    public RateService(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    public BtcUsdRate getLatestRate() {
        rateRepository.getLatest();
        return null;
    }
}
