package org.ravi.inaction;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.ravi.inaction.domain.Dish;
import org.ravi.inaction.domain.Trader;
import org.ravi.inaction.domain.Transaction;

import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/** Page numbers are from: Java_8_in_Action.pdf */
public class Chapter5 {

    private static String asPairs(List<Integer[]> pairsList) {
        StringBuilder sb = new StringBuilder(1024);

        try (Formatter formatter = new Formatter(sb)) {
            formatter.format("{");
            pairsList
                    .forEach(a -> formatter.format("(%d,%d) ", a[0], a[1]));
            formatter.format("}%n");
        }

        return sb.toString();
    }

    @Test
    public void quiz521() { // 5.2.1
        List<Integer> numbers = Lists.newArrayList(1, 2, 3, 4, 5);

        List<Integer> squares = numbers.stream()
                .map(i -> i * i)
                .collect(toList());
        DishesStreamTest.show("squares = %s", squares);
    }

    @Test
    public void section522() {
        String[] words = {"Hello", "World"};
        Arrays.stream(words)
                .map(word -> word.split(""))
                .forEach(s ->
                        System.out.printf("[%s].%d->%s%n", s, s.length, s.getClass().getSimpleName())
                );
        Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(s ->
                        System.out.printf("[%s].%d->%s%n", s, s.length(), s.getClass().getSimpleName())
                );
    }

    @Test
    public void quiz522() { // 5.2.2 -- combine 2 lists of integers into pairs!
        List<Integer> one = Lists.newArrayList(1, 2, 3);
        List<Integer> two = Arrays.asList(3, 4);

        List<Integer[]> pairs = one.stream()
                .flatMap(o ->
                        two.stream()
                                .map(t -> new Integer[]{o, t})
                )
                .collect(toList());
        DishesStreamTest.show("pairs %s", asPairs(pairs));
    }

    @Test
    public void quiz523() { // 5.2.3
        // from 522, but the sum of each pair is divisible by 3
        List<Integer> one = Lists.newArrayList(1, 2, 3);
        List<Integer> two = Arrays.asList(3, 4);

        List<Integer[]> pairs = one.stream()
                .flatMap(o -> two.stream()
                        .filter(t -> (o + t) % 3 == 0)
                        .map(t -> new Integer[]{o, t})
                )
                .collect(toList());
        DishesStreamTest.show("pairs divisible by 3 = %s", asPairs(pairs));
    }

    @Test
    public void quiz53() { // p136
        int count = Dish.menu().stream()
                .map(d -> 1)
                .reduce(0, (a, b) -> (a + 1));
        assertThat(count)
                .isEqualTo(12);
    }

    @Test
    public void section531() { // section 5.3.1 anyMatch for vegitarian
        AtomicInteger anyCtr = new AtomicInteger(0);
        DishesStreamTest.show("%s ", Dish.menu().stream()
                .anyMatch(d -> {
                    anyCtr.incrementAndGet();
                    return d.isVegetarian();
                }));
        DishesStreamTest.show("anyCtr = %d", anyCtr.get());
    }

    @Test
    public void section533() {
        AtomicInteger filterCtr = new AtomicInteger(0);

        // compare findAny vs findFirst
        // CONCLUSION -- since the terminal operation wants only ONE, filter runs exactly identical times
        //                if parallelism is ever desired findFirst is IN-EFFICIENT
        DishesStreamTest.show("%s ", Dish.menu().stream()
                .filter(dish -> {
                    filterCtr.incrementAndGet();
                    return dish.isVegetarian();
                })
                .findAny()
                .orElse(Dish.EMPTY));
        DishesStreamTest.show("numFilters for findAny=%s", filterCtr);
        filterCtr.set(0);
        DishesStreamTest.show("%s ", Dish.menu().stream()
                .filter(dish -> {
                    filterCtr.incrementAndGet();
                    return dish.isVegetarian();
                })
                .findFirst()
                .orElse(Dish.EMPTY));
        DishesStreamTest.show("numFilters for findFirst=%s", filterCtr);
    }

