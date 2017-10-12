package DP.DP3;

//_1PaintHouseII  01:39

/*
-----------------------------------------------------------------------------------------------
LintCode 516 Paint House II

• 题意:
• 有一排N栋房子，每栋房子要漆成K种颜色中的一种
• 任何两栋相邻的房子不能漆成同样的颜色
• 房子i染成第j种颜色的花费是cost[i][j]
• 问最少需要花多少钱油漆这些房子
• 例子:
• 输入:
    – N=3, K=3
    – Cost = [[14,2,11],[11,14,5],[14,3,10]]
• 输出:
    –10(房子0蓝色，房子1绿色，房子2蓝色, 2+5+3=10)
---------------------------------------|--------------------------------------------------------
题目分析

• 这题和Paint House非常类似，只是颜色种类变成K种
• 动态规划思路和Paint House一样，需要记录油漆前i栋房子并且房子i-1 是颜色1, 颜色2, ..., 颜色K的最小花费:f[i][1], f[i][2], ..., f[i][K]
-----------------------------------------------------------------------------------------------
 动态规划组成部分二:转移方程

• 设油漆前i栋房子并且房子i-1是颜色1,颜色2,...颜色K的最小花费分别为 f[i][1], f[i][2], ..., f[i][K]
f[i][1] = min{f[i-1][2] + cost[i-1][1], f[i-1][3] + cost[i-1][1], ..., f[i-1][K] + cost[i-1][1]}
f[i][2] = min{f[i-1][1] + cost[i-1][2], f[i-1][3] + cost[i-1][2], ..., f[i-1][K] + cost[i-1][2]}
...

f[i][K] = min{f[i-1][1] + cost[i-1][K], f[i-1][2] + cost[i-1][K], ..., f[i-1][K-1] + cost[i-1][K]}
-----------------------------------------------------------------------------------------------
动态规划组成部分二:转移方程

• 设油漆前i栋房子并且房子i-1是颜色1,颜色2,...颜色K的最小花费分别为 f[i][1], f[i][2], ..., f[i][K]

f[i][j] = min k≠j {f[i-1][k]} + cost[i-1][j]

f[i][j]:             油漆前i栋房子并且房子i- 1是颜色j的最小花费
min k≠j {f[i-1][k]}: 油漆前i-1栋房子并且房子i-2 不是颜色j的最小花费
cost[i-1][j]:        用颜色j的油漆房子i-1的花 费

• f[i][j] = mink≠j {f[i-1][k]} + cost[i-1][j]
• 直接计算的时间复杂度(计算步数):
    – i从0到N
    – j从1到K
    – k从1到K
    – O(NK2)


-----------------------------------------------------------------------------------------------
动态规划常见优化:

• f[i][j] = mink≠j {f[i-1][k]} + cost[i-1][j]
• 每次需要求f[i-1][1], ..., f[i-1][K]中除了一个元素之外其他元素的最小值

如果最小值是第i个元素，次小值是第j个元素
1. 只要除掉的元素不是第i个，剩下的最小值就是第i个元素
2. 如果除掉的元素是第i个，剩下的最小值就是第j个元素

• 记录下f[i-1][1], ..., f[i-1][K]中的最小值和次小值分别是哪个
• 假如最小值是f[i-1][a],次小值是f[i-1][b]
• 则对于j=1,2,3,...,a-1, a+1,...,K, f[i][j] = f[i-1][a] + cost[i-1][j]
• f[i][a] = f[i-1][b] + cost[i-1][a]

• 时间复杂度降为O(NK)



-----------------------------------------------------------------------------------------------
动态规划常见优化

• f[i][j] = mink≠j {f[i-1][k]} + cost[i-1][j]
• 记录下f[i-1][1], ..., f[i-1][K]中的最小值和次小值分别是哪个
• 假如最小值是f[i-1][a],次小值是f[i-1][b]
• 则对于j=1,2,3,...,a-1, a+1,...,K, f[i][j] = f[i-1][a] + cost[i-1][j]
• f[i][a] = f[i-1][b] + cost[i-1][a]
• 时间复杂度降为O(NK)
-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

 */

import org.junit.Test;

/*
Given n = 3, k = 3, costs = [[14,2,11],[11,14,5],[14,3,10]] return 10

house 0 is color 2, house 1 is color 3, house 2 is color 2, 2 + 5 + 3 = 10
 */

//Paint HouseII
public class _1PaintHouseII {

    // 9Ch DP
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }

        int n = costs.length;
        int K = costs[0].length;
        int[][] f = new int[n + 1][K];
        int min1, min2;
        int j1 = 0, j2 = 0; //最小值和次小值对应的坐标
        int i, j, k;

        // init
        for (j = 0; j < K; ++j) {
            f[0][j] = 0;
        }

        for (i = 1; i <= n; ++i) {
            // calculate min1, min2
            min1 = min2 = Integer.MAX_VALUE;
            // min1 = f[i - 1][j1]
            // 这个for是为了找到最小值和次小值的坐标j1, j2
            for (j = 0; j < K; ++j) {
                // 先和最小值比较
                if (f[i - 1][j] < min1) {
                    min2 = min1;
                    j2 = j1;

                    min1 = f[i - 1][j]; //当前值给最小值
                    j1 = j;
                } else {
                    if (f[i - 1][j] < min2) {
                        min2 = f[i - 1][j];
                        j2 = j;
                    }
                }
            }

            for (j = 0; j < K; ++j) {
                if (j != j1) {
                    f[i][j] = f[i - 1][j1] + costs[i - 1][j];
                } else {
                    // j == j1
                    f[i][j] = f[i - 1][j2] + costs[i - 1][j];
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (j = 0; j < K; ++j) {
            res = Math.min(res, f[n][j]);
        }
        return  res;
    }

    @Test
    public void test01(){
        int[][] costs = {{14,2,11}, {11,14,5}, {14,3,10}};
        System.out.println(minCostII(costs));
    }

/////////////////////////////////////////////////////////////

    //jiuzhang
    /**
     * @param costs n x k cost matrix
     * @return an integer, the minimum cost to paint all houses
     */
    public int minCostII1(int[][] costs) {
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

    @Test
    public void test02(){
        int[][] costs = {{14,2,11}, {11,14,5}, {14,3,10}};
        System.out.println(minCostII1(costs));
    }
/////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////
}
/*
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

 Notice

costsll costs are positive integers.

Have you met this question in a real interview? Yes
Example
Given n = 3, k = 3, costs = [[14,2,11],[11,14,5],[14,3,10]] return 10

house 0 is color 2, house 1 is color 3, house 2 is color 2, 2 + 5 + 3 = 10
 */

/*
这里有n个房子在一列直线上，现在我们需要给房屋染色，共有k种颜色。
每个房屋染不同的颜色费用也不同，你需要设计一种染色方案使得相邻的房屋颜色不同，并且费用最小。

费用通过一个nxk 的矩阵给出，比如cost[0][0]表示房屋0染颜色0的费用，cost[1][2]表示房屋1染颜色2的费用。

 注意事项

所有费用都是正整数

您在真实的面试中是否遇到过这个题？ Yes
样例
costs = [[14,2,11],[11,14,5],[14,3,10]] return 10

房屋 0 颜色 1, 房屋 1 颜色 2, 房屋 2 颜色 1， 2 + 5 + 3 = 10
 */