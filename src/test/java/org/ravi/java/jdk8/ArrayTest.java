package org.ravi.java.jdk8;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.Function;

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
    public void arrays_copyFrom_byObservation() {
        String[] copyFrom = {"one", "two", "three", "four", "five", "six", "seven", "eight",
                "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen"};

        // @WorthLooking - three to nine, zero based!
        String[] copyTo = java.util.Arrays.copyOfRange(copyFrom, 2, 9);
        for (String coffee : copyTo) {
            System.out.print(coffee + " ");
        }
        System.out.println();
        // without looping explicitly ...
        System.out.println(java.util.Arrays.toString(copyTo));
    }

    private void perform(int[] ary, Function<Integer, Integer>op) {
        for(int i = 0; i < ary.length; i++) {
            ary[i] = op.apply(ary[i]);
        }
        System.out.printf("inside method before return=%s%n", Arrays.toString(ary));
    }
    @Test
    public void arrayValuesChangedInMethod() {
        int[] ary = {3, 4, 5, 6};
        System.out.printf("BEFORE = %s %n", Arrays.toString(ary));
        Function<Integer, Integer> op = i -> i += 10;
        perform(ary, op);
        System.out.printf("BACK=%s%n", Arrays.toString(ary));
        assertThat(ary).describedAs("after operating on values in a called method")
                .contains(16, 14, 15, 13);
    }
}
