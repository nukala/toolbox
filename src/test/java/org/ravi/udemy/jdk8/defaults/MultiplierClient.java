package org.ravi.udemy.jdk8.defaults;

import org.assertj.core.data.Offset;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// lab82 - fairly obvious stuff ... but part of an exercise.
public class MultiplierClient {

    public static void main(String[] args) {
        Multiplier<Integer> multiplier = new MultiplierImpl();

        List<Integer> integerList = Arrays.asList(1, 3, 5, 6);
        System.out.println("Integer result=" + multiplier.multiply(integerList));
        System.out.println("size=" + multiplier.size(integerList));
        System.out.println("static method isEmpty=" + Multiplier.isEmpty(integerList));
        System.out.println("static method isEmpty, null list="
                + Multiplier.isEmpty(null));

        System.out.println("=== now Double based invokations ==");
        List<Double> doubels = Arrays.asList(1.0, 2.0, 5.0);
        Multiplier<Double> dm = new DoubleMultiplier();
        assertThat(doubels.size()).isEqualTo(3);
        assertThat(dm.size(doubels))
                .describedAs("should see inside interface")
                .isEqualTo(3);
        assertThat(dm.multiply(doubels))
                .describedAs("double assert tolerance offset")
                .isEqualTo(9.99, Offset.offset(0.01));
    }

    // simple double impl to prove size method resolves to interface!
    static class DoubleMultiplier implements Multiplier<Double> {

        @Override
        public Double multiply(List<Double> list) {
            return list.stream()
                    .reduce(1.0, (x, y) -> x * y);
        }
    }
}
