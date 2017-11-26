package DP_Classified._2Sequence;

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

//  Paint House II
public class _1PaintHouseII {
    // 9Ch
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


//-------------------------------------------------------------------------------

    //AC Java solution without extra space
    public int minCostII2(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;

        int n = costs.length, k = costs[0].length;
        // min1 is the index of the 1st-smallest cost till previous house
        // min2 is the index of the 2nd-smallest cost till previous house
        int min1 = -1, min2 = -1;

        for (int i = 0; i < n; i++) {
            int last1 = min1, last2 = min2;
            min1 = -1; min2 = -1;

            for (int j = 0; j < k; j++) {
                if (j != last1) {
                    // current color j is different to last min1
                    costs[i][j] += last1 < 0 ? 0 : costs[i - 1][last1];
                } else {
                    costs[i][j] += last2 < 0 ? 0 : costs[i - 1][last2];
                }

                // find the indices of 1st and 2nd smallest cost of painting current house i
                if (min1 < 0 || costs[i][j] < costs[i][min1]) {
                    min2 = min1; min1 = j;
                } else if (min2 < 0 || costs[i][j] < costs[i][min2]) {
                    min2 = j;
                }
            }
        }

        return costs[n - 1][min1];
    }

//-------------------------------------------------------------------------------

    //Fast DP Java solution Runtime O(nk) space O(1)
    //Explanation: dp[i][j] represents the min paint cost from house 0 to house i when house i use color j;
    // The formula will be dp[i][j] = Math.min(any k!= j| dp[i-1][k]) + costs[i][j].
    public int minCostII3(int[][] costs) {
        if(costs == null || costs.length == 0 || costs[0].length == 0) return 0;

        int n = costs.length, k = costs[0].length;
        if(k == 1) return (n==1? costs[0][0] : -1);

        int prevMin = 0, prevMinInd = -1, prevSecMin = 0;//prevSecMin always >= prevMin
        for(int i = 0; i<n; i++) {
            int min = Integer.MAX_VALUE, minInd = -1, secMin = Integer.MAX_VALUE;
            for(int j = 0; j<k;  j++) {
                int val = costs[i][j] + (j == prevMinInd? prevSecMin : prevMin);
                if(minInd< 0) {min = val; minInd = j;}//when min isn't initialized
                else if(val < min) {//when val < min,
                    secMin = min;
                    min = val;
                    minInd = j;
                } else if(val < secMin) { //when min<=val< secMin
                    secMin = val;
                }
            }
            prevMin = min;
            prevMinInd = minInd;
            prevSecMin = secMin;
        }
        return prevMin;
    }

//-------------------------------------------------------------------------------

    //Easiest O(1) space JAVA solution
    /*
    To solve this DP problem:

    If there's no constraint, we choose min cost for each house.
    Since house[i] and house[i - 1] cannot have the same color j, we should choose 2nd min color for house[i - 1].
    If we choose the 3rd min color for house[i - 1], we might miss potential min cost.
    min(i) = min(cost[i][j] + 1st min / 2nd min), 0 < j < n.
    Since current row only relies on last row for getting mins and avoiding same color, O(1) space is enough.
     */
    public int minCostII4(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        int min1 = 0, min2 = 0, index1 = -1;

        for (int i = 0; i < costs.length; i++) {
            int m1 = Integer.MAX_VALUE, m2 = Integer.MAX_VALUE, idx1 = -1;

            for (int j = 0; j < costs[0].length; j++) {
                int cost = costs[i][j] + (j != index1 ? min1 : min2);

                if (cost < m1) {           // cost < m1 < m2
                    m2 = m1; m1 = cost; idx1 = j;

                } else if (cost < m2) {    // m1 < cost < m2
                    m2 = cost;
                }
            }

            min1 = m1; min2 = m2; index1 = idx1;
        }
        return min1;
    }
//-------------------------------------------------------------------------------

    //Accepted Simple JAVA O(NK) solution

    public int minCostII5(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int m = costs.length, n = costs[0].length, m1 = 0, m2 = 0;
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            int t1 = m1, t2 = m2;
            m1 = Integer.MAX_VALUE;
            m2 = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                dp[j] = (dp[j] == t1 ? t2 : t1) + costs[i][j];
                if (m1 <= dp[j]) {
                    m2 = Math.min(dp[j], m2);
                }
                else {
                    m2 = m1;
                    m1 = dp[j];
                }
            }
        }

        return m1;
    }
//-------------------------------------------------------------------------------


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

/*
这里有n个房子在一列直线上，现在我们需要给房屋染色，共有k种颜色。每个房屋染不同的颜色费用也不同，
你需要设计一种染色方案使得相邻的房屋颜色不同，并且费用最小。

费用通过一个nxk 的矩阵给出，比如cost[0][0]表示房屋0染颜色0的费用，cost[1][2]表示房屋1染颜色2的费用。

 注意事项

所有费用都是正整数

您在真实的面试中是否遇到过这个题？ Yes
样例
costs = [[14,2,11],[11,14,5],[14,3,10]] return 10

房屋 0 颜色 1, 房屋 1 颜色 2, 房屋 2 颜色 1， 2 + 5 + 3 = 10


 */