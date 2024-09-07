package org.ravi.udemy.jdk8;

import org.ravi.udemy.dsa.WorthLooking;

import java.util.function.Consumer;

public class LambdaVariable1 {
    public static void main(String[] args) {
        int outer = 1;

        @WorthLooking("variable name conflict, local variables cannot be altered!")
        Consumer<Integer> c1 = (i1) -> {
            System.out.println("Value is " + i1 + ", while outer=" + outer);

            //outer = 4;
        };
        c1.accept(8);
    }
}
