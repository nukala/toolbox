package org.ravi.udemy.jdk8.dates;

import java.time.Duration;
import java.time.Instant;

// lab95
public class InstantExample {
    public static void main(String[] args) {
        Instant instant = Instant.now();
        System.out.println("instant=" + instant + ", machineFormat=" + instant.getEpochSecond() + ", nano=" + instant.getNano());

        System.out.println("first=" + Instant.ofEpochSecond(1)
                + ", epoch=" + Instant.ofEpochMilli(1).getEpochSecond());

        // WORTHLOOKING - machine readable format. Not much to see here.
        Instant instant1 = Instant.now();
        Duration duration = Duration.between(instant, instant1);
        System.out.println(duration + " - seconds=" + duration.getSeconds()
                + ", nanos=" + duration.getNano());
        //RNTODO explore instant1.query()
    }
}
