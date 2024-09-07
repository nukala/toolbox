package org.ravi.udemy.jdk8.dates;

import org.ravi.udemy.dsa.WorthLooking;

import java.time.LocalDate;
import java.time.Period;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

public class ComparingDatesPeriodExample {
    public static void main(String[] args) {
        LocalDate beg = LocalDate.now().withMonth(1).withDayOfMonth(1);
        LocalDate eoy = LocalDate.now().withMonth(12).with(lastDayOfMonth());

        System.out.println("beg=" + beg + ", eoy=" + eoy);
        @WorthLooking("performs 31-1 only, not the date-representation")
        Period till = beg.until(eoy);
        System.out.println(till + " - getDays=" + till.getDays()
                + ", months=" + till.getMonths()
                + ", yrs=" + till.getYears());

        Period tenDays = Period.of(0, 0, 10);
        System.out.println(tenDays + " - days=" + tenDays.getDays());

        Period tenYrs = Period.ofYears(10);
        System.out.println(tenYrs + " - years=" + tenYrs.getYears()
                + ", totalMonths=" + tenYrs.toTotalMonths()
                + ", NO TOTAL DAYS METHOD");

        @WorthLooking("For date related comparisons")
        Period btwn = Period.between(beg, eoy);
        System.out.println(btwn + " - days=" + btwn.getDays() + ", months="
                + btwn.getMonths() + ", yrs=" + btwn.getYears());
    }
}
