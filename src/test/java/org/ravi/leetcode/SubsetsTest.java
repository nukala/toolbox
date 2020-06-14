package org.ravi.leetcode;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.ravi.udemy.dsa.WorthLooking;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class SubsetsTest {
    @Test
    public void integerSubsets() {
        @WorthLooking("stream of primitives -- use boxed")
        Integer[] ary = IntStream.range(1, 4)
                .boxed()
                .collect(Collectors.toList())
                .toArray(new Integer[0]);

        List<List<Integer>> result = Subsets.subsets(ary);
        result.stream()
                .forEach(l -> System.out.printf("%s ", l));
        System.out.println("");
        assertThat(result.size())
                .isEqualTo((int) Math.pow(2, ary.length));
        assertThat(result.stream()
                .filter(l -> l.size() == 1)
                .collect(Collectors.toList())
                .size())
                .as("THREE subsets of size == 1, expected")
                .isEqualTo(3);
        assertThat(result.stream()
                .filter(l -> l.size() == 2)
                .collect(Collectors.toList())
                .size())
                .as("THREE subsets of size == 2, expected")
                .isEqualTo(3);
        assertThat(result.stream()
                .filter(l -> l.size() == 3)
                .collect(Collectors.toList())
                .size())
                .as("ONE subsets of size == 3, expected")
                .isEqualTo(1);
    }

    @Test
    public void twoDifferentRanges() { // stupid test
        System.out.printf("range: ");
        AtomicInteger elems = new AtomicInteger(0);
        IntStream.range(1, 5)
                .forEach(i -> {
                    System.out.printf("%d ", i);
                    elems.incrementAndGet();
                });
        System.out.println("");
        assertThat(elems.get()).isEqualTo(4);

        elems.set(0);
        System.out.printf("rangeClosed: ");
        IntStream.rangeClosed(1, 5)
                .forEach(i -> {
                    System.out.printf("%d ", i);
                    elems.incrementAndGet();
                });
        System.out.println("");
        assertThat(elems.get()).isEqualTo(5);
    }

    @Test
    public void pwwkew() { // from levelup
        assertThat(Subsets.lengthOfLongestSubstring("pwwkew"))
                .as("longestSubstringLen should be 3")
                .isEqualTo(3);
    }

    @Test
    public void setToArray() {
        // demonstrates how to use setToArray
        Set<String> set = Sets.newHashSet("A1", "A4", "A3", "A2");

        String[] ary = set.toArray(new String[]{});
        System.out.printf("array = [%s]%n", Joiner.on(",").join(ary));
        assertThat(ary)
                .as("Set converted into array")
                .containsAll(set);
    }
}