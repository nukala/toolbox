package org.ravi.udemy.dsa;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ravi.udemy.dsa.DsaArrays.*;


public class MergeSortedArraysTest {

    static void showLeftRightMerged(int[] left, int[] right, int[] merged) {
        System.out.printf("Left = [%s]     Right = [%s]%n", arrayToString(left), arrayToString(right));
        System.out.printf("\tMerged = [%s] %n", arrayToString(merged));
    }


    @Test
    public void removeDupsTest() {
        int nums[] = new int[]{1, 1, 3, 4, 4, 5};
        int nonDupLen = removeDuplicatesAndReturnSize(nums);
        assertThat(nonDupLen)
                .isEqualTo(4);
        System.out.printf("len=%d: array=[%s]%n", nonDupLen, arrayToString(nums, Optional.of(nonDupLen)));
    }

    @Test
    public void mergeFromTheCourse() {
        int[] left = new int[]{0, 3, 4, 31};
        int[] right = new int[]{4, 6, 30};
        int[] merged = mergeSortedArrays2(left, right);

        showLeftRightMerged(left, right, merged);
    }

    @Test
    public void mergeSmallLeft() {
        int[] left = new int[]{99, 101};
        int[] right = new int[]{4, 6, 30};
        int[] merged = mergeSortedArrays2(left, right);

        showLeftRightMerged(left, right, merged);
    }

    @Test
    public void mergeEmptyLeft() {
        int[] left = new int[]{};
        int[] right = new int[]{4, 6, 30};
        int[] merged = mergeSortedArrays2(left, right);

        showLeftRightMerged(left, right, merged);
    }

    @Test
    public void mergeTwoEmpties() {
        int[] left = new int[]{};
        int[] right = new int[]{};
        int[] merged = mergeSortedArrays2(left, right);

        showLeftRightMerged(left, right, merged);
    }

    @Test
    public void mergeThreeAndOneElements() {
        int[] left = new int[]{12, 12, 13};
        int[] right = new int[]{-1};
        int[] merged = mergeSortedArrays2(left, right);

        showLeftRightMerged(left, right, merged);
    }
}
