/*
* 980. Unique Paths III
* hard
* https://leetcode.com/problems/unique-paths-iii/
*
* 43ms / 38.9MB
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


public class H_0980uniquePathIII_backtrack_heuristic {
    public static int rowSize = 0;
    public static int colSize = 0;
    public static int routeCount = 0;
    public static int totalStep = 0;

    public static int[] endPoint = {-1, -1};

    public static void print2dAry(int[][] ary2d) {
        for (int i = 0; i < ary2d.length; i++) {
            for (int j = 0; j < ary2d[0].length; j++) {
                System.out.print(ary2d[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static void patrol(int[][] board, int row, int col, int step) {
        if (row >= 0 && row < rowSize && col >= 0 && col < colSize) {
            if (board[row][col] == 0) {
//                board[row][col] = step + 1;
                board[row][col] = 1;


                int[][] board1 = java.util.Arrays.stream(board).map(el -> el.clone()).toArray($ -> board.clone());
                patrol(board1, row, col + 1, step + 1);
                int[][] board2 = java.util.Arrays.stream(board).map(el -> el.clone()).toArray($ -> board.clone());
                patrol(board2, row, col - 1, step + 1);
                int[][] board3 = java.util.Arrays.stream(board).map(el -> el.clone()).toArray($ -> board.clone());
                patrol(board3, row + 1, col, step + 1);
                int[][] board4 = java.util.Arrays.stream(board).map(el -> el.clone()).toArray($ -> board.clone());
                patrol(board4, row - 1, col, step + 1);
            } else if (board[row][col] == -2 && step == totalStep && col == endPoint[1] && row == endPoint[0]) {
                routeCount++;
//                print2dAry(board);
            }

        }
    }

    public static int uniquePathsIII(int[][] grid) {
        rowSize = grid.length;
        colSize = grid[0].length;
        totalStep = rowSize * colSize - 1;
        routeCount = 0;
        endPoint = new int[]{-1, -1};

        int startRow = -1;
        int startCol = -1;

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (grid[i][j] == -1) {
                    totalStep--;
                } else if (grid[i][j] == 1) {
                    startRow = i;
                    startCol = j;
                } else if (grid[i][j] == 2) {
                    endPoint = new int[]{i, j};
                    grid[i][j] = -2;
                }
            }
        }

        grid[startRow][startCol] = 0;
        patrol(grid, startRow, startCol, 0);

        return routeCount;
    }

    public static void main(String[] args) {
        int[][] grid1 = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}};
        System.out.println(uniquePathsIII(grid1));

//        Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
//        Output: 2
        int[][] grid2 = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}};
        System.out.println(uniquePathsIII(grid2));

        int[][] grid3 = {{0, 1}, {2, 0}};
        System.out.println(uniquePathsIII(grid3));

    }

}
