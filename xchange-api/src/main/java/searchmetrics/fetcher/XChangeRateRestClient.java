package searchmetrics.fetcher;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

/**
 * XChangeRateRestClient is an interface used by quarkus to create a
 * rest client to fetch bitcoin values over dollar.
 * <p>
 * curl -X GET "https://api.blockchain.com/v3/exchange/tickers/BTC-USD" -H  "accept: application/json"
 */
@Path("/v3")
@RegisterRestClient(configKey = "xchange-rest-client")
public interface XChangeRateRestClient {

    /**
     * XChangeRateRestClient.fetchRateBySymbol() is the client
     * of the bitcoin.com RESTful interface to get
     *
     * @param symbol the symbol we want to get info of
     * @return the BitcoinRate json object with the latest
     * bitcoin data
     */
    @GET
    @Path("exchange/tickers/{symbol}")
    BitcoinRate fetchRateBySymbol(@PathParam String symbol);
}
