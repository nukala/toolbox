package org.ravi.udemy.dsa;

import org.junit.Test;

import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ravi.udemy.dsa.Strings.permutations;

public class StringPermutationTest {
    Function<String, List<String>> permuter = input -> {
        List<String> list = permutations(input);

        StringJoiner joiner = new StringJoiner(", ");
        List<String> combinations = permutations(input);
        combinations.stream()
                .forEach(s -> joiner.add(s));
        System.out.printf("[%s]: [%s] %n", input, joiner);
        return list;
    };

    @Test
    public void threeDigit() {
        assertThat(permuter.apply("123"))
                .containsExactlyInAnyOrder("123", "213", "231", "312", "321", "132");
    }

    @Test
    public void abc() {
        assertThat(permuter.apply("abc"))
                .containsExactlyInAnyOrder("abc", "acb", "bac", "bca", "cab", "cba");
    }

    @Test
    public void testNull() {
        assertThat(permuter.apply(null))
                .isEmpty();
    }

    @Test
    public void empty() {
        assertThat(permuter.apply(""))
                .containsExactly("");
    }
}
