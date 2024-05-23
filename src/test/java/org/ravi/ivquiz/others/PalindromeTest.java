package org.ravi.ivquiz.others;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PalindromeTest {
    boolean isPalindrome(String s) {
        if ((s == null) || (s.length() == 0)) {
            return false;
        } else if (s.length() == 1) {
            return true;
        }

        int len = s.length();
        int mid = len / 2;
        for (int i = 0; i <= mid; i++) {
            char ch = s.charAt(i);
            char oth = s.charAt(len - 1 - i);
            if (ch == oth) {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }

    boolean isPalindromeNumber(String s) {
        throw new IllegalArgumentException("RNTODO finish me!");
    }

    public boolean isPalindromeNumber(long l) {
        return isPalindromeNumber(Long.toString(l));
    }

    @Test
    public void palindromeChk() {
        assertThat(isPalindrome("cattac")).as("cattac").isTrue();
        assertThat(isPalindrome("abba")).as("abba").isTrue();
        assertThat(isPalindrome("abc")).as("abc").isFalse();
        assertThat(isPalindrome("")).isFalse();
        assertThat(isPalindrome(null)).isFalse();
        assertThat(isPalindrome("a")).isTrue();
        assertThat(isPalindrome("aa")).as("aa").isTrue();
        assertThat(isPalindrome("axa")).as("axa").isTrue();
    }
}
