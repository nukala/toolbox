package org.ravi.udemy.jdk8.streams;


import org.junit.Test;
import org.ravi.udemy.dsa.WorthLooking;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

// lab46 limit and skip
public class StreamsLimitSkipTest {
    List<Integer> integers = Arrays.asList(2, 4, 5, 6, 7, 8);

    @WorthLooking("limit returns ONLY the first specified number")
    public int addFirst(List<Integer> integers, int count) {
        // @WorthLooking("peek sees element that went through prior step")
        return integers.stream()
                .limit(count)
                .peek(ival -> System.out.println("    after-limit=" + count + ", ival=" + ival))
                .reduce(0, Integer::sum);
    }

    @Test
    public void testAddFew() {
        assertThat(addFirst(integers, 2)).isEqualTo(6);

        assertThat(addFirst(integers, 4)).isEqualTo(17);

        assertThat(addFirst(integers, 0)).
                describedAs("zero count").isEqualTo(0);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .describedAs("negative limit")
                .isThrownBy(() -> addFirst(integers, -9))
                .withMessageContaining("-9");
    }

    @WorthLooking("skip discards the first specified")
    public Optional<Integer> multSkip(List<Integer> integers, int count) {
        return integers.stream()
                .skip(count)
                .peek(ival -> System.out.println("    after-skip=" + count + ", ival=" + ival))
                .reduce((a, b) -> a * b);
    }

    @Test
    public void testSkip() {
        assertThat(multSkip(integers, 4)).isPresent().hasValue(56);

        assertThat(multSkip(integers, 3)).isPresent().hasValue(336);

        assertThat(multSkip(integers, integers.size()))
                .describedAs("skip sizeof list").isNotPresent();

        assertThat(multSkip(integers, 0)).describedAs("zero skipped")
                .isPresent().hasValue(13440);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .describedAs("negative skip")
                .isThrownBy(() -> addFirst(integers, -4))
                .withMessageContaining("-4");

    }
}
