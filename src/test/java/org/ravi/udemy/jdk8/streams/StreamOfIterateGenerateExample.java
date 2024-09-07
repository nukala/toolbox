package org.ravi.udemy.jdk8.streams;

import org.ravi.udemy.dsa.WorthLooking;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

// lab50
public class StreamOfIterateGenerateExample {
    public static void main(String[] args) {
        Stream.of("a", "b", "c", "e", "a")
                .forEach(System.out::println);

        // infinite iteration uses a UnaryOperator
        Stream.iterate(1, x -> x * 2)
                .limit(4)
                .forEach(System.out::println);

        @WorthLooking("using `new Random` creates many many objects!")
        Random rand = new Random();
        Supplier<Integer> intSupplier = rand::nextInt;
        Stream.generate(intSupplier)
                .limit(4)
                .forEach(System.out::println);
    }
}
