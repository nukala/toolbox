package org.ravi.udemy.dsa;

// https://www.udemy.com/master-the-coding-interview-data-structures-algorithms/learn/lecture/12333060#overview
//
// this is like a stack of plates. LIFO
public interface TheStack<T> {
    boolean isEmpty();

    T pop();

    void push(T value);

    T peek();

    int getSize();
}
