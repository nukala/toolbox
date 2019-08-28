package org.ravi.rutils;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class FooTest {
    private static void log(String format, Object... args) {
        String theFormat = String.format("%s (%s) - %s", new Date(), Thread.currentThread(), format);
        System.err.printf(theFormat, args);
    }

    public static void main(String args[]) throws Exception {
        final AtomicInteger counter = new AtomicInteger(0);

        log("Started program %n");
        Callable<Runnable> c = () -> () -> {
            counter.getAndIncrement();
            log("hi %n");
        };

        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<?> f = es.submit(c);

        c.call().run();
        f.get();
        int napMillis = 850;
        sleepUninterruptibly(napMillis, MILLISECONDS);
        es.shutdown();
        log("Ending after nap(%d), counter=%s%n", napMillis, counter);
    }
}
