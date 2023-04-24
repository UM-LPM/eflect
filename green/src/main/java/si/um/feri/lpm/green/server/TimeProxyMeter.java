package si.um.feri.lpm.green.server;

import java.time.Duration;
import java.time.Instant;

/*
  A meter which uses the execution time as a proxy, i.e. longer execution time means more energy was consumed.

  This is meant for testing on machines without RAPL.
*/

public class TimeProxyMeter implements Meter {

    double factor;

    public TimeProxyMeter(double factor) {
        this.factor = factor;
    }

    @Override
    public double measureEnergy(Runnable runnable) {
        Instant start = Instant.now();
        runnable.run();
        Instant end = Instant.now();
        return factor * Duration.between(start, end).toMillis();
    }
}
