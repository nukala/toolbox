package org.ravi.java.util;

import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test to repeat/verify claims made in various medium articles
 */
public class MediumTest {

    @Test
    @Ignore("All assertions fail -  as expected!")
    public void oneAnd128() {
        // medium.com/@tecnicorabi/why-1-1-is-true-but-128-128-is-false-in-java-4ea544e83eef
        Integer a = 1, b = 2, x = 128, y = 128;

        assertThat(a == b).describedAs("1: ==").isFalse();
        assertThat(x == y).describedAs("128: == ").isFalse();

        assertThat(a.equals(b)).describedAs("1: equals").isTrue();
        assertThat(x.equals(y)).describedAs("128: equals ").isTrue();
    }
}
