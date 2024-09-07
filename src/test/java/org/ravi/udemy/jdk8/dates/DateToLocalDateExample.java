package org.ravi.udemy.jdk8.dates;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class DateToLocalDateExample {
    public static void main(String[] args) {
        /// java.util.Date -> LocalDate
        Date date = new Date();
        System.out.println("date="+ date);

        // java.util.Date -> LocalDate 5 lines
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println("localDate=" + localDate
        + ", localTime=" + date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime()
        + ", localDateTime=" + date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());


        // LocalDate -> java.util.Date 5 lines
        // first line makes a localdatetime, second a zdt
        Date date1 = Date.from(localDate.atTime(LocalTime.now())
                        .atZone(ZoneId.systemDefault())
                        .toInstant());
        System.out.println("date1=" + date1);

        // java.sql.Date <--> Localdate

        // LocalDate -> java.sql.Date
        java.sql.Date date2 = java.sql.Date.valueOf(localDate);
        System.out.println(localDate + " - in sql=" + date2);

        // java.sql.Date -> LocalDate
        System.out.println("localDate from sql.Date=" + date2.toLocalDate());
    }
}
