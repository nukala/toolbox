package org.ravi.educative;

import lombok.Getter;
import lombok.Setter;

import java.util.StringJoiner;

@Getter
@Setter
public final class QueueNode<T> {
    private T payload;
    private QueueNode<T> prev;

    public QueueNode(T val) {
        super();
        this.prev = null;
        this.payload = val;
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

