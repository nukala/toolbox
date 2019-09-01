package org.ravi.revisited;

import org.junit.Test;
import org.ravi.udemy.dsa.Strings;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

// Tests of code that was inspired from javarevisited.blogspot.com
public class RevisitedTest {
    private static Function<String, Boolean> ONLY_DIGITS = input -> Strings.hasOnlyDigits(input);

    private <T> void checker(Function<String, T> func, String str, T expected) {
        assertThat(func)
                .as("checking function cannot be null")
                .isNotNull();
        assertThat(expected)
                .as("expected value cannot be null")
                .isNotNull();

        assertThat(func.apply(str)).as(str).isEqualTo(expected);
    }

    @Test
    public void onlyDigits() {
        checker(ONLY_DIGITS, null, false);
        checker(ONLY_DIGITS, "", false);
        checker(ONLY_DIGITS, "123", true);
        checker(ONLY_DIGITS, "-123", false);
        checker(ONLY_DIGITS, "12.3", false);
    }
}
