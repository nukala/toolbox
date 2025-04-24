package org.ravi.udemy.dsa;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;

import static org.assertj.core.api.Assertions.assertThat;

public class CompareToTest {

    @Test
    @WorthLooking("compareToTest: proves -1 (this < param) and 1 (this > param) scenario")
    public void compareToTest() {
        Integer small = 1;
        Integer large = 9;

        assertThat(small.compareTo(large))
                .as("negative since this < param")
                .isNegative()
                .isNotPositive()
                .isEqualTo(-1);

        assertThat(large.compareTo(small))
                .as("positive since this > param")
                .isPositive()
                .isNotNegative()
                .isEqualTo(1);

        assertThat(Integer.valueOf(1).compareTo(small))
                .as("zero since both are equal")
                .isNotPositive()
                .isNotNegative()
                .isZero();
    }

    @Test
    public void ascendingTest() {
        List<Integer> nums = Arrays.asList(44, 99, 33, 12, 17, 44);
        @WorthLooking("o1-o2 is ascending, o2-o1 is descending")
        // TODO: use compareTo instead of subtraction so params can be non-integers
        BiFunction<Integer, Integer, Integer> asc = (o1, o2) -> o1 - o2;
        BiFunction<Integer, Integer, Integer> desc = (o1, o2) -> o2 - o1;

        AtomicReference<BiFunction<Integer, Integer, Integer>> funcHolder = new AtomicReference<>(asc);
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return funcHolder.get().apply(o1, o2);
            }
        };

        List<Integer> sorted = nums.stream()
                .sorted(comparator)
                .toList();
        System.out.println(sorted);
        assertThat(sorted.get(0) < sorted.get(1)).isTrue();
        funcHolder.set(desc);
        List<Integer> ds = nums.stream()
                .sorted(comparator)
                .toList();
        System.out.println(ds);
        assertThat(ds.get(0) > ds.get(1)).isTrue();
    }

    @Test
    public void comparatorVsThenComparing() {
        record NumName(Integer num, String name) implements Comparable<NumName> {
            public String toString() {
                return num + "::" + name;
            }

            @Override
            public int compareTo(NumName o) {
                int diff = num.compareTo(o.num());
                // NOT NEEDED. USE thenComparing
                if (diff == 0)
                    return name.compareTo(o.name);
                return diff;
            }
        }
        List<NumName> numNames = Arrays.asList(
                new NumName(1000, "onek"),
                new NumName(1500, "onehalf"),
                new NumName(2000, "twok"),
                new NumName(1000, "oscnd"),
                new NumName(1000, "oabs"),
                new NumName(800, "eight")
        );
        System.out.println("raw list = " + numNames);
        List<NumName> complst = numNames.stream()
                .sorted()
                .toList();

        System.out.println("checkTwiceAndCompare_InCode =" + complst);
        assertThat(complst.get(0).num).isEqualTo(800);
        assertThat(complst.get(1).name).isEqualTo("oabs");
        assertThat(complst.get(2).name).isEqualTo("onek");
        assertThat(complst.get(3).name).isEqualTo("oscnd");

        @WorthLooking("thenComparing sorts after first comparison")
        List<NumName> thnCmp = numNames.stream()
                .sorted(Comparator.comparing(NumName::num)
                        .thenComparing(NumName::name))
                .toList();
        System.out.println("thenComparing = " + thnCmp);
        assertThat(thnCmp.get(0).num).isEqualTo(800);
        assertThat(thnCmp.get(1).name).isEqualTo("oabs");
        assertThat(thnCmp.get(2).name).isEqualTo("onek");
        assertThat(thnCmp.get(3).name).isEqualTo("oscnd");
    }
}
