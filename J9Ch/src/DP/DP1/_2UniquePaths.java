package DP.DP1;

//计数型动态规划

//• 坐标型动态规划

// f[i][j] = f[i-1][j] + f[i][j-1]

/*
f[i][j]: 机器人有多少种方式 走到(i, j)

f[i-1][j]: 机器人有多少种 方式走到(i-1, j)

f[i][j-1]: 机器人有多少种方 式走到(i, j-1)

 */



/*
• 初始条件:f[0][0] = 1，因为机器人只有一种方式到左上角(什么都不做)
• 边界情况:i = 0 或 j = 0，则前一步只能有一个方向过来

• f[0][0] = 1
• 计算第0行:f[0][0], f[0][1], ..., f[0][n-1]
• 计算第1行:f[1][0], f[1][1], ..., f[1][n-1]
•...
• 计算第m-1行:f[m-1][0], f[m-1][1], ..., f[m-1][n-1]
• 答案是f[m-1][n-1]
• 时间复杂度(计算步数):O(MN)，空间复杂度(数组大小):O(MN)
 */

//Unique Paths
public class _2UniquePaths {
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 1;
        }

        int[][] sum = new int[m][n];
        for (int i = 0; i < m; i++) {
            sum[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            sum[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1];
            }
        }
        return sum[m - 1][n - 1];
    }

////////////////////////////////////////////////////////////////////////////

    // 9Ch DP
    // 方法二
    /**
     * @param n, m: positive integer (1 <= n ,m <= 100)
     * @return an integer
     */
    public int uniquePaths2(int m, int n) {
        int[][] f = new int[m][n];
        int i, j;
        // row
        for (i = 0; i < m; ++i) {
            // column
            for (j = 0; j < n; ++j) {
                if (i == 0 || j == 0) {
                    f[i][j] = 1; // corner case
                }
                else {       // up         left
                    f[i][j] = f[i-1][j] + f[i][j-1];
                }
            }
        }

        return f[m-1][n-1];
    }

////////////////////////////////////////////////////////////////////////////
    // 9Ch

////////////////////////////////////////////////////////////////////////////

}

/*
A robot is located at the top-left corner of a m x n grid.

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid.

How many possible unique paths are there?

 Notice

m and n will be at most 100.

Have you met this question in a real interview? Yes
Example
Given m = 3 and n = 3, return 6.
Given m = 4 and n = 5, return 35.
 */