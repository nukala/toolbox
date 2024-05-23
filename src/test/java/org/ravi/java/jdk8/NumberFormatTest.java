package org.ravi.java.jdk8;


import org.junit.Test;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * <a href="https://docs.oracle.com/javase/tutorial/java/data/numberformat.html">number format</a>
 */
class DecimalFormatDemo {

    static public void customFormat(String pattern, double value) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        System.out.println(value + "  " + pattern + "  " + output);
    }
}
public class NumberFormatTest {
    @Test
    public void decimalFormatTest() {
        DecimalFormatDemo.customFormat("###,###.###", 123456.789);
        DecimalFormatDemo.customFormat("###.##", 123456.789);
        DecimalFormatDemo.customFormat("000000.000", 123.78);
        DecimalFormatDemo.customFormat("$###,###.###", 12345.67);
        DecimalFormatDemo.customFormat("$###,###,###.###", 12345.67);
    }

    @Test
    public void formatTest() {
        long n = 461012456;
        System.out.format("%d%n", n);      //  -->  "461012"
        System.out.format("%08d%n", n);    //  -->  "00461012"
        System.out.format("%+8d%n", n);    //  -->  " +461012"
        System.out.format("%,8d%n", n);    // -->  " 461,012"
        System.out.format("%+,8d%n%n", n); //  -->  "+461,012"

        double pi = Math.PI;

        System.out.format("%f%n", pi);       // -->  "3.141593"
        System.out.format("%.3f%n", pi);     // -->  "3.142"
        System.out.format("%10.3f%n", pi);   // -->  "     3.142"
        System.out.format("%-10.3f%n", pi);  // -->  "3.142"
        System.out.format(Locale.CANADA_FRENCH,
                "Canada French %-10.4f%n%n", pi); // -->  "3,1416"

        Calendar c = Calendar.getInstance();
        System.out.format("%tB %te, %tY%n", c, c, c); // -->  "May 29, 2006"

        System.out.format("%tl:%tM %tp%n", c, c, c);  // -->  "2:34 am"

        System.out.format("%tD%n", c);    // -->  "05/29/06"
    }
}
