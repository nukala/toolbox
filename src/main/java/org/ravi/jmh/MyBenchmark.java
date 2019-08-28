package org.ravi.jmh;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

/** to use in code
    public static void main(String[] args) throws RunnerException {
        int numThreads = Runtime.getRuntime().availableProcessors();
        Options opts = new OptionsBuilder()
                .include(Benchmark.class.getSimpleName())
                .warmupIterations(3)
                .measurementIterations(5)
                .forks(1)
                .timeUnit(TimeUnit.MILLISECONDS)
                .shouldDoGC(true)
                .mode(Mode.AverageTime)
                .threads(numThreads)
                .verbosity(VerboseMode.NORMAL)
                .build();

        new Runner(opts)
                .run();
        logger.warn("numThreads={}", numThreads);
    }
*/


@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 2, time = 1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Threads(2)
@State(Scope.Thread)
// nothing for verbosity
/** this does not seem to work */
public @interface MyBenchmark {

}
