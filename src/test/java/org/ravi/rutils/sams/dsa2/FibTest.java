package org.ravi.rutils.sams.dsa2;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.*;

// in youtube by V Anton Spraul he claims recursion and many calls
public class FibTest {
    static AtomicInteger counter = new AtomicInteger(0);
    public static int fib(int num) {
        counter.incrementAndGet();
        if ((num == 0) || (num == 1)) {
            return 1;
        }

        return fib(num - 1) + fib(num - 2);
    }

    @Test
    public void fibTest() {
        assertThat(fib(10))
//                .isEqualTo(13)
                .isGreaterThan(0)
        ;
        System.out.printf("Num calls=%d %n", counter.get());
        counter.set(0);
    }

}
