package org.ravi.rutils.sams.dsa2;

import org.junit.Test;
import org.ravi.udemy.dsa.WorthLooking;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FizzBuzzTest {
    private static final HashMap<Integer, String> divisorsMap = new HashMap<>() {
        {
            put(3, "Fizz");
            put(5, "Buzz");
            put(7, "Jazz");
            // for 11, just added test cases!
            put(11, "Elzz");
        }
    };

    public static String fizzBuzz(int i) {
        StringBuilder sb = new StringBuilder(17);
        @WorthLooking("check out usage of stream -> toList collector")
        List<Integer> divisors = divisorsMap.keySet().stream().toList();
        for (int d : divisors) {
            boolean works = i % d == 0;
            if (works) {
                sb.append(divisorsMap.get(d));
            }
            //System.out.printf("%d%%%d=%b, sb=[%s]%n", i, d, works, sb.toString());
        }
        // RNTODO - use stream.forEach?
        return sb.toString();
    }

    @Test
    public void fizzBuzzTest() {
        assertThat(fizzBuzz(2)).isEqualTo("");
        assertThat(fizzBuzz(3)).isEqualTo("Fizz");
        assertThat(fizzBuzz(5)).isEqualTo("Buzz");
        assertThat(fizzBuzz(7)).isEqualTo("Jazz");
        assertThat(fizzBuzz(11)).isEqualTo("Elzz");
        assertThat(fizzBuzz(15)).isEqualTo("FizzBuzz");
        assertThat(fizzBuzz(33)).isEqualTo("FizzElzz");
        assertThat(fizzBuzz(55)).isEqualTo("BuzzElzz");
        assertThat(fizzBuzz(77)).isEqualTo("JazzElzz");
        assertThat(fizzBuzz(105)).isEqualTo("FizzBuzzJazz");
        assertThat(fizzBuzz(1050)).isEqualTo("FizzBuzzJazz");
        assertThat(fizzBuzz(3*5*7*11)).isEqualTo("FizzBuzzJazzElzz");//1155
        assertThat(fizzBuzz(1157)).isEmpty();
    }
}
