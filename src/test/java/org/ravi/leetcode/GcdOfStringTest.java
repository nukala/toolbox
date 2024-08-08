package org.ravi.leetcode;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

// 1071. Greatest Common Divisor of String
public class GcdOfStringTest {
    private static boolean isSubStr(String s, String base, int k) {
        // if length check works ... replace-substrings and see
        if ((k > 0) && (s.length() % k == 0)) {
            String after = s.replace(base, "");
            return after.isEmpty();
        }

        return false;
    }

    public static String gcd(String s, String t) {
        if ((s == null) || (t == null)) {
            return "";
        }
        int slen = s.length();
        int tlen = t.length();
        String base = tlen <= slen ? t : s;

        for (int i = Math.min(slen, tlen); i > 0; i--) {
            if (isSubStr(s, base, i)) {
                return s.substring(0, i);
            } else {
                base = base.substring(0, base.length() - 1);
            }
        }

        return "";
    }

    @Test
    public void ex1() {
        assertThat(gcd("ABCABC", "ABC"))
                .isEqualTo("ABC");
    }

    @Test
    public void ex2() {
        assertThat(gcd("ABABAB", "ABAB"))
                .isEqualTo("AB");
    }

    @Test
    public void ex3() {
        assertThat(gcd("LEET", "CODE"))
                .isEqualTo("");
    }
}
