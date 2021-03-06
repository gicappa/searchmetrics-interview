package searchmetrics;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;

import searchmetrics.domain.RateRepository;
import searchmetrics.domain.RateService;
import searchmetrics.storage.FakeRateRepository;

/**
 * AppContext is building and providing the objects
 * to be used by the application.
 *
 * This is the default test appContext
 */
@Alternative
@Priority(1)
@ApplicationScoped
public class TestAppContext {

    private final RateService rateService;
    private final RateRepository rateRepository;

    public TestAppContext() {
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
