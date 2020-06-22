package org.ravi.ivquiz.others;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You have d dice, and each die has f faces numbered 1, 2, ..., f.
 * <p>
 * Return the number of possible ways (out of fd total ways) modulo 10^9 + 7 to roll the dice so the sum of the face up numbers equals target
 */
// https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
public class DynamicDiceProblem {
    private static final int MOD = 1_000_000_007;
    Map<String, Integer> dp = new HashMap<>();
    private int computes;

    void computed() {
        computes++;
    }

    String mkKey(int dice, int target) {
        return dice + "." + target;
    }

    public int numRolls(int dice, int faces, int target) {
        if (dice == 0 && target == 0) {
            computed();
            return 1;
        }
        if (dice == 0 || target == 0) {
            computed();
            return 0;
        }
        String key = mkKey(dice, target);
        if (dp.containsKey(key)) {
            return dp.get(key);
        }
        int res = 0;
        for (int i = 1; i <= faces; i++) {
            if (target >= i) {
                res = (res + numRolls(dice - 1, faces, target - i)) % MOD;
                computed();
            } else {
                break;
            }
        }
        dp.put(key, res);
        return res;
    }

    private void doCheck(int dice, int faces, int target, int exp) {
        String msg = String.format("dice=%d faces=%d target=%d exp=%d", dice, faces, target, exp);

        try {
            assertThat(numRolls(dice, faces, target))
                    .as(msg)
                    .isEqualTo(exp);
        } finally {
            System.out.printf("%s(%s): Number of computations=%d, sizeOfMap=%d %n",
                    Thread.currentThread().getStackTrace()[2].getMethodName(), msg, computes, dp.size());
            if (dp.size() < 40) {
                dp.entrySet()
                        .forEach((e) -> System.out.printf("[%s] = :%s: ", e.getKey(), e.getValue()));
                System.out.printf("%n");
            }
        }
    }

    @Test
    public void threeWithOneDie6() {
        doCheck(1, 6, 3, 1);
    }

    @Test
    public void sevenWithTwoDice6() {
        doCheck(2, 6, 7, 6);
    }

    @Test
    public void tenWithTwoDice5() {
        doCheck(2, 5, 10, 1);
    }

    @Test
    public void threeWithOneDie2() {
        doCheck(1, 2, 3, 0);
    }

    @Test
    public void fiveHundredWithThrityDice30() {
        doCheck(30, 30, 500, 222616187);
    }
}
