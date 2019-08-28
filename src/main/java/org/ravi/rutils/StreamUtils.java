package org.ravi.rutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

public class StreamUtils {
    private static final Logger logger = LoggerFactory.getLogger(StreamUtils.class);

    private StreamUtils() {
        // not instantiable.
    }

    public static <T> boolean contains(Stream<T> stream, T toCheck, BiPredicate<T, T> biPredicate) {
        Objects.requireNonNull(stream, "stream cannot be null");
        Objects.requireNonNull(biPredicate, "biPredicate cannot be null");

        AtomicInteger ctr = new AtomicInteger(0);
        try {
            return stream
                    .anyMatch(t -> {
                        ctr.incrementAndGet();

                        return biPredicate.test(t, toCheck);
                    });
        } finally {
            logger.info("[{}]: counter={}", toCheck, ctr.get());
        }
    }

    public static <T> boolean contains(T elems[], T toCheck, BiPredicate<T, T> biPredicate) {
        Objects.requireNonNull(elems, "elements array cannot be null");
        Objects.requireNonNull(biPredicate, "biPredicate cannot be null");

        return contains(Arrays.stream(elems), toCheck, biPredicate);
    }
}
