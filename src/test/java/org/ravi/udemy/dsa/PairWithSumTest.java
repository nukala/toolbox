package org.ravi.udemy.dsa;

// see: https://www.youtube.com/watch?v=XKu_SEDAykw
//
// Given an array of integers (unknown size) and a sum -> check iff any pair of number add-up to sum.
//  return boolean , no need for indices ...

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ravi.udemy.dsa.DsaArrays.hasPairWithSum;

public class PairWithSumTest {
    @Test
    public void noMatchingPair() {
        assertThat(hasPairWithSum(new int[]{1, 2, 3, 4, 9}, 9))
                .as("no possible pair")
                .isFalse();
    }

    @Test
    public void pairWithNegative() {
        assertThat(hasPairWithSum(new int[]{1, 2, -3, 4, 12}, 9))
                .as("pairs-up with negative")
                .isTrue();
    }

    @Test
    public void pairWithDuplicates() {
        assertThat(hasPairWithSum(new int[]{1, 2, -3, 4, 4}, 8))
                .as("pair with dups")
                .isTrue();
    }

    @Test(expected =  NullPointerException.class)
    public void pairWithNullArray() {
        assertThat(hasPairWithSum(null, 9))
                .isFalse();
    }

    @Test
    public void blankArrayIsFalse() {
        assertThat(hasPairWithSum(new int[1], 9))
                .isFalse();
    }
}
