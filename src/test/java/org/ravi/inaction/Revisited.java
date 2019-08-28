package org.ravi.inaction;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

// https://javarevisited.blogspot.com/2015/10/133-java-interview-questions-answers-from-last-5-years.html
public class Revisited {
    @Test(expected = ClassCastException.class)
    public void q21() {
        B b = new B();
        C c = (C) b;

        fail("should encounter class-cast-exception");
    }

    @Test
    public void q26() {
        float f = 0.1f;

        assertThat(3 * f)
                .as("should equal with variable")
                .isEqualTo(0.3f);

        assertThat(3 * 0.1)
                .as("float multiply without variable should be false")
                .isNotEqualTo(0.3f);
    }

    @Test(expected = OutOfMemoryError.class)
    public void tryFinallyWithError() {
        try {
            System.out.printf("In try block. Next throwing error %n");
            throw new OutOfMemoryError("blah");
        } finally {
            System.out.printf("finally %n");
        }
    }

    private class A {
    }

    private class B extends A {
    }

    private class C extends B {
    }
}
