package org.ravi.udemy.dsa;

import java.util.StringJoiner;

// in addition to standard array stuff, it adds push_and_pop as seen in JavaScript
// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/ push_and_pop
//
// JavaScript does not care about types. It uses Object(s)

/**
 * To simulate a javascript Array.
 * <br/>
 * In addition to standard array stuff, it adds push_and_pop as seen in JavaScript
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/ push_and_pop
 * <p/>
 * JavaScript does not care about types. It uses Object(s)
 *
 * @param <T> parameter type of the "array".
 */
public class MyJavaScriptArray<T> implements TheArray<T> {
    private int next;
    private Object[] array;

    public MyJavaScriptArray() {
        this(10);
    }

    public MyJavaScriptArray(int length) {
        this.array = new Object[length];
    }

    @Override
    public int getCount() {
        return next;
    }

    @Override
    public int getLength() {
        return array.length;
    }

    @Override
    public boolean isEmpty() {
        return next == 0;
    }

    public MyJavaScriptArray<T> push(T obj) {
        array[next++] = obj;

        if (next >= array.length) {
            Object[] tmp = new Object[array.length * 2];
            System.arraycopy(array, 0, tmp, 0, array.length);
            array = tmp;
        }

        return this;
    }

    @SuppressWarnings({"unchecked"})
    public T pop() {
        if (next <= 0) {
            throw new IllegalStateException("nothing to pop");
        }

        return (T) array[--next];
    }

    @SuppressWarnings({"unchecked"})
    public T peek() {
        if (next <= 0) {
            throw new IllegalStateException("nothing to pop");
        }

        return (T) array[next - 1];
    }

    @SuppressWarnings({"unchecked"})
    public T elementAt(int index) {
        if (index >= next) {
            throw new IllegalStateException(String.format("Nothing to look at: next=%d, index=%d", next, index));
        }

        return (T) array[index];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("next=%d, length=%d", next, getLength()));
        if (next > 0) {
            StringJoiner joiner = new StringJoiner(",", ", elems=[", "]");
            for (int i = 0; i < next; i++) {
                joiner.add(array[i].toString());
            }
            sb.append(joiner);
        }
        return sb.toString();
    }
}
