package org.ravi.udemy.dsa;

// FIFO
public interface TheQueue<T> {
    T peek();

    /**
     * To allow for method chaining
     */
    TheQueue<T> enqueue(T value);

    T dequeue();

    int getSize();

    boolean isEmpty();

    boolean isFull();
}
