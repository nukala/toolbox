package org.ravi.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Thread)
@BenchmarkMode(Mode.SingleShotTime)
@Measurement(batchSize = 100000, iterations = 20)
@Warmup(batchSize = 100000, iterations = 10)
@Fork(5)
//http://www.pellegrino.link/2015/08/22/string-concatenation-with-java-8.html
public class StringConcatenationBenchmark {
    private String string;
    private String stringConcat;
    private StringBuilder stringBuilder;
    private StringBuffer stringBuffer;

    // do not convert this into a test. It will slow down builds.
    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder()
                .include(StringConcatenationBenchmark.class.getSimpleName())
                .build())
                .run();
    }

    @Setup(Level.Iteration)
    public void setup() {
        string = "";
        stringConcat = "";
        stringBuilder = new StringBuilder();
        stringBuffer = new StringBuffer();
    }

    @Benchmark
    public void stringConcatenation() {
        string += "some more data";
    }

    @Benchmark
    public void stringConcatConcatenation() {
        stringConcat = stringConcat.concat("some more data");
    }

    @Benchmark
    public void stringBuilderConcatenation() {
        stringBuilder.append("some more data");
    }

    @Benchmark
    public void stringBufferConcatenation() {
        stringBuffer.append("some more data");
    }
}
