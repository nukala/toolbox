package org.ravi.java.jdk8;

import org.junit.Test;
import org.ravi.udemy.dsa.WorthLooking;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for string methods that needed samples to help me understand
 */
public class StringMethodsTest {
    @Test
    public void fullSubstrings() {
        String sub = "abc";
        String repl = "";
        String str = "abcabc";

        @WorthLooking("replaces matching substrings with replacement string. 1.5+")
        // after replacing matching substrings with empty
        String after = str.replace(sub, repl);
        System.out.printf("%s replace-substrings with \"%s\" is [%s]%n", str, repl, after);
        assertThat(after)
                .describedAs("sub=[%s], repl=[%s]", sub, repl)
                .isEqualTo(repl);
    }

    @Test
    public void partialSubstring() {
        String sub = "abc";
        String repl = "";
        String str = "abc55";
        String after = str.replace(sub, repl);

        System.out.printf("%s replace-substrings with \"%s\" is [%s]%n", str, repl, after);
        assertThat(after)
                .describedAs("sub=[%s], repl=[%s]", sub, repl)
                .isEqualTo("55");
    }
}
