package searchmetrics;

import searchmetrics.domain.RateRepository;
import searchmetrics.domain.RateService;

/**
 * AppContext is building and providing the objects
 * to be used by the application.
 */
public interface AppContext {

    /**
     * @return the RateService object to give command
     * from the input sides and get the rates data out
     * of it
     */
    RateService getRateService();

    /***
     * @return the RateRepository to interact with rates data
     */
    RateRepository getRateRepository();
}
