package org.ravi.udemy.jdk8;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class PredicateExample {
    static Predicate<Integer> even = ival -> modWithBiP(ival, 2);
    static Predicate<Integer> three = ival -> modWithBiP(ival, 3);
    static Predicate<Integer> five = ival -> modWithBiP(ival, 5);
    static Predicate<Integer> seven = ival -> modWithBiP(ival, 7);

    private static boolean modWithBiP(int i, int divisor) {
        BiPredicate<Integer, Integer> mod = (im, dm) -> im % dm == 0;

        return mod.test(i, divisor);
    }

    static void checkEven(Integer ival) {
        System.out.println("Even Predicate(" + ival + ") = " + even.test(ival));
    }

    static void fizzBuzzTwoAndFive(int val) {
        System.out.println("fizzBuzz25(" + val + ") = " +
                even.and(five).test(val));
    }

    static void threeOrSeven(int val) {
        System.out.println("threeOrFive(" + val + ") = " +
                three.or(seven).test(val));
    }

    static void leapYear(int yr) {
        Predicate<Integer> four = ival -> modWithBiP(ival, 4);
        Predicate<Integer> hundred = ival -> modWithBiP(ival, 100);
        Predicate<Integer> fourHundred = ival -> modWithBiP(ival, 400);
        
        Predicate<Boolean> alwaysFalse = (bb) -> false;
        if (four.test(yr)) {
            if (four.and(hundred).test(yr)) {
                if (four.and(hundred).and(fourHundred).test(yr)) {
                    System.out.println("all-three LeapYear(" + yr + ") = "
                            + alwaysFalse.negate().test(false));
                } else {
                    System.out.println("fourAndHundred.negate leapYear(" + yr + ") = "
                            + four.and(hundred).negate().test(yr));
                }

                return;
            }
            System.out.println("four leapYear(" + yr + ") = " +
                    four.test(yr));
        } else {
            System.out.println("fallThrough leapYear(" + yr + ") = " + alwaysFalse.test(false));
        }
    }

    public static void main(String[] args) {
        System.out.println();
        checkEven(4);
        checkEven(9);

        System.out.println("====");
        fizzBuzzTwoAndFive(10);
        fizzBuzzTwoAndFive(2);
        fizzBuzzTwoAndFive(5);
        fizzBuzzTwoAndFive(20);

        System.out.println("====");
        threeOrSeven(3);
        threeOrSeven(7);
        threeOrSeven(21);
        threeOrSeven(18);

        System.out.println("====");
        List.of(2023, 2024, 2000, 1900).forEach((yr) -> {
            //System.out.println("   ");
            leapYear(yr);
        });
    }
}
