package org.ravi.udemy.dsa;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ravi.udemy.dsa.Strings.reverse;


public class ReverseStringTest {


    @Test
    public void reverseThis() {
        assertThat(reverse("this"))
                .isEqualTo("siht");
    }

    @Test
    public void reverseHello() {
        assertThat(reverse("hello"))
                .isEqualTo("olleh");
    }

    @Test
    public void reverseNull() {
        assertThat(reverse(null))
                .isNull();
    }

    @Test
    public void reverseFromTheCourse() {
        assertThat(reverse("Hi My name is Andrei"))
                .isEqualTo("ierdnA si eman yM iH");
    }
}
