package org.ravi.leetcode;

import org.ravi.udemy.dsa.WorthLooking;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class LeetProblems {
    public static int[] twoSum(int[] numbers, int sum) {
        int[] result = new int[]{-1, -1};

        // key = i's pair, value = i
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            if (map.containsKey(number)) {
                // found
                result[0] = i;
                result[1] = map.get(number);
                break;
            } else {
                int other = sum - number;
                map.put(other, i);
            }
        }
        return result;
    }

    //leetcode.com/problems/valid-parentheses
    @WorthLooking("order is not required --- add into sb -> push back -> dont miss ch")
    public static boolean isValidParantheses(String s) {
        if ((s == null) || (s.length() <= 1)) {
            return false;
        }

        Deque<Character> deque = new ArrayDeque<>();
        for (char ch : s.toCharArray()) {
            StringBuilder sb = new StringBuilder();
            if ((ch == ')') || (ch == ']') || (ch == '}')) {
                if (deque.isEmpty()) {
                    // initial close is not valid
                    return false;
                }
                Character popped = deque.pop();
                if ((ch == ')') && (popped.equals('('))) {
                    continue;
                } else if ((ch == ']') && (popped.equals('['))) {
                    continue;
                } else if ((ch == '}') && (popped.equals('{'))) {
                    continue;
                }
                return false;
            } else {
                deque.push(ch);
            }
        }

        return deque.isEmpty();
    }
}
