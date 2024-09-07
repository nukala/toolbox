package org.ravi.udemy.jdk8.streams;

import org.ravi.udemy.dsa.WorthLooking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

// lab41 reduce operations
public class StreamReduceExample {
    @SuppressWarnings("all")
    // convert to getOrNull
    static Function<Optional<Integer>, String> optIntFunc = (oi) -> {
        if (oi.isPresent()) {
            return "PRESENT, value=" + oi.get();
        }
        return "present=" + oi.isPresent() + ", no value";
    };

    @WorthLooking("first a = identity, b is always element from stream\n" +
            "subsequent a = previous result")
    static int mult(List<Integer> list) {
        return list.stream() // stream of 2, 5, 7, 9
                // first a = identity, b is always element from stream
                // subsequent a = previous result
                .reduce(1, (a, b) -> a * b);
    }

    @WorthLooking("no intial value, hence returns Optional")
    static Optional<Integer> multNoIdentity(List<Integer> list) {
        return list.stream()
                .reduce((a, b) -> a * b);
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(2, 5, 7, 9);

        System.out.println("multiply = " + mult(integers));
        System.out.println("valid optional mult : " + optIntFunc.apply(multNoIdentity(integers)));
        System.out.println("optional mult with empty list : "
                + optIntFunc.apply(multNoIdentity(new ArrayList<>())));
    }
}
