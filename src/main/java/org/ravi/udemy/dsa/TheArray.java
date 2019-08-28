package org.ravi.udemy.dsa;

// corresponding impl will be My+Suffix
public interface TheArray<T> {
    int getCount();

    int getLength();

    TheArray push(T obj);

    T pop();

    T peek();

    T elementAt(int index);

    boolean isEmpty();
}
