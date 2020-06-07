package org.ravi.leetcode;

import java.util.ArrayList;
import java.util.List;

// given a unique set, make all possible subsets including empty and all-elements
public class Subsets<T> {
    private static <T> void generateSubset(List<List<T>> results, T[] ary, int i, List<T> prev) {
        if (i >= ary.length) {
            return;
        }
        List<T> ls = new ArrayList<>(prev);
        results.add(ls);
        generateSubset(results, ary, i + 1, ls); // Generate new subset without adding current number.
        ls.add(ary[i]);
        generateSubset(results, ary, i + 1, ls); // Generate new subset with current number added.
    }

    // leet -> resc
    public static <T> List<List<T>> subsets(T[] ary) {
        List<List<T>> results = new ArrayList<>();
        results.add(new ArrayList<T>());
        generateSubset(results, ary, 0, new ArrayList<T>());
        return results;
    }
}
