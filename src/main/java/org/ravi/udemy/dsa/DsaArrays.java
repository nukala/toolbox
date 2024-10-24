package org.ravi.udemy.dsa;

import java.util.*;
import java.util.function.Supplier;

public class DsaArrays {
    private static final Random RANDOM = new Random();


    @SuppressWarnings({"unchecked"})
    public static <T> T[] from(Supplier<T> supplier, int numItems, T[] sample) {
        if (sample == null) {
            throw new IllegalArgumentException("PROGRAMMER-ERROR: Sample cannot be null");
        }
        Object[] array = new Object[numItems];

        for (int i = 0; i < numItems; i++) { 
            array[i] = supplier.get();
        }

        // copied from Arrays.toArray
        if (sample.length < numItems) {
            return Arrays.copyOf(array, numItems, (Class<? extends T[]>) sample.getClass());
        }

        System.arraycopy(array, 0, sample, 0, numItems);
        return sample;
    }

    @SuppressWarnings({"unchecked"})
    public static <T> T[] doSplit(T[] orig, int start, int end) {
        int numItems = end - start;
        Object[] objs = new Object[numItems];
        System.arraycopy(orig, start, objs, 0, numItems);

        return Arrays.copyOf(objs, numItems, (Class<? extends T[]>) orig.getClass());
    }

    public static Integer[] randomInts(int numItems) {
        int maxNum = (numItems > 511) ? numItems : 512;

        return from(() ->
                RANDOM.nextInt(maxNum), numItems, new Integer[0]);
    }

    // cannot genericize
    public static String arrayToString(int[] array) {
        return arrayToString(array, Optional.empty());
    }

    public static String arrayToString(int[] array, Optional<Integer> lenOpt) {
        if ((array == null) || (array.length == 0)) {
            return "";
        }
        int len = lenOpt.orElseGet(() -> array.length);

        StringJoiner joiner = new StringJoiner(",");
        for (int i = 0; i < len; i++) {
            joiner.add(Integer.toString(i));
        }
        return joiner.toString();
    }


    // MY-NAIVE-IMPLEMENTATION
    // 21 mins for broken code
    // 7 to add break. AND fix-shift
    // 3 more mins for empty checks
    // MY-NAIVE-IMPLEMENTATION
    // https://www.udemy.com/master-the-coding-interview-data-structures-algorithms/learn/lecture/12308750#content
    // Section6
    static int[] mergeSortedArrays(int[] left, int[] right) {
        if ((left == null) && (right == null)) {
            return null;
        } else if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        }

        int leftLength = left.length, rightLength = right.length;
        if (leftLength == 0) {
            return right;
        }
        if (rightLength == 0) {
            return left;
        }
        int[] merged = new int[leftLength + rightLength];
        for (int li = 0; li < leftLength; li++) {
            merged[li] = left[li];
        }

        for (int ri = 0; ri < rightLength; ri++) {
            int rightItem = right[ri];

            for (int mi = 0; mi < merged.length; mi++) {
                if (rightItem <= merged[mi]) {
                    // shift 1 item down and insert
                    shiftToNext(merged, mi);
                    merged[mi] = rightItem;
                    break;
                }
            }
        }

        return merged;
    }

    static void shiftToNext(int[] ary, int index) {
        for (int i = ary.length - 1; i > index; i--) {
            ary[i] = ary[i - 1];
        }
    }

    private static Integer getValueAtIndex(int[] ary, int index) {
        return (index < ary.length) ?
                ary[index] : null;
    }

    // https://www.udemy.com/master-the-coding-interview-data-structures-algorithms/learn/lecture/12308750#content
    // Section6
    // inspired by class solution
    @WorthLooking("merge two sorted arrays")
    //TODO convert into Comparables
    static int[] mergeSortedArrays2(int[] left, int[] right) {
        if ((left == null) && (right == null)) {
            return null;
        } else if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        }
        int leftLength = left.length, rightLength = right.length;
        if (leftLength == 0) {
            return right;
        }
        if (rightLength == 0) {
            return left;
        }
        int[] merged = new int[leftLength + rightLength];
        int ll = 0;
        Integer leftItem = getValueAtIndex(left, ll);
        int rr = 0;
        Integer rightItem = getValueAtIndex(right, rr);
        int mm = 0;

        while ((leftItem != null) || (rightItem != null)) {
            if ((rightItem == null) || (leftItem < rightItem)) {
                merged[mm++] = leftItem;
                leftItem = getValueAtIndex(left, ++ll);
            } else {
                merged[mm++] = rightItem;
                rightItem = getValueAtIndex(right, ++rr);
            }
        }
        return merged;
    }

    // page14 of coding-interview-in-java.pdf (Program Creek)
    public static int removeDuplicatesAndReturnSize(int[] A) {
        if (A.length < 2)
            return A.length;
        int j = 0;
        int i = 1;
        while (i < A.length) {
            if (A[i] != A[j]) {
                j++;
                A[j] = A[i];
            }
            i++;
        }

        return j + 1;
    }

    @WorthLooking("efficient twoSum with a hashset for the other-number.")
    static boolean hasPairWithSum(int[] numbers, int sum) {
        Objects.requireNonNull(numbers, "null numbers");
        Set<Integer> othersSet = new HashSet<>();

        for (int number : numbers) {
            if (othersSet.contains(number)) {
                return true;
            } else {
                // guard against underflow
                int otherPart = Math.subtractExact(sum, number);
                othersSet.add(otherPart);
            }
        }
        return false;
    }

    // Given two arrays, create a function that returns true/false if those 2 arrays contain a matching item
    //
    // ['a', 'b', 'c', 'x' ] with ['z', 'y', 'i'] returns false
    // WHILE
    // ['a', 'b', 'c', 'z' ] with ['z', 'y', 'x'] returns true
    static boolean containsNaive(char[] arr1, char[] arr2) {
        for (char c : arr1) {
            for (int j = 0; j < arr2.length; j++) {
                if (c == arr2[j]) {
                    return true;
                }
            }
        }

        return false;
    }

    // Given two arrays, create a function that returns true/false if those 2 arrays contain a matching item
    //
    // ['a', 'b', 'c', 'x' ] with ['z', 'y', 'i'] returns false
    // WHILE
    // ['a', 'b', 'c', 'z' ] with ['z', 'y', 'x'] returns true
    @WorthLooking("contains between two arrays using leftSet O(n)")
    static boolean containsImproved(char[] left, char[] right) {
        Set<Character> leftSet = new HashSet<>();
        for (char c : left) {
            leftSet.add(c);
        }

        for (char c : right) {
            if (leftSet.contains(c)) {
                return true;
            }
        }

        return false;
    }

    public static <T> String asString(T[] array) {
        StringJoiner joiner = new StringJoiner(", ");
        Arrays.asList(array)
                .forEach(e -> joiner.add(e.toString()));
        return joiner.toString();
    }
}
