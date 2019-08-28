package org.ravi.udemy.dsa;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ArrayQueueTest {
    private TheQueue<String> queue = new MyArrayQueue<>();

    @Test
    public void checkEmptyBehavior() {
        assertThat(queue.isEmpty()).isTrue();
        assertThat(queue.getSize()).isEqualTo(0);

        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> queue.peek())
                .withNoCause()
                .withMessageContaining("empty");
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> queue.dequeue())
                .withNoCause()
                .withMessageContaining("empty");

    }

    @Test
    public void probableUseCase() {
        queue.enqueue("Fremont");
        queue.enqueue("California");
        queue.enqueue("United States");
        queue.enqueue("North America");

        assertThat(queue.getSize()).isEqualTo(4);
        assertThat(queue.isEmpty()).isFalse();

        assertThat(queue.dequeue()).isEqualTo("Fremont");
        assertThat(queue.dequeue()).isEqualTo("California");

        queue.enqueue("Earth");
        queue.enqueue("Solar system");

        assertThat(queue.getSize()).isEqualTo(4);
        assertThat(queue.dequeue()).isEqualTo("United States");
        assertThat(queue.dequeue()).isEqualTo("North America");
        assertThat(queue.dequeue()).isEqualTo("Earth");
    }

    @Test
    public void exhaustDeqThenEnq() {
        queue = new MyArrayQueue<>(2);

        assertThat(queue.getSize()).isEqualTo(0);
        queue.enqueue("One");
        queue.enqueue("Two");

        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> queue.enqueue("Three"))
                .withMessageContaining("exhausted")
                .withNoCause();

        // now dequeue and ensure we have ONE more spot
        assertThat(queue.dequeue())
                .isEqualTo("One");
        assertThat(queue.isEmpty()).isFalse();

        queue.enqueue("Three");
        assertThat(queue.getSize()).isEqualTo(2);
    }
}
