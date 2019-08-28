package org.ravi.rutils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeTest {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private static final LocalDateTime FROZEN = LocalDateTime.parse("2019-01-20T19:27:44");
    private static final ZoneId INDIA_ZONE_ID = ZoneId.of("Asia/Kolkata");

    @Test
    public void conversion() {
        ZonedDateTime ist = FROZEN.atZone(INDIA_ZONE_ID);
        logger.info("Then = [{}] and IST = [{}]", FROZEN, ist);

        Date istDate = Date.from(ist.toInstant());
        logger.info("istDate=[{}]", istDate);

        logger.info("{}", FROZEN.atZone(ZoneId.systemDefault()).withZoneSameInstant(INDIA_ZONE_ID));

        Date now = Date.from(FROZEN.atZone(ZoneId.systemDefault()).toInstant());
        ZonedDateTime nowZdt = ZonedDateTime.ofInstant(now.toInstant(), ZoneId.systemDefault()).withZoneSameInstant(INDIA_ZONE_ID);
        LocalDateTime istLocalDateTime = nowZdt.toLocalDateTime();
        logger.info("then becomes={} istLdt={}", nowZdt, istLocalDateTime);
        assertThat(istLocalDateTime.getDayOfMonth())
                .isEqualTo(21);
        assertThat(istLocalDateTime.getHour())
                .isEqualTo(8);
        assertThat(istLocalDateTime.getMinute())
                .isEqualTo(57);

    }
}
