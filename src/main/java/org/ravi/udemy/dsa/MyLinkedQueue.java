package org.ravi.udemy.dsa;


import org.ravi.educative.QueueNode;

// FIFO
public class MyLinkedQueue<T> implements TheQueue<T> {
    private int length;
    private QueueNode<T> top;
    private QueueNode<T> bottom;

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("empty");
        }

        return bottom.getPayload();
    }

    @Override
    public TheQueue<T> enqueue(T value) {
        QueueNode<T> newNode = new QueueNode<>(value);

        if (top == null) {
            top = newNode;
        } else {
            top.setPrev(newNode);
            top = newNode;
        }
        if (bottom == null) {
            bottom = top;
        }
        length++;

        return this;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("empty");
        }

        QueueNode<T> tmp = bottom;
        bottom = bottom.getPrev();
        length--;
        return tmp.getPayload();
    }

    @Override
    public int getSize() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    public boolean isFull() {
        return length == getSize();
    }
}
