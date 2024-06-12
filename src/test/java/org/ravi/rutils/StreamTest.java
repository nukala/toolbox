package org.ravi.rutils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.ravi.rutils.StreamUtils.contains;

/**
 * I typed these up while learning about streams.
 * <br/>
 * Some of these are not really tests. They are a way for me to start playing with streams.
 */
public class StreamTest {
    private static final Logger logger = LoggerFactory.getLogger(StreamTest.class);
    private final String[] array = {"ravi", "was", "here", "and", "so", "was", "I", "nothing", "more"};

    @Test
    public void streamMapTest() {
        Lists.newArrayList("there", "once", "was", "a", "lamb").stream()
                .map(StringUtils::upperCase)
                .forEach(str -> logger.info("uppercase={}", str));
    }

    @Test
    public void sizeTest() {
        int sum = Stream.of("abc1", "qqq", "lkj4", "qwrt7", "def2", "ghi3")
                .skip(1)
                .map(s -> StringUtils.substring(s, 0, 3))
                .sorted()
                .peek(e -> logger.info("{} ", e))
                //.count();
                .mapToInt(e -> 1)
                .sum();

        assertThat(sum)
                .isEqualTo(5);
    }

    @Test
    public void emptyStream() {
        assertThat(Stream.empty()
                .findFirst())
                .isEmpty();

        assertThat(Stream.empty()
                .findAny())
                .isEmpty();

        assertThat(Stream.empty()
                .count())
                .isEqualTo(0L);
    }

    @Test
    public void collectFromStream() {
        List<Product> productList = Arrays.asList(new Product(23, "potatoes"),
                new Product(14, "orange"), new Product(13, "lemon"),
                new Product(23, "bread"), new Product(13, "sugar"),
                new Product(44, "lemon"));

        List<String> names = productList.stream()
                .map(Product::name)
                .distinct()
                .collect(Collectors.toList());
        assertThat(names)
                .hasSize(5);

        String joined = productList.stream()
                .map(Product::name)
                .sorted()
                .collect(Collectors.joining(", ", "[", "]"));
        logger.info("joined = :{}:", joined);

        IntSummaryStatistics intStats = productList.stream()
                .map(Product::name)
                .collect(Collectors.summarizingInt(String::length));
        assertThat(intStats.getCount())
                .isEqualTo(6);
        logger.info("intStats=[{}]", intStats);

        Map<String, Set<String>> groupedMap = productList.stream()
                .map(Product::name)
                .collect(Collectors.groupingBy(StringUtils::upperCase, Collectors.toSet()));
        assertThat(groupedMap)
                .containsKeys("BREAD", "POTATOES", "LEMON", "SUGAR");
        logger.info("groupedMap=[{}]", groupedMap);
    }

    @Test
    public void splitString() {
        String bigStr = "ravi,was,here,so,were,numerous,others";

        Stream<String> stream = Stream.of(bigStr.split(","));

        List<String> immutableList = stream
                .collect(ImmutableList.toImmutableList());
        assertThat(immutableList)
                .isInstanceOf(ImmutableList.class);
    }

    @Test
    public void boundaryConditionsInContains() {
        // error conditions
        assertThatNullPointerException()
                .as("null array")
                .isThrownBy(() -> contains((String[]) null, "blah", StringUtils::startsWith))
                .withMessageContaining("array cannot be null");
        assertThatNullPointerException()
                .as("null stream")
                .isThrownBy(() -> contains((Stream<String>) null, "blah", StringUtils::startsWith))
                .withMessageContaining("stream cannot be null");
        assertThatNullPointerException()
                .as("null predicate")
                .isThrownBy(() -> contains(array, "blah", null))
                .withMessageContaining("biPredicate cannot be null");
    }

    @Test
    public void usingBiPredicate() {
        BiPredicate<String, String> startsWith = StringUtils::startsWith;
        assertThat(contains(array, "ravi", startsWith))
                .isTrue();
        assertThat(contains(array, "boo", startsWith))
                .isFalse();
        assertThat(contains(array, null, startsWith))
                .isFalse();
    }

    @Test
    public void arrayContains() {
        assertThat(contains(array, "ravi", Objects::equals))
                .isTrue();
        assertThat(contains(array, "boo", Objects::equals))
                .isFalse();
        assertThat(contains(array, null, Objects::equals))
                .isFalse();
    }

    @Test
    public void fromArray() {
        // to remove dups from array and print them (see docs.oracle.com/javase/tutorial/collections/interfaces/set.html)
        String[] ary = new String[]{"hello", "this", "is", "ravi", "this", "here", "is", "hello", "ravi", "is"};

        // Stream.forEach requires underlying stream to be not-modified
        // List.forEach may change values of list but not the structure (mostly)
        Arrays.stream(ary)
                .collect(Collectors.toCollection(LinkedHashSet::new))
                .forEach(e -> System.out.printf("%s %n", e));
    }

    @Test
    public void rangeClosedTest() {
        StringJoiner joiner = new StringJoiner(",");
        IntStream.rangeClosed(1, 5)
                .boxed()
                .forEach((Integer ii) -> joiner.add(ii.toString()));

        assertThat(joiner.toString())
                .isEqualTo("1,2,3,4,5");
    }

    private record Product(int count, String name) {
    }
}
