package org.ravi.udemy.jdk8.defaults;

import org.ravi.udemy.dsa.WorthLooking;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

// lab 79 - default methods
public class DefaultMethodsExample {
    @WorthLooking("Each supplier invocation returns a new version!")
    static Supplier<List<String>> namesSupplier = () -> Arrays.asList("Adam", "Jenny", "Alex", "Mike", "Sophia", "Dan", "Zack", "Dave");
    public static void main(String[] args) {
        // prior art
        List<String> namesList = namesSupplier.get();
        Collections.sort(namesList);
        System.out.println("Old style with Collections.sort=" + namesList);

        namesList = namesSupplier.get();
        System.out.println("  After sorting another get=" + namesList);

        // using default impl
        namesList.sort(Comparator.naturalOrder());
        System.out.println("using default method List.sort=" + namesList);

        namesList = namesSupplier.get();
        namesList.sort(Comparator.reverseOrder());
        System.out.println("reverse order sort=" + namesList);
    }
}
