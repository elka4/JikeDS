package DP_Classified._2Sequence;

//Paint House
public class _2PaintHouse {

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

////////////////////////////////////////////////////////////////////////////

}
/*
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

 Notice

All costs are positive integers.

Have you met this question in a real interview? Yes
Example
Given costs = [[14,2,11],[11,14,5],[14,3,10]] return 10

house 0 is blue, house 1 is green, house 2 is blue, 2 + 5 + 3 = 10


 */