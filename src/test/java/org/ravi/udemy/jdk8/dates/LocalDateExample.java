package org.ravi.udemy.jdk8.dates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

// lab87 - localdate "creation" choices
public class LocalDateExample {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate=" + localDate);

        LocalDate then = LocalDate.of(2025, 12, 31);
        System.out.println("then=" + then);

        LocalDate yearDay = LocalDate.ofYearDay(2032, 117);
        System.out.println("yearDay=" + yearDay);

        // get date elements
        System.out.println(localDate + ", year=" + localDate.getYear()
                + ", month=" + localDate.getMonth() + ", monthValue="
                + localDate.getMonthValue() + ", dayofweek=" + localDate.getDayOfWeek()
                + ", dayOfYear=" + localDate.getDayOfYear());
        System.out.println("chronology=" + localDate.getChronology()
                + ", (non-JP ignore) era=" + localDate.getEra());

        System.out.println("Chrono Fields dow=" + localDate.get(ChronoField.DAY_OF_WEEK));

        /// modifiers
        System.out.println(System.lineSeparator() + "====");
        System.out.println("add 2 days=" + localDate.plusDays(2));
        System.out.println("add 33 days=" + localDate.plusDays(33));
        System.out.println("minus 20 days=" + localDate.minusDays(20));

        System.out.println("add 1 year=" + localDate.plusYears(1));
        System.out.println("add 3 months=" + localDate.plusMonths(3));

        // with modifiers
        System.out.println("ChronoField yr="
                + localDate.with(ChronoField.YEAR, 2032)
                + ", dayOfYear=" + localDate.with(ChronoField.DAY_OF_YEAR, 116));

        System.out.println("TemporalAdjusters mon="
                + localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                + ", nextYear=" + localDate.with(TemporalAdjusters.firstDayOfNextYear()));

        System.out.println("minus using ChronoUnit years="
                + localDate.minus(2, ChronoUnit.YEARS));

        /// additional
        System.out.println(System.lineSeparator() + "====");
        System.out.println("leapYear=" + localDate.isLeapYear());
        System.out.println("2020 leap=" + localDate.withYear(2020).isLeapYear());
        System.out.println(localDate + ".isEqual(" + then + ")=" + localDate.isEqual(then));
        System.out.println(localDate + ".isBefore(" + then + ")=" + localDate.isBefore(then));
        System.out.println(localDate + ".isAfter(" + then + ")=" + localDate.isAfter(then));
        // calling `with` throws exception
        System.out.println("with(HOUR) supported=" + localDate.isSupported(ChronoUnit.HOURS));
    }
}
