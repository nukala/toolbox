package org.ravi.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class MovingAverage {
    final int size;
    final MovingType type;
    private final Deque<Integer> deque;
    private final Deque<Integer> queue;
    int sum = 0;

    MovingAverage(int size, MovingType type) {
        this.size = size;
        this.type = type;
        this.deque = new ArrayDeque<>(size);
        this.queue = new ArrayDeque<>(size);
    }

    public MovingType getType() {
        return type;
    }

    /**
     * moving average across all the values (subject to size) that were sent via next
     */
    public double next(int num) {
        int kickedOut = 0;
        int denom = 1;
        switch (type) {
            case QUEUE: {
                queue.add(num);
                kickedOut = (queue.size() > size) ? queue.poll() : 0;
                denom = queue.size();
                break;
            }
            case DEQUEUE: {
                deque.addLast(num);
                kickedOut = (deque.size() > size) ? deque.removeFirst() : 0;
                denom = deque.size();
                break;
            }
        }

        sum -= kickedOut;
        sum += num;
        return ((double) sum / denom);
    }

    public static enum MovingType {QUEUE, DEQUEUE}
}
