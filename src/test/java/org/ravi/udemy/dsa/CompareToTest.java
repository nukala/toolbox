package org.ravi.udemy.dsa;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

// used to be simpletest, but renamed to be help with finds.
public class CompareToTest {

    @Test
    @WorthLooking("compareToTest: proves -1 (this < param) and 1 (this > param) scenario")
    public void compareToTest() {
        Integer small = 1;
        Integer large = 9;

        assertThat(small.compareTo(large))
                .as("negative since this < param")
                .isNegative()
                .isNotPositive()
                .isEqualTo(-1);

        assertThat(large.compareTo(small))
                .as("positive since this > param")
                .isPositive()
                .isNotNegative()
                .isEqualTo(1);

        large = Integer.parseInt("1");
        assertThat(large.compareTo(small))
                .as("zero since both are equal")
                .isZero();
    }
}
