package org.ravi.udemy.dsa;

//Google Question
//Given an array = [2,5,1,2,3,5,1,2,4]:
//It should return 2

//Given an array = [2,1,1,2,3,5,1,2,4]:
//It should return 1

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

//Given an array = [2,3,4,5]:
//It should return undefined
//
// https://www.udemy.com/master-the-coding-interview-data-structures-algorithms/learn/lecture/12314712#overview
public class FirstRecurringValueTest {
    public static final int UNDEFINED = Integer.MIN_VALUE;

    static int firstRecurringValue(int[] array) {
        Set<Integer> set = new HashSet<>();

        for (int num : array) {
            if (set.contains(num)) {
                return num;
            }

            set.add(num);
        }

        return UNDEFINED;
    }

    @Test
    public void firstCase() {
        int[] array = {2, 5, 1, 2, 3, 5, 1, 2, 4};

        assertThat(firstRecurringValue(array))
                .isEqualTo(2);
    }

    @Test
    public void secondCase() {
        int[] array = {2, 1, 1, 2, 3, 5, 1, 2, 4};

        assertThat(firstRecurringValue(array))
                .isEqualTo(1);
    }

    @Test
    public void noMatch() {
        int[] array = {2, 3, 4, 5};

        assertThat(firstRecurringValue(array))
                .isEqualTo(UNDEFINED);
    }
}
