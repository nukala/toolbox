package org.ravi.udemy.jdk8;

import java.util.function.Function;

public class FunctionExample {
    static Function<String, String> upper = s -> ":" + s.toUpperCase() + ":";
    static Function<String, String> pipe = s -> "|" + s + "|default";
    static Function<String, String> lower = s ->
            "[" + s + "]".toLowerCase().concat("LOWER");

    public static void main(String[] args) {
        System.out.println("Just upper: " + upper.apply("java8"));
        System.out.println("Just pipe: " + pipe.apply("java8"));
        System.out.println("Just lower(JDK8): " + lower.apply("JDK8"));

        System.out.println("====");
        System.out.println("andThen executes in order upper.andThen(pipe):"
                + upper.andThen(pipe).apply("java8"));
        System.out.println("compose pipelinines parameter to prior upper.compose(pipe):" +
                upper.compose(pipe).apply("java8"));
        // compose is all the things after then before. This is wierd
        System.out.println("compose pipelinines prams to prior upper.compose(pipeDefault).andThen(lower):" +
                upper.compose(pipe).andThen(lower).apply("lower"));
    }
}
