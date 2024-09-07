package org.ravi.udemy.jdk8.dates;

//FROM https://github.com/dilipsundarraj1/java-8

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormattingLocalDateExample {

    public static void parseLocalDate() {
        /**
         * Using system defined format
         */
        String date = "2018-04-28";
        LocalDate localDate0 = LocalDate.parse(date);
        System.out.println("localDate0 : " + localDate0);
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE); //ISO_DATE is the same
        System.out.println("localDate " + localDate);

        String isoDate = "20180428";
        System.out.println(isoDate + " - BASIC_ISO_DATE : " + LocalDate.parse(isoDate, DateTimeFormatter.BASIC_ISO_DATE));


        /**
         * Custom system defined format
         */
        String date1 = "2018|04|28";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy|MM|dd");
        LocalDate localDate1 = LocalDate.parse(date1, dateTimeFormatter);
        System.out.println(date1 + " - localDate1 : " + localDate1);

        String date2 = "2018*04*28";
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("uuuu*MM*dd");
        LocalDate localDate2 = LocalDate.parse(date2, dateTimeFormatter1);
        System.out.println(date2 + " - localDate2 : " + localDate2);

        String date3 = "8/30/2024";
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("M/dd/uuuu");
        LocalDate localDate3 = LocalDate.parse(date3, dateTimeFormatter2);
        System.out.println(date3 + " - localDate3=" + localDate3);
        // RNTODO - how to handle 1 or 2 digit
    }

    public static void formatLocalDate() {
        //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd|MM|yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY|MM|dd");
        LocalDate localDate = LocalDate.now();
        String formattedDate = localDate.format(dateTimeFormatter);
        System.out.println("formattedDate : " + formattedDate);
    }

    public static void main(String[] args) {
        System.out.println("Current date on machine=" + LocalDate.now());
        parseLocalDate();
        formatLocalDate();
    }
}
