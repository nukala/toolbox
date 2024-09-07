package org.ravi.udemy.jdk8.dates;

import org.ravi.udemy.dsa.WorthLooking;

import java.time.*;

// lab96 and 97
public class ZonedDateTimeExample {
    public static void main(String[] args) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("zdt=" + zonedDateTime);

        System.out.println(zonedDateTime + " - offset=" + zonedDateTime.getOffset()
                        + ", zid=" + zonedDateTime.getZone()
                //+ ", zioOfPst=" + ZoneId.of("PST")
                //+ ", zioOfPdt=" + ZoneId.of("PDT")
        );

        /*
        System.out.println("All zoneIds = " + ZoneId.getAvailableZoneIds());
        ZoneId.getAvailableZoneIds().stream()
                .filter(s -> {return !s.contains("/") && s.length() <= 6;})
                .forEach(System.out::println);
        ZoneId.getAvailableZoneIds().stream()
                // numerous -- not just US
                //.filter(s -> s.startsWith("America/"))

                //.filter(s -> s.length() <= 4)
                .filter(s -> s.toUpperCase().contains("US/"))
                .forEach(System.out::println);
        System.out.println("num zoneids=" + ZoneId.getAvailableZoneIds().size());
                */

        // CST does not work!
        System.out.println("ExT New_York    time=" + ZonedDateTime.now(ZoneId.of("America/New_York")));
        System.out.println("CxT Chicago     time=" + ZonedDateTime.now(ZoneId.of("America/Chicago")));
        System.out.println("MxT Denver      time=" + ZonedDateTime.now(ZoneId.of("America/Denver")));
        System.out.println("PxT Los_Angeles time=" + ZonedDateTime.now(ZoneId.of("America/Los_Angeles")));
        System.out.println("?xT Sitka       time=" + ZonedDateTime.now(ZoneId.of("America/Sitka")));
        System.out.println("?xT Hawaii      time=" + ZonedDateTime.now(ZoneId.of("US/Hawaii")));


        System.out.println("===");
        System.out.println("zdt using clock with zoneId="
                + ZonedDateTime.now(Clock.system(ZoneId.of("America/New_York"))));

        System.out.println("LocalDateTime of Denver="
                + LocalDateTime.now(ZoneId.of("America/Denver")));
        System.out.println("LocalDateTime of Sitka with clock"
                + LocalDateTime.now(Clock.system(ZoneId.of("America/Sitka"))));
        System.out.println("LocalDateTime using instant of zone(Hawaii)="
                + LocalDateTime.ofInstant(Instant.now(), ZoneId.of("US/Hawaii")));

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("now=" + localDateTime);

        ZonedDateTime zonedDateTime1 = localDateTime.atZone(ZoneId.of("America/New_York"));
        System.out.println("zoneDateTime1=" + zonedDateTime1);

        System.out.println("sitka with Instant=" + Instant.now().atZone(ZoneId.of("America/Sitka")));

        @WorthLooking("only offset ... no conversion, no zone!")
        OffsetDateTime offsetDateTime = localDateTime.atOffset(ZoneOffset.ofHours(-4));
        System.out.println("offsetDateTime still in PxT (-4=NYC)=" + offsetDateTime);
    }
}
