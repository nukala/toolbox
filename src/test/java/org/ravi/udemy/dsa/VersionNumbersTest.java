package org.ravi.udemy.dsa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ravi.udemy.dsa.VersionNumbers.check;

// to compare version numbers such as
//  15 > 14; 15.0.1 < 15.0.2 and 15.0 < 15.0.0.1 and so on
// see p11 of TOP-30-Java-Coding-Tasks in read.amazon.com
@RunWith(Parameterized.class)
public class VersionNumbersTest {

    @Parameterized.Parameter
    public String first;
    @Parameterized.Parameter(1)
    public String second;
    @Parameterized.Parameter(2)
    public int expected;

    // for tests use parameterized
    @Parameterized.Parameters(name = "compare({0}, {1}) = {2}")
    public static Collection testData() {
        // v1, v2, expected-answer
        return Arrays.asList(new Object[][]{
                {"15", "14", 1},
                {"15.0.1", "15.0.2", -1},
                {"15.0.0", "15", 0},
                {"15", "15.0.0.0.1", -1},
                {"15.0.0.0.0.1", "15", 1},
        });
    }

    @Test
    public void test() {
        assertThat(check(first, second))
                .as(String.format("compare(%s, %s) = %d", first, second, expected))
                .isEqualTo(expected);
    }
}
