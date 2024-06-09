package org.ravi.rutils;

import org.junit.Test;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * JDK has many improvements that I am not fully familiar with.
 * These tests help me validate those.
 */
public class DeepDiveIntoJdk21Test {
    // Inspired from: medium.com/capital-one-tech/java-programming-a-deep-dive-into-java-21s-key-features-8776f75bc6b8

    private interface SomeIface {
        void someMethod();
    }

    private class SomeImpl implements SomeIface {
        @Override
        public void someMethod() {
            System.out.printf("Impl - Entered %s%n", "someMethod");
        }
    }

    private void someCaller(SomeIface iface) {
        System.err.printf("iface=%s %n", iface.getClass());
        iface.someMethod();
    }

    @Test
    public void varTest() {
        var foo = new SomeImpl();

        someCaller(foo);
        var str = "foo";
        // DOES NOT COMPILE someCaller(str);
    }

    @Test
    public void usingRecordLessBoilerPlateCode() {
        record Person(long id, String name, int age) {
        }

        var p1 = new Person(1234L, "Teddy", 12);
        var p2 = new Person(1234L, "Teddy", 12);
        
        assertThat(p1.equals(p2)).isTrue();
        assertThat(p1.hashCode() == p2.hashCode()).isTrue();

        // not yet in corretto21 - 24jun05 var p3 = p2 with { age = 11 };
    }
}
