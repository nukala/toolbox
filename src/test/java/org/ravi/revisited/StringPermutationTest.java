package org.ravi.revisited;

import org.junit.Test;

import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ravi.udemy.dsa.Strings.permutations;

// Tests of code that was inspired from javarevisited.blogspot.com
public class StringPermutationTest {
    private static final Function<String, List<String>> permuter = input -> {
        List<String> list = permutations(input);

        List<String> combinations = permutations(input);
        System.out.printf("toString [%s]: %s%n", input, combinations);
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
