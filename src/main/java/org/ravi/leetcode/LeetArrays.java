package org.ravi.leetcode;

/**
 * <a href="https://leetcode.com/discuss/interview-question/operating-system/4417164/Basic-to-Advanced-Array-Questions-with-Techniques">array questions with techniques</a>
 * <p>
 * Solutions described there
 * <p>
 * First solve for ints and then genericize later
 **/

import org.ravi.udemy.dsa.WorthLooking;

import java.util.Arrays;

public class LeetArrays {
    public static int findMax(int[] array) {
        if ((array == null) || (array.length == 0)) {
            throw new IllegalArgumentException("empty or null array");
        }
        return Arrays.stream(array)
                .boxed()
                .max((a, b) -> a.compareTo(b))
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("no maximum found");
                });
    }

    @WorthLooking("Watch for array boundaries and pay attention to mid point")
    public static void reverseArray(int[] array) {
        if ((array == null) || (array.length == 0)) {
            return;
        }

        int len = array.length, numSwaps = 0;
        for (int left = 0, right = len - 1; right > left;
             left++, right--) {
            int tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;

            numSwaps++;
        }

        System.out.printf("length=%d, numSwaps=%d %n", len, numSwaps);
    }
}

