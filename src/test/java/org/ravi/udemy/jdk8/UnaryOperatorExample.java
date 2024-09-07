package org.ravi.udemy.jdk8;

import org.ravi.udemy.dsa.WorthLooking;

import java.util.function.UnaryOperator;

public class UnaryOperatorExample {
    @WorthLooking("Use unary operator -- iff input and output are same type!")
    static UnaryOperator<String> uop = s -> s + " |Default";

    public static void main(String[] args) {
        System.out.println("result = " + uop.apply("java8"));
    }
}
