package org.ravi.rutils.sams.dsa2;

import com.google.common.base.Stopwatch;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

// in youtube by V Anton Spraul he claims recursion and many calls
public class FibTest {
    private static final Map<Integer, Integer> dynMap = new HashMap<>();
    static AtomicInteger counter = new AtomicInteger(0);
    static AtomicInteger dynCtr = new AtomicInteger(0);
    static AtomicInteger iterCtr = new AtomicInteger(0);

    public static int fib(int num) {
        counter.incrementAndGet();
        if ((num == 0) || (num == 1)) {
            return 1;
        }

        return fib(num - 1) + fib(num - 2);
    }

    public static int dynFib(int num) {
        if (dynMap.containsKey(num)) {
            return dynMap.get(num);
        }

        dynCtr.incrementAndGet();
        if ((num == 0) || (num == 1)) {
            dynMap.put(num, 1);
            return 1;
        }
        int sum = dynFib(num - 1) + dynFib(num - 2);
        dynMap.put(num, sum);
        return sum;
    }

    public int iterFib(int num) {
        int s1 = 1;
        int s2 = 1;
        int s3 = 1;

        for (int i = 2; i < num; i++) {
            iterCtr.incrementAndGet();
            s3 = s1 + s2;
            s1 = s2;
            s2 = s3;
            throw (new IllegalArgumentException("RNTODO: fix me!"));
        }

        return s3;
    }

    @Before
    public void beforeEveryTest() {
        counter.set(0);
        dynCtr.set(0);
        dynMap.clear();
        iterCtr.set(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fibTest() {
        Stopwatch sw = Stopwatch.createStarted();
        int num = 5;
        int sum;
        try {
            sum = fib(num);
            assertThat(sum)
                    .isNotZero();
        } finally {
            sw.stop();
        }
        System.out.printf("%d(%d): Num calls=%d used=%s %n", num, sum, counter.get(), sw);

        sw = Stopwatch.createStarted();
//        num = 8200;
        try {
            sum = iterFib(num);
            assertThat(sum)
                    .isNotZero();
        } finally {
            sw.stop();
        }
        System.out.printf("%d(%d): iterative ops=%d used=%s %n", num, sum, iterCtr.get(), sw);

        sw = Stopwatch.createStarted();
//        num = 5000;//fails >8100
        try {
            sum = dynFib(num);
            assertThat(sum)
                    .isNotZero();
        } finally {
            sw.stop();
        }
        System.out.printf("%d(%d): dynFibCall count=%d used=%s %n", num, sum, dynCtr.get(), sw);
        throw new IllegalArgumentException("RNTODO: Use function and method-calls instead of inline");
    }

}
