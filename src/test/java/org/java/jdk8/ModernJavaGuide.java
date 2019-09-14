package org.java.jdk8;

import org.junit.Test;

import java.time.Clock;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.assertThat;

// modern-java-a-guide-to-java8
public class ModernJavaGuide {
    @Test // not allowed to modify anywhere in the method
    public void lambdaScopeLocalVar() { //p9
        int num = 1;
        Converter<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num);
        assertThat(stringConverter.convert(2))
                .isEqualTo("3");

        // causes compiler error num = 99;
    }

    @Test //@WorthLooking("timeMillisVsClock: initialization takes ~30ms")
    public void timeMillisVsClock() { // p16
        Clock systemClock = Clock.systemDefaultZone();
        Clock utcClock = Clock.systemUTC();

        long now = System.currentTimeMillis();
        long sys = systemClock.millis();
        long utc = utcClock.millis();

        System.out.printf("now=%d clock=%d utc=%d %n", now, sys, utc);

        assertThat(now)
                .as("All millis are same if separated, else static-initialization cost")
                .isEqualTo(utc)
                .isEqualTo(sys);
    }

    @Test
    public void streamStringListWithJoiner() {//p21
        List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");

        StringJoiner joiner = new StringJoiner(",");
        myList
                .stream()
                .filter(s -> s.startsWith("c"))
                .sorted()
                .map(String::toUpperCase)
                .forEach(joiner::add);
        System.out.printf("%s: %s%n", myList, joiner.toString());
        assertThat(joiner.toString())
                .isEqualTo("C1,C2");
    }

    @FunctionalInterface
    interface Converter<F, T> { //p7
        T convert(F from);
    }
}
