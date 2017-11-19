package DP_Classified._1Coordinate;

// Unique Paths II
/*
最后一步一定是从左边(i, j-1)或上边(i-1, j)过来
状态f[i][j]表示从左上角有多少种方式走到格子(i, j)
坐标型动态规划:数组下标[i][j]即坐标(i, j):

f[i][j] = f[i-1][j] + f[i][j-1]
机器人有多少种方式 走到(i, j)
机器人有多少种 方式走到(i-1, j)
机器人有多少种方 式走到(i, j-1)
 */

/*
 初始条件:f[0][0] = 1

 */

public class _1UniquePathsII {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }

        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] paths = new int[n][m];

        for (int i = 0; i < n; i++) {
            if (obstacleGrid[i][0] != 1) {
                paths[i][0] = 1;
            } else {
                break;
            }
        }

        for (int i = 0; i < m; i++) {
            if (obstacleGrid[0][i] != 1) {
                paths[0][i] = 1;
            } else {
                break;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] != 1) {
                    paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
                } else {
                    paths[i][j] = 0;
                }
            }
        }

        return paths[n - 1][m - 1];
    }

//-------------------------------------------------------------------------////////////

    // 方法二
    /**
     * @param A: A list of lists of integers
     * @return: An integer
     */
    public int uniquePathsWithObstacles2(int[][] A) {
        int m = A.length;
        if (m == 0) {
            return 0;
        }

        int n = A[0].length;
        if (n == 0) {
            return 0;
        }

        if (A[0][0] == 1 || A[m-1][n-1] == 1) {
            return 0;
        }

        int[][] f = new int[2][n];
        int i, j, old, now;
        now = 0;

        for (i = 0; i < m; ++i) {
            old = now;
            now = 1 - now;
            for (j = 0; j < n; ++j) {
                f[now][j] = 0;
                if (A[i][j] == 1) {
                    f[now][j] = 0;
                }
                else {
                    if (i == 0 && j == 0) {
                        f[now][j] = 1;
                    }
                    if (i > 0) {
                        f[now][j] += f[old][j];
                    }
                    if (j > 0) {
                        f[now][j] += f[now][j-1];
                    }
                }
            }
        }

        return f[now][n-1];
    }


//-------------------------------------------------------------------------////////////

}

/*
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

 Notice

m and n will be at most 100.

Have you met this question in a real interview? Yes
Example
For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.
 */