package org.ravi.tlap;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;


public class LineStatsTest {
    @Test
    public void onlySpacesIsEmpty() {
        assertThat(LineStats.processLine("   " + System.lineSeparator()).isEmpty()).isTrue();
    }

    @Test
    public void verifyHelloWorld() {
        LineStats stats = LineStats.processLine("Hello World!");

        assertThat(stats.getNumWords()).isEqualTo(2);
        assertThat(stats.getLongestWord()).isEqualTo("World!");
        assertThat(stats.getLongestLen()).isEqualTo(6);
        assertThat(stats.getMostVowels()).isEqualTo(2);
    }
}
