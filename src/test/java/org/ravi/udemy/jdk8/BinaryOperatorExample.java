package org.ravi.udemy.jdk8;

import org.ravi.udemy.dsa.WorthLooking;

import java.util.Comparator;
import java.util.function.BinaryOperator;

public class BinaryOperatorExample {
    @WorthLooking("use if input type == output type")
    static BinaryOperator<Integer> mult = (a, b) -> a * b;

    public static void main(String[] args) {
        System.out.println("mult(3*5)=" + mult.apply(3, 5));

        Comparator<Integer> comp = (l, r) -> l.compareTo(r);
        System.out.println("maxBy=" + BinaryOperator.maxBy(comp).apply(4, 5));

        System.out.println("minBy=" + BinaryOperator.minBy(comp).apply(4, 5));
    }
}
