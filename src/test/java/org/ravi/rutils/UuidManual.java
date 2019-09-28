package org.ravi.rutils;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Runtime.getRuntime;
import static org.apache.commons.lang3.StringUtils.substringAfterLast;

/**
 * To see how UUID class performs under heavy MT load
 */
public class UuidManual {
    private static final Logger logger = LoggerFactory.getLogger(UuidManual.class);
    // begin-setup log4j and info for me!
    private static volatile boolean logInited = false;
    private static Stopwatch _timr;
    private static AtomicInteger counter = new AtomicInteger(0);

    static {
        if (!logInited) {
            org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.WARN);
            org.apache.log4j.Logger.getRootLogger().removeAllAppenders();
            org.apache.log4j.Logger.getRootLogger().addAppender(new org.apache.log4j.ConsoleAppender(new org.apache.log4j.PatternLayout("%d [%t] %p %c{4}::%M@%L %x - %m%n")));

            org.apache.log4j.Logger.getLogger("org.springframework").setLevel(org.apache.log4j.Level.WARN);

            org.apache.log4j.Logger.getLogger(UuidManual.class.getPackage().getName()).setLevel(org.apache.log4j.Level.DEBUG);

            logInited = true;
        }
    }

    @BeforeClass
    public static void beforeClass() {
        _timr = Stopwatch.createStarted();
    }

    @AfterClass
    public static void afterClass() {
        logger.info("Running this class=[{}] consumed {}", substringAfterLast(logger.getName(), "."), _timr);
    }

    private String getUuid() {
        String ret = UUID.randomUUID().toString()
                //.replaceAll("-", "")
                ;
        int uuidNum = counter.incrementAndGet();

        //System.out.printf("%s-%s: ret = [%s] %n", Thread.currentThread().getId(), Thread.currentThread().getName(), ret);
        logger.info("ret = [{}].{}", ret, uuidNum);
        return ret;
    }

    @Test
    public void twice() {
        String uuid = getUuid();
        String uuid2 = getUuid();

        Assert.assertNotEquals(uuid, uuid2);
    }

    //@Ignore("takes too long. Figure out an alternative when running from package-level-tests")
    @Test
    public void manyThreads() throws InterruptedException {
        final int numLoops = 100 /** 10_000*/;
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
