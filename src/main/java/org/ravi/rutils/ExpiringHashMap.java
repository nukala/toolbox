package org.ravi.rutils;


import lombok.ToString;

import java.util.HashMap;
import java.util.Map;


public class ExpiringHashMap<K, V> implements ExpiringMap<K, V> {
    private final HashMap<K, ExpiringHashMap.ExpiringValue<V>> map;

    public ExpiringHashMap() {
        super();

        this.map = new HashMap<>();
    }

    // expensive, is this needed?
    private void cleanExpired() {
        // stream barfs!
        for (Map.Entry<K, ExpiringValue<V>> entry : map.entrySet()) {
            if (System.currentTimeMillis() > entry.getValue().expiresAt) {
                map.remove(entry.getKey());
            }
        }
    }

    @Override
    public V put(K key, V value, long ttl) {
        ExpiringValue<V> old = map.put(key, new ExpiringValue<>(value, ttl));
        return old == null ? null : old.value;
    }

    @Override
    public V get(K key) {
        ExpiringValue<V> ev = map.get(key);

        if (ev == null) {
            return null;
        }

        long now = System.currentTimeMillis();
        if (now < ev.expiresAt) {
            //System.out.printf("%d: ev=[%s] %n", now, ev);
            return ev.value;
        }

        //System.out.printf("%d: removing [%s]%n", now, ev);
        remove(key);
        return null;
    }

    @Override
    public V remove(K key) {
        ExpiringValue<V> removed = map.remove(key);

        return removed == null ? null : removed.value;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        cleanExpired();
        return map.size();
    }

    @ToString
    private static class ExpiringValue<V> {
        private final long expiresAt;
        private final V value;

        ExpiringValue(V value, long ttl) {
            super();

            this.value = value;
            this.expiresAt = System.currentTimeMillis() + ttl;
        }
    }
}
