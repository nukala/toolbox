package org.ravi.rutils.sams.dsa2;

import org.ravi.udemy.dsa.TheQueue;

/**
 * Natural order comparision. T is comparable or ClassCast
 * <p>
 * smallest is the first to be removed. Ascending-order-queue
 * p148 or 173-in-pdf
 */
public class PriorityQueue<T extends Comparable<T>> implements TheQueue<T> {
    private Object[] array;
    private int count;

    public PriorityQueue() {
        this(10);
    }

    public PriorityQueue(int size) {
        array = new Object[size];
        count = 0;
    }

    private void ensureCapacity() {
        //TODO
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        if (count == 0) {
            return null;
        }
        return (T) array[count - 1];
    }

    @Override
    @SuppressWarnings("unchecked")
    public TheQueue<T> enqueue(T item) {
        ensureCapacity();
        if (count == 0) {
            array[count++] = item;
        } else {
            int smallest;
            for (smallest = count - 1; smallest >= 0; smallest--) {
                int result = item.compareTo((T) array[smallest]);
                if (result > 0) { // new item is bigger, so move up
                    array[smallest + 1] = array[smallest];
                } else {
                    break;
                }
            }
            array[smallest + 1] = item;
            count++;
        }

        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T dequeue() {
        return (T) array[--count];
    }

    @Override
    public int getSize() {
        return array.length;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public boolean isFull() {
        return count == array.length;
    }
}
