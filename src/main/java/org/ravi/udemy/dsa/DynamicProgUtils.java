package org.ravi.udemy.dsa;

import java.util.HashMap;
import java.util.Map;

/**
 * Placeholder for dynamic programming utilities.
 */
public class DynamicProgUtils {

    // https://www.udemy.com/course/master-the-coding-interview-data-structures-algorithms/learn/lecture/12409090#overview
    // TODO: implement me
    public static void houseRobber() {
        throw new IllegalStateException("TODO: implement houseRobber DP");
    }
    // TODO: implement me
    public static void buyAndSellStock() {
        throw new IllegalStateException("TODO: implement buyAndSellStock DP");
    }
    public static void climbingStairs() {
        throw new IllegalStateException("TODO: implement climbingStairs DP");
    }
    // https://www.udemy.com/course/master-the-coding-interview-data-structures-algorithms/learn/lecture/12409090#overview
    // TODO: implement me
    public static int fibonacci(int n) {
        throw new IllegalStateException("TODO: implement fibonacci DP");
    }

    // TODO: implement me
    public static void factorial(int n) {
        throw new IllegalStateException("TODO: implement factorial DP");
    }

    public static int fibonacciBottomUp(int n) {
        Map<Integer, Integer> memo = new HashMap<>(n);

        for (int i = 0; i <= n; i++) {
            if (i <= 2) {
                memo.put(i, 1);
            } else {
                int tmp = memo.get(i - 1) + memo.get(i - 2);
                memo.put(i, tmp);
            }
        }

        return memo.get(n);
    }
}
