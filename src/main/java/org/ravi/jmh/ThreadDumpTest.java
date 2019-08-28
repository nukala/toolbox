package org.ravi.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.util.Map;
import java.util.concurrent.TimeUnit;

// http://java-performance.info/introduction-jmh-profilers/

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 10, time = 1)
@Threads(2)
@Fork(1)
@State(Scope.Thread)
public class ThreadDumpTest {
    private static final long SLEEP_MILLIS = 10;

    boolean lockedMonitors = false;
    boolean lockedSynchronizers = false;

    double val = Math.PI;

    // do not convert this into a test. It will slow down builds.
    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder()
                .include(ThreadDumpTest.class.getSimpleName())
                .build())
                .run();
    }

    @Benchmark
    public double testSine() {
        return Math.sin(val);
    }

    @Group("jmh")
    @GroupThreads
    @Benchmark
    public ThreadInfo[] testJmhWay() throws InterruptedException {
        Thread.sleep(SLEEP_MILLIS);
        return ManagementFactory.getThreadMXBean().dumpAllThreads(lockedMonitors, lockedSynchronizers);
    }

    @Group("jmh")
    @GroupThreads
    @Benchmark
    public double sine1() {
        return Math.sin(val);
    }

    @Group("jdk")
    @GroupThreads
    @Benchmark
    public Map<Thread, StackTraceElement[]> testCoreWay() throws InterruptedException {
        Thread.sleep(SLEEP_MILLIS);
        return Thread.getAllStackTraces();
    }

    @Group("jdk")
    @GroupThreads
    @Benchmark
    public double sine2() {
        return Math.sin(val);
    }
}
