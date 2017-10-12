package DP.DP3;

//_6BestTimeToBuyAndSellStockIII  31:38
//• 有状态的序列型动态规划

// 2次交易

/*

-----------------------------------------------------------------------------------------------
LintCode 151: Best Time to Buy and Sell Stock III
• 题意:
• 给定一支股票N天的价格
• 可以进行最多两次买+两次卖，每次买卖都是一股
• 不能在卖光手中股票前买入，但可以同一天卖完后买入 • 问最大收益
• 例子:
• 输入:[4,4,6,1,1,4,2,5]
• 输出:6 (4买入，6卖出，1买入，5卖出，收益为(6-4) + (5-1) = 6)

-----------------------------------------------------------------------------------------------
题目分析

• 题目大意和I, II基本相似
• 只能最多两次买卖
• 所以需要记录已经买卖了多少次
-----------------------------------------------------------------------------------------------
动态规划组成部分一:确定状态

• 最后一步:最优策略中，最后一次卖发生在第j天
• 枚举最后一次买发生在第几天
• 但是不知道之前有没有买卖过
-----------------------------------------------------------------------------------------------
记录阶段

• 阶段可以保持:即不进行买卖操作
• 阶段可以变化:买或卖
    – 在阶段2，卖了一股后，进入阶段3
    – 在阶段2，卖了一股后当天买一股，进入阶段4
-----------------------------------------------------------------------------------------------
动态规划组成部分一:确定状态

• 最优策略一定是前N天(第N-1天)结束后，处于 – 阶段1:没买卖过
– 阶段3:买卖过一次
– 阶段5:买卖过两次

• 例如，如果要求前N天(第N-1天)结束后，在阶段5的最大获利，设为f[N][5]
– 情况1:第N-2天就在阶段5 --- f[N-1][5]
– 情况2:第N-2天还在阶段4(第二次持有股票)，第N-1天卖掉
  • f[N-1][4] + (PN-1-PN-2)
思考:为什么是f[N-1][4]+(PN-1-PN-2)

• 例如，如果要求前N天(第N-1天)结束后，在阶段4的最大获利，设为f[N][4]
– 情况1:第N-2天就在阶段4 --- f[N-1][4] + (PN-1-PN-2)
– 情况2:第N-2天还在阶段3 --- f[N-1][3]
– 情况3:第N-2天还在阶段2，第N-1天卖完了立即买 --- f[N-1][2] + (PN-1-PN-2)
-----------------------------------------------------------------------------------------------
子问题
• 要求f[N][1], ..., f[N][5]
• 需要知道f[N-1][1], ..., f[N-1][5]
• 子问题
• 状态:f[i][j]表示前i天(第i-1天)结束后，在阶段j的最大获利
-----------------------------------------------------------------------------------------------
动态规划组成部分二:转移方程

• f[i][j]:前i天(第i-1天)结束后，处在阶段j,最大获利

阶段1, 3, 5 --- 手中无股票状态:
f[i][j] = max{f[i-1][j], f[i-1][j-1] + Pi-1 – Pi-2}

f[i-1][j]:                 昨天没有持有股票
f[i-1][j-1] + Pi-1 – Pi-2: 昨天持有股票，今天卖出清仓



阶段2, 4 --- 手中有股票状态:
f[i][j] = max{f[i-1][j] + Pi-1 – Pi-2, f[i-1][j-1], f[i-1][j-2] + Pi-1 – Pi-2}

f[i-1][j] + Pi-1 – Pi-2:   昨天就持有股票，继续持有并获利
f[i-1][j-1]:               昨天没有持有股票，今天买入
f[i-1][j-2] + Pi-1 – Pi-2: 昨天持有上一次买的股票， 今天卖出并立即买入




-----------------------------------------------------------------------------------------------
动态规划组成部分三:初始条件和边界情况

• 刚开始(前0天)处于阶段1 – f[0][1] = 0
– f[0][2] = f[0][3] = f[0][4] = f[0][5] = -∞
• 阶段1, 3, 5: f[i][j] = max{f[i-1][j], f[i-1][j-1] + Pi-1 – Pi-2}
• 阶段2, 4: f[i][j] = max{f[i-1][j] + Pi-1 – Pi-2, f[i-1][j-1], f[i-1][j-2] + Pi-1 – Pi-2}
• 如果j – 1 < 1或j – 2 < 1或i – 2 < 0, 对应项不计入max
• 因为最多买卖两次，所以答案是max{f[N][1], f[N][3], f[N][5]}, 即清仓状态 下最后一天最大获利
-----------------------------------------------------------------------------------------------
动态规划组成部分四:计算顺序
• 初始化f[0][1], ..., f[0][5] • f[1][1], ..., f[1][5]
•...
• f[N][1], ..., f[N][5]
• 时间复杂度:O(N),空间复杂度:O(N)，优化后可以O(1)，因为f[i][1..5] 只依赖于f[i-1][1..5]

-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

 */


import org.junit.Test;

//Best Time To Buy And Sell Stock III
public class _6BestTimeToBuyAndSellStockIII {
    // 9Ch DP
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }

        int[][] f = new int[n + 1][5 + 1];
        int i, j, k;
        //init
        //first 0 days, phase 1, max profit = 0
        f[0][1] = 0;
        f[0][2] = f[0][3] = f[0][4] = f[0][5] = Integer.MIN_VALUE;

        for (i = 1; i <= n; i++) {
            // 1, 3, 5
            for (j = 1; j <= 5 ; j += 2) {
                f[i][j] = f[i - 1][j];
                // 判断j i，为了之后f[i - 1][j - 1] index 不越界
                if (j > 1 && i > 1 && f[i - 1][j - 1] != Integer.MIN_VALUE) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + prices[i - 1] - prices[i - 2]);
                }
            }

            // 2, 4
            for (j = 2; j <= 5; j += 2) {
                //max
                f[i][j] = f[i - 1][j - 1];
                if (i > 1 && f[i - 1][j] != Integer.MIN_VALUE) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j] + prices[i - 1] - prices[i - 2]);
                }

                if (j > 2 && i > 1 && f[i - 1][j - 2] != Integer.MIN_VALUE) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 2] + prices[i - 1] - prices[i - 2]);
                }
            }
        }
        return Math.max(Math.max(f[n][1], f[n][3]), f[n][5]);

    }

////////////////////////////////////////////////////////////////////////////////////////////////
    // 动态规划专题班版本 verison 1
    private int update(int a, int b, int delta) {
        if (b == Integer.MIN_VALUE) {
            return a;
        }

        if (b + delta > a) {
            return b + delta;
        }

        return a;
    }
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit1(int[] prices) {
        int K = 2;
        int n = prices.length;
        int i, j, k;

        int[][] f = new int[n+1][2*K+1+1];
        for (i = 0; i <= n; ++i) {
            for (j = 1; j <= 2*K+1; ++j) {
                f[i][j] = Integer.MIN_VALUE;
            }
        }

        f[0][1] = 0;
        for (i = 1; i <= n; ++i) {
            for (j = 1; j <= 2 * K + 1; j += 2) {
                f[i][j] = update(f[i][j], f[i-1][j], 0);
                if (j > 1 && i > 1) f[i][j] =
                        update(f[i][j], f[i - 1][j - 1], prices[i - 1] - prices[i - 2]);
            }

            for (j = 2; j <= 2 * K; j += 2) {
                if (i > 1) f[i][j] =
                        update(f[i][j], f[i-1][j], prices[i - 1] - prices[i - 2]);
                if (j > 1) f[i][j] =
                        update(f[i][j], f[i-1][j-1], 0);
            }
        }

        int res = Integer.MIN_VALUE;
        for (j = 1; j <= 2 * K + 1; j += 2) {
            res = update(res, f[n][j], 0);
        }

        return res;
    }

/////////////////////////////////////////////////////////////////////


    // version 2
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int[] left = new int[prices.length];
        int[] right = new int[prices.length];

        // DP from left to right;
        left[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(prices[i], min);
            System.out.println("min: " + min);
            left[i] = Math.max(left[i - 1], prices[i] - min);
            System.out.println("left[i]: " + left[i]);

        }

        //DP from right to left;
        right[prices.length - 1] = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(prices[i], max);
            System.out.println("max: " + max);
            right[i] = Math.max(right[i + 1], max - prices[i]);
            System.out.println("right[i]: " + right[i]);

        }

        int profit = 0;
        for (int i = 0; i < prices.length; i++){
            profit = Math.max(left[i] + right[i], profit);
        }

        return profit;
    }
/*
min: 4
left[i]: 0
min: 4
left[i]: 2
min: 1
left[i]: 2
min: 1
left[i]: 2
min: 1
left[i]: 3
min: 1
left[i]: 3
min: 1
left[i]: 4

max: 5
right[i]: 3
max: 5
right[i]: 3
max: 5
right[i]: 4
max: 5
right[i]: 4
max: 6
right[i]: 4
max: 6
right[i]: 4
max: 6
right[i]: 4
 */
    @Test
    public void test03() {
        //给出一个样例数组 [4,4,6,1,1,4,2,5], 返回 6
        int[] prices = {4,4,6,1,1,4,2,5};
        System.out.println(maxProfit2(prices));
    }

/////////////////////////////////////////////////////////////////////

    // 方法二
    class Solution3 {

        private int update(int a, int b, int delta) {
            if (b == Integer.MIN_VALUE) {
                return a;
            }

            if (b + delta > a) {
                return b + delta;
            }

            return a;
        }
        /**
         * @param K: An integer
         * @param prices: Given an integer array
         * @return: Maximum profit
         */
        public int maxProfit(int K, int[] prices) {
            int n = prices.length;
            int i, j, k;
            if (K == 0) {
                return 0;
            }

            if (K >= n - 1) {
                j = 0;
                for (i = 1; i < n; ++i) {
                    if (prices[i] > prices[i - 1]) {
                        j += prices[i] - prices[i - 1];
                    }
                }

                return j;
            }

            int[][] f = new int[n+1][2*K+1];
            for (i = 0; i <= n; ++i) {
                for (j = 0; j <= 2*K; ++j) {
                    f[i][j] = Integer.MIN_VALUE;
                }
            }

            f[0][0] = 0;
            for (i = 1; i <= n; ++i) {
                int delta;
                if (i == 1) {
                    delta = 0;
                }
                else {
                    delta = prices[i-1] - prices[i - 2];
                }

                f[i][0] = update(f[i][0], f[i-1][0], 0);
                for (j = 1; j <= 2 * K; j += 2) {
                    if (i > 1) f[i][j] = update(f[i][j], f[i-1][j], delta);
                    if (i > 1) f[i][j] = update(f[i][j], f[i-1][j-1], delta);
                }

                for (j = 2; j <= 2 * K; j += 2) {
                    f[i][j] = update(f[i][j], f[i-1][j], 0);
                    if (i > 1) f[i][j] = update(f[i][j], f[i-1][j-1], delta);
                    if (i > 1) f[i][j] = update(f[i][j], f[i-1][j-2], delta);
                }
            }

            int res = Integer.MIN_VALUE;
            for (j = 2; j <= 2 * K; j += 2) {
                res = update(res, f[n][j], 0);
            }

            return res;
        }
    }

/////////////////////////////////////////////////////////////////////

}
/*
假设你有一个数组，它的第i个元素是一支给定的股票在第i天的价格。设计一个算法来找到最大的利润。你最多可以完成两笔交易。

 注意事项

你不可以同时参与多笔交易(你必须在再次购买前出售掉之前的股票)

您在真实的面试中是否遇到过这个题？ Yes
样例
给出一个样例数组 [4,4,6,1,1,4,2,5], 返回 6
 */