package org.ravi.java.jdk8;

import org.junit.Test;
import org.ravi.udemy.dsa.WorthLooking;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

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

    @WorthLooking("arrays are passed as reference, hence mutable in methods")
    public <T> void clear_Array(T[] ary, T replacement) {
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
        assertThat(ary).containsExactly(0, 4, 0, 6).describedAs("alternate zeroes");
    }

    @Test
    public void arrayOfStrings() {
        String[] ary = {"hello", "from", "Oakland", "CA", "USA"};
        System.out.printf("BEFORE = %s %n", Arrays.toString(ary));
        clear_Array(ary, "-");
        System.out.printf("AFTER = %s %n", Arrays.toString(ary));
        assertThat(ary).containsExactly("-", "from", "-", "CA", "-")
                .describedAs("alternate dashes");
    }
}
