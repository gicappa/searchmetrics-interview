package searchmetrics;

import java.time.LocalDateTime;

public class Scenario {
    static BtcUsdRate rate20210314() {
        var ts20210314 = LocalDateTime.of(2021, 3, 14, 21, 9, 0);
        return new BtcUsdRate(1, 60000.15, ts20210314);
    }

    static BtcUsdRate rate20210301() {
        var ts20210318 = LocalDateTime.of(2021, 3, 1, 11, 18, 32);
        return new BtcUsdRate(1, 60001.21, ts20210318);
    }

}
