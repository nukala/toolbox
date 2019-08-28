package org.ravi.udemy.dsa;

import org.junit.Ignore;

import static org.assertj.core.api.Assertions.fail;
import static org.ravi.udemy.dsa.DsaArrays.arrayToString;

public class RotateArrayTest {
    // https://leetcode.com/problems/rotate-array/
    void rotateArray(int[] nums, int k) {
        if (nums.length < k)
            return;
        // TODO
        fail("not implemented");
    }

    @Ignore("not implemented") // TODO
    public void rotateTest() {
        int nums[] = new int[]{1, 2, 3, 4, 5, 6, 7};
        System.out.printf("INPUT %s%n", arrayToString(nums));

        int numRotations = 3;
        rotateArray(nums, numRotations);
        System.out.printf("After rotating %d times = [%s] %n", numRotations, arrayToString(nums));
    }
}
