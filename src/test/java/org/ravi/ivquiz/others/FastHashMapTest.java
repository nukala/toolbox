package org.ravi.ivquiz.others;

import org.junit.Test;
import org.ravi.interview.FastHashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class FastHashMapTest {
    @Test
    public void bigTest() {
        FastHashMap<String, Integer> map = new FastHashMap<>();

        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        assertThat(map.get("a")).isEqualTo(1);
        assertThat(map.get("b")).isEqualTo(2);
        assertThat(map.get("c")).isEqualTo(3);

        map.setAll(4);
        assertThat(map.get("a")).isEqualTo(4);
        assertThat(map.get("b")).isEqualTo(4);
        assertThat(map.get("c")).isEqualTo(4);

        map.put("c", 5);
        assertThat(map.get("a")).isEqualTo(4);
        assertThat(map.get("b")).isEqualTo(4);
        assertThat(map.get("c")).isEqualTo(5);
    }
}
