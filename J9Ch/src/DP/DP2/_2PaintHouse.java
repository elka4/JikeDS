package DP.DP2;

//• 序列型动态规划

// 带状态

/*
f[i][0] = min{f[i-1][1] + cost[i-1][0], f[i-1][2] + cost[i-1][0]}

f[i][0]:                        油漆前i栋房子 并且 房子i - 1是红色的最小花费
min{f[i-1][1] + cost[i-1][0]:   油漆前i - 1 栋房子 并且 房子i-2是蓝色的最小花费，加上i-1油漆房子i-1的花费
f[i-1][2] + cost[i-1][0]:       油漆前i - 1 栋房子 并且 房子i-2是绿色的最小花费，加上i-1油漆房子i-1的花费

f[i][0] = min{f[i-1][1] + cost[i-1][0], f[i-1][2] + cost[i-1][0]}
f[i][1] = min{f[i-1][0] + cost[i-1][1], f[i-1][2] + cost[i-1][1]}
f[i][2] = min{f[i-1][0] + cost[i-1][2], f[i-1][1] + cost[i-1][2]}

•  初始条件    f[0][0] = f[0][1] = f[0][2] = 0

-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

 */


//  Paint House
public class _2PaintHouse {
    // 9Ch DP

    public int minCost4(int[][] costs) {
        int n = costs.length;
        if (n == 0) {
            return 0;
        }

        // n + 1 f[0], .... f[n]
        int[][] f = new int[n + 1][3];
        int i, j, k, res;
        //init
        f[0][0] = f[0][1] = f[0][2] = 0;

        // 从没有房子到第n个房子
        for (i = 0; i <= n; ++i) {
            // j is the color of house i - 1， 也就是前1个房子
            for (j = 0; j < 3; ++j) {
                f[i][j] = Integer.MAX_VALUE;
                // k is the color of house i - 2， 也就是前2个房子
                for (k = 0; k < 3; ++k) {
                    // cannot be the same color
                    if(j == k){
                        continue;
                    }
                    // f[i - 1][k]    前i - 1栋房子并且i - 1染成k的颜色的最小花费，

                    // costs[i - 1][j] 第i栋房子染成j这个颜色的花费
                    if (f[i - 1][k] + costs[i - 1][j] < f[i][j]){
                        f[i][j] = f[i - 1][k] + costs[i - 1][j];
                    }
                    // f[i][j] = Math.max(f[i][j], f[i - 1][k] + costs[i - 1][j]);
                }
            }
        }
/*        res = f[n][0];
        if (f[n][1] < res) {
            res = f[n][1];
        }
        if (f[n][2] < res) {
            res = f[n][2];
        }
        return res;*/
        return Math.min(Math.min(f[n][0],f[n][1]),f[n][2]);


    }

////////////////////////////////////////////////////////////////////////////
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
    // mine on lint
    public int minCostX(int[][] costs) {
        // write your code here
        int n = costs.length;

        int[][] f = new int[2][3];

        int now = 0;
        int old = 0;
        f[now][0] = f[now][1] = f[now][2] = 0;

        for (int i = 1; i <= n; i++) {
            old = now;
            now = 1 - now;
            for (int j = 0; j < 3; j++) {
                f[now][j] = Integer.MAX_VALUE;
                for (int k = 0; k < 3; k++) {
                    if (j != k) {
                        f[now][j] = Math.min(f[now][j], f[old][k] + costs[i - 1][j]);
                    }
                }
            }
        }
        return Math.min(f[now][0], Math.min(f[now][1], f[now][2 ]));

    }
////////////////////////////////////////////////////////////////////////////

    public int minCost2(int[][] costs) {
        if(costs==null||costs.length==0){
            return 0;
        }
        for(int i=1; i<costs.length; i++){
            costs[i][0] += Math.min(costs[i-1][1],costs[i-1][2]);
            costs[i][1] += Math.min(costs[i-1][0],costs[i-1][2]);
            costs[i][2] += Math.min(costs[i-1][1],costs[i-1][0]);
        }
        int n = costs.length-1;
        return Math.min(Math.min(costs[n][0], costs[n][1]), costs[n][2]);
    }

////////////////////////////////////////////////////////////////////////////

    public int minCost3(int[][] costs) {
        if(costs.length==0) return 0;
        int lastR = costs[0][0];
        int lastG = costs[0][1];
        int lastB = costs[0][2];
        for(int i=1; i<costs.length; i++){
            int curR = Math.min(lastG,lastB)+costs[i][0];
            int curG = Math.min(lastR,lastB)+costs[i][1];
            int curB = Math.min(lastR,lastG)+costs[i][2];
            lastR = curR;
            lastG = curG;
            lastB = curB;
        }
        return Math.min(Math.min(lastR,lastG),lastB);
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

/*
这里有n个房子在一列直线上，现在我们需要给房屋染色，分别有红色蓝色和绿色。
每个房屋染不同的颜色费用也不同，你需要设计一种染色方案使得相邻的房屋颜色不同，并且费用最小。

费用通过一个nx3 的矩阵给出，比如cost[0][0]表示房屋0染红色的费用，cost[1][2]表示房屋1染绿色的费用。

 注意事项

所有费用都是正整数

您在真实的面试中是否遇到过这个题？ Yes
样例
costs = [[14,2,11],[11,14,5],[14,3,10]] return 10

房屋 0 蓝色, 房屋 1 绿色, 房屋 2 蓝色， 2 + 5 + 3 = 10
 */