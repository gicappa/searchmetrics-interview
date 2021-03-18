package searchmetrics;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.time.ZoneOffset.UTC;
import static java.time.format.DateTimeFormatter.ISO_INSTANT;

public class BtcUsdRate {
    private final double btc;
    private final double usd;
    private final LocalDateTime timestamp;

    public BtcUsdRate(double btc, double usd, LocalDateTime timestamp) {
        this.btc = btc;
        this.usd = usd;
        this.timestamp = timestamp;
    }

    public double getBTC() {
        return btc;
    }

    public double getUSD() {
        return usd;
    }

    public String getTimestamp() {
        return ISO_INSTANT.format(timestamp.toInstant(UTC));
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
