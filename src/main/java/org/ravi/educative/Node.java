package org.ravi.educative;

import lombok.Getter;
import lombok.Setter;

import java.util.StringJoiner;

@Getter
@Setter
public final class Node<T> {
    private T payload;
    private Node<T> next;

    private Node(T val) {
        super();
        this.next = null;
        this.payload = val;
    }

    public static <T> Node<T> of(T value) {
        return new Node<>(value);
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