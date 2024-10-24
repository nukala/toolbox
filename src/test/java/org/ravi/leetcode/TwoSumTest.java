package org.ravi.leetcode;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

// https://leetcode.com/problems/two-sum/description/
public class TwoSumTest {

    @Test
    public void ex1() {
        int[] output = LeetProblems.twoSum(new int[]{2, 7, 11, 15}, 9);
        assertThat(output).containsExactlyInAnyOrder(0, 1);
    }

    @Test
    public void ex2() {
        int[] output = LeetProblems.twoSum(new int[]{3, 2, 4}, 6);
        assertThat(output).containsExactlyInAnyOrder(1, 2);
    }

    @Test
    public void ex3() {
        int[] output = LeetProblems.twoSum(new int[]{3, 3}, 6);
        assertThat(output).containsExactlyInAnyOrder(0, 1);
    }
}
