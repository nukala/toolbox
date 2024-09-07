package org.ravi.udemy.jdk8.streams.numeric;

import java.util.OptionalInt;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

// lab52
public class NumericStreamRangesExample {
    public static void main(String[] args) {
        int max = 10;
        Supplier<IntStream> excludedSupplier = () -> IntStream.range(1, max);
        System.out.println("excluded count=" + excludedSupplier.get().count());
        excludedSupplier.get().forEach((i) -> System.out.print(i + ", "));
        System.out.println();
        System.out.println("max excluded=" + excludedSupplier.get().max());
        System.out.println("====");

        Supplier<IntStream> everythingSupplier = () -> IntStream.rangeClosed(1, max);
        System.out.println("everything count=" + everythingSupplier.get().count());
        everythingSupplier.get().forEach((i) -> System.out.print(i + ", "));
        System.out.println();
        System.out.println("min Everything=" + everythingSupplier.get().min());
        System.out.println("====");

        // @WorthLooking("DoubleStream has no ranges ... hence")
        Supplier<DoubleStream> dsSupplier = () -> IntStream.rangeClosed(1, 9).asDoubleStream();
        System.out.println("ds count=" + dsSupplier.get().count());
        dsSupplier.get().forEach((d) -> System.out.print(d + ", "));
        System.out.println();
        System.out.println("====");

        Supplier<IntStream> emptyIS = () -> IntStream.range(1, 1);
        System.out.println("emptyIS count=" + emptyIS.get().count());
        emptyIS.get().forEach((i) -> System.out.print(i + ", "));
        System.out.println();
        OptionalInt maxOpt = emptyIS.get().max();
        System.out.println("emptyIS maxOpt=" +
                (maxOpt.isPresent() ? maxOpt.getAsInt() : "EMPTY"));
        System.out.println("====");
    }
}
