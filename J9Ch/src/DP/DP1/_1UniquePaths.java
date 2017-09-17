package DP.DP1;

public class _1UniquePaths {
    public class Solution {
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
    }

    // 方法二
    public class Solution2 {
        /**
         * @param n, m: positive integer (1 <= n ,m <= 100)
         * @return an integer
         */
        public int uniquePaths(int m, int n) {
            int[][] f = new int[m][n];
            int i, j;
            for (i = 0; i < m; ++i) {
                for (j = 0; j < n; ++j) {
                    if (i == 0 || j == 0) {
                        f[i][j] = 1;
                    }
                    else {
                        f[i][j] = f[i-1][j] + f[i][j-1];
                    }
                }
            }

            return f[m-1][n-1];
        }
    }
}
