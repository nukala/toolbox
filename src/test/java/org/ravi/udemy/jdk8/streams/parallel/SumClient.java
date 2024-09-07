package org.ravi.udemy.jdk8.streams.parallel;

// FROM https://github.com/dilipsundarraj1/java-8

import java.util.stream.IntStream;

public class SumClient {

    public static void main(String[] args) {

        Sum sum = new Sum();

        IntStream.rangeClosed(1, 1000)
                .parallel()
                .forEach(sum::performSum); //result is : 500500

        System.out.println(sum.getTotal());
    }
}
