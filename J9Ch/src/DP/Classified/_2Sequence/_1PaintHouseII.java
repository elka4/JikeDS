package DP.Classified._2Sequence;

/*
f[i][0] = min{f[i-1][1] + cost[i-1][0], f[i-1][2] + cost[i-1][0]}
f[i][0] :油漆前i栋房子并且房子i- 1是红色的最小花费
f[i-1][1] + cost[i-1][0]: 油漆前i-1栋房子并且房子 i-2是蓝色的最小花费，加 上油漆房子i-1的花费
f[i-1][2] + cost[i-1][0]: 油漆前i-1栋房子并且房子i- 2是绿色的最小花费，加上 油漆房子i-1的花费
 */

/*
设油漆前i栋房子并且房子i-1是红色、蓝色、绿色的最小花费分别为f[i][0],f[i][1], f[i][2]

f[i][0] = min{f[i-1][1] + cost[i-1][0], f[i-1][2] + cost[i-1][0]}
f[i][1] = min{f[i-1][0] + cost[i-1][1], f[i-1][2] + cost[i-1][1]}
f[i][2] = min{f[i-1][0] + cost[i-1][2], f[i-1][1] + cost[i-1][2]}
 */

/*
• 序列型动态规划:...前i个...最小/方式数/可行性
• 在设计动态规划的过程中，发现需要知道油漆前N-1栋房子的最优策略 中，房子N-2的颜色
• 如果只用f[N-1], 将无法区分
• 解决方法:记录下房子N-2的颜色
– 在房子N-2是红/蓝/绿色的情况下，油漆前N-1栋房子的最小花费
• 问题迎刃而解
• 序列+状态
 */

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
