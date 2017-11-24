package DP.DP3;
import org.junit.Test;

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


/*
Given n = 3, k = 3, costs = [[14,2,11],[11,14,5],[14,3,10]] return 10

house 0 is color 2, house 1 is color 3, house 2 is color 2, 2 + 5 + 3 = 10
 */

//  265. Paint House II
//  https://leetcode.com/problems/paint-house-ii/description/
public class _1PaintHouse_II {
/*    AC Java solution without extra space

94
    jeantimex
    Reputation:  4,407
    The idea is similar to the problem Paint House I, for each house and each color, the minimum cost of painting the house with that color should be the minimum cost of painting previous houses, and make sure the previous house doesn't paint with the same color.

    We can use min1 and min2 to track the indices of the 1st and 2nd smallest cost till previous house, if the current color's index is same as min1, then we have to go with min2, otherwise we can safely go with min1.

    The code below modifies the value of costs[][] so we don't need extra space.*/

    //这个比下面九章的好看多了，把计算结果和update min1，min2分开来做
    //last1, last2, min1, min2都是index
    //min1，min2是用来update last1，last2的。和old，now的用法很类似。
    //
    public int minCostII(int[][] costs) {
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
                    costs[i][j] += last1 == -1 ? 0 : costs[i - 1][last1];//last1 == -1 应该是初始化
                } else {
                    costs[i][j] += last2 == -1 ? 0 : costs[i - 1][last2];
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


    public int minCostII_Original(int[][] costs) {
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
//------------------------------------------------------------------------------//
    // 9Ch DP
    public int minCostII2(int[][] costs) {
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

//---------------------------------////////////////////////

    // 9Ch
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
//---------------------------------////////////////////////
    // 9CH DP

    //------------------------------------------------------------------------------//////
    public int minCostII3(int[][] costs) {
        //Method DP: Using an int[k] last representing the last min costs in k tracks
        //Using an int[k] cur representing the current min costs

        //TimeO(kn) Space O(k)
        //use heap, nlogk
        if(costs == null || costs.length == 0)
            return 0;
        if(costs[0] == null || costs[0].length == 0)
            return 0;
        int col = costs[0].length;
        int[] last = new int[col];
        int[] cur = new int[col];
        for(int[] cost : costs) {
            for(int i = 0; i < col; i++) {
                cur[i] = cost[i] + findMin(last, i);
            }
            int[] tmp = cur;
            cur = last;
            last = tmp;
        }
        return findMin(last, last.length);
    }

    private int findMin(int[] arr, int except) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length; i++) {
            if(i != except) {
                min = Math.min(min, arr[i]);
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

//------------------------------------------------------------------------------//////

    //time O(kn), space O(1)
    public int minCostII22(int[][] costs) {
        if(costs == null || costs.length == 0) return 0;
        int m = costs.length, n = costs[0].length;
        int lastMin = 0;
        int lastSec = 0;
        int lastIndex = -1;
        for (int[] cost : costs) {
            int curMin = Integer.MAX_VALUE;
            int curSec = m;
            int curIndex = -1;
            for(int j = 0; j < n; j++) {
                int val = cost[j] + (j == lastIndex ? lastSec : lastMin);
                if(val < curMin) {
                    curSec = curMin;
                    curMin = val;
                    curIndex = j;
                } else if(val < curSec) {
                    curSec = val;
                }
            }
            lastMin = curMin;
            lastSec = curSec;
            lastIndex = curIndex;
        }
        return lastMin;
    }
//------------------------------------------------------------------------------//////
//---------------------------------////////////////////////
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