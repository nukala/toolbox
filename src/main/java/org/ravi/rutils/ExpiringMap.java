package org.ravi.rutils;

/**
 * Given this timeline:
 * <p>
 * 12:00:00 - map.put(10, 25, 5000)
 * <p>
 * 12:00:04 - map.get(10) -> 25
 * <p>
 * 12:00:06 - map.get(10) -> null
 * <br/>
 * Write some code to complete this requirement.
 */
public interface ExpiringMap<K, V> {
    V put(K key, V value, long ttlMillis);

    V get(K key);

    V remove(K key);

    void clear();

    int size();
}
