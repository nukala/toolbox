package org.ravi.udemy.dsa;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class LinkedQueueTest {
    private TheQueue<String> queue = new MyLinkedQueue<>();

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
        assertThat(queue.getSize()).isEqualTo(3);
        assertThat(queue.dequeue()).isEqualTo("United States");
        assertThat(queue.dequeue()).isEqualTo("North America");
        assertThat(queue.dequeue()).isEqualTo("Earth");

    }
}
