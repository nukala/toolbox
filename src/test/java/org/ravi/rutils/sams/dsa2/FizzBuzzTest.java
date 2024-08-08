package org.ravi.rutils.sams.dsa2;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.ravi.udemy.dsa.WorthLooking;

import java.util.HashMap;
import java.util.List;

public class FizzBuzzTest {
    @WorthLooking("To help ease future changes")
    private static HashMap<Integer, String> map = new HashMap<Integer, String>() {
        {
            put(3, "Fizz");
            put(5, "Buzz");
            put(7, "Jazz");
        }
    };

    public static String fizzBuzz(int i) {
        StringBuilder sb = new StringBuilder(13);

        @WorthLooking("check out usage of stream -> toList collector")
        List<Integer> divisors = map.keySet().stream().toList();
        for (int d : divisors) {
            boolean works = i % d == 0;
            if (works) {
                sb.append(map.get(d));
            }
            //System.out.printf("%d%%%d=%b, sb=[%s]%n", i, d, works, sb.toString());
        }
        return sb.toString();
    }

    @Test
    public void fizzBuzzTest() {
        Assertions.assertThat(fizzBuzz(2)).isEqualTo("");
        Assertions.assertThat(fizzBuzz(3)).isEqualTo("Fizz");
        Assertions.assertThat(fizzBuzz(5)).isEqualTo("Buzz");
        Assertions.assertThat(fizzBuzz(7)).isEqualTo("Jazz");
        Assertions.assertThat(fizzBuzz(15)).isEqualTo("FizzBuzz");
        Assertions.assertThat(fizzBuzz(105)).isEqualTo("FizzBuzzJazz");
        Assertions.assertThat(fizzBuzz(1050)).isEqualTo("FizzBuzzJazz");
    }
}
