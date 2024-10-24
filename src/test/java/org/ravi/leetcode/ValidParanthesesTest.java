package org.ravi.leetcode;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ravi.leetcode.LeetProblems.isValidParantheses;

public class ValidParanthesesTest {
    @Test
    public void ex1() {
        String s = "()";
        assertThat(isValidParantheses(s))
                .isTrue();
    }

    @Test
    public void ex2() {
        String s = "()[]{}";
        assertThat(isValidParantheses(s))
                .isTrue();
    }

    @Test
    public void ex3() {
        String s = "(]";
        assertThat(isValidParantheses(s))
                .isFalse();
    }

    @Test
    public void ex4() {
        String s = "([])";
        assertThat(isValidParantheses(s))
                .isTrue();
    }
    @Test
    public void ex5() {
        String s = "(])";
        assertThat(isValidParantheses(s))
                .isFalse();
    }

    @Test
    public void rn1() {
        String s = "([)]";
        assertThat(isValidParantheses(s))
                .isFalse();
    }

    @Test
    public void rn2() {
        String s = "(){}}{";

        assertThat(isValidParantheses(s))
                .isFalse();
    }
}
