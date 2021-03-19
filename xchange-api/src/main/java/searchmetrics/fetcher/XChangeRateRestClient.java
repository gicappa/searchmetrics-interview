package searchmetrics.fetcher;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

/**
 * FetchBtcUsdRestClient is an interface used by quarkus to create a
 * rest client to fetch bitcoin values over dollar.
 * <p>
 * curl -X GET "https://api.blockchain.com/v3/exchange/tickers/BTC-USD" -H  "accept: application/json"
 */
@Path("/v3")
@RegisterRestClient(configKey = "xchange-rest-client")
public interface XChangeRateRestClient {

    @GET
    @Path("exchange/tickers/{symbol}")
    BitcoinRate fetchRateBySymbol(@PathParam String symbol);
}
