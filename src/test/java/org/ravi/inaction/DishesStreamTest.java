package org.ravi.inaction;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.ravi.inaction.domain.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class DishesStreamTest {
    public static void show(String format, Object... args) {
        if (!StringUtils.contains(format, "%n")
                || !StringUtils.contains(format, "\n")) {
            format += "%n";
        }

        System.out.printf(format, args);
    }

    @Test
    public void highCalorieDishes() {
        int highCal = 490;
        AtomicInteger filterCtr = new AtomicInteger(1);
        AtomicInteger mapCtr = new AtomicInteger(1);
        List<String> names = Dish.menu().stream()
                .filter(dish -> {
                    show("filtering#%d %s", filterCtr.getAndIncrement(), dish);
                    return dish.getCalories() > highCal;
                })
                .map(dish -> {
                    show("mapping#%d %s", mapCtr.getAndIncrement(), dish);
                    return dish.getName();
                })
                .limit(3)
                .collect(toList());
        show("Names > %d calories=%s %n", highCal, names);
    }

    @Test
    public void helloSplit() {
        String ary[] = "Hello" .split("");
        show(Joiner.on(",").join(ary));
        Assertions.assertThat(ary)
                .hasSize(5);
    }

    @Test // complex example around quiz5.2 to teach flatMap related
    public void twoWords() {
        String words[] = {"Ravi", "Nukala"};
        Stream<String> stream = Arrays.stream(words);
        show("%d", Arrays.stream(words).count());

        Stream<String[]> stream1 = stream
                .map(w -> w.split(""));
        show(stream1
                .peek((String a[]) -> System.out.printf("peeked=[%s].%d(%s) %n", Joiner.on("").join(a), a.length, a))
                .collect(toList())
                .toString());
    }
}
