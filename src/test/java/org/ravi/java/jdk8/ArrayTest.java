package org.ravi.java.jdk8;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Simple test to show that arrays are passed as reference (mutable inside a method)
 */
public class ArrayTest {
    public void clear_Array(int[] ary) {
        int count = 0;
        for (int i = 0; i < ary.length; i += 2) {
            ary[i] = 0;
            count++;
        }
        System.out.printf("primitive cleared=%d %n", count);
    }

    public <T> void clear_Array(T[] ary, T replacement) {
        // ary = (T[])new Object[ary.length];
        for (int i = 0; i < ary.length; i += 2) {
            ary[i] = replacement;
        }
    }

    @Test
    public void arrayOfPrimitives() {
        int[] ary = {3, 4, 5, 6};
        System.out.printf("BEFORE = %s %n", Arrays.toString(ary));
        clear_Array(ary);
        System.out.printf("AFTER = %s %n", Arrays.toString(ary));
        assertThat(ary).describedAs("alternate zeroes")
                .containsExactly(0, 4, 0, 6);
    }

    @Test
    public void arrayOfStrings() {
        String[] ary = {"hello", "from", "Oakland", "CA", "USA"};
        System.out.printf("BEFORE = %s %n", Arrays.toString(ary));
        clear_Array(ary, "-");
        System.out.printf("AFTER = %s %n", Arrays.toString(ary));
        assertThat(ary).describedAs("alternate dashes")
                .containsExactly("-", "from", "-", "CA", "-");
    }


    @Test
    public void arrayCopyDemo_byObservation() {
        String[] copyFrom = {"Affogato", "Americano", "Cappuccino", "Corretto", "Cortado", "Doppio", "Espresso", "Frappucino", "Freddo", "Lungo", "Macchiato", "Marocchino", "Ristretto"};

        // @WorthLooking - Cappuccino to Freddo, no Lungo!, not arrayCopy
        String[] copyTo = java.util.Arrays.copyOfRange(copyFrom, 2, 9);
        for (String coffee : copyTo) {
            System.out.print(coffee + " ");
        }
        System.out.println();
        // without looping explicitly ...
        System.out.println(java.util.Arrays.toString(copyTo));
    }
}
