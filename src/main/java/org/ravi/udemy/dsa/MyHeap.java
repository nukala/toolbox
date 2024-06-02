package org.ravi.udemy.dsa;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.Consumer;

/**
 * MinHeap has the smallest element at the root. Reverse for Max
 */
public class MyHeap<E> {
    private PriorityQueue<E> pq;

    /**
     * RNTODO - these two comparators call each other ... yucky implementation. needs improvement
     */
    private static <T> Comparator<T> ascending(Comparator<T> baseComp) {

        return (o1, o2) -> {
            if (baseComp != null) {
                return baseComp.compare(o1, o2);
            }
            if (o1 instanceof Comparable<?>) {
                return ((Comparable<T>) o1).compareTo(o2);
            }

            throw new IllegalArgumentException("no base comparator AND not comparable");
        };
    }

    private static <T> Comparator<T> descending(Comparator<T> baseComp) {
        return (o1, o2) -> {
            int ans = ascending(baseComp).compare(o1, o2);

            if (ans < 0) {
                return 1;
            } else if (ans > 0) {
                return -1;
            }
            return 0;
        };
    }

    private MyHeap(int capacity, Comparator<E> comparator) {
        pq = new PriorityQueue<E>(capacity, comparator);
    }

    public static <T> MyHeap<T> MinHeap(Comparator<T> comparator) {
        return new MyHeap(11, ascending(comparator));
    }

    public static <T> MyHeap<T> MaxHeap(Comparator<T> comparator) {
        return new MyHeap(11, descending(comparator));
    }

    public boolean add(E e) {
        return pq.add(e);
    }

    public boolean offer(E e) {
        return pq.offer(e);
    }

    public E peek() {
        return pq.peek();
    }

    public boolean remove(E e) {
        return pq.remove(e);
    }

    public boolean contains(E e) {
        return pq.contains(e);
    }

    public void clear() {
        pq.clear();
    }

    public E poll() {
        return pq.poll();
    }

    public void forEach(Consumer<? super E> action) {
        pq.forEach(action);
    }
}
