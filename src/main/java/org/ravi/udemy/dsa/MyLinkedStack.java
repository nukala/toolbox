package org.ravi.udemy.dsa;

import org.ravi.educative.Node;

public class MyLinkedStack<T> implements TheStack<T> {
    private Node<T> top;
    //private Node<T> bottom;
    private int length;

    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("empty");
        }

        Node<T> tmp = top;
        top = top.getNext();
        length--;
        return tmp.getPayload();
    }

    @Override
    public void push(T value) {
        Node<T> tmp = Node.of(value);
        tmp.setNext(top);
        top = tmp;
        length++;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("empty");
        }

        return top.getPayload();
    }

    @Override
    public int getSize() {
        return length;
    }
}
