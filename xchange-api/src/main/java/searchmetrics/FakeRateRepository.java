package searchmetrics;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;

@Default
@ApplicationScoped
public class FakeRateRepository implements RateRepository {
    @Override
    public void getLatest() {

    }
}
