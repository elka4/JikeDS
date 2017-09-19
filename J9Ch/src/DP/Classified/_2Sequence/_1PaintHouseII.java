package DP.Classified._2Sequence;

//Paint HouseII
public class _1PaintHouseII {
    //jiuzhang
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


/////////////////////////////////////////////////////////////


}
/*
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

 Notice

All costs are positive integers.

Have you met this question in a real interview? Yes
Example
Given n = 3, k = 3, costs = [[14,2,11],[11,14,5],[14,3,10]] return 10

house 0 is color 2, house 1 is color 3, house 2 is color 2, 2 + 5 + 3 = 10
 */
