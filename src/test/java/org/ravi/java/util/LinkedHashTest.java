package org.ravi.java.util;

import org.junit.Test;

import java.util.*;

// to visually test insertion order of linked-hash-thingys
public class LinkedHashTest {
    private static String words[] = new String[]{"We", "live", "here", "in", "Fremont", "California"};

    private static <T> void addValues(Set<T> set, T values[]) {
        for (T v : values) {
            set.add(v);
        }
    }

    @Test
    public void linkedHashSet() {
        Set<String> set = new LinkedHashSet<>();

        addValues(set, words);
        System.out.printf("%20s: %s %n", "linkedHashSet", set);
    }

    @Test
    public void hashSet() {
        Set<String> set = new HashSet<>();

        addValues(set, words);
        System.out.printf("%20s: %s %n", "hashSet", set);
    }

    @Test
    public void linkedSetStream() {
        Set<String> set = new LinkedHashSet<>();

        addValues(set, words);

        StringBuilder sb = new StringBuilder(256);
        try (Formatter formatter = new Formatter(sb)) {
            set.stream()
                    .forEach(s -> formatter.format("%s ", s));
        }
        System.out.printf("%20s: %s %n", "linkedSetStream", sb);
    }

    @Test
    public void linkedSetIterator() {
        Set<String> set = new LinkedHashSet<>();

        addValues(set, words);
        StringBuilder sb = new StringBuilder(256);
        try (Formatter formatter = new Formatter(sb)) {
            Iterator<String> iter = set.iterator();
            while (iter.hasNext()) {
                formatter.format("%s ", iter.next());
            }
        }
        System.out.printf("%20s: %s %n", "linkedSetIterator", sb);
    }

    @Test
    public void hashSetIterator() {
        Set<String> set = new HashSet<>();

        addValues(set, words);
        StringBuilder sb = new StringBuilder(256);
        try (Formatter formatter = new Formatter(sb)) {
            Iterator<String> iter = set.iterator();
            while (iter.hasNext()) {
                formatter.format("%s ", iter.next());
            }
        }
        System.out.printf("%20s: %s %n", "hashSetIterator", sb);
    }
}
