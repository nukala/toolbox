package org.ravi.udemy.dsa;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ravi.udemy.dsa.Strings.reverseSlow;
import static org.ravi.udemy.dsa.Strings.reverse;

public class ReverseStringTest {
    @Test
    public void reverseThis() {
        assertThat(reverseSlow("this"))
                .isEqualTo("siht");
    }

    @Test
    public void reverseHello() {
        assertThat(reverseSlow("hello"))
                .isEqualTo("olleh");
    }

    @Test
    public void reverseEmpty() {
        assertThat(reverseSlow(null))
                .isNull();
        assertThat(reverseSlow(""))
                .isEmpty();
    }

    @Test
    public void reverseFromTheCourse() {
        assertThat(reverseSlow("Hi My name is Andrei"))
                .isEqualTo("ierdnA si eman yM iH");
    }

    @Test
    public void reverseEfficientTests() {
        assertThat(reverse(null))
                .isNull();
        assertThat(reverse(""))
                .isEmpty();

        assertThat(reverse("Hello"))
                .isEqualTo("olleH");
        assertThat(reverse("ravi"))
                .isEqualTo("ivar");
    }
}
