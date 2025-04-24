package org.ravi.java.util;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.*;

// to house all collection related tests from :

public class CollectionTest {
    Collection<String> coll = new ArrayList<>();

    @Before
    public void beforeAll() {
        coll.add("one");
        coll.add("two");
        coll.add("three");
    }

    private void initialCheck() {
        assertThat(coll.size()).isEqualTo(3);
    }

    private void endingCheck() {
        assertThat(coll.size()).isEqualTo(0);
        assertThat(coll.contains("two")).isFalse();
    }

    @Test
    public void removeWithForLoopBarfs() {
        initialCheck();
        assertThatThrownBy(() -> {
            for (String element : coll) {
                coll.remove(element);
            }
        }).isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    public void removeFromCollectionWithIteratorAlsoCME() {
        initialCheck();
        Iterator<String> iter = coll.iterator();
        assertThatExceptionOfType(ConcurrentModificationException.class)
                .isThrownBy(() -> {
                    while (iter.hasNext()) {
                        String str = iter.next();
                        coll.remove(str);
                    }
                }).withNoCause();
    }

    @Test
    public void removeViaIteratorAlsoCME() {
        initialCheck();
        assertThatThrownBy(() -> {
            for (Iterator<String> iter = coll.iterator(); iter.hasNext(); ) {
                iter.remove();
            }
        }).isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    public void removeIfWorks() {
        throw new IllegalArgumentException("implement me");
    }
}
