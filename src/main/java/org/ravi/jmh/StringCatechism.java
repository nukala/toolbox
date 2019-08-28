package org.ravi.jmh;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Catchetism - summarization of sacred truths ...
 * <p/>
 *
 * IntelliJ: search and add JMH plugin.
 * <p/>
 * BUILD:
 * <pre>
 *  <dependency>
 *   <groupId>org.openjdk.jmh</groupId>
 *   <artifactId>jmh-core</artifactId>
 *   <version>LATEST</version>
 *  </dependency>
 *  <dependency>
 *   <groupId>org.openjdk.jmh</groupId>
 *   <artifactId>jmh-generator-annprocess</artifactId>
 *   <version>LATEST</version>
 *  </dependency>
 * </pre>
 **/


/*
* Inspired by:
*
* https://shipilev.net/talks/joker-Oct2014-string-catechism.pdf
*    AND
* http://tutorials.jenkov.com/java-performance/jmh.html
*    AND
* stackoverflow and others
*/


// warmup once for 2 seconds/each
@Warmup(iterations = 1, time = 2000, timeUnit = TimeUnit.MILLISECONDS)
// measure two times for 10 seconds/each
@Measurement(iterations = 2, time = 10000, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Threads(1)
//@Fork(1)
@BenchmarkMode({Mode.AverageTime})

// Tell JVM to optimize
@Fork(value = 1, jvmArgsAppend = "-XX:+OptimizeStringConcat")
// hint to JIT for inlining
@CompilerControl(CompilerControl.Mode.INLINE)
public class StringCatechism {
    @Benchmark
    public void concatSix(ParamHolder params, Blackhole blackhole) {
        String answer = params.getHello() + params.getWorld()
                + params.getDash() + params.getFrom()
                + params.getFremont() + params.getCali();
        blackhole.consume(answer);
    }

    @Benchmark
    public void sbSix(ParamHolder params, Blackhole blackhole) {
        String answer = new StringBuilder()
                .append(params.getHello()).append(params.getWorld())
                .append(params.getDash()).append(params.getFrom())
                .append(params.getFremont()).append(params.getCali())
                .toString();
        blackhole.consume(answer);
    }

    @Benchmark
    public void concatRandomWords(ParamHolder params, Blackhole blackhole) {
        String answer = params.get() + params.get()
                + params.get() + params.get()
                + params.get() + params.get();
        blackhole.consume(answer);
    }

    @Benchmark
    public void sbRandomWords(ParamHolder params, Blackhole blackhole) {
        String answer = new StringBuilder(256)
                .append(params.get()).append(params.get())
                .append(params.get()).append(params.get())
                .append(params.get()).append(params.get())
                .toString();
        blackhole.consume(answer);
    }

    /** Pass state to the test to prevent compiler from optimizing out ... */
    @State(Scope.Thread)
    public static class ParamHolder {
        private String hello = "Hello ";
        private String world = "World ";
        private String dash = "- ";
        private String from = "from ";
        private String fremont = "Fremont ";
        private String cali = "California !! ";
        private List<String> words = Lists.newArrayList(hello, world, dash, from, fremont, cali);

        public String get() {
            return words.get(RandomUtils.nextInt(0, 6));
        }

        public String getHello() {
            return hello;
        }

        public String getWorld() {
            return world;
        }

        public String getDash() {
            return dash;
        }

        public String getFrom() {
            return from;
        }

        public String getFremont() {
            return fremont;
        }

        public String getCali() {
            return cali;
        }
    }
}
