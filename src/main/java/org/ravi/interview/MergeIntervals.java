package org.ravi.interview;

import java.util.Arrays;
import java.util.Formatter;

/*
Given set of intervals merge overlapping intervals and output only mutually exclusive intervals.

Example:
Input: { { 14, 16 }, { 24, 60 }, { 2, 15 }, { 1, 2 }, { 8, 10 }, { 72, 83 } }
Output: { { 1, 16 }, { 24, 60 }, { 72, 83 } }
*/


public class MergeIntervals {
    private static boolean isOverlapped(Pair p1, Pair p2) {
        if ((p1 == null) || (p2 == null)) {
            return false;
        }
        return p2.left <= p1.right;
    }

    private static Pair mergePair(Pair p1, Pair p2) {
        if (p2 == null) {
            return p1;
        }
        if (p1 == null) {
            return p2;
        }
        return new Pair(Math.min(p1.left, p2.left),
                Math.max(p1.right, p2.right));
    }

    public static void main(String[] args) {
        //Input: { { 14, 16 }, { 24, 60 }, { 2, 15 }, { 1, 2 }, { 8, 10 }, { 72, 83 } }
        Pair[] pairs/* = {new Pair(14, 16), new Pair(24, 60), new Pair(2, 15), new Pair(1, 2), new Pair(8, 10), new Pair(72, 83)}*/;
        pairs = new Pair[]{
                new Pair(1, 6),
                new Pair(4, 8),
                new Pair(15, 17),
                new Pair(15, 22),
                new Pair(9, 14)
        };

        Arrays.sort(pairs);

        Pair[] answer = new Pair[pairs.length];
        int answerIndex = 0;

        Pair bigPair = null;
        for (Pair curr : pairs) {
            if (bigPair == null) {
                bigPair = curr;
                continue;
            }
            if (isOverlapped(bigPair, curr)) {
                bigPair = mergePair(bigPair, curr);
            } else {
                answer[answerIndex] = bigPair;
                answerIndex++;
                bigPair = curr;
            }
        }

        if (bigPair != null) {
            answer[answerIndex] = bigPair;
            answerIndex++;
        }

        try (Formatter formatter = new Formatter(new StringBuilder())) {
            for (int i = 0; i < answerIndex; i++) {
                Pair pair = answer[i];

                formatter.format("{%d, %d}%s", pair.left, pair.right, (i < answerIndex - 1) ? ", " : "");
            }
            System.out.printf("{ %s }%n", formatter);
        }
    }

    // private static Pair getNext(Pair[] pairs, int i) {
    //     int nextIndex = i + 1;
    //     if (nextIndex < pairs.length) {
    //         return pairs[nextIndex];
    //     }
    //     return null;
    // }
    static class Pair implements Comparable<Pair> {
        private final int left;
        private final int right;

        Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public int compareTo(Pair other) {
            //return this.left.compareTo(other.left);
            return Integer.compare(this.left, other.left);
        }
    }


}
