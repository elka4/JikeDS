package July._1DP;
//• 坐标型动态规划

import org.junit.Test;

// Minimum Path Sum
public class _1MinimumPathSum_64 {
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

    //https://leetcode.com/articles/minimum-path-sum/#approach-1-brute-force-time-limit-exceeded

    //Approach #1 Brute Force [Time Limit Exceeded]
    public class Solution1 {
        public int calculate(int[][] grid, int i, int j) {
            if (i == grid.length || j == grid[0].length) return Integer.MAX_VALUE;
            if (i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];
            return grid[i][j] + Math.min(calculate(grid, i + 1, j), calculate(grid, i, j + 1));
        }
        public int minPathSum(int[][] grid) {
            return calculate(grid, 0, 0);
        }
    }

    @Test
    public void test11(){
        int[][] grid = {{1,2,3},
                        {4,8,2},
                        {1,5,3}};
        Solution1 sol1 = new Solution1();
        System.out.println(sol1.minPathSum(grid));

    }
/////////////////////////////////////////////////////////////////////////////

    //Approach #2 Dynamic Programming 2D [Accepted]
    public class Solution2 {
        public int minPathSum(int[][] grid) {
            int[][] dp = new int[grid.length][grid[0].length];

            for (int i = grid.length - 1; i >= 0; i--) {
                for (int j = grid[0].length - 1; j >= 0; j--) {
                    if(i == grid.length - 1 && j != grid[0].length - 1)
                        dp[i][j] = grid[i][j] +  dp[i][j + 1];

                    else if(j == grid[0].length - 1 && i != grid.length - 1)
                        dp[i][j] = grid[i][j] + dp[i + 1][j];

                    else if(j != grid[0].length - 1 && i != grid.length - 1)
                        dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);

                    else
                        dp[i][j] = grid[i][j];
                }
            }
            return dp[0][0];
        }
    }
/////////////////////////////////////////////////////////////////////////////

    //Approach #3 Dynamic Programming 1D [Accepted]

    public class Solution3 {
        public int minPathSum(int[][] grid) {
            int[] dp = new int[grid[0].length];

            for (int i = grid.length - 1; i >= 0; i--) {
                for (int j = grid[0].length - 1; j >= 0; j--) {
                    if(i == grid.length - 1 && j != grid[0].length - 1)
                        dp[j] = grid[i][j] +  dp[j + 1];

                    else if(j == grid[0].length - 1 && i != grid.length - 1)
                        dp[j] = grid[i][j] + dp[j];

                    else if(j != grid[0].length - 1 && i != grid.length - 1)
                        dp[j] = grid[i][j] + Math.min(dp[j], dp[j + 1]);

                    else
                        dp[j] = grid[i][j];
                }
            }
            return dp[0];
        }
    }

/////////////////////////////////////////////////////////////////////////////

    //Approach #4 Dynamic Programming (Without Extra Space) [Accepted]

    public class Solution4 {
        public int minPathSum(int[][] grid) {
            for (int i = grid.length - 1; i >= 0; i--) {
                for (int j = grid[0].length - 1; j >= 0; j--) {
                    if(i == grid.length - 1 && j != grid[0].length - 1)
                        grid[i][j] = grid[i][j] +  grid[i][j + 1];

                    else if(j == grid[0].length - 1 && i != grid.length - 1)
                        grid[i][j] = grid[i][j] + grid[i + 1][j];

                    else if(j != grid[0].length - 1 && i != grid.length - 1)
                        grid[i][j] = grid[i][j] + Math.min(grid[i + 1][j],grid[i][j + 1]);
                }
            }
            return grid[0][0];
        }
    }
/////////////////////////////////////////////////////////////////////////////



}
/*
Minimum Path Sum


Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

 Notice

You can only move either down or right at any point in time.
 */