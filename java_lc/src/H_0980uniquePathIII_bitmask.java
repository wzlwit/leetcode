/*
* 980. Unique Paths III
* hard
* https://leetcode.com/problems/unique-paths-iii/
*

* You are given an m x n integer array grid where grid[i][j] could be:

1 representing the starting square. There is exactly one starting square.
2 representing the ending square. There is exactly one ending square.
0 representing empty squares we can walk over.
-1 representing obstacles that we cannot walk over.

Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.

Example 1:

Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
Output: 2
Explanation: We have the following two paths:
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)

Example 2:

Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
Output: 4
Explanation: We have the following four paths:
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)

Example 3:

Input: grid = [[0,1],[2,0]]
Output: 0
Explanation: There is no path that walks over every empty square exactly once.
Note that the starting and ending square can be anywhere in the grid.

*
Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 20
1 <= m * n <= 20
-1 <= grid[i][j] <= 2
There is exactly one starting cell and one ending cell.

* */

/*
 * SOLUTION:
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths III.
 * Memory Usage: 36.2 MB, less than 80.66% of Java online submissions for Unique Paths III.
* * */


import lib.FormatPrinter.*;

public class H_0980uniquePathIII_bitmask {
    public static int rowSize = 0;
    public static int colSize = 0;
    public static int routeCount = 0;

    //    two options to check if the path covers all the point:
    public static int totalStep = 0;
//    public static int fullBit = 0;

    public static int endRow = -1;
    public static int endCol = -1;

    public static void patrol(int boardBit, int row, int col, int step) {
        if (row >= 0 && row < rowSize && col >= 0 && col < colSize) {
            int posBit = (1 << (row * colSize + col));
            int newBoardBit = (boardBit | posBit);

            if (boardBit != newBoardBit) {
//                System.out.println("step (" + step + "/" + totalStep + "); position (" + row + "," + col + ")");
//                System.out.println(String.valueOf((new StringBuilder(Integer.toBinaryString(posBit)).reverse())));
//                System.out.println(String.valueOf((new StringBuilder(Integer.toBinaryString(newBoardBit)).reverse())));

                if (step < totalStep && (col != endCol || row != endRow)) {
                    step++;
                    patrol(newBoardBit, row, col + 1, step);
                    patrol(newBoardBit, row, col - 1, step);
                    patrol(newBoardBit, row + 1, col, step);
                    patrol(newBoardBit, row - 1, col, step);
                } else if (step == totalStep && col == endCol && row == endRow) {
                    routeCount++;
                }
            }
        }

    }


    public static int uniquePathsIII(int[][] grid) {
        rowSize = grid.length;
        colSize = grid[0].length;
        totalStep = rowSize * colSize - 1;
        routeCount = 0;

        int boardBit = 0;
        int startRow = -1;
        int startCol = -1;

//        when using BitMask, be carefull about the start point
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (grid[i][j] == -1) {
                    boardBit = boardBit | (1 << i * colSize + j);
                    totalStep--;
                } else if (grid[i][j] == 1) {
                    startRow = i;
                    startCol = j;
                } else if (grid[i][j] == 2) {
                    endRow = i;
                    endCol = j;
                }
            }
        }

//        System.out.println("step needed: " + totalStep);
//        System.out.println("board: " + boardBit + " ; start point: " + startRow + " | " + startCol);
        patrol(boardBit, startRow, startCol, 0);

        return routeCount;
    }

    public static void main(String[] args) {
        long milliStart = System.currentTimeMillis();

//        Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
//        Output: 2
        int[][] grid1 = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}};
        System.out.println(uniquePathsIII(grid1));

//        Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
//        Output: 4
        int[][] grid2 = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}};
        System.out.println(uniquePathsIII(grid2));

//        Input: grid = [[0,1],[2,0]]
//        Output: 0
        int[][] grid3 = {{0, 1}, {2, 0}};
        System.out.println(uniquePathsIII(grid3));

        System.out.println("Time used in milli: " + (System.currentTimeMillis() - milliStart));
    }

}
