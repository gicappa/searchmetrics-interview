package searchmetrics;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import searchmetrics.domain.RateRepository;
import searchmetrics.domain.RateService;
import searchmetrics.storage.InMemoryRateRepository;

/**
 * AppContext is building and providing the objects
 * to be used by the application.
 *
 * This is the default production appContext
 */
@ApplicationScoped
public class DefaultAppContext implements AppContext {

    private final RateService rateService;
    private final RateRepository rateRepository;

    /**
     * Creating the application objects
     */
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
