package org.ravi.ivquiz.others;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ravi.interview.StringCompressor.compress;

public class StringCompressorTest {
    @Test
    public void boundaries() {
        assertThat(compress(null))
                .isEqualTo("");
        assertThat(compress(""))
                .isEqualTo("");
        assertThat(compress(" "))
                .isEqualTo(" ");
        assertThat(compress("   "))
                .isEqualTo(" 3");
    }

    @Test
    public void documentedCases() {
        assertThat(compress("aaabbbccc"))
                .isEqualTo("a3b3c3");
        assertThat(compress("aabbcc"))
                .isEqualTo("aabbcc");
        assertThat(compress("abc"))
                .isEqualTo("abc");
        assertThat(compress("aaaaaabbbbbaaaa"))
                .isEqualTo("a6b5a4");

    }
}
