package org.ravi.rank;

import org.ravi.udemy.dsa.WorthLooking;

import java.util.Objects;

public class Searchers {
    @WorthLooking("binarySearch: iterative, assume ascending-order-sorted-input, O(log n)")
    public static <T extends Comparable<T>> int binarySearch(T[] array, T toFind) {
        Objects.requireNonNull(array, "non-null array required");
        Objects.requireNonNull(toFind, "null object cannot be found");

        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int result = toFind.compareTo(array[mid]);

            if (result == 0) { // found
                return mid;
            } else if (result < 0) { // toFind is smaller, go left
                right = mid - 1;
            } else { // toFind is larger, go right
                left = mid + 1;
            }
        }

        return -1;
    }

    // www.youtube.com/watch?v=P3YID7liBug
    @WorthLooking("binarySearchRecursive: assume ascending-order-sorted input, O(log n) time complexity")
    public static <T extends Comparable<T>> int binarySearchRecursive(T[] array, T toFind) {
        Objects.requireNonNull(array, "non-null array required");
        Objects.requireNonNull(toFind, "null object cannot be found");

        return binarySearchRecursiveHelper(array, toFind, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> int binarySearchRecursiveHelper(T[] array, T toFind, int left, int right) {
        if (left > right) {
            return -1;
        }

        // look out for overflow, some smart algo
        int mid = (left + right) / 2;
        int result = toFind.compareTo(array[mid]);
        if (result == 0) {
            return mid;
        } else if (result < 0) { // toFind is smaller, go left
            return binarySearchRecursiveHelper(array, toFind, left, mid - 1);
        } else { //toFind is larger, go right
            return binarySearchRecursiveHelper(array, toFind, mid + 1, right);
        }
    }
}
