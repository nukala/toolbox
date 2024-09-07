package org.ravi.udemy.jdk8.dates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;

// lab91
public class LocalDateTimeExample {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime - " + localDateTime);

        System.out.println("of7 =" +
                LocalDateTime.of(2024, 8, 30, 10, 53, 33, 98562));

        System.out.println("of 2 objects =" +
                LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        System.out.println(localDateTime + " - "
                + ", hour=" + localDateTime.getHour()
                + ", dayOfMonth=" + localDateTime.getDayOfMonth()
                + ", CLOCK_HOUR_OF_DAY=" + localDateTime.get(ChronoField.CLOCK_HOUR_OF_DAY)
                + ", firstDayOfNextYear=" + localDateTime.with(TemporalAdjusters.firstDayOfNextYear())
        );

        System.out.println(localDateTime + " - "
                + ", +33days=" + localDateTime.plusDays(33)
                + ", +52hous=" + localDateTime.plusHours(52)
                + ", with23hrs=" + localDateTime.with(ChronoField.HOUR_OF_DAY, 23)
                + ", -66mins=" + localDateTime.minusMinutes(66)
        );

        /* Convert complex object into simple versions */
        System.out.println("from localDate get an ldt="
                + LocalDate.of(2032, 12, 26)
                .atTime(22, 44, 55, 353563));
        System.out.println("from localTime get an ldt="
                + LocalTime.of(23, 44, 55, 646646)
                .atDate(LocalDate.of(2033,12,25)));
    }
}
