package org.ravi.udemy.dsa;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTest {
    // in JS this seems to be wrong. Lets see in jdk
    // 01:25 https://www.udemy.com/master-the-coding-interview-data-structures-algorithms/learn/lecture/12397596#overview
    //
    // Chrome console is OK, repl.it FAILS, jdk OK too.
    @Test
    public void sillyNumericTest() {
        List<Integer> list = Lists.newArrayList(2, 65, 34, 2, 1, 7, 8);

        assertThat(list)
                .as("unsorted intial input")
                .containsExactly(2, 65, 34, 2, 1, 7, 8);
        Collections.sort(list);
        System.out.printf("After sorting=%s %n", list.toString());
        assertThat(list)
                .as("after sorting")
                .containsExactly(1, 2, 2, 7, 8, 34, 65);
    }

    @Test
    @WorthLooking("compareToTest: proves -1 (this < param) and 1 (this > param) scenario")
    public void compareToTest() {
        Integer small = 1;
        Integer large = 9;

        assertThat(small.compareTo(large))
                .as("negative since this < param")
                .isNegative()
                .isNotPositive()
                .isEqualTo(-1);

        assertThat(large.compareTo(small))
                .as("positive since this > param")
                .isPositive()
                .isNotNegative()
                .isEqualTo(1);

        large = Integer.parseInt("1");
        assertThat(large.compareTo(small))
                .as("zero since both are equal")
                .isZero();
    }
}
