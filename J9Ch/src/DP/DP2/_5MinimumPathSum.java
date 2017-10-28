package DP.DP2;

//• 坐标型动态规划

/*

•   设从(0, 0)走到格子(i, j)的路径的最小数字综合是f[i][j]

f[i][j] = min{f[i-1][j], f[i][j-1]} + A[i][j]

f[i][j]:    从(0, 0)走到格子(i, j)的最小路径数字总和
f[i-1][j]:  从(0, 0)走到格子(i-1, j)的最小路径数字总和
f[i][j-1]:  从(0, 0)走到格子(i, j-1)的最小路径数字总和
A[i][j]:    格子（i，j）的数字

初始条件：f[0][0] = A[0][0]
边界情况：i = 0 或者 j = 0， 则前一步只能有一个方向过来

• f[i][j] = f[i-1][j] + f[i][j-1]
所以， 计算第i行时，只需要第i行和第i-1行。

空间优化：


-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

 */


//  lint 110. Minimum Path Sum
// leet https://leetcode.com/articles/minimum-path-sum/
public class _5MinimumPathSum {
    public int minPathSum11(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int M = grid.length;
        int N = grid[0].length;
        int[][] sum = new int[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 && j == 0) {
                    sum[0][0] = grid[0][0];
                } else if (i == 0) {
                    sum[0][j] = sum[0][j - 1] + grid[0][j];
                } else if (j == 0) {
                    sum[i][0] = sum[i - 1][0] + grid[i][0];
                } else {
                    sum[i][j] = Math.min(sum[i - 1][j], sum[i][j - 1]) + grid[i][j];
                }
            }
        }
        return sum[M - 1][N - 1];
    }


    public int minPathSum111 (int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int M = grid.length;
        int N = grid[0].length;
        int[][] sum = new int[2][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 && j == 0) {
                    sum[0][0] = grid[0][0];
                } else if (i == 0) {
                    sum[0][j] = sum[0][j - 1] + grid[0][j];
                } else if (j == 0) {
                    sum[i % 2][0] = sum[(i - 1) % 2][0] + grid[i][0];
                } else {
                    sum[i % 2][j] = Math.min(sum[(i - 1) % 2][j], sum[i % 2][j - 1]) + grid[i][j];
                }
            }
        }
        return sum[(M - 1) % 2][N - 1];
    }


    public int minPathSum1111 (int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int M = grid.length;
        int N = grid[0].length;
        int[][] sum = new int[2][N];
        int old = 0;
        int now = 1;

        for (int i = 0; i < M; i++) {
            old = now;
            now = 1 - old;
            for (int j = 0; j < N; j++) {
                if (i == 0 && j == 0) {
                    sum[0][0] = grid[0][0];
                } else if (i == 0) {
                    sum[0][j] = sum[0][j - 1] + grid[0][j];
                } else if (j == 0) {
                    sum[now][0] = sum[old][0] + grid[i][0];
                } else {
                    sum[now][j] = Math.min(sum[old][j], sum[now][j - 1]) + grid[i][j];
                }
            }
        }
        return sum[now][N - 1];
    }




//////////////////////////////////////////////////////////////////////////
    // DP
    public int minPathSum(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0){
            return 0;
        }

        int m = A.length;
        int n = A[0].length;
        int[][] f = new int[2][n];
        int old = 1, now = 0;
        int i, j, t1, t2;
        for (i = 0; i < m; ++i) {
            old = now;
            now = 1 - now;
            for (j = 0; j < n; ++j) {
                if (i == 0 && j == 0) {
                    f[now][j] = A[i][j];
                    continue;
                }

                f[now][j] = A[i][j];
                if (i > 0) {
                    t1 = f[old][j];

                } else {
                    t1 = Integer.MAX_VALUE;
                }

                if (j > 0) {
                    t2 = f[now][j - 1];
                } else {
                    t2 = Integer.MAX_VALUE;
                }

                if (t1 < t2) {
                    f[now][j] += t1;
                } else {
                    f[now][j] += t2;
                }
            }
        }

        return f[now][n - 1];
    }

/////////////////////////////////////////////////////////////////////////////

    public int minPathSum1(int[][] grid) {
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





/////////////////////////////////////////////////////////////////////////////

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

/////////////////////////////////////////////////////////////////////////////

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

/*
给定一个只含非负整数的m*n网格，找到一条从左上角到右下角的可以使数字和最小的路径。



 注意事项

你在同一时间只能向下或者向右移动一步
 */