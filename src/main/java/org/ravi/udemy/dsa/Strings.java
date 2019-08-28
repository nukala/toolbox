package org.ravi.udemy.dsa;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class Strings {
    // https://www.udemy.com/master-the-coding-interview-data-structures-algorithms/learn/lecture/12308750#content
    // section6
    static String reverse(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }

        StringBuilder sb = new StringBuilder(str.length());
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    // https://javarevisited.blogspot.com/2015/08/how-to-find-all-permutations-of-string-java-example.html
    @WorthLooking("all permutations of a string using recursive helper")
    public static List<String> permutations(String input) {
        return permutations("", input);
    }

    private static List<String> permutations(String fixed, String rest) {
        List<String> list = new ArrayList<>();
        if (rest == null) {
            return list;
        } else if (rest.isEmpty()) {
            list.add(fixed + rest);
            return list;
        }

        for (int i = 0; i < rest.length(); i++) {
            List<String> tmp = permutations(fixed + rest.charAt(i),
                    rest.substring(0, i) + rest.substring(i + 1));
            list.addAll(tmp);
        }
        return list;
    }

    // From "ABCD": A, AB, ABC, ABCD, B, BC, BCD, C, CD, D
    // actually asked at Zume Inc
    public static List<String> substrings(String input) {
        if ((input == null) || (input.length() == 0)) {
            return Lists.newArrayList(input);
        }
        List<String> list = new ArrayList();

        int len = input.length();
        for (int i = 0; i < len; i++) {
            char ch = input.charAt(i);
            //list.add("" + ch);

            for (int j = i + 1; j <= len; j++) {
                String sub = input.substring(i + 1, j);
                list.add(ch + sub);
            }
        }
        return list;
    }
}