    @Test
    public void quiz551() {
        DishesStreamTest.show("of 2011 sorted ascending %s", Transaction.transactions().stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList())
        );
    }

    @Test
    public void quiz552() {
        DishesStreamTest.show("all unique cities of traders %s", Transaction.transactions().stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList())
        );
    }

    @Test
    public void quiz553() {
        DishesStreamTest.show("all traders in Cambridge sorted by name = %s", Transaction.transactions().stream()
                .filter(transaction ->
                        StringUtils.equals("Cambridge", transaction.getTrader().getCity()))
                .map(Transaction::getTrader)
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList())
        );
    }

    @Test
    public void quiz554() {
        DishesStreamTest.show("Traders names sorted alphabetically = %s", Transaction.transactions().stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (a, b) -> a + b)
        );
    }

    @Test
    public void quiz555() {
        String city = "Milan";
        AtomicInteger ctr = new AtomicInteger(0);

        ctr.set(0);
        // amyMatch or findAny has SAME compares
        DishesStreamTest.show("anyMatch in %s = %s", city, Transaction.transactions().stream()
                .anyMatch(transaction -> {
                    ctr.incrementAndGet();
                    return city.equals(transaction.getTrader().getCity());
                })
        );
        DishesStreamTest.show("anyMatch count=%s", ctr);
    }

    @Test
    public void quiz556() {
        String city = "Cambridge";
        DishesStreamTest.show("all transaction' value of %s = %s", city, Transaction.transactions().stream()
                .filter(transaction -> city.equals(transaction.getTrader().getCity()))
                .map(transaction -> {
                    DishesStreamTest.show("  counting chosen=[%s]", transaction);
                    return transaction.getValue();
                })
                .reduce(0, (a, b) -> a + b)
        );
    }

    @Test
    public void quiz557() {
        DishesStreamTest.show("highest value of trade = %s", Transaction.transactions().stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .orElseThrow(() -> new RuntimeException("is empty"))
        );
    }

    @Test
    public void quiz558() {
        DishesStreamTest.show("lowest amount of trade = %s", Transaction.transactions().stream()
                        .min(comparing(Transaction::getValue))
//                .map(Transaction::getValue)
//                .reduce(Integer::min)
                        .map(Transaction::getValue)
                        .orElseThrow(() -> new RuntimeException("empty"))
        );
    }

    @Test
    public void section561() {
        DishesStreamTest.show("max calories = %d", Dish.menu().stream()
                // .filter(Dish::isVegetarian)
                .mapToInt(Dish::getCalories)
                .max()
                .orElseThrow(() -> new RuntimeException("empty"))
        );

        DishesStreamTest.show("sum of calories = %d", Dish.menu().stream()
                .mapToInt(Dish::getCalories)
                .sum()
        );

        DishesStreamTest.show("max calorie item = %s", Dish.menu().stream()
                .max(comparing(Dish::getCalories))
                .orElseThrow(() -> new RuntimeException("empty"))
        );

        DishesStreamTest.show("max calorie vegetarian item = %s", Dish.menu().stream()
                .filter(Dish::isVegetarian)
                .max(comparing(Dish::getCalories))
                .orElseThrow(() -> new RuntimeException("empty"))
        );

        DishesStreamTest.show("max calorie fish dish = %s", Dish.menu().stream()
                .filter(dish -> dish.getDishType() == Dish.DishType.FISH)
                .max(comparing(Dish::getCalories))
                .orElseThrow(() -> new RuntimeException("empty"))
        );

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() ->
                        Dish.menu().stream()
                                .filter(dish -> dish.getDishType() == Dish.DishType.NON_EXISTANT)
                                .max(comparing(Dish::getCalories))
                                .orElseThrow(() -> new RuntimeException("empty non-existant"))
                )
                .withMessageContaining("empty non-existant");
    }

    @Test
    public void section563() { // p148
        final int max = 100;
        AtomicInteger ctr = new AtomicInteger(0);
        AtomicInteger filterCtr = new AtomicInteger(0);
        IntStream.rangeClosed(1, max)
                .boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, max)
                                .filter(b -> {
                                    filterCtr.incrementAndGet();
                                    return Math.sqrt(a * a + b * b) % 1 == 0;
                                })
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                )
                .limit(3)
                .forEach(ary -> System.out.printf("%d: (%d, %d, %d)%n", ctr.incrementAndGet(), ary[0], ary[1], ary[2]));
        System.out.printf("filterCtr = %d %n", filterCtr.get());

        filterCtr.set(0);
        // CONCLUSION: both run the same number of filters, but below uses ONE sqrt per attempt!
        IntStream.rangeClosed(1, max)
                .boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, max)
                                .mapToObj(b -> new int[]{a, b, a * a + b * b})
                                .filter(ary -> {
                                    filterCtr.incrementAndGet();
                                    return Math.sqrt(ary[0] * ary[0] + ary[1] * ary[1]) % 1 == 0;
                                })
                )
                .limit(3)
                .forEach(ary -> System.out.printf("%d: (%d, %d, %d)%n", ctr.incrementAndGet(), ary[0], ary[1], ary[2]));
        System.out.printf("with more Maps::filterCtr = %d %n", filterCtr.get());

    }

    @Test
    public void quiz54() { // page 154
        Stream.iterate(new int[]{0, 1},
                ary -> new int[]{ary[1], ary[0] + ary[1]})
                .limit(8)
                .forEach(ary -> System.out.printf("(%d, %d) ", ary[0], ary[1]));
        System.out.printf("%n");

        // page 157
        IntStream.generate(new IntSupplier() {
            private int prev = 0;
            private int curr = 1;

            @Override
            public int getAsInt() {
                int next = prev + curr;
                int prevToReturn = prev;
                prev = curr;
                curr = next;
                return prevToReturn;
            }
        })
                .limit(8)
                .forEach(i -> System.out.printf("%d ", i));
        System.out.printf("%n");
    }
}
