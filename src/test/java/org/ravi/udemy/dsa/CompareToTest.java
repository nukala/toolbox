package org.ravi.udemy.dsa;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        
        assertThat(Integer.valueOf(1).compareTo(small))
                .as("zero since both are equal")
                .isNotPositive()
                .isNotNegative()
                .isZero();
    }
}
