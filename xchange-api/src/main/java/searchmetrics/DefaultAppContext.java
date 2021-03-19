package searchmetrics;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import searchmetrics.domain.RateRepository;
import searchmetrics.domain.RateService;
import searchmetrics.infrastructure.InMemoryRateRepository;

@ApplicationScoped
public class DefaultAppContext implements AppContext {
    private final RateService rateService;
    private final RateRepository rateRepository;

    public DefaultAppContext() {
        this.rateRepository = new InMemoryRateRepository();
        this.rateService = new RateService(rateRepository);
    }

    @Produces
    public RateService getRateService() {
        return rateService;
    }

    @Produces
    public RateRepository getRateRepository() {
        return rateRepository;
    }
}
