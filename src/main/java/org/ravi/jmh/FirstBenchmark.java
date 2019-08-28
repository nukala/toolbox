package org.ravi.jmh;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;


@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 2, time = 1)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Threads(2)
@State(Scope.Thread)
public class FirstBenchmark {

    // do not convert this into a test. It will slow down builds.
    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder()
                .include(FirstBenchmark.class.getSimpleName())
                .build())
                .run();
    }

    @Benchmark
    public String firstBenchmark() {
        int dec = 123456789;

        return Integer.toBinaryString(dec);
    }

    @SuppressWarnings("squid:S1186") // intentionally empty
    private void shortNap() {
        Uninterruptibles.sleepUninterruptibly(1L, TimeUnit.NANOSECONDS);
    }

    @Benchmark
    public void shortNapBenchmark() {
        shortNap();

        firstBenchmark();
    }

    @Benchmark
    public void shortNapNothing() {
        shortNap();

        nothing();
    }

    @SuppressWarnings("squid:S1186") // intentionally empty
    @Benchmark
    public void nothing() {

    }
}
