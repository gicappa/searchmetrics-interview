package searchmetrics.domain;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

import static java.time.ZoneOffset.UTC;
import static java.time.format.DateTimeFormatter.ISO_INSTANT;

/**
 * BtcUsdRate is the data model of the application.
 * It holds the rate of the exchange BTC-USD and its timestamp.
 */
public class BtcUsdRate {

    private final double btc;
    private final double usd;
    private final LocalDateTime timestamp;

    /**
     * Construct a new Rate
     *
     * @param btc the btc value
     * @param usd the usd value
     * @param timestamp the timestamp when it happens
     */
    public BtcUsdRate(double btc, double usd, LocalDateTime timestamp) {
        this.btc = btc;
        this.usd = usd;
        this.timestamp = timestamp;
    }

    /**
     * @return the btc value
     */
    public double getBTC() {
        return btc;
    }

    /**
     * @return the usd value
     */
    public double getUSD() {
        return usd;
    }

    /**
     * @return the timestamp in formatted in ISO-8601 format
     */
    public String getTimestamp() {
        return ISO_INSTANT.format(timestamp.toInstant(UTC));
    }

    /**
     * @return the timestamp in Instant object
     */
    public Instant getInstant() {
        return timestamp.toInstant(UTC);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BtcUsdRate that = (BtcUsdRate) o;
        return Double.compare(that.btc, btc) == 0 && Double.compare(that.usd, usd) == 0 && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(btc, usd, timestamp);
    }

    @Override
    public String toString() {
        return "BtcUsdRate{" +
            "btc=" + btc +
            ", usd=" + usd +
            ", timestamp=" + timestamp +
            '}';
    }
}
