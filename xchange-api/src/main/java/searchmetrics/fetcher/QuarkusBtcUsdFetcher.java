package searchmetrics.fetcher;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.quarkus.scheduler.Scheduled;
import io.quarkus.scheduler.ScheduledExecution;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import searchmetrics.domain.RateService;

/**
 * QuarkusBtcUsdFetcher is a scheduled job that fetches the last btc - usd
 * xchange rate and records it using the rateService
 */
@ApplicationScoped
public class QuarkusBtcUsdFetcher {

    private static final Logger LOG = Logger.getLogger(QuarkusBtcUsdFetcher.class);

    @Inject
    @RestClient
    XChangeRateRestClient restClient;

    @Inject
    RateService rateService;

    /**
     * QuarkusBtcUsdFetcher.onTimeout() is call on scheduled time
     * following the {cron.expr} scheduling.
     * <p>
     * When the method is called the rest client fetches from a
     * bitcoin RESTful service.
     *
     * @param execution contains the execution data of the schedule
     */
    @Scheduled(cron = "{cron.expr}")
    void onTimeout(ScheduledExecution execution) {
        final var bitcoinRate = restClient.fetchRateBySymbol("BTC-USD");

        rateService.updateBtcUsdRate(bitcoinRate.lastTradePrice, execution.getFireTime());

        LOG.info(bitcoinRate);
    }

}
