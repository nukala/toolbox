package org.ravi.udemy.dsa;

// non-resizing array (deviation from the JavaScript code) LIFO
public class MyArrayStack<T> implements TheStack<T> {
    private int size;
    private final Object[] array;
    private int top;

    public MyArrayStack() {
        this(10);
    }

    public MyArrayStack(int length) {
        this.array = new Object[length];
        this.top = array.length;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("empty");
        }

        size--;
        return (T) array[top++];
    }

    @Override
    public void push(T value) {
        if (isFull()) {
            throw new RuntimeException("need to resize");
        }

        array[--top] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T peek() {
        if (size == 0) {
            throw new IllegalStateException("empty");
        }
        return (T) array[top];
    }

    @Override
    public int getSize() {
        return size;
    }

    private boolean isFull() {
        return size == array.length;
    }
}
