package org.ravi.java.util;

import java.math.BigDecimal;

public class FloatDecimalBigDecimalTest {
    public static void main(String... args) {
        // floating point calculation
        double amount1 = 2.15;
        double amount2 = 1.10;
        System.out.println("difference between 2.15 and 1.0 using double is: " + (amount1 - amount2));
        double ds1 = Double.parseDouble("2.15");
        double ds2 = Double.parseDouble("1.10");
        System.out.println("difference between 2.15 and 1.0 using double-parsed is: " + (ds1 - ds2));
        System.out.printf("%n");

        float f1 = 2.15f;
        float f2 = 1.10f;
        System.out.println("difference between 2.15 and 1.0 using float is:" + (f1 - f2));
        float fs1 = Float.parseFloat("2.15");
        float fs2 = Float.parseFloat("1.10");
        System.out.println("difference between 2.15 and 1.0 using float-parsed is: " + (fs1 - fs2));
        System.out.printf("%n");

        BigDecimal bf1 = new BigDecimal(2.15f);
        BigDecimal bf2 = new BigDecimal(1.10f);
        System.out.println("difference between 2.15 and 1.0 using BigDecimal-newFloat is: " + (bf1.subtract(bf2)));

        BigDecimal bd1 = new BigDecimal(2.15D);
        BigDecimal bd2 = new BigDecimal(1.10D);
        System.out.println("difference between 2.15 and 1.0 using BigDecimal-newDbl is: " + (bd1.subtract(bd2)));

        BigDecimal bs1 = new BigDecimal("2.15");
        BigDecimal bs2 = new BigDecimal("1.10");
        System.out.println("difference between 2.15 and 1.0 using BigDecimal-str is: " + (bs2.subtract(bs1)));
    }
}
