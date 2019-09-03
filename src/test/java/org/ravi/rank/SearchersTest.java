package org.ravi.rank;

import org.junit.Test;

import java.util.function.BiFunction;

import static org.assertj.core.api.Assertions.*;

// TODO: use Function to be DRY
public class SearchersTest {
    private static final String[] strAry = {"a", "b", "c", "d", "q", "w", "x", "z"};

    private static BiFunction<String[], String, Integer> RECURSIVE = Searchers::binarySearchRecursive;
    private static BiFunction<String[], String, Integer> ITERATIVE = Searchers::binarySearch;

    @Test
    public void binarySearchRecursiveBoundaries() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> Searchers.binarySearchRecursive(null, 54))
                .withNoCause()
                .withMessageContaining("non-null array required");

        assertThatNullPointerException()
                .isThrownBy(() -> Searchers.binarySearchRecursive(strAry, null))
                .withNoCause();
    }

    private void doSearch(BiFunction<String[], String, Integer> func, String toFind, int expected) {
        assertThat(func.apply(strAry, toFind))
                .as("find(" + toFind + ") should return " + expected)
                .isEqualTo(expected);
    }

    @Test
    public void binarySearchRecursive() {
        doSearch(RECURSIVE, "q", 4);
        doSearch(RECURSIVE, "ww", -1);
    }

    @Test
    public void binarySearchBoundaries() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> Searchers.binarySearch(null, 54))
                .withNoCause()
                .withMessageContaining("non-null array required");

        assertThatNullPointerException()
                .isThrownBy(() -> Searchers.binarySearch(strAry, null))
                .withNoCause();
    }

    @Test
    public void binarySearch() {
        doSearch(ITERATIVE, "q", 4);
        doSearch(ITERATIVE, "ww", -1);
    }
}
