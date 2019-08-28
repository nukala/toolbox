package org.ravi.ivquiz;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

import static org.ravi.udemy.dsa.Strings.substrings;

public class StringsTest {
    // A, AB, ABC, ABCD, B, BC, BCD, C, CD, D
    @Test
    public void substringsTest() {
        String input = "ABCD";

        List<String> list = substrings(input);
        list.stream()
                .forEach(s -> System.out.printf("%s ", s));
        System.out.printf("%n");

        Assertions.assertThat(list)
                .contains("A", "B", "C", "D", "AB", "ABC", "ABCD", "BC", "BCD", "C", "CD", "D");

    }
}
