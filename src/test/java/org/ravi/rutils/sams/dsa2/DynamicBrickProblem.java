package org.ravi.rutils.sams.dsa2;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

// https://algorithmsandme.com/category/algorithms/dynamic-programming/
public class DynamicBrickProblem {
    // simple recursive with repeated compuations
    public static int findWays(int rows) {
        Stopwatch timer = Stopwatch.createStarted();
        //System.out.printf("\t Processing %d %n", rows);
        if (rows <= 3) {
            return 1;
        }

        int ways =  findWays(rows - 1) + findWays(rows - 4);
        if (timer.elapsed(TimeUnit.SECONDS) > 60) {
            System.out.printf(" %d recursive-rows consumed=%d seconds %n", rows, timer.elapsed(TimeUnit.SECONDS));
        }
        return ways;
    }

    public static int findDynamicWays(int rows) {
        Stopwatch timer = Stopwatch.createStarted();
        int ways[] = new int[rows + 1];
        // initiailze (first if block)
        ways[0] = ways[1] = ways[2] = ways[3] = 1;
        for (int i = 4; i <= rows; i++) {
            ways[i] = ways[i - 1] + ways[i - 4];
        }

        int answer = ways[rows];
        System.out.printf(" %d dynamic-rows consumed=%s %n", rows, timer);
        return answer;
    }

    public static void main(String args[]) {
        int height = 66;
        System.out.printf("%10s = %d %n", "Recursive", findWays(height));
        System.out.printf("%10s = %d %n", "Dynamic", findDynamicWays(height));
    }
}
