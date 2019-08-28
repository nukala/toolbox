package org.ravi.udemy.dsa;

public class MyArrayQueue<T> implements TheQueue<T> { // FIFO
    private int size;
    private int enqIndex;
    private Object[] array;

    public MyArrayQueue() {
        this(10);
    }

    public MyArrayQueue(int length) {
        this.array = new Object[length];
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("empty");
        }

        return (T) array[0];
    }

    // TODO: Disallow nulls, see https://docs.oracle.com/javase/tutorial/collections/interfaces/queue.html
    @Override
    public TheQueue<T> enqueue(T value) {
        if (enqIndex >= array.length) {
            throw new IllegalStateException("exhausted");
        }

        array[enqIndex++] = value;
        size++;
        return this;
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("empty");
        }

        T value = (T) array[0];
        // Still O(n)
        System.arraycopy(array, 1, array, 0, array.length - 1);

        enqIndex--;
        size--;
        return value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }
}
