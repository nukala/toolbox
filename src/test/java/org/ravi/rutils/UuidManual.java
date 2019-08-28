package org.ravi.rutils;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.*;

import static java.lang.Runtime.getRuntime;
import static org.apache.commons.lang3.StringUtils.substringAfterLast;

/**
 * To see how UUID class performs under heavy MT load
 */
public class UuidManual {
    private static final Logger logger = LoggerFactory.getLogger(UuidManual.class);

    private static Stopwatch _timr;

    @BeforeClass
    public static void beforeClass() {
        _timr = Stopwatch.createStarted();
    }

    @AfterClass
    public static void afterClass() {
        logger.info("Running this class=[{}] consumed {}", substringAfterLast(logger.getName(), "."), _timr);
    }

    private String getUuid() {
        String ret = UUID.randomUUID().toString().replaceAll("-", "");

        //logger.info("ret = [{}]", ret);
        return ret;
    }

    @Test
    public void twice() {
        String uuid = getUuid();
        String uuid2 = getUuid();

        Assert.assertNotEquals(uuid, uuid2);
    }

    @Ignore("takes too long. Figure out an alternative when running from package-level-tests")
    public void manyThreads() throws InterruptedException {
        final int numLoops = 1_000_000;
        final Multimap<String, String> acqMap = ArrayListMultimap.create(numLoops, 1);
        ExecutorService svc = Executors.newFixedThreadPool(getRuntime().availableProcessors());

        final CountDownLatch latch = new CountDownLatch(numLoops);
        latch.countDown();
        for (int i = 0; i < numLoops; i++) {
            svc.submit((Callable<Void>) () -> {
                String uuid = getUuid();
                acqMap.put(uuid, uuid);
                latch.countDown();
                return null;
            });
        }

        Stopwatch elapsed = Stopwatch.createStarted();
        // should be waaaay less than this ...
        boolean stat = latch.await(90, TimeUnit.SECONDS);
        logger.info("Waited=[{}], loops={}, count={} for {}", stat, numLoops, latch.getCount(), elapsed.toString());
        Assert.assertTrue("should have waited long enough", stat);

        for (String toCheck : acqMap.keySet()) {
            Collection<String> vals = acqMap.get(toCheck);

            int numMatches = vals.size();
            if (numMatches > 1) {
                logger.info("[{}] occurs {} times", toCheck, numMatches);
            }

            Assert.assertTrue("only 1 match", numMatches == 1);
        }
    }
}
