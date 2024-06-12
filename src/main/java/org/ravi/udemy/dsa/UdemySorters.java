package org.ravi.udemy.dsa;

import com.google.common.base.Stopwatch;
import org.ravi.rutils.sams.dsa2.Sorters;

import java.util.HashMap;
import java.util.Map;

import static org.ravi.rutils.sams.dsa2.Sorters.swap;
import static org.ravi.udemy.dsa.DsaArrays.doSplit;

// sort routines learned at Udemy class
public class UdemySorters {
    // TODO: use threadLocal instead of using name everywhere
    private static final Map<String, SortMetrics> metricsMap = new HashMap<>();

    private UdemySorters() {
        // cant instantiate
    }

    // changes: inner=outer+1 and sign of result
    @WorthLooking("bubbleSort O(n^2)")
    public static <T extends Comparable<T>> void bubbleSort(T[] array) {
        if ((array == null) || (array.length == 1)) { // NOP case
            return;
        }
        final String sortName = "bubble";
        begin(sortName, array);

        // compare each-other all the time.
        for (int outer = 0; outer < array.length; outer++) {
            for (int inner = outer + 1; inner < array.length; inner++) {
                int result = array[outer].compareTo(array[inner]);
                compared(sortName);
                if (result > 0) { // swap
                    swap(array, outer, inner);
                    swapped(sortName);
                }
            }
        }
        end(sortName);
    }

    public static <T extends Comparable<T>> void selectionSort(T[] array) {
        if ((array == null) || (array.length == 1)) { // NOP case
            return;
        }
        final String sortName = "selection";
        begin(sortName, array);

        // find the smallest and move it to ZERO
        for (int outer = 0; outer < array.length; outer++) {
            int smallestIndex = outer;

            for (int inner = outer + 1; inner < array.length; inner++) {
                int result = array[smallestIndex].compareTo(array[inner]);
                compared(sortName);
                if (result > 0) {
                    smallestIndex = inner;
                }
            }
            if (outer != smallestIndex) {
                swap(array, outer, smallestIndex);
                swapped(sortName);
            }
        }
        end(sortName);
    }

    public static <T extends Comparable<T>> void mergeSort(T[] array) {
        if ((array == null) || (array.length == 1)) { // NOP case
            return;
        }
        final String sortName = "merge";

        begin(sortName, array);
        mergeSort(array, array.length);
        end(sortName);
    }

    //https://www.youtube.com/watch?v=lCDZ0IprFw4
    public static <T extends Comparable<T>> void insertionSort(T[] array) {
        if ((array == null) || (array.length == 1)) { // NOP case
            return;
        }
        final String sortName = "insert";
        begin(sortName, array);

        // ensure that left-of-key is lesser
        for (int outer = 1; outer < array.length; outer++) {
            T key = array[outer];
            for (int inner = outer - 1; inner >= 0; inner--) {
                int result = key.compareTo(array[inner]);
                compared(sortName);
                if (result < 0) { // key-value > inner-value
                    swap(array, inner, inner + 1);
                    swapped(sortName);
                } else {
                    break;
                }
            }
        }
        end(sortName);
    }

    @SuppressWarnings("unused")
    // TODO: implement quick sort
    public static <T extends Comparable<T>> void quickSort(T[] array) {
    }

    //
    // -------------------------------------------------------------------
    //
    private static <T extends Comparable<T>> void mergeSort(T[] array, int numItems) {
        if (numItems < 2) {
            return;
        }

        int middle = numItems / 2;
        T[] left = doSplit(array, 0, middle);
        T[] right = doSplit(array, middle, numItems);

        mergeSort(left, middle);
        mergeSort(right, numItems - middle);

        combine(array, left, right, middle, numItems - middle);
    }

    private static <T extends Comparable<T>> void combine(T[] array, T[] leftArray, T[] rightArray, int leftCount, int rightCount) {
        int left = 0, right = 0, combined = 0;

        while ((left < leftCount) && (right < rightCount)) {
            int result = leftArray[left].compareTo(rightArray[right]);
            compared("merge");

            if (result < 0) {
                array[combined++] = leftArray[left++];
            } else {
                array[combined++] = rightArray[right++];
            }
            swapped("merge");
        }

        while (left < leftCount) {
            array[combined++] = leftArray[left++];
            swapped("merge");
        }
        while (right < rightCount) {
            array[combined++] = rightArray[right++];
            swapped("merge");
        }
    }

    private static <T> SortMetrics begin(String name, T[] array) {
        if (array == null) {
            return null;
        }
        metricsMap.put(name, new SortMetrics(name, array.length));
        return metricsMap.get(name);
    }

    private static void end(String name) {
        if (metricsMap.containsKey(name)) {
            metricsMap.get(name).end();
            metricsMap.remove(name);
        }
    }

    private static void compared(String name) {
        if (metricsMap.containsKey(name)) {
            metricsMap.get(name).compared();
        }
    }

    private static void swapped(String name) {
        if (metricsMap.containsKey(name)) {
            metricsMap.get(name).swapped();
        }
    }

    private static class SortMetrics {
        private final String name;
        private final int count;
        private final Stopwatch timer;
        private int numCompares;
        private int numSwaps;

        SortMetrics(String name, int count) {
            this.name = name;
            this.count = count;
            timer = Stopwatch.createStarted();
        }

        void compared() {
            numCompares++;
        }

        void swapped() {
            numSwaps++;
        }

        void end() {
            if (count > 3) {
                int totalOps = numCompares + numSwaps;
                timer.stop();
                System.out.printf("%s-%d items: ops=%d(%d/%d) %s%n", name, count, totalOps,
                        numCompares, numSwaps, timer);
            }
        }
    }
}
