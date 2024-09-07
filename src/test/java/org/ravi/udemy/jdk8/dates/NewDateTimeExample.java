package org.ravi.udemy.jdk8.dates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

// lab86 - simple invokations.  
public class NewDateTimeExample {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate=" + localDate);

        LocalTime localTime = LocalTime.now();
        System.out.println("localTime=" + localTime);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime=" + localDateTime);
    }
}
