package searchmetrics;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.quarkus.scheduler.Scheduled;
import io.quarkus.scheduler.ScheduledExecution;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 * BtcUsdFetcher is a scheduled job that fetches the last btc - usd
 * xchange rate and records it using the rateService
 */
@ApplicationScoped
public class BtcUsdFetcher {

    @Inject
    @RestClient
    XChangeRateRestClient restClient;

    @Scheduled(cron = "{cron.expr}")
    void cronJob(ScheduledExecution execution) {
        System.out.println(restClient.fetchRateBySymbol("BTC-USD"));
        System.out.println(execution.getScheduledFireTime());
    }

}
