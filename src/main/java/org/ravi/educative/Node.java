package org.ravi.educative;

import java.util.StringJoiner;

public final class Node<T> {
    private T payload;
    private Node<T> next;

    public Node(T val) {
        super();
        this.next = null;
        this.payload = val;
    }

    public T getPayload() {
        return payload;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> node) {
        this.next = node;
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner(" >> ");
        joiner.add(getPayload().toString());

        Node<T> node = next;
        while (node != null) {
            joiner.add(node.getPayload().toString());
            node = node.getNext();
        }
        return joiner.toString();
    }
}