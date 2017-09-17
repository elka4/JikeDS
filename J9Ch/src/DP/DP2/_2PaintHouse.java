package DP.DP2;

public class _2PaintHouse {
    public class Solution {
        /**
         * @param costs n x 3 cost matrix
         * @return an integer, the minimum cost to paint all houses
         */
        public int minCost(int[][] costs) {
            int n = costs.length;
            if (n == 0) {
                return 0;
            }
            int[][] f = new int[2][3];
            int old, now = 0;
            f[now][0] = f[now][1] = f[now][2] = 0;

            int i, j, k;
            for (i = 1; i <= n; ++i) {
                old = now;
                now = 1 - now;
                for (j = 0; j < 3; ++j) {
                    f[now][j] = Integer.MAX_VALUE;
                    for (k = 0; k < 3; ++k) {
                        if (k != j && f[old][k] + costs[i-1][j] < f[now][j]) {
                            f[now][j] = f[old][k] + costs[i-1][j];
                        }
                    }
                }
            }

            int res = f[now][0];
            if (f[now][1] < res) {
                res = f[now][1];
            }

            if (f[now][2] < res) {
                res = f[now][2];
            }

            return res;
        }
    }
}
