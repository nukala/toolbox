package org.ravi.udemy.dsa;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyHashMapTest {
    @Test
    public void putGet() {
        TheHashMap map = new MyHashMap();

        map.put("hello", "world");
        assertThat(map.get("hello"))
                .isEqualTo("world");
    }

    @Test
    public void collissions() {
        TheHashMap map = new MyHashMap(1);

        map.put("hello", "world");
        map.put("Fremont", "CA");
        map.put("City", "San Francisco");

        assertThat(map.get("hello"))
                .isEqualTo("world");
        assertThat(map.get("Fremont"))
                .isEqualTo("CA");
        assertThat(map.get("City"))
                .isEqualTo("San Francisco");
        assertThat(map.get("boo"))
                .as("unknown key")
                .isNull();
        assertThat(map.getSize())
                .isEqualTo(3);
    }

    @Test
    public void containsTest() {
        TheHashMap map = new MyHashMap(5);

        map.put("Hello", "World");
        assertThat(map.containsKey("Hello"))
                .isTrue();
        assertThat(map.containsKey("City"))
                .as("Unknown key")
                .isFalse();
    }


    @Test
    public void keysTest() {
        TheHashMap map = new MyHashMap(2);

        map.put("hello", "world");
        map.put("Fremont", "CA");
        map.put("City", "San Francisco");
        map.put("City", "Fremont");
        map.put("city", "San Jose");

        MyJavaScriptArray cities = map.keys();
        assertThat(cities.getCount())
                .isEqualTo(5);
        assertThat(map.countCollissions())
                .as("between 2-3 collissions")
                .isBetween(2, 3);
        assertThat(cities.toString())
                .contains("hello", "Fremont", "City", "city");
        assertThat(map.getSize())
                .isEqualTo(5);
    }
}
