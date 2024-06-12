package org.ravi.udemy.dsa;


import lombok.Getter;
import lombok.Setter;

/**
 * Count number of islands by using another boolean array and DFS.  Returns count of islands (that have a 1) in a
 * given boolean 2D square-matrix
 * <br/>
 * more OO than www.geeksforgeeks.org/find-the-number-of-islands-using-dfs
 * <p/>
 * Time complexity = O(r*c) or O(n^2)
 * <p/>
 * Space compexity is O(r*c) or O(n^2)
 */
public class CountIslandDFS {
    @Getter
    @Setter
    private static int dfsInvoked = 0;

    private void assertRowsAreSameLen(int[][] matrix) {
        int numCols = matrix[0].length;

        for (int r = 1; r < matrix.length; r++) {
            int len = matrix[r].length;
            if (len != numCols) {
                throw new IllegalArgumentException(
                        String.format("IRREGULAR-ARRAY: row=%d has cols=%d, expected=%d", r, len, numCols));
            }
        }
    }

    private boolean isValidRowCol(int[][] matrix, int row, int col) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        if (row >= 0 && row < numRows && col >= 0 && col < numCols) {
            return true;
        }
        return false;
    }

    // A function to check if a given cell (row, col) can be included in DFS
    private boolean isIsland(int[][] matrix, int row, int col, boolean[][] visited) {
        // row number is in range, column number is in range
        // and value is 1 and not yet visited
        if (isValidRowCol(matrix, row, col)) {
            //  not been visited AND is an island
            return !visited[row][col] && matrix[row][col] == 1;
        }
        return false;
    }

    // A utility function to do DFS for a 2D boolean matrix.
    // It only considers the 8 neighbors as adjacent vertices
    private void depthFirst(int[][] matrix, int row, int col, boolean[][] visited) {
        // These arrays are used to get row and column
        // numbers of 8 neighbors of a given cell
        @WorthLooking("figure out neighbors' indices math, start with comments FIRST!")
        int[][] neighborAddends = {
                {-1, -1}, // above-left
                {-1, 0}, // above-
                {-1, 1}, // above-right
                {0, -1}, // -left
                {0, 1}, // -right
                {1, -1}, // bottom-left
                {1, 0}, // bottom-
                {1, 1} // bottom-right
        };

        dfsInvoked++;
        // Mark this cell as visited
        visited[row][col] = true;

        // Recur for all connected neighbours
        for (int k = 0; k < 8; ++k) {
            int rowAddend = neighborAddends[k][0];
            int colAddend = neighborAddends[k][1];
            if (isIsland(matrix, row + rowAddend, col + colAddend, visited)) {
                depthFirst(matrix, row + rowAddend, col + colAddend, visited);
            }
        }
    }

    public int countIslands(int[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        // ensure all columns are same length
        assertRowsAreSameLen(matrix);
        setDfsInvoked(0);

        // Make a bool array to mark visited cells.
        // Initially all cells are unvisited
        boolean[][] visited = new boolean[numRows][numCols];

        // Initialize count as 0 and traverse through the
        // all cells of given matrix
        int count = 0;
        for (int r = 0; r < numRows; ++r) {
            for (int c = 0; c < numCols; ++c) {
                int theCell = matrix[r][c];
                if (theCell == 1 && !visited[r][c]) {
                    // If a cell with value 1 is not visited yet, then new island found,
                    // Visit all cells in this island and increment island count
                    depthFirst(matrix, r, c, visited);
                    count++;
                }
            }
        }

        return count;
    }
}
