package org.ravi.educative;

// https://www.educative.io/collection/page/5642554087309312/5679846214598656/70003
// modified to convert into a true LinkedList

// corresponding impl will be My+Suffix
public interface TheLinkedList<T> {
    String display();

    T valueAt(int index);

    int getLength();

    void append(T value);

    void prepend(T value);

    T insert(T value, int index);

    T remove(int index);

    /**
     * reverse in a loop
     */
    //https://algorithms.tutorialhorizon.com/reverse-a-linked-list
    default void reverse() {
        throw new RuntimeException("TODO - " + getClass().getSimpleName() + "::reverse");
    }

    // recursively walk down to the last
    // when no more start printing?
    default String printReverse() {
        throw new RuntimeException("TODO - " + getClass().getSimpleName() + "::printReverse");
    }

    /**
     * Wrapper for recursive reverse in Node class
     */
    default void reverseRecursive() {
        throw new RuntimeException("TODO - " + getClass().getSimpleName() + "::reverseRecursive");
    }
}
