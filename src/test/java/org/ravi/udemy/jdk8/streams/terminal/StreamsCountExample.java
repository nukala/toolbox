package org.ravi.udemy.jdk8.streams.terminal;

import org.ravi.udemy.dsa.WorthLooking;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// lab57
//    &&
// https://nicksamoylov.medium.com/java-streams-28-collect-4-counting-stream-elements-11572122b8a3
// Every method here is synchronized to protect against parallel streams. -- poor impl
public class StreamsCountExample {
    private final AtomicInteger numPeeks = new AtomicInteger(0);
    private final List<String> names;

    public StreamsCountExample() {
        this.names = Stream.of("adam", "doug", "jenny", "andy").collect(Collectors.toList());
    }

    @WorthLooking("counting is preferred when using other functions, count when raw!")
    public static void main(String[] args) {
        StreamsCountExample sce = new StreamsCountExample();

        System.out.println(" noFilter, numPeeks=" + sce.getNumPeeks()
                + ", counting=" + sce.counting(Optional.empty()));
        System.out.println(" noFilter, numPeeks=" + sce.getNumPeeks()
                +", count=" + sce.count(Optional.empty()));
        System.out.println("==== " + System.lineSeparator());

        System.out.println(" \"a\", numPeeks=" + sce.getNumPeeks()
                +", counting=" + sce.counting(Optional.of("a")));
        System.out.println(" \"a\", numPeeks=" + sce.getNumPeeks()
                +", count=" + sce.count(Optional.of("a")));
    }

    public synchronized  int getNumPeeks() {
        return numPeeks.get();
    }

    public synchronized long counting(Optional<String> filterStr) {
        long counting = -1L;

        numPeeks.set(0);
        Consumer<String> countingCounsumer = (s) -> numPeeks.incrementAndGet();
        if (filterStr.isPresent()) {
            counting = names.stream()
                    .filter((s) -> s.contains(filterStr.get()))
                    .peek(countingCounsumer)
                    .collect(Collectors.counting());
        } else {
            counting = names.stream()
                    .peek(countingCounsumer)
                    .collect(Collectors.counting());
        }

        return counting;
    }

    public synchronized long count(Optional<String> filterStr) {
        long count = -1L;

        numPeeks.set(0);
        Consumer<String> countingCounsumer = (s) -> numPeeks.incrementAndGet();
        if (filterStr.isPresent()) {
            count = names.stream()
                    .filter((s) -> s.contains(filterStr.get()))
                    .peek(countingCounsumer)
                    .count();
        } else {
            count = names.stream()
                    .peek(countingCounsumer)
                    .count();
        }

        return count;
    }
}
