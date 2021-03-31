package org.ravi.ivquiz.others;

import org.junit.Test;
import org.ravi.udemy.dsa.WorthLooking;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 * <p/>
 * Imagine the two integers as hours that one might want to reserve
 *  a conference room.
 * <br/>
 * 
 *class BookIntervals
 ---- boolean book(int start, int end)
 obj.book(10, 20) - true
 obj.book(15, 25) - false
 obj.book(1, 5) - true
 * If you need more classes, simply define them inline.
 */

// devs=25-30 total=300/350
// book=reserve-conference-room or similar
public class BookIntervals {
    private void doCheck(MyIntervals site, int start, int end, boolean exp) {
        assertThat(site.reserve(start, end))
                .as(String.format("(%d, %d) = %b", start, end, exp))
                .isEqualTo(exp);
    }

    @Test
    public void megaTestThatDoesEverything() {
        MyIntervals site = new MyIntervals();

        doCheck(site, 10, 20, true);

        doCheck(site, 15, 25, false);
        doCheck(site, 1, 5, true);

        doCheck(site, 6, 19, false);

        doCheck(site, 6, 19, false);

        doCheck(site, 12, 16, false);

        doCheck(site, 2, 4, false);

        doCheck(site, 21, 23, true);

        doCheck(site, 0, 30, false);

        doCheck(site, 0, 2, false);

        doCheck(site, 0, 1, false);


        System.out.printf("%n%nRESERVATIONS IN THE SYSTEM:%n");
        site.reservedList
                .forEach(
                        (rl) -> System.out.printf("\t(%d, %d) RESERVED %n", rl.start, rl.end)
                );
    }

//    // (10, 20), (25, 35), (50, 60), (70, 80) (100, 150)
//
//    .reserve(70, 80)  30, 40
// 20, 50

    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @WorthLooking("Missed the second part of condition. Pay attention to the equals sign too")
        public boolean isOverlap(Interval other) {
            return other.start <= end &&
                    other.end >= start;
        }
    }

    static class MyIntervals {
        List<Interval> reservedList = new ArrayList<>();

        public boolean reserve(int start, int end) {
            // validate start < end;
            Interval checking = new Interval(start, end);

            for (Interval reserved : reservedList) {
                if (reserved.isOverlap(checking)) {
                    return false;
                }
            }

            reservedList.add(checking);
            return true;
        }
    }
}
