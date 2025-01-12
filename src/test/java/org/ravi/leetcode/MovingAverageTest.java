package org.ravi.leetcode;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

public class MovingAverageTest {
    @Test
    public void movingAvgTest() {
        MovingAverage avg = new MovingAverage(3, MovingAverage.MovingType.QUEUE);
        String type = avg.getType().name();
        assertThat(avg.next(1)).describedAs(type).isEqualTo(1.0);
        assertThat(avg.next(10)).describedAs(type).isEqualTo(5.5);
        assertThat(avg.next(3)).describedAs(type).isEqualTo(4.66, withPrecision(2d));
        assertThat(avg.next(5)).describedAs(type).isEqualTo(6.0);
        assertThat(avg.next(4)).describedAs(type).isEqualTo(4.0);

        avg = new MovingAverage(3, MovingAverage.MovingType.DEQUEUE);
        assertThat(avg.next(1)).describedAs(type).isEqualTo(1.0);
        assertThat(avg.next(10)).describedAs(type).isEqualTo(5.5);
        assertThat(avg.next(3)).describedAs(type).isEqualTo(4.66, withPrecision(2d));
        assertThat(avg.next(5)).describedAs(type).isEqualTo(6.0);
        assertThat(avg.next(4)).describedAs(type).isEqualTo(4.0);
    }
}
