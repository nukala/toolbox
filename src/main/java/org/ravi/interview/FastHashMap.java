package org.ravi.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Write put,get,setAll methods for a HashMap, to support scenarios like this:
 * m.put("a", 1);
 * m.put("b", 2);
 * m.put("c", 3);
 * >>> get should return those values
 * m.setAll(4) >>> subsequent gets should return only 4 for all keys (old or new)
 * m.put("c", 5);
 * >>> get("b") == 4, while get(c") == 5
 * and so on.
 * <br/>
 * All the operations should be <b>O(1)</b> or just-about that
 */
interface FastMap<KK, VV> {
    default VV get(KK key) {
        return null;
    }

    default void put(KK key, VV value) {
    }

    default void setAll(VV forcedValue) {
    }
}

public class FastHashMap<K, V> implements FastMap<K, V> {
    private HashMap<K, V> map = new HashMap<>();

    private Optional<V> allValueOpt = Optional.empty();

    private HashMap<K, V> forcedMap = new HashMap<>();

    @Override
    public V get(K key) {
        if (forcedMap.containsKey(key)) {
            return forcedMap.get(key);
        }

        return allValueOpt.orElseGet(() -> map.get(key));
    }

    @Override
    public void put(K key, V value) {
        if (allValueOpt.isPresent()) {
            forcedMap.put(key, value);
        }

        map.put(key, value);
    }

    @Override
    public void setAll(V forcedValue) {
        allValueOpt = Optional.of(forcedValue);

        forcedMap = new HashMap<>();
    }
}
