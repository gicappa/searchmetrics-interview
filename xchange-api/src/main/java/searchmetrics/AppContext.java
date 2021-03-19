package searchmetrics;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import searchmetrics.domain.RateRepository;
import searchmetrics.domain.RateService;
import searchmetrics.infrastructure.FakeRateRepository;

@ApplicationScoped
public class AppContext {
    private final RateService rateService;
    private final RateRepository rateRepository;

    public AppContext() {
        this.rateRepository = new FakeRateRepository();
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
