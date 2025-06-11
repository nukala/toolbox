package org.ravi.udemy.jdk8;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

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

        // compilation fails, why?
        // Function<String, Boolean> toLength = String::length > 3;

        // donot_commit -- cleanup first!
        System.out.println("====");
        Function<String, Boolean> isEmptyFunc = s -> s != null && s.isEmpty();
        String str = "hello";
        System.out.printf("str=[%s], empty=[%b]%n", str, isEmptyFunc.apply(str));
        str = null;
        System.out.printf("str=[%s], empty=[%b]%n", str, isEmptyFunc.apply(str));
        str = "";
        System.out.printf("str=[%s], empty=[%b]%n", str, isEmptyFunc.apply(str));

        // RNTODO - does not work at this time! demoTrimNotEmptyNotNull();
    }

    static void demoTrimNotEmptyNotNull() {
        System.out.println("====");
        Predicate<String> notNull = Objects::nonNull;
        Predicate<String> notEmpty = String::isEmpty;
        Predicate<String> trimNotEmpty = s -> s.trim().isEmpty();
        Predicate<String> trimmedEmptyCheck = notNull.and(notEmpty).and(trimNotEmpty);
        String str = "hello";
        System.out.printf("str=[%s], trimmedEmptyCheck=[%b]%n", str, trimmedEmptyCheck.test(str));
        str = null;
        System.out.printf("str=[%s], trimmedEmptyCheck=[%b]%n", str, trimmedEmptyCheck.test(str));
        str = "";
        System.out.printf("str=[%s], trimmedEmptyCheck=[%b]%n", str, trimmedEmptyCheck.test(str));
        str = "   ";
        System.out.printf("str=[%s], trimmedEmptyCheck=[%b]%n", str, trimmedEmptyCheck.test(str));
    }
}
