package org.ravi.udemy.jdk8.dates;

import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class LocalTimeExample {
    public static void main(String[] args) {
        LocalTime localTime = LocalTime.now();
        System.out.println("localTime=" + localTime);

        LocalTime localTime1 = LocalTime.of(23, 33);
        System.out.println("localTime1=" + localTime1);

        LocalTime localTime2 = LocalTime.of(23, 33, 44);
        System.out.println("localTime2=" + localTime2);

        LocalTime localTime3 = LocalTime.of(23, 33, 44, 90908);
        System.out.println("localTime3=" + localTime3);
        System.out.println(System.lineSeparator() + "====");

        System.out.println("hour=" + localTime.getHour());
        System.out.println("getMinute=" + localTime.getMinute());

        System.out.println(localTime
                + " - CLOCK_HOUR_OF_DAY=" + localTime.get(ChronoField.CLOCK_HOUR_OF_DAY)
                + ", CLOCK_HOUR_OF_AMPM=" + localTime.get(ChronoField.CLOCK_HOUR_OF_AMPM)
        );
        System.out.println("toSecondOfDay=" + localTime.toSecondOfDay());


        // modifiers
        System.out.println(System.lineSeparator() + "====");
        System.out.println(localTime + " - "
                + " minus2=" + localTime.minusHours(2)
                + ", chrono-2=" + localTime.minus(2, ChronoUnit.HOURS)
        );
        System.out.println("oneSec b4 midnight=" + localTime.with(LocalTime.MIDNIGHT).minusSeconds(1));
        System.out.println(localTime + " - "
                + " noon=" + localTime.with(LocalTime.NOON)
        );

        System.out.println(localTime + " - "
                + ", +1h36m=" + localTime.plusMinutes(36).plusHours(1)
                + ", +96m=" + localTime.plusMinutes(96));
    }
}
