package org.ravi.java.jdk8;

import org.junit.Test;
import org.ravi.udemy.dsa.WorthLooking;

import java.util.TreeMap;

public class TreeMapTest {
    // figure out a consumer to parameterize this code and add asserts! - RNTODO
    @Test
    public void treeMapHigherVsCeilingKey() {
        TreeMap<Integer, String> map = new TreeMap<>();

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

        @WorthLooking("pay attention to xx_yy messages")
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
}
