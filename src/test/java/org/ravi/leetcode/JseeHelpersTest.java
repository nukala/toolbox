package org.ravi.leetcode;

import org.junit.Test;
import org.ravi.udemy.dsa.WorthLooking;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

public class JseeHelpersTest {

    // https://leetcode.com/problems/simplify-path/editorial/
    public static String simplifyPath(String path) {
        // Initialize a deque
        Deque<String> deque = new ArrayDeque<>();

        String[] components = path.split("/");

        // Split the input string on "/" as the delimiter
        // and process each portion one by one
        for (String directory : components) {
            // A no-op for a "." or an empty string
            if (directory.equals(".") || directory.isEmpty()) {
                continue;
            } else if (directory.equals("..")) {

                if (!deque.isEmpty()) {
                    deque.removeFirst();
                }
            } else {
                deque.addFirst(directory);
            }
        }

        // Stich together all the directory names together
        StringBuilder result = new StringBuilder();
        while (!deque.isEmpty()) {
            result.append("/").append(deque.removeLast());
        }

        return result.length() > 0 ? result.toString() : "/";
    }

    @Test
    public void sbDeleteChar() {
        StringBuilder sb = new StringBuilder(10);
        sb.append("Cablifornia");
        assertThat(sb.toString()).isEqualToIgnoringCase("cablifornia");
        sb.deleteCharAt(2);
        assertThat(sb.toString()).isEqualTo("California");
    }

    @Test
    public void firstKeyTreeMapDoesNotRemove() {
        TreeMap<String, Boolean> map = new TreeMap<>();
        map.put("z", Boolean.TRUE);
        map.put("d", Boolean.TRUE);
        map.put("a", Boolean.TRUE);

        assertThat(map.size()).isEqualTo(3);
        String first = map.firstKey();
        assertThat(first).isEqualTo("a");
        assertThat(map.size()).isEqualTo(3);
    }

    @Test
    public void treeMapHigherVsCeilingKey() {
        TreeMap<Integer, String> map = new TreeMap<Integer, String>();

        map.put(0, "zero");
        map.put(20, "twenty");
        map.put(62, "sixtyTwo");
        map.put(70, "seventy");
        map.put(65, "sixtyFive");
        map.put(30, "thirty");
        map.put(63, "sixtyThree");
        map.put(40, "forty");
        map.put(110, "oneHundredTen");

        System.out.printf(">>> %s <<< %n", map);

        @WorthLooking("pay attention to xx_yy")
        int key = 30;
        // next greater key-value
        System.out.printf("Ceiling(%d): equal_or_greater is [%s]%n", key, map.ceilingEntry(key));
        // greater than or equal to number
        key = 64;
        System.out.printf("Ceiling(%d): equal_or_greater is [%s]%n", key, map.ceilingEntry(key));

        // slightly below
        key = 38;
        System.out.printf("Floor: less_or_equal (slightly below) for %d is [%s]%n", key, map.floorEntry(key));
        key = 63;
        System.out.printf("Floor: less_or_equal (slightly below) for %d is [%s]%n", key, map.floorEntry(key));

        System.out.printf("==%n");
        // getting higher key value for 3
        key = 30;
        System.out.printf("Higher: next_greater than %d is [%s]%n", key, map.higherEntry(key));
        key = 63;
        System.out.printf("Higher: next_greater than %d is [%s]%n", key, map.higherEntry(key));

        // definitely below
        key = 38;
        System.out.printf("Lower: less_than (definitely below) for %d is [%s]%n", key, map.lowerEntry(key));
        key = 62;
        System.out.printf("Lower: less_than (definitely below) for %d is [%s]%n", key, map.lowerEntry(key));
    }

    @Test
    public void problem71() {
        assertThat(simplifyPath("/a/b////c/../././d/../f/"))
                .isEqualTo("/a/b/f");

        assertThat(simplifyPath("/.../a/../b/c/../d/./"))
                .isEqualTo("/.../b/d");
    }

    @Test
    public void movingAvgTest() {
        MovingAverage avg = new MovingAverage(3, MovingType.QUEUE);
        String type = avg.getType().name();
        assertThat(avg.next(1)).describedAs(type).isEqualTo(1.0);
        assertThat(avg.next(10)).describedAs(type).isEqualTo(5.5);
        assertThat(avg.next(3)).describedAs(type).isEqualTo(4.66, withPrecision(2d));
        assertThat(avg.next(5)).describedAs(type).isEqualTo(6.0);
        assertThat(avg.next(4)).describedAs(type).isEqualTo(4.0);

        avg = new MovingAverage(3, MovingType.DEQUEUE);
        assertThat(avg.next(1)).describedAs(type).isEqualTo(1.0);
        assertThat(avg.next(10)).describedAs(type).isEqualTo(5.5);
        assertThat(avg.next(3)).describedAs(type).isEqualTo(4.66, withPrecision(2d));
        assertThat(avg.next(5)).describedAs(type).isEqualTo(6.0);
        assertThat(avg.next(4)).describedAs(type).isEqualTo(4.0);
    }

    private enum MovingType {QUEUE, DEQUEUE}

    class MovingAverage {
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
    }
}
