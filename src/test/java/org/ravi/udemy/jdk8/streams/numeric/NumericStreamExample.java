package org.ravi.udemy.jdk8.streams.numeric;

import org.ravi.udemy.dsa.WorthLooking;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

// lab51
public class NumericStreamExample {

    static int sumOfList(List<Integer> integerList) {
        return integerList.stream()
                .reduce(0, Integer::sum);
    }

    @WorthLooking("reduce effort of unboxing")
    static int sumOfIntStream(int max) {
        //@WorthLooking("includes the last element with Closed")
        return IntStream.rangeClosed(1, max)
                .sum();
    }

    public static void main(String[] args) {
        System.out.println("sum of list=" + sumOfList(Arrays.asList(1, 2, 3, 4, 5, 6)));

        System.out.println("sum of self-built-intstream=" + sumOfIntStream(6));
    }
}
