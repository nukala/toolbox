package org.ravi.rutils;

import org.junit.After;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExpiringMapTest {
    private ExpiringMap<Integer, String> expMap = new ExpiringHashMap<>();

    private static void napMillis(int millis) {
        long then = System.currentTimeMillis();
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) { // ignored
            ;
        }
        long now = System.currentTimeMillis();
        long diff = now - then - millis;
        if (diff > 1) {
            System.err.printf("%d: napped too long? diff=%d %n", millis, diff);
        }
    }

    @After
    public void afterEveryTest() {
        expMap.clear();
    }

    @Test
    public void immediateTest() {
        String old = expMap.put(1, "one", 99);
        assertThat(old).isNull();

        assertThat(expMap.get(1)).isEqualTo("one");
    }

    @Test
    public void napSometimeAndVerify() {
        expMap.put(1, "napSometimeAndVerify", 99);

        assertThat(expMap.get(1)).isEqualTo("napSometimeAndVerify");
        napMillis(150);
        assertThat(expMap.get(1)).isNull();
    }

    //@Ignore("TODO: fails after first nap reason.") //TODO
    @Test
    public void twoNapsThenVerify() {
        expMap.put(1, "twoNapsThenVerify", 250);

        assertThat(expMap.get(1)).isEqualTo("twoNapsThenVerify");
        napMillis(55);
        assertThat(expMap.get(1))
                .as("after 55ms nap")
                .isEqualTo("twoNapsThenVerify");

        napMillis(255);
        assertThat(expMap.get(1))
                .as("after two naps")
                .isNull();
    }

    @Test
    public void verifySizeAfterNap() {
        expMap.put(53, "verifySizeAfterNap", 59);

        assertThat(expMap.size()).isEqualTo(1);

        napMillis(75);
        assertThat(expMap.size()).isEqualTo(0);
    }

    @Test
    public void sameSizeAfterShortNap() {
        expMap.put(63, "sameSizeWithNoNap", 75);
        expMap.put(1, "one", 99900L);

        assertThat(expMap.size()).isEqualTo(2);

        napMillis(18);
        assertThat(expMap.size()).isEqualTo(2);
    }
}
