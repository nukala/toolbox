package org.ravi.udemy.jdk8;

import org.ravi.udemy.dsa.WorthLooking;

import java.util.function.Consumer;

public class LambdaVariable2 {
    @WorthLooking("instance variable or statics can be modified ")
    int value = 4;

    public static void main(String[] args) {
        LambdaVariable2 lv2 = new LambdaVariable2();

        lv2.call();
        lv2.modifyAndPrint();
    }

    public void modifyAndPrint() {
        value = 9;
        System.out.println("new value=" + value);
        call();
    }

    public void call() {
        Consumer<Integer> c1 = (i) -> {
            value++;
            System.out.println("after inc, sum(" + i + ")=" + (i + value));
        };

        c1.accept(1);
    }
}
