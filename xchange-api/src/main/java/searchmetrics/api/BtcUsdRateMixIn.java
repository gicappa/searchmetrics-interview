package searchmetrics.api;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public interface BtcUsdRateMixIn {
    @JsonProperty("btc")
    double getBTC();

    @JsonProperty("usd")
    double getUSD();

    @JsonProperty("timestamp")
    String getTimestamp();

    @JsonIgnore
    Instant getInstant();

}
