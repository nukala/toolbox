package org.ravi.educative;

import java.util.StringJoiner;

public final class QueueNode<T> {
    private T payload;
    private QueueNode<T> prev;

    public QueueNode(T val) {
        super();
        this.prev = null;
        this.payload = val;
    }

    public T getPayload() {
        return payload;
    }

    public QueueNode<T> getPrev() {
        return prev;
    }

    public void setPrev(QueueNode<T> node) {
        this.prev = node;
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner(" >> ");
        joiner.add(getPayload().toString());

        QueueNode<T> node = prev;
        while (node != null) {
            joiner.add(node.getPayload().toString());
            node = node.getPrev();
        }
        return joiner.toString();
    }
}

