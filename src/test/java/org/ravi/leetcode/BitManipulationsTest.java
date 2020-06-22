package org.ravi.leetcode;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BitManipulationsTest {
    public int[] countBits(int num) {
        int[] dp = new int[num + 1];
        dp[0] = 0;

        for (int i = 1; i <= num; i++) {
            if (i % 2 == 0)
                dp[i] = dp[i / 2];
            else
                dp[i] = dp[i / 2] + 1;
        }

        return dp;
    }

    @Test
    public void nineteen() {
        int[] ones = countBits(21);
        for (int i = 0; i < ones.length; i++) {
            System.out.printf("%2d: (%5s)=%d %n", i, Integer.toBinaryString(i), ones[i]);
        }
    }

    // lc=332912
    public int addWithIncrDecr(int l, int r) {
        int a = Math.max(l, r);
        int b = Math.min(l, r);
        if (b > 0) {
            while (b != 0) {
                a++;
                b--;
            }
        } else {
            while (b != 0) {
                a--;
                b++;
            }
        }
        return a;
    }

    //lc=84290
    public int addWithoutPlus(int a, int b) {
        return (b == 0) ? a : addWithoutPlus(a ^ b, (a & b) << 1);
    }

    @Test
    public void addWithoutPlusTest() {
        assertThat(addWithoutPlus(4, 5))
                .as("4, 5")
                .isEqualTo(9);
        assertThat(addWithoutPlus(-2, 3))
                .as("-2,3")
                .isEqualTo(1);
        assertThat(addWithoutPlus(3, -2))
                .as("3,-2")
                .isEqualTo(1);


        assertThat(addWithoutPlus(1, -1))
                .as("1, -1")
                .isEqualTo(0);
        assertThat(addWithoutPlus(-1, 1))
                .as("-1, 1")
                .isEqualTo(0);
        assertThat(addWithoutPlus(5, 5))
                .as("5,5")
                .isEqualTo(10);
    }

    // lc = hamming-distance
    public int hammingDistance(int x, int y) {
        if ( (x < 0) || (y < 0)) {
            return -1;
        }
        int result = 0;
        while ( (x > 0) || (y > 0)) {
            result += (x % 2 ) ^ (y % 2);
            x >>= 1;
            y >>= 1;
        }

        return result;
    }

    @Test
    public void hammingOneFour() {
        assertThat(hammingDistance(1, 4))
                .isEqualTo(2);
    }
}
