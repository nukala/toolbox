package org.ravi.udemy.jdk8.method;

import org.ravi.udemy.dsa.WorthLooking;

import java.util.function.Function;

public class FunctionMethodReferenceExample {
    static Function<String, String> upperLambda = (s) -> s.toUpperCase();

    @WorthLooking("method reference when - direct reference-no-params?")
    static Function<String, String> upperReference = String::toUpperCase;

    public static void main(String[] args) {
        System.out.println("lambda = " + upperLambda.apply("hello"));

        System.out.println("reference = " + upperReference.apply("hello"));
    }
}
