package org.ravi.rutils.sams.dsa2;

import org.junit.Test;
import org.ravi.udemy.dsa.TheQueue;

import static org.assertj.core.api.Assertions.*;

public class PriorityQueueTest {
    @Test
    public void allMethods() {
        TheQueue<Integer> pq = new PriorityQueue<>();

        assertThat(pq.isFull()).isFalse();
        assertThat(pq.isEmpty()).isTrue();
        pq.enqueue(40);
        assertThat(pq.peek()).isEqualTo(40);
        pq.enqueue(30);
        assertThat(pq.peek()).isEqualTo(30);
        pq.enqueue(50);
        assertThat(pq.peek()).isEqualTo(30);
        assertThat(pq.isFull()).isFalse();
        pq.enqueue(50);
        pq.enqueue(51);
        assertThat(pq.dequeue()).isEqualTo(30);
        assertThat(pq.dequeue()).isEqualTo(40);
        assertThat(pq.dequeue()).isEqualTo(50);
        assertThat(pq.dequeue()).isEqualTo(50);
        assertThat(pq.dequeue()).isEqualTo(51);
        assertThat(pq.isEmpty()).isTrue();

        assertThat(pq.peek()).isNull();
    }
}
