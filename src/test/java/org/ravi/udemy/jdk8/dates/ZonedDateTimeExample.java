package org.ravi.udemy.jdk8.dates;

import org.ravi.udemy.dsa.WorthLooking;

import java.time.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

// lab96 and 97
public class ZonedDateTimeExample {
    private static Map<String, ZoneId> map = new HashMap<>() {{
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


        ofZones((z) -> {
            return ZonedDateTime.now(z).toString();
        });

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

        System.out.println("==== converted to other TZs === ");
        @WorthLooking("convert to different timezone")
        Function<ZoneId, String> convertToTz = (z) -> {
            LocalDateTime ldt = LocalDateTime.now();
            Instant inst = ldt.atZone(ZoneId.systemDefault()).toInstant();
            return inst.atZone(z).toLocalDateTime().toString();
        };
        ofZones(convertToTz);
        System.out.println("==");
    }
}
