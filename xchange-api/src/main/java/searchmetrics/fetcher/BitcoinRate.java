package searchmetrics.fetcher;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * BitcoinRate is the entity that will hold the data fetched by the ticker
 * <p>
 * The data format is the following:
 * <pre>
 * {"symbol":"BTC-USD","price_24h":58600.0,"volume_24h":318.27966087,"last_trade_price":56391.2}
 * </pre>
 *
 * Only two data are saved in this entity:
 * symbol and last_trade_price
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitcoinRate {

    @JsonProperty("symbol")
    public final String symbol;

    @JsonProperty("last_trade_price")
    public final String lastTradePrice;

    public BitcoinRate(String symbol, String lastTradePrice) {
        this.symbol = symbol;
        this.lastTradePrice = lastTradePrice;
    }

    @Override
    public String toString() {
        return "BitcoinRate{" +
            "symbol='" + symbol + '\'' +
            ", lastTradePrice='" + lastTradePrice + '\'' +
            '}';
    }

}
