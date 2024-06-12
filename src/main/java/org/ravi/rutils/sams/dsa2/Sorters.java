package org.ravi.rutils.sams.dsa2;

// book "Sams - Data Structures and Algorithms in Java, 2nd edition"
public class Sorters {
    // page 87 or 112
    public static <T extends Comparable<T>> void bubbleSort(T[] ary) {
        int outer, inner;
        int num = ary.length;
        for (outer = num - 1; outer >= 0; outer--) {
            for (inner = 0; inner < outer; inner++) {
                if (ary[inner].compareTo(ary[inner + 1]) > 0) {
                    swap(ary, inner, inner + 1);
                }
            }
        }
    }

    // page92 or 117
    public static <T extends Comparable<T>> void selectionSort(T[] array) {
        int outer, inner, selected;
        int num = array.length;

        for (outer = 0; outer <= num - 1; outer++) {
            selected = outer;
            for (inner = outer + 1; inner <= num - 1; inner++) {
                if (array[selected].compareTo(array[inner]) > 0) { // selected is bigger
                    selected = inner;
                }
            }
            swap(array, outer, selected);
        }
    }

    // TODO - needs understanding.
    // page99 or 124
    public static <T extends Comparable<T>> void insertionSort(T[] array) {
        int inner, outer;
        int num = array.length;
        T marked;

        for (outer = 1; outer <= num - 1; outer++) {
            marked = array[outer];
            inner = outer;
            while (inner > 0 && array[inner - 1].compareTo(marked) > 0) {
                array[inner] = array[inner - 1];
                inner--;
            }
            array[inner] = marked;
        }
    }

    // p278 or 303 Towers of Hanoi
    public static void doTowers(int topN, String from, String inter, String to) {
        if (topN == 1) {
            System.out.printf("Disk %d from %s to %s%n", topN, from, to);
        } else {
            doTowers(topN - 1, from, to, inter); // from-->inter
            System.out.printf("Disk %d from %s to %s%n", topN, from, to);
            doTowers(topN - 1, inter, from, to); // inter-->to
        }
    }

    // p281 or 306
    private static <T extends Comparable<T>> void merge(T[] workspace, T[] source, int lower, int high, int upper) {
        if (workspace.length < (source.length)) {
            throw new IllegalArgumentException("merged array is smaller than inputs");
        }

        int wdex = 0;
        int numSorted = upper - lower + 1;
        int mid = high - 1;
        while (lower <= high - 1 && high <= upper) {
            if (source[lower].compareTo(source[high]) > 0) {
                workspace[wdex++] = source[high++];
            } else {
                workspace[wdex++] = source[lower++];
            }
        }

        // leftover lowers
        while (lower <= mid) {
            workspace[wdex++] = source[lower++];
        }

        // leftover highers
        while (high <= upper) {
            workspace[wdex++] = source[high++];
        }
        for (int i = 0; i < numSorted; i++) {
            source[i] = workspace[i];
        }
    }

    private static <T extends Comparable<T>> void recMergeSort(T[] workspace, T[] array, int lower, int upper) {
        if (lower == upper) {
            return;
        }
        int mid = (lower + upper) / 2;
        // sort lower half
        recMergeSort(workspace, array, lower, mid);

        // sort uppper half
        recMergeSort(workspace, array, mid + 1, upper);

        merge(workspace, array, lower, mid + 1, upper);
    }

    // TODO: broken, lookback after pocketgems
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void mergeSort(T[] array) {
        T workspace[] = (T[]) new Object[array.length];

        recMergeSort(workspace, array, 0, array.length - 1);
    }

    public static <T> void swap(T[] array, int from, int to) {
        if (array == null || from < 0 || from > array.length || to < 0 || to > array.length) {
            return;
        }
        T tmp = array[from];
        array[from] = array[to];
        array[to] = tmp;
    }
}

