package org.ravi.udemy.jdk8.dates;
//
////FROM https://github.com/dilipsundarraj1/java-8

import org.ravi.udemy.dsa.WorthLooking;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class FormattingLocalDateTimeExample {


    public static void parseLocalDateTime() {
        String dateTime = "2018-04-18T14:33:33";
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
        System.out.println(localDateTime);

        LocalDateTime localDateTime1 = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("localDateTime1 : " + localDateTime1);

        String dateTime1 = "2018-04-1814|33|33";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHH|mm|ss");
        System.out.println(dateTime1 + " - custom date time format: "
                + LocalDateTime.parse(dateTime1, dateTimeFormatter));

        String dateTime2 = "2018-04-18abc14|33|33";
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'abc'HH|mm|ss");
        System.out.println(dateTime2 + " - custom date time format: "
                + LocalDateTime.parse(dateTime2, dateTimeFormatter1));

        String dateTime3 = "2018-04-18#14|33|33";
        @WorthLooking("For reserved characters - use single quote \\'")
        DateTimeFormatter dateTimeFormatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd'#'HH|mm|ss");
        System.out.println(dateTime3 + " - custom date time format: "
                + LocalDateTime.parse(dateTime3, dateTimeFormatter3));
    }

    public static void formatLocalDateTime() {
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'abc'HH|mm|ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String convertedDate = localDateTime.format(dateTimeFormatter1);
        System.out.println("convertedDate : " + convertedDate);
    }

    static void parseHghaiSample() {
        String dtStr = "2014-02-13T20:48:57.000+0000";

        //System.out.println("substring23=[" + dtStr.substring(23));
        DateTimeFormatter fmt = DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSxxxx");
        LocalDateTime ldt = LocalDateTime.parse(dtStr, fmt);
        System.out.println("str=[" + dtStr + "], ldt=[" + ldt + "]");
        System.out.println("zdt.ny=[" + ldt.atZone(ZoneId.of("America/New_York")) + "]");
        System.out.println("zdt.la=[" + ldt.atZone(ZoneId.of("America/Los_Angeles")) + "]");
    }

    public static void main(String[] args) {
        parseLocalDateTime();
        System.out.println("=== HGHAI ==");
        parseHghaiSample();
        System.out.println("=== FORMAT ==");
        formatLocalDateTime();
    }
}
