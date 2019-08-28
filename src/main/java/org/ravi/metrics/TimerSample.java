package org.ravi.metrics;

import com.codahale.metrics.Meter;
import com.codahale.metrics.Timer;

public class TimerSample extends AbstractSample {
    private final Timer timer;

    private Timer.Context context;
    private final Meter meter;

    protected TimerSample() {
        super(20);

        this.timer = registry.timer(getClass().getSimpleName());
        meter = registry.meter("timer");
    }

    public static void main(String... args) {
        TimerSample sample = new TimerSample();

        sample.simulateWork();
        sample.showReport();
    }

    @Override
    public void bootUp(WorkContext workContext) {
        context = timer.time();
        meter.mark();
    }

    @Override
    public void shutDown(WorkContext workContext) {
        context.stop();
        context = null;
    }
}
