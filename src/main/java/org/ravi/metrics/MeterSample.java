package org.ravi.metrics;

import com.codahale.metrics.Meter;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class MeterSample extends AbstractSample {
    private final Meter meter;


    protected MeterSample(int loops) {
        super(loops);
        meter = registry.meter("requests");
    }

    public static void main(String... args) {
        MeterSample sample = new MeterSample(12);
        sample.startReport();
        sample.simulateWork();
        sample.showReport();
    }

    @Override
    public void bootUp(WorkContext workContext) {
        meter.mark();
    }

    @Override
    public void shutDown(WorkContext workContext) {
        // nothing to do
    }

    public void startReport() {
        getReporter().start(60_000, MILLISECONDS);
    }
}
