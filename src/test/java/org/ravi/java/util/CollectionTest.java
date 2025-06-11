package org.ravi.java.util;

import org.junit.Before;
import org.junit.Test;
import org.ravi.udemy.dsa.WorthLooking;

import java.util.*;

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
        }).isInstanceOfAny(ConcurrentModificationException.class, IllegalStateException.class);
    }

    /**
     * Check if removeIf works while iterating
     */
    @Test(expected = IllegalArgumentException.class)
    public void removeIfWorks() {
        // RNTODO - implement
        throw new IllegalArgumentException("implement me");
    }

    @Test
    public void toStringTest() {
        Collection<String> strings = new ArrayList<>();
        strings.add("one");
        strings.add("two");
        strings.add("three");
        strings.add("four");

        System.out.println("toString on collection" + strings);
        String noParamStr = Arrays.toString(strings.toArray());
        System.out.println("Arrays.toString on collection=" + noParamStr);
        @WorthLooking("Works with zero-sized arrays also")
        String[] oneOrZero = new String[0];
        String str = Arrays.toString(strings.toArray(oneOrZero));
        System.out.println("Arrays.toString with one element=" + str);
        assertThat(str)
                .describedAs("toArray with one elem array")
                .isEqualTo(strings.toString());
    }
}
