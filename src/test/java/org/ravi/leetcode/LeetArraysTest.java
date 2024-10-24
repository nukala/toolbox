package org.ravi.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class LeetArraysTest {
    @Test
    public void reverseOddLen() {
        int[] a = {4, 9, 5, 2, 3};
        LeetArrays.reverseArray(a);

        int[] exp = { 3, 2, 5, 9, 4 };
        Assert.assertArrayEquals("after reverse",
                a,
                exp);
    }

    @Test
    public void reverseEvenLen() {
        int[] a = {4, 9, 2, 3};
        LeetArrays.reverseArray(a);

        int[] exp = { 3, 2, 9, 4 };
        Assert.assertArrayEquals("after reverse",
                a,
                exp);
    }

    @Test
    public void testMax() {
        int[] a = {45, 94, 12, 789};
        Assert.assertTrue("max=789", LeetArrays.findMax(a) == 789);
    }

    @Test
    public void maxAmongEquals() {
        int[] a = {45, 45, 45, 45, 45};
        Assert.assertTrue("max=45", LeetArrays.findMax(a) == 45);
    }
}
