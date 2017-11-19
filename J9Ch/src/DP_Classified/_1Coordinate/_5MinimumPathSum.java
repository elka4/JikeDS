package DP_Classified._1Coordinate;

/*
• 设从(0, 0)走到格子(i, j)的路径的最小数字总和是f[i][j]
f[i][j] = min{f[i-1][j], f[i][j-1]} + A[i][j]

f[i][j]
从(0, 0)走到格子(i, j) 的最小路径数字总和

f[i-1][j]
从(0, 0)走到格子(i-1, j) 的最小路径数字总和

f[i][j-1]}
从(0, 0)走到格子(i, j-1) 的最小路径数字总和

A[i][j]
格子(i, j) 的数字
 */

/*
 初始条件:f[0][0] = A[0][0]
• 边界情况:i = 0 或 j = 0，则前一步只能有一个方向过来
 */

//  Minimum Path Sum
public class _5MinimumPathSum {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int M = grid.length;
        int N = grid[0].length;
        int[][] sum = new int[M][N];

        sum[0][0] = grid[0][0];

        for (int i = 1; i < M; i++) {
            sum[i][0] = sum[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < N; i++) {
            sum[0][i] = sum[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                sum[i][j] = Math.min(sum[i - 1][j], sum[i][j - 1]) + grid[i][j];
            }
        }

        return sum[M - 1][N - 1];
    }



//-------------------------------------------------------------------------///////////////

    // 方法二
    /**
     * @param A: a list of lists of integers.
     * @return: An integer, minimizes the sum of all numbers along its path
     */
    public int minPathSum2(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) {
            return 0;
        }

        int m = A.length, n = A[0].length;
        int[][] f = new int[2][n];
        int old, now = 0;

        for (int i = 0; i < m; ++i) {
            old = now;
            now = 1 - now;
            for (int j = 0; j < n; ++j) {
                int min = -1;
                if (i > 0 && (min == -1 || f[old][j] < min)) {
                    min = f[old][j];
                }
                if (j > 0 && (min == -1 || f[now][j-1] < min)) {
                    min = f[now][j-1];
                }

                if (min == -1) {
                    min = 0;
                }

                f[now][j] = min + A[i][j];
            }
        }

        return f[now][n-1];
    }

//-------------------------------------------------------------------------///////////////

}
/*
Minimum Path Sum

 Description
 Notes
 Testcase
 Judge
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

 Notice

You can only move either down or right at any point in time.
 */