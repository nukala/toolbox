package org.ravi.udemy.jdk8.dates;

import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.ravi.udemy.dsa.WorthLooking;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

// lab96 and 97
public class ZonedDateTimeExample {
    private static final Map<String, ZoneId> map = new HashMap<>() {{
        put("New_York", ZoneId.of("America/New_York"));
        put("Chicago", ZoneId.of("America/Chicago"));
        put("Denver ", ZoneId.of("America/Denver"));
        put("Los_Angeles", ZoneId.of("America/Los_Angeles"));
        put("Sitka", ZoneId.of("America/Sitka"));
        put("Hawaii", ZoneId.of("US/Hawaii"));
        put("Kolkata", ZoneId.of("Asia/Kolkata"));
    }};

    static void ofZones(Function<ZoneId, String> function) {
        if (function != null) {
            map.keySet()
                    .forEach((k) -> {
                        //System.out.println(k + "=" + function.apply(map.get(k)));
                        String oth = function.apply(map.get(k));
                        System.out.printf("%-11s = %s[%s]%n", k, oth, map.get(k).toString());
                    });
        }
    }

    public static void main(String[] args) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("zdt=" + zonedDateTime);

        System.out.println(zonedDateTime + " - offset=" + zonedDateTime.getOffset()
                        + ", zid=" + zonedDateTime.getZone()
                //+ ", zioOfPst=" + ZoneId.of("PST")
                //+ ", zioOfPdt=" + ZoneId.of("PDT")
        );

//        System.out.println("All zoneIds = " + ZoneId.getAvailableZoneIds());
//        ZoneId.getAvailableZoneIds().stream()
//                .filter(s -> {
//                    return !s.contains("/") && s.length() <= 6;
//                })
//                .forEach(System.out::println);
//        ZoneId.getAvailableZoneIds().stream()
//                // numerous -- not just US
//                //.filter(s -> s.startsWith("America/"))
//
//                //.filter(s -> s.length() <= 4)
//                .filter(s -> s.toUpperCase().contains("US/"))
//                .forEach(System.out::println);
//        System.out.println("num zoneids=" + ZoneId.getAvailableZoneIds().size());


        ofZones(z -> ZonedDateTime.now(z).toString());

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

        LocalDateTime ldt = LocalDateTime.of(2025, 6, 11, 14, 22, 33);
        Instant ldtInst = ldt.atZone(ZoneId.systemDefault()).toInstant();
        System.out.println("==== converted(" + ldt + ") to other TZs === ");
        @WorthLooking("convert to different timezone")
        Function<ZoneId, String> convertToTz = (z) -> ldtInst.atZone(z).toLocalDateTime().toString();
        ofZones(convertToTz);
        System.out.println("==");

        travelExample(Hours.hours(10), Minutes.minutes(55));
    }

    static void travelExample(Hours hrs, Minutes mins) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm a");

// Leaving from San Francisco on July 20, 2013, at 7:30 p.m.
        LocalDateTime leaving = LocalDateTime.now();
        ZoneId leavingZone = ZoneId.of("America/Los_Angeles");
        ZonedDateTime departure = ZonedDateTime.of(leaving, leavingZone);

        try {
            String out1 = departure.format(format);
            System.out.printf("LEAVING:  %s (%s)%n", out1, leavingZone);
        } catch (DateTimeException exc) {
            System.out.printf("%s can't be formatted!%n", departure);
            throw exc;
        }


        ZoneId arrivingZone = ZoneId.of("Asia/Tokyo");
        ZonedDateTime arrival = departure.withZoneSameInstant(arrivingZone)
                .plusHours(hrs.getHours())
                .plusMinutes(mins.getMinutes());

        try {
            String out2 = arrival.format(format);
            System.out.printf("ARRIVING: %s (%s)%n", out2, arrivingZone);
        } catch (DateTimeException exc) {
            System.out.printf("%s can't be formatted!%n", arrival);
            throw exc;
        }

        if (arrivingZone.getRules().isDaylightSavings(arrival.toInstant())){
            System.out.printf("  (%s daylight saving time will be in effect.)%n",
                    arrivingZone);
        } else{
            System.out.printf("  (%s standard time will be in effect.)%n",
                    arrivingZone);
        }
    }
}
