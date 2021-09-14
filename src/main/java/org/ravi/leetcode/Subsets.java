package org.ravi.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    // leet -> recursive
    public static <T> List<List<T>> subsets(T[] ary) {
        List<List<T>> results = new ArrayList<>();
        results.add(new ArrayList<T>());
        generateSubset(results, ary, 0, new ArrayList<T>());
        return results;
    }

    // lc, solution
    public static int lengthOfLongestSubstring(String s) {
        int len = s.length(), longest = 0, left = 0, right = 0;
        Set<Character> set = new HashSet<>();

        while (left < len && right < len) {
            // try to extend the range [left, j]
            char ch = s.charAt(right);
            if (set.contains(ch)) {
                set.remove(s.charAt(left));
                left++;
            } else {
                set.add(ch);
                right ++;
                longest = Math.max(longest, right - left);
            }
        }

        return longest;
    }
}