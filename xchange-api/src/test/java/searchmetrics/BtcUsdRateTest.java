package searchmetrics;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import searchmetrics.domain.BtcUsdRate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("The BtcUsdRate entity")
class BtcUsdRateTest {

    @Test
    @DisplayName("format the dates with the ISO-8603 format")
    void it_uses_the_iso8603_format() {

        var rate = new BtcUsdRate(1, 2, LocalDateTime.of(2021, 3, 18, 10, 34, 12));

        assertThat(rate.getTimestamp()).isEqualTo("2021-03-18T10:34:12Z");
    }
}