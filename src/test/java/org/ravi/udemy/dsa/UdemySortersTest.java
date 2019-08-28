package org.ravi.udemy.dsa;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class UdemySortersTest {
    private static Integer[] randomArray = DsaArrays.randomInts(1_999);
    private Consumer<Comparable<?>[]> worker;
    @SuppressWarnings({"unused"})
    private String sortType;

    public UdemySortersTest(Consumer<Comparable<?>[]> worker, String name) {
        this.worker = worker;
        this.sortType = name;
    }

    @Parameterized.Parameters(name = "{1}Sort")
    public static <T extends Comparable<T>> Collection parameters() {

        return Arrays.asList(new Object[][]{
                {(Consumer<T[]>) UdemySorters::bubbleSort, "bubble"},
                {(Consumer<T[]>) UdemySorters::insertionSort, "insertion"},
                {(Consumer<T[]>) UdemySorters::selectionSort, "selection"},
                {(Consumer<T[]>) UdemySorters::mergeSort, "merge"},
        });
    }

    @Before
    public void beforeEveryTest() {
        Integer[] array = {1, 2, 3};
        worker.accept(array);
    }

    private void verifyIntegers(Integer[] array) {
        for (int i = 0; i <= array.length - 2; i++) {
            //System.out.printf("%d: Comparing (%d, %d) %n", i, array[i], array[i + 1]);
            assertThat(array[i])
                    .as(String.format("%d < %d", array[i], array[i + 1]))
                    .isLessThanOrEqualTo(array[i + 1]);
        }
    }

    @Test
    public void randomized() {
        Integer[] copy = DsaArrays.doSplit(randomArray, 0, randomArray.length);
        worker.accept(copy);
        verifyIntegers(copy);
    }

    @Test
    public void fromClass() {
        Integer[] array = {99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0, 2};

        worker.accept(array);
        verifyIntegers(array);
    }

    //https://www.youtube.com/watch?v=lCDZ0IprFw4
    @Test
    public void fewNumbers() {
        Integer[] array = {5, 8, 1, 3, 9, 6};

        worker.accept(array);
        //System.out.printf("AFTER %s%n", org.ravi.udemy.dsa.Arrays.asString(array));
        verifyIntegers(array);
    }

    @Test
    public void alreadySorted() {
        Integer[] array = {1, 9, 22, 77, 99};

        worker.accept(array);
        verifyIntegers(array);
    }

    @Test
    public void reversedArray() {
        Integer[] array = {99, 22, 9, 1};

        worker.accept(array);
        verifyIntegers(array);
    }

    @Test
    public void sixCharacters() {
        Character[] array = {'z', 'b', 'A', 'c', 'Z', '9', 'q'};

        worker.accept(array);
        for (int i = 0; i < array.length - 2; i++) {
            assertThat((int) array[i])
                    .isLessThanOrEqualTo((int) array[i + 1]);
        }
    }

    @Test
    public void strings() {
        String[] array = {"hello", "from", "Fremont", "California", "in", "the", "USA"};

        worker.accept(array);
        //System.out.printf("AFTER %s %n", Arrays.toString(array));
        for (int i = 0; i < array.length - 2; i++) {
            assertThat(array[i].compareTo(array[i + 1]))
                    .as(String.format("[%s].compareTo [%s] <= 0", array[i], array[i + 1]))
                    .isLessThanOrEqualTo(0);
        }
    }
}
