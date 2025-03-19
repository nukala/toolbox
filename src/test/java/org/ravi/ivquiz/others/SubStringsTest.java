package org.ravi.ivquiz.others;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.ravi.udemy.dsa.Strings.substrings;

public class SubStringsTest {
    // A, AB, ABC, ABCD, B, BC, BCD, C, CD, D
    @Test
    public void substringsTest() {
        String input = "ABCD";

        List<String> list = substrings(input);
        System.out.printf("input=[%s], substrings=%s %n", input, list);

        Assertions.assertThat(list)
                .containsAll(newArrayList("A", "AB", "ABC", "ABCD", "B", "BC", "BCD", "C", "CD", "D"));

    }
}
