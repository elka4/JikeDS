package DP.DP3;

//_7BestTimeToBuyAndSellStockIV   01:17:13
//• 有状态的序列型动态规划

/*
-----------------------------------------------------------------------------------------------
LintCode 393 Best Time To Buy and Sell Stock IV

• 题意:
• 给定一支股票N天的价格
• 可以进行最多K次买+K次卖，每次买卖都是一股
• 不能在卖光手中股票前买入，但可以同一天卖完后买入 • 问最大收益
• 例子:
• 输入: [4,4,6,1,1,4,2,5], K = 2
• 输出:6 (4买入，6卖出，1买入，5卖出，收益为(6-4) + (5-1) = 6)
-----------------------------------------------------------------------------------------------
题目分析
• 首先，如果K很大，K>N/2, 则题目可以化简成为Best Time to Buy and Sell Stock II，每天买入当且仅当价格比下一天低
• Best Time to Buy and Sell Stock III 相当于这题中K = 2
• 所以我们可以借鉴之前的解法

-----------------------------------------------------------------------------------------------
动态规划组成部分二:转移方程

• f[i][j]:前i天(第i-1天)结束后，处在阶段j,最大获利

阶段1, 3, 5, ...., 2K+1 --- 手中无股票状态:
f[i][j] = max{f[i-1][j], f[i-1][j-1] + Pi-1 – Pi-2}

f[i-1][j]:                 昨天没有持有股票
f[i-1][j-1] + Pi-1 – Pi-2: 昨天持有股票，今天卖出清仓



阶段2, 4, ...., 2K --- 手中有股票状态:
f[i][j] = max{f[i-1][j] + Pi-1 – Pi-2, f[i-1][j-1], f[i-1][j-2] + Pi-1 – Pi-2}

f[i-1][j] + Pi-1 – Pi-2:   昨天就持有股票，继续持有并获利
f[i-1][j-1]:               昨天没有持有股票，今天买入
f[i-1][j-2] + Pi-1 – Pi-2: 昨天持有上一次买的股票， 今天卖出并立即买入



-----------------------------------------------------------------------------------------------
动态规划组成部分三:初始条件和边界情况

• 刚开始(前0天)处于阶段1 – f[0][1] = 0
– f[0][2] = f[0][3] = ... = f[0][2K+1] = +∞
• 阶段1, 3, 5, .., 2K+1: f[i][j] = max{f[i-1][j], f[i-1][j-1] + Pi-1 – Pi-2}
• 阶段2, 4, .., 2K: f[i][j] = max{f[i-1][j] + Pi-1 – Pi-2, f[i-1][j-1], f[i-1][j-2] + Pi-1 – Pi-2}
• 如果j – 1 < 1或j – 2 < 1或i – 2 < 0, 对应项不计入max
• 因为最多买卖K次，所以答案是max{f[N][1], f[N][3], .., f[N][2K+1]}, 即 清仓状态下最后一天最大获利
-----------------------------------------------------------------------------------------------
动态规划组成部分四:计算顺序
• 初始化f[0][1], ..., f[0][2K+1] • f[1][1], ..., f[1][2K+1]
•...
• f[N][1], ..., f[N][2K+1]
• 时间复杂度:O(NK),空间复杂度:O(NK)，优化后可以O(K)，因为 f[i][1.. 2K+1]只依赖于f[i-1][1.. 2K+1]
-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

 */

//Best Time To Buy And Sell Stock IV
public class _7BestTimeToBuyAndSellStockIV {

//////////////////////////////////////////////////////////////////////////////
    // 动态规划专题班版本 Version 1
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

                if (j > 1) f[i][j] = update(f[i][j], f[i-1][j-1], 0);
            }
        }

        int res = Integer.MIN_VALUE;
        for (j = 1; j <= 2 * K + 1; j += 2) {
            res = update(res, f[n][j], 0);
        }

        return res;
    }



//////////////////////////////////////////////////////////////////////////////

    // version 2
    class Solution2 {
        /**
         * @param k: An integer
         * @param prices: Given an integer array
         * @return: Maximum profit
         */
        public int maxProfit(int k, int[] prices) {
            // write your code here
            if (k == 0) {
                return 0;
            }
            if (k >= prices.length / 2) {
                int profit = 0;
                for (int i = 1; i < prices.length; i++) {
                    if (prices[i] > prices[i - 1]) {
                        profit += prices[i] - prices[i - 1];
                    }
                }
                return profit;
            }
            int n = prices.length;

            // mustSell[i][j] 表示前i天，至多进行j次交易，第i天必须sell的最大获益
            int[][] mustsell = new int[n + 1][n + 1];

            // globalbest[i][j] 表示前i天，至多进行j次交易，第i天可以不sell的最大获益
            int[][] globalbest = new int[n + 1][n + 1];

            mustsell[0][0] = globalbest[0][0] = 0;
            for (int i = 1; i <= k; i++) {
                mustsell[0][i] = globalbest[0][i] = 0;
            }

            for (int i = 1; i < n; i++) {
                int gainorlose = prices[i] - prices[i - 1];
                mustsell[i][0] = 0;
                for (int j = 1; j <= k; j++) {
                    mustsell[i][j] = Math.max(globalbest[(i - 1)][j - 1] + gainorlose,
                            mustsell[(i - 1)][j] + gainorlose);
                    globalbest[i][j] = Math.max(globalbest[(i - 1)][j], mustsell[i ][j]);
                }
            }
            return globalbest[(n - 1)][k];
        }
    };

//////////////////////////////////////////////////////////////////////////////

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
//////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////
}
/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

 Notice

You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Have you met this question in a real interview? Yes
Example
Given prices = [4,4,6,1,1,4,2,5], and k = 2, return 6.
 */

/*
假设你有一个数组，它的第i个元素是一支给定的股票在第i天的价格。

设计一个算法来找到最大的利润。你最多可以完成 k 笔交易。

 注意事项

你不可以同时参与多笔交易(你必须在再次购买前出售掉之前的股票)

样例
给定价格 = [4,4,6,1,1,4,2,5], 且 k = 2, 返回 6.


 */