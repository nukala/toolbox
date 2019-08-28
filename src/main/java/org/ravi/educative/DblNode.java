package org.ravi.educative;

public final class DblNode<T> {
    private DblNode<T> next;
    private DblNode<T> prev;
    private T value;

    public DblNode(T value) {
        this.value = value;
    }

    public DblNode<T> getNext() {
        return next;
    }

    public void setNext(DblNode<T> next) {
        this.next = next;
    }

    public DblNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DblNode<T> prev) {
        this.prev = prev;
    }

    public T getValue() {
        return value;
    }

    public String toString() {
        return value.toString();
    }
}
