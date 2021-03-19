package searchmetrics.api;

import javax.inject.Singleton;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.jackson.ObjectMapperCustomizer;
import searchmetrics.domain.BtcUsdRate;

@Singleton
public class ObjectMapperContextResolver implements ObjectMapperCustomizer {

    public void customize(ObjectMapper mapper) {
        mapper.addMixIn(BtcUsdRate.class, BtcUsdRateMixIn.class);
    }
}