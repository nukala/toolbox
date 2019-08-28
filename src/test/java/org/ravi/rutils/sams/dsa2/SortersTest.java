package org.ravi.rutils.sams.dsa2;

import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SortersTest {
    Integer array[] = new Integer[]{
            55, 1945, 14, /*69, 212, 78, 55, 1,*/ -5
    };

    void show(String message) {
        System.out.printf("%12s: ", message.toUpperCase());
        for (Integer a : array) {
            System.out.printf("%s ", a);
        }
        System.out.printf("%n");

        assertThat(array[0]).isEqualTo(-5);
        assertThat(array[array.length - 1]).isEqualTo(1945);
    }

    @Test
    public void bubble() {
        Sorters.bubbleSort(array);
        show("bubble");
    }

    @Test
    public void selection() {
        Sorters.selectionSort(array);
        show("selection");
    }

    @Test
    public void insertion() {
        Sorters.insertionSort(array);
        show("insertion");
    }

    @Test
    public void towers() {
        Sorters.doTowers(3, "A", "B", "C");
    }

    @Ignore("Broken code") // TODO
    public void merge() {
        Sorters.mergeSort(array);
        show("merge");
    }
}
