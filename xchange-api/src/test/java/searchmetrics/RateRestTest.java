package searchmetrics;

import org.assertj.core.internal.IterableElementComparisonStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateRestTest {

    @Test
    @DisplayName("It answers with a status code 200")
    void it_returns_200() {
        var rateResource = new RateResource();

        var actual = rateResource.getLatest();

        assertThat(actual.getStatus()).isEqualTo(200);
    }
}
