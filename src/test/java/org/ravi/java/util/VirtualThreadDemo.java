package org.ravi.java.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

public class VirtualThreadDemo {
    // dev.java/learn/new-features/virtual-threads/
    // docs.oracle.com/en/java/javase/21/core/virtual-threads.html
    // how do we know which real thread executed this? - RNTODO
    public static void main(String[] args) {
        final int NTASKS = 115;
        int SHOW_EVERY = 7;
        ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();
        for (int i = 0; i < NTASKS; i++) {
            final int lamdaNeedsFinal = i;
            service.submit(() -> {
                String id = String.format("i=%3d, thrd=%s", lamdaNeedsFinal,
                        Thread.currentThread());
                if (lamdaNeedsFinal % SHOW_EVERY == 0) {
                    id += String.format(" numThreads=%d, name=%s", Thread.getAllStackTraces().size(), Thread.currentThread());
                }
                LockSupport.parkNanos(1_000_000_000);
                System.out.println(id);
            });
        }
        service.close();
        Thread.getAllStackTraces().keySet().forEach(System.out::println);
    }
}


