package searchmetrics;

import searchmetrics.domain.RateRepository;
import searchmetrics.domain.RateService;

public interface AppContext {
    RateService getRateService();

    RateRepository getRateRepository();
}
