package org.ravi.tlap;

import org.junit.Test;
import org.ravi.tlap.DecodeSpecial.Mode;
import org.ravi.udemy.dsa.WorthLooking;

import java.util.Formatter;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class Chapter2Test {
    @Test
    // print '#' as half of a perfect square
    public void halfOfSquare() { //p29 or pdf=49
        final int limit = 5;
        AtomicInteger max = new AtomicInteger(limit);
        @WorthLooking("Use limit if supplier is un-bounded")
        IntSupplier supplier = max::decrementAndGet;

        StringBuilder sb = new StringBuilder();
        try (Formatter fmtr = new Formatter(sb)) {
            IntStream.generate(supplier)
                    .limit(limit)
                    .forEach(num -> {
                        // using StringUtils#repeat is a library-call
                        for (int i = 0; i <= num; i++) {
                            fmtr.format("#");
                        }
                        fmtr.format("%n");
                    });
        }
        System.out.printf("%s", sb);
        assertThat(sb.toString()).contains("#####");
        assertThat(sb.toString()).contains("###");
        assertThat(sb.toString()).doesNotContain("######");
    }

    @Test
    public void decodeSampleString() { // towards decoding p41 or pdf=61
        String str = "18,12312,171,763,98423,1208,216,11,500,18,241,0,32,20620,27,10";
        String decoded = new DecodeSpecial().decode(str);
        assertThat(decoded).isEqualTo("Right? Yes!");
    }

    @Test
    public void modeTests() {
        assertThat(Mode.UPPER.convert(18)).isEqualTo('R');
        assertThat(Mode.UPPER.convert(1)).isEqualTo('A');

        assertThat(Mode.LOWER.convert(18)).isEqualTo('r');
        assertThat(Mode.LOWER.convert(1)).isEqualTo('a');

        assertThat(Mode.PUNCTUATION.convert(1)).isEqualTo('!');
        assertThat(Mode.PUNCTUATION.convert(5)).isEqualTo(' ');
    }

    @Test
    public void intSupplierFakeTest() { // to see how supplier works in this case
        Random random = new Random();
        IntSupplier newRandom = () -> random.nextInt(54);
        BiConsumer<Integer, Integer> biConsumer = (i, r) ->
            System.out.printf("BI: #%d rand=%d %n", i, r);

        IntStream.range(0, 5)
                .forEach(i -> biConsumer.accept(i, newRandom.getAsInt()));
    }
}
