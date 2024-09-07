package org.ravi.udemy.jdk8.streams.parallel;

import com.google.common.base.Stopwatch;
import org.ravi.udemy.dsa.WorthLooking;

import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.IntStream;

// lab66
public class ParallelStreamExample {
    Supplier<IntStream> intStreamSupplier = () -> IntStream.rangeClosed(1, 110_000);

    public static void main(String[] args) {
        System.out.println("processors = " + Runtime.getRuntime().availableProcessors());
        ParallelStreamExample se = new ParallelStreamExample();

        int numLoops = 20_000;
        System.out.println("Sequential time consumed=" +
                timedInvokation(se::sequentialSum, numLoops));
        System.out.println("  Parallel time consumed=" +
                timedInvokation(se::parallelSum, numLoops));
    }

    @WorthLooking("summation methods are supplier ( no parameters-> return something)" +
            "   NOT A FUNCTION - no paramter!")
    public static String timedInvokation(Supplier<?> supplier, int numLoops) {
        @WorthLooking("dont use ranges -- they mess up number!!")
        Stopwatch sw = Stopwatch.createStarted();
        for (int i = 0; i < numLoops; i++) {
            supplier.get();
        }

        return sw.stop().elapsed(TimeUnit.MILLISECONDS) + " ms.";
    }

    public int sequentialSum() {
        return intStreamSupplier.get()
                .sum();
    }

    public int parallelSum() {
        return intStreamSupplier.get()
                .parallel()
                .sum();
    }
}
