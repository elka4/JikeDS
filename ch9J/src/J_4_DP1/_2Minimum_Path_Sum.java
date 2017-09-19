package chapter4_DP1;

public class _2Minimum_Path_Sum {
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

    // bottom to top, by me
    public int minPathSum2(int[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0 ||
                grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] f = new int[m][n];

        f[m - 1][n - 1] = grid[m - 1][n - 1];

        for(int i = n - 1; i >= 1; i--){
            f[m - 1][i - 1] = f[m - 1][i] + grid[m - 1][i - 1];
        }

        for(int j = m - 1; j >= 1; j--){
            f[j - 1][n - 1] = f[j][n - 1] + grid[j - 1][n - 1];
        }


        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                f[i][j] = Math.min(f[i +1][j], f[i][j + 1]) + grid[i][j];
            }
        }
        return f[0][0];


    }
}

/*
 * Given a m x n grid filled with non-negative numbers, 
 * find a path from top left to bottom right which minimizes 
 * the sum of all numbers along its path.

 Notice: You can only move either down or right at any point in time.

Tags: Dynamic Programming
Related Problems: Easy Triangle 25 %, Medium Binary Tree Maximum Path Sum
 */