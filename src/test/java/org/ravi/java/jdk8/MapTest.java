package org.ravi.java.jdk8;

import org.junit.Test;
import org.ravi.udemy.dsa.WorthLooking;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

// to help test some Map methods to help my understanding
public class MapTest {
    @Test
    public void fakeValuesTest_byObservation() {
        @WorthLooking("Values() are not insertion order, looks like keySet order!")
        Map<String, String> map = new HashMap<>();

        map.put("one", "1");
        map.put("two", null);
        map.put("three", "3");
        map.put("four", null);
        map.put("five", "5");

        System.out.printf("Map before printing anything[%s]%n", map);
        for (String value : map.values()) {
            System.out.println("value = " + value);
        }
        System.out.println("==== ");
        for (String key : map.keySet()) {
            System.out.printf("key=[%s]%n", key);
        }
    }

    @Test
    public void removalWithDescendingMap() {
        NavigableMap<Integer, String> map = new TreeMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");

        map.keySet().forEach(key -> System.out.print(key + " "));
        System.out.println();

        NavigableSet<Integer> descendingKeys = map.descendingKeySet();
        descendingKeys.forEach(key -> System.out.print(key + " "));

        assertThat(descendingKeys.contains(3)).isTrue();
        assertThat(descendingKeys.remove(3)).describedAs("removing key=3, should succeed").isTrue();
        assertThat(descendingKeys.contains(3)).describedAs("not exist after remove").isFalse();
        assertThat(map.containsKey(3)).describedAs("map - not exist after remove").isFalse();
    }

    @Test
    public void keySetStream_byObservation() {
        Map<String, String> map = Map.of(
                "1", "one",
                "2", "two",
                "3", "three",
                "10", "ten",
                "11", "eleven",
                "22", "twentytwo",
                "33", "thirtythree"
        );

        map.keySet().forEach(k -> System.out.printf("%s = %s%n", k, map.get(k)));
        System.out.println("=== from stream of keys");
        int cutoff = 10;
        map.keySet().stream()
                .filter(s -> Integer.parseInt(s) > cutoff)
                .forEach(k -> System.out.printf("greater_than%d[%s] = [%s] ", cutoff, k, map.get(k)));
    }
}
