package org.ravi.udemy.dsa;

import com.google.common.collect.ImmutableMap;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Orphan tests
 */
public class SimpleTest {
    // in JS this seems to be wrong. Lets see in jdk
    // 01:25 https://www.udemy.com/master-the-coding-interview-data-structures-algorithms/learn/lecture/12397596#overview
    //
    // Chrome console is OK, repl.it FAILS, jdk OK too.
    @Test
    public void sillyNumericTest() {
        List<Integer> list = Lists.newArrayList(2, 65, 34, 2, 1, 7, 8);

        assertThat(list)
                .as("unsorted intial input")
                .containsExactly(2, 65, 34, 2, 1, 7, 8);
        Collections.sort(list);
        System.out.printf("After sorting=%s %n", list);
        assertThat(list)
                .as("after sorting")
                .containsExactly(1, 2, 2, 7, 8, 34, 65);
    }

    @Test
    public void hashingFunctionTest() {
        int h = Math.abs("hello world! this is ravi".hashCode());

        int shifted = h >>> 16;
        int index = h ^ shifted;
        System.out.printf("orig=%d, index=%d shifted=%d %n", h, index, shifted);
    }

    @Test
    public void convertMapToList() {
        Map<Integer, String> map = ImmutableMap.of(1, "1", 2, "2", 3, "3",
                4, "4", 5, "5");

        String[] copy = map.values().toArray(new String[0]);
        assertThat(copy)
                .containsExactlyInAnyOrder("1", "3", "2", "4", "5");
    }

    @Test
    public void computeIfAbsentMapOfList() {
        Map<String, List<String>> map = new HashMap<>();
        map.computeIfAbsent("key1", k -> new ArrayList<>()).add("value1");
        map.computeIfAbsent("key1", k -> new ArrayList<>()).add("value2");

        assertThat(map.get("key1").get(0)).isEqualTo("value1");
        assertThat(map.get("key1").get(1)).isEqualTo("value2");
        assertThat(map.get("key1"))
                .containsExactlyInAnyOrder("value2", "value1");
    }

    @Test
    // From: github.com/kishanjavatrainer/ConcurrentSkipListMapDemo/.../ClientTest2.java
    public void usingConcurrentSkipListMap_NotTest() {
        ConcurrentSkipListMap<String, Integer> map = new ConcurrentSkipListMap<>();
        IntStream.rangeClosed(1, 8)
                .forEach((i) -> map.put(Integer.toString(i), i));

        System.out.println("-------- Traversing the map --------");
        // using for-each loop in Java 8
        map.forEach((key1, value1) -> System.out.printf("[%s]=:%s: ", key1, value1));
        System.out.printf("%n");

        System.out.println("------ Map keys in reverse order ---------");
        map.descendingKeySet()
                .forEach((ss) -> System.out.printf("[%s] ", ss));
        System.out.printf("%n");

        System.out.println("---- Floor entry(closest <= 4)-----");
        Map.Entry<String, Integer> tempMap = map.floorEntry("4");
        System.out.println(tempMap);

        System.out.println("---- Head Map (all keys < 3) -----");
        ConcurrentNavigableMap<String, Integer> headMap = map.headMap("3");
        // using for-each loop in Java 8
        headMap.forEach((key, value) -> System.out.printf("[%s]=:%d: ", key, value));
        System.out.printf("%n");

        System.out.printf("--- Removing all headMap entries from map%n");
        headMap.forEach(map::remove);
        map.forEach((key, value) -> System.out.printf("[%s]=:%s: ", key, value));
        System.out.printf("%n");

        System.out.println("---- Higher entry (next Higher after key=3) -----");
        System.out.println(map.higherEntry("3"));
    }

    @Test
    public void linkedHashSetTest() {
        LinkedHashSet<String> streets = new LinkedHashSet<>();
        streets.add("Mowry");
        streets.add("Stevenson");
        streets.add("Washington");
        streets.add("Fremont");
        streets.add("Bay");

        assertThat(streets.add("Bay"))
                .isFalse();
    }
}
