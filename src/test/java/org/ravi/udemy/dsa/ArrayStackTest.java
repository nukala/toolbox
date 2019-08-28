package org.ravi.udemy.dsa;


import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ArrayStackTest {
    private TheStack<String> stack = new MyArrayStack<>();

     @Test
    public void initiallyEmpty() {
        assertThat(stack.isEmpty())
                .as("empty check")
                .isEqualTo(true);
     }

    @Test
    public void sizeCheck() {
        assertThat(stack.getSize())
                .as("size check")
                .isEqualTo(0);

    }

    @Test
    public void pushTooMany() {
        stack = new MyArrayStack<>(2);
        stack.push("Fremont");
        stack.push("California");

        assertThat(stack.getSize())
                .isEqualTo(2);

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> stack.push("foo"))
                .withNoCause();
    }

    @Test
    public void peekTest() {
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> stack.peek())
                .withMessageContaining("empty");

        stack.push("Mission");
        assertThat(stack.peek())
                .isEqualTo("Mission");
        stack.push("San Jose");
        assertThat(stack.peek())
                .isEqualTo("San Jose");
    }

    @Test
    public void exampleFromVideo() {
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> stack.pop())
                .withMessageContaining("empty")
                .withNoCause();

        stack.push("Google");
        stack.push("Udemy");
        stack.push("discord");

        assertThat(stack.getSize())
                .isEqualTo(3);

        assertThat(stack.pop())
                .isEqualTo("discord");
        assertThat(stack.getSize())
                .isEqualTo(2);

        assertThat(stack.pop())
                .isEqualTo("Udemy");
        assertThat(stack.getSize())
                .isEqualTo(1);

        assertThat(stack.pop())
                .isEqualTo("Google");
        assertThat(stack.getSize())
                .isEqualTo(0);
        assertThat(stack.isEmpty())
                .isTrue();

        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> stack.pop());
        stack.push("Of");
        assertThat(stack.getSize())
                .isEqualTo(1);
        assertThat(stack.peek())
                .isEqualTo("Of");
        assertThat(stack.pop())
                .isEqualTo("Of");
    }
}
