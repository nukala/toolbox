package org.ravi.udemy.dsa;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Strings {
    // https://www.udemy.com/master-the-coding-interview-data-structures-algorithms/learn/lecture/12308750#content
    // section6
    static String reverseSlow(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }

        StringBuilder sb = new StringBuilder(str.length());
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    // listing 1.1 in the book
    public static String reverse(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }

        char[] array = str.toCharArray();
        int midPoint = array.length / 2;
        int right;
        char tmp;

        for (int left = 0; left < midPoint; left++) {
            right = array.length - 1 - left;
            tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;
        }

        return String.valueOf(array);
    }

    // javarevisited.blogspot.com/2015/08/how-to-find-all-permutations-of-string-java-example.html
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
            String prev = rest.substring(0, i);
            String remain = rest.substring(i + 1);
            List<String> tmp = permutations(fixed + rest.charAt(i), prev + remain);
            list.addAll(tmp);
        }
        return list;
    }

    // From "ABCD": A, AB, ABC, ABCD, B, BC, BCD, C, CD, D
    @WorthLooking("substrings from a big str O(n^2) add-to-list-in-inner-loop")
    public static List<String> substrings(String input) {
        if ((input == null) || (input.length() == 0)) {
            return Lists.newArrayList(input);
        }
        List<String> list = new ArrayList<>();

        int len = input.length();
        for (int i = 0; i < len; i++) {
            char ch = input.charAt(i);

            for (int j = i + 1; j <= len; j++) {
                String sub = input.substring(i + 1, j);
                list.add(ch + sub);
            }
        }
        return list;
    }

    // javarevisited.blogspot.com/2012/10/regular-expression-example-in-java-to-check-String-number.html
    // regex listed there does not work with java8-162
    public static boolean hasOnlyDigits(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }

        String pattern = "^[0-9]*";
        return Pattern.compile(pattern)
                .matcher(str).matches();
    }
}
