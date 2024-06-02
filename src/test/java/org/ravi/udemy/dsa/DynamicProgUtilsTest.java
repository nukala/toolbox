package org.ravi.udemy.dsa;

import org.junit.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

//0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233...
//https://repl.it/@rnukala/Fib-Dynamic-Programming
public class DynamicProgUtilsTest {
    private void doCheck(Function<Integer, Integer> function, int param, int expected) {
        int actual = function.apply(param);
        String message = String.format("f(%d) = [%d] expected=%d", param, actual, expected);
        System.out.printf("%s%n", message);
        assertThat(actual)
                .as(message)
                .isEqualTo(expected);
    }

    private void verifyMany(Function<Integer, Integer> function) {
        doCheck(function, 0, 1);
        doCheck(function, 1, 1);
        doCheck(function, 2, 1);

        doCheck(function, 5, 5);

        doCheck(function, 14, 377);
        doCheck(function, 24, 46368);

        doCheck(function, 46, 1836311903);
    }

    @Test
    public void bottomUpFibonacciTest() {
        verifyMany(DynamicProgUtils::fibonacciBottomUp);
    }
}
