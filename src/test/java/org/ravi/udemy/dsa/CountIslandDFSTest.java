package org.ravi.udemy.dsa;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class CountIslandDFSTest {
    int[][] fiveByFive = new int[][]{
            {1, 1, 0, 0, 0},
            {0, 1, 0, 0, 1},
            {1, 0, 0, 1, 1},
            {0, 0, 0, 0, 0},
            {1, 0, 1, 0, 1}
    };

    // Driver method
    @Test
    public void fiveByFiveArray() {
        CountIslandDFS dfs = new CountIslandDFS();
        int actual = dfs.countIslands(fiveByFive);
        System.out.printf("fiveByFive: Number of islands is: %d, numDFS=%d %n", actual, dfs.getDfsInvoked());
        assertThat(actual)
                .isEqualTo(5);
    }

    @Test
    public void sevenByFiveArray() {
        int[][] sevenByFive = new int[][]{
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0}
        };
        CountIslandDFS dfs = new CountIslandDFS();
        int actual = dfs.countIslands(sevenByFive);
        System.out.printf("sevenByFive: Number of islands is: %d, numDFS=%d %n", actual, dfs.getDfsInvoked());
        int numExpected = 6;
        assertThat(actual)
                .withFailMessage("expected island count=%d", numExpected)
                .isEqualTo(numExpected);
    }

    @Test
    public void irregularArray() {
        int[][] irregular = new int[][]{
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1},
                {0, 0, 0, 0},
                {0, 1, 1}
        };
        assertThatExceptionOfType(IllegalArgumentException.class)
                .describedAs("rows=%d, row[0].length=%d, lastRow.length=%d", irregular.length,
                        irregular[0].length, irregular[irregular.length - 1].length)
                .isThrownBy(() -> {
                    new CountIslandDFS().countIslands(irregular);
                })
                .withNoCause()
                .withMessageContaining("IRREGULAR-ARRAY")
        ;
    }

    int[][] twoByThree = {
            {0, 0, 1},
            {1, 0, 1}
    };

    @Test
    public void twoByThree() { // use this for debugging, easier
        CountIslandDFS dfs = new CountIslandDFS();
        int actual = dfs.countIslands(twoByThree);
        System.out.printf("twoByThree: Number of islands is: %d, numDFS=%d %n", actual, dfs.getDfsInvoked());

        assertThat(actual)
                .isEqualTo(2);
    }
}
