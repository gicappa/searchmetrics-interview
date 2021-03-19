package searchmetrics;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import searchmetrics.api.BtcUsdRateMixIn;
import searchmetrics.domain.BtcUsdRate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("The ObjectMapper")
class ObjectMapperMarshallerTest {
    private ObjectWriter writer = null;

    @BeforeEach
    public void setUp() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setMixIns(ImmutableMap.of(BtcUsdRate.class, BtcUsdRateMixIn.class));
        writer = mapper.writer();
    }

    @Test
    @DisplayName("doesn't marshal the instant field")
    void it_does_not_marshal_the_instance_field() throws JsonProcessingException {
        var actual = writer.writeValueAsString(new BtcUsdRate(1, 3, dateTime()));

        assertThat(actual).isEqualTo(getJson());
    }

    private String getJson() {
        return "{\"btc\":1.0,\"usd\":3.0,\"timestamp\":\"2021-03-19T21:46:07.220Z\"}";
    }

    private LocalDateTime dateTime() {
        return ZonedDateTime.parse("2021-03-19T21:46:07.220Z").toLocalDateTime();
    }
}
