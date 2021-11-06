import java.util.Arrays;

// time 1ms >100%
// ram 38.2M >56%

/*
994. Rotting Oranges
(https://leetcode.com/problems/rotting-oranges/)

You are given an m x n grid where each cell can have one of three values:

    0 representing an empty cell,
    1 representing a fresh orange, or
    2 representing a rotten orange.

Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.


Example 1:

Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

Example 2:

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

Example 3:

Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.


Constraints:

    m == grid.length
    n == grid[i].length
    1 <= m, n <= 10
    grid[i][j] is 0, 1, or 2.


* * */


public class M_0994rottingOranges {
    public static int orangesRotting(int[][] grid) {
//        1.get a copy of the matrix and spread rotten in the new grid
        int gridLen = grid.length;
        int[][] newGrid = new int[gridLen][grid[0].length];

        for (int i = 0; i < gridLen; i++) {
            newGrid[i] = Arrays.copyOf(grid[i], grid[i].length);
        }

        int isFresh = 0, isRotten = 0, spreadable = 0;
        int left = -1, right = -1, up = -1, down = -1;

//        check 4-directional for each cell, and assign status
        for (int i = 0; i < gridLen; i++) {
            int subLen = grid[i].length;
            for (int j = 0; j < subLen; j++) {
                if (grid[i][j] == 1) {
                    isFresh = 1;
                } else if (grid[i][j] == 2) {
                    isRotten = 1;
                    left = j > 0 ? j - 1 : j;
                    if (newGrid[i][left] == 1) {
                        newGrid[i][left] = 2;
                        spreadable = 1;
                    }

                    right = j < subLen - 1 ? j + 1 : j;
                    if (newGrid[i][right] == 1) {
                        newGrid[i][right] = 2;
                        spreadable = 1;
                    }

                    up = i > 0 ? i - 1 : i;
                    if (newGrid[up][j] == 1) {
                        newGrid[up][j] = 2;
                        spreadable = 1;
                    }

                    down = i < gridLen - 1 ? i + 1 : i;
                    if (newGrid[down][j] == 1) {
                        newGrid[down][j] = 2;
                        spreadable = 1;
                    }
                }
            }
        }

//        3. return status and base condition for recursion
        if (isFresh == 1 && spreadable == 1) {
            int subCheck = orangesRotting(newGrid);
            if (subCheck <= -1) {
                return -1;
            } else return 1 + subCheck;

        } else if (isFresh == 1 && spreadable == 0) {
            return -1;
        } else return 0;
    }

    public static void main(String[] args) {
        long milliStart = System.currentTimeMillis();

        int[][] input = {{1, 2, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println(orangesRotting(input));

        System.out.println("Time used in milli: " + (System.currentTimeMillis() - milliStart));
    }
}
