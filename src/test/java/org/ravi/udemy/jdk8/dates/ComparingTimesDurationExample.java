package org.ravi.udemy.jdk8.dates;

import org.ravi.udemy.dsa.WorthLooking;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class ComparingTimesDurationExample {
    public static void main(String[] args) {
        LocalTime localTime = LocalTime.of(7, 20);
        LocalTime localTime1 = LocalTime.of(8, 20);

        long diff = localTime.until(localTime1, ChronoUnit.MINUTES);
        System.out.println("diff=" + diff);

        // reversing - returns negative number
        System.out.println(localTime1 + ".until(" + localTime + ")="
                + localTime1.until(localTime, ChronoUnit.MINUTES));

        Duration duration = Duration.between(localTime, localTime1);
        System.out.println(duration + " - " + ", mins=" + duration.toMinutes());
        System.out.println("between reversed=" + Duration.between(localTime1, localTime));

        @WorthLooking("For time (including days) related comparisons")
        Duration duration1 = Duration.ofHours(-3);
        System.out.println(duration1 + " - toMins=" + duration1.toMinutes());

        Duration duration2 = Duration.ofDays(1);
        System.out.println(duration2 + " - " + ", abs=" + duration2.abs()
                + ", toMins=" + duration2.toMinutes()
                + ", getSecs=" + duration2.getSeconds());
    }
}
