package org.ravi.udemy.dsa;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ravi.udemy.dsa.DsaArrays.containsImproved;
import static org.ravi.udemy.dsa.DsaArrays.containsNaive;

// Given two arrays, create a function that returns true/false if those 2 arrays contain a matching item
//
// ['a', 'b', 'c', 'x' ] with ['z', 'y', 'i'] returns false
// WHILE
// ['a', 'b', 'c', 'z' ] with ['z', 'y', 'x'] returns true
public class ContainsProblemTest {
    char array1[] = new char[]{'a', 'b', 'c', 'x'};
    char array2[] = new char[]{'z', 'y', 'i'};
    char array3[] = new char[]{'a', 'b', 'c', 'z'};
    char array4[] = new char[]{'z', 'y', 'x'};

    @Test
    public void naiveCheck() {
        assertThat(containsNaive(array1, array2))
                .isFalse();

        assertThat(containsNaive(array3, array4))
                .as("3 and 4")
                .isTrue();
    }

    @Test
    public void improvedCheck() {
        assertThat(containsImproved(array1, array2))
                .isFalse();

        assertThat(containsImproved(array3, array4))
                .as("3 and 4")
                .isTrue();
    }
}
