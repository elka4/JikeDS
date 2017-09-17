package DP.DP3;

public class _1PaintHouseII {
    public class Solution {
        /**
         * @param costs n x k cost matrix
         * @return an integer, the minimum cost to paint all houses
         */
        public int minCostII(int[][] costs) {
            int n = costs.length;
            if (n == 0) {
                return 0;
            }

            int K = costs[0].length;

            int[][] f = new int[2][K];
            int old, now = 0;
            int i, j, k;
            for (k = 0; k < K; ++k) {
                f[now][k] = 0;
            }

            for (i = 1; i <= n; ++i) {
                old = now;
                now = 1 - now;
                int min1 = Integer.MAX_VALUE;
                int min2 = Integer.MAX_VALUE;
                for (k = 0; k < K; ++k) {
                    if (f[old][k] < min1) {
                        min2 = min1;
                        min1 = f[old][k];
                    }
                    else {
                        if (f[old][k] < min2) {
                            min2 = f[old][k];
                        }
                    }
                }

                for (j = 0; j < K; ++j) {
                    if (min1 == f[old][j]) {
                        f[now][j] = min2 + costs[i-1][j];
                    }
                    else {
                        f[now][j] = min1 + costs[i-1][j];
                    }
                }
            }

            int res = f[now][0];
            for (k = 0; k < K; ++k) {
                if (f[now][k] < res) {
                    res = f[now][k];
                }
            }

            return res;
        }
    }
}
