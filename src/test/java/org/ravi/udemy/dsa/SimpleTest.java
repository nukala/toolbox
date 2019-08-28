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

        System.out.printf("Before = %s%n", list);
        Collections.sort(list);
        System.out.printf("After sorting=%s %n", list.toString());
    }

    @Test
    public void compareToTest() {
        Integer small = Integer.valueOf(1);
        Integer large = Integer.valueOf(9);

        assertThat(small.compareTo(large))
                .as("negative since this < param")
                .isEqualTo(-1);

        assertThat(large.compareTo(small))
                .as("positive since this > param")
                .isEqualTo(1);
    }
}
