package searchmetrics.fetcher;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.quarkus.scheduler.Scheduled;
import io.quarkus.scheduler.ScheduledExecution;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import searchmetrics.domain.RateService;

/**
 * BtcUsdFetcher is a scheduled job that fetches the last btc - usd
 * xchange rate and records it using the rateService
 */
@ApplicationScoped
public class QuarkusBtcUsdFetcher {

    @Inject
    @RestClient
    XChangeRateRestClient restClient;

    @Inject
    RateService rateService;

    @Scheduled(cron = "{cron.expr}")
    void onTimeout(ScheduledExecution execution) {
        final var bitcoinRate = restClient.fetchRateBySymbol("BTC-USD");

        rateService.updateBtcUsdRate(bitcoinRate.lastTradePrice, execution.getFireTime());

        System.out.println(bitcoinRate);
    }

}
