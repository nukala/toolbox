package org.ravi.udemy.jdk8.defaults;

import org.junit.Test;
import org.ravi.udemy.dsa.WorthLooking;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

// inspired by baeldung.com
public class SpliteratorTest {
    @Test
    @WorthLooking("tryAdvance keeps moving till ")
    public void tryAdvanceTest() {
        List<Bike> bikes = Bike.bikeSupplier.get();
        Spliterator<Bike> spliterator = bikes.spliterator();

        // could be ONE line ... compiler dont like it
        while (true) {
            if (!spliterator.tryAdvance(bike -> bike.businessLogic(bike)))
                break;
        }


        bikes.forEach(bike -> {
                    assertThat(bike.getName()).endsWith("-foobar");
                    assertThat(bike.getModel()).startsWith("boohoo::");
                });
    }

    @WorthLooking("each time we split ... callee is broken into half!")
    @Test
    public void trySplitTest() {
        List<Bike> bikes = Bike.bikeSupplier.get();

        Spliterator<Bike> spliterator = bikes.spliterator();

        // this will split into half
        //   then the first will be split again into equal subsizes
        Spliterator<Bike> first = spliterator.trySplit();
        assertThat(first.estimateSize()).describedAs("first before sub-splits")
                .isEqualTo(256);
        assertThat(spliterator.estimateSize())
                .describedAs("remaining with parent")
                .isEqualTo(256);
        Spliterator<Bike> second = first.trySplit();
        Spliterator<Bike> third = second.trySplit();

        assertThat(first.estimateSize()).describedAs("first quarter")
                .isEqualTo(128);
        assertThat(second.estimateSize()).describedAs("split1 from first")
                .isEqualTo(64);
        assertThat(third.estimateSize()).describedAs("split2 from first")
                .isEqualTo(64);
    }

    static class Bike {
        public static Supplier<List<Bike>> bikeSupplier = () -> {
            return Stream.generate(() -> new Bike("name", "model"))
                    .limit(512)
                    .toList();
        };
        private String name;
        private String model;

        public Bike(String name, String model) {
            this.name = name;
            this.model = model;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public void businessLogic(Bike bike) {
            bike.setName(bike.getName() + "-foobar");
            bike.setModel("boohoo::" + bike.getName());
        }
    }
}
