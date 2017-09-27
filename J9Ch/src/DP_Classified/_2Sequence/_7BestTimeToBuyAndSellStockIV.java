package DP_Classified._2Sequence;

//DP

//k 笔交易


//Best Time To Buy And Sell Stock IV
public class _7BestTimeToBuyAndSellStockIV {

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
                if (j > 1 && i > 1) f[i][j] = update(f[i][j], f[i - 1][j - 1],
                        prices[i - 1] - prices[i - 2]);
            }

            for (j = 2; j <= 2 * K; j += 2) {
                if (i > 1) f[i][j] = update(f[i][j], f[i-1][j],
                        prices[i - 1] - prices[i - 2]);
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
    /**
     * @param k: An integer
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit2(int k, int[] prices) {
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



//////////////////////////////////////////////////////////////////////////////

    // 方法二
    private int update3(int a, int b, int delta) {
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
    public int maxProfit3(int K, int[] prices) {
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

            f[i][0] = update3(f[i][0], f[i-1][0], 0);
            for (j = 1; j <= 2 * K; j += 2) {
                if (i > 1) f[i][j] = update3(f[i][j], f[i-1][j], delta);
                if (i > 1) f[i][j] = update3(f[i][j], f[i-1][j-1], delta);
            }

            for (j = 2; j <= 2 * K; j += 2) {
                f[i][j] = update3(f[i][j], f[i-1][j], 0);
                if (i > 1) f[i][j] = update3(f[i][j], f[i-1][j-1], delta);
                if (i > 1) f[i][j] = update3(f[i][j], f[i-1][j-2], delta);
            }
        }

        int res = Integer.MIN_VALUE;
        for (j = 2; j <= 2 * K; j += 2) {
            res = update(res, f[n][j], 0);
        }

        return res;
    }


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

您在真实的面试中是否遇到过这个题？ Yes
样例
给定价格 = [4,4,6,1,1,4,2,5], 且 k = 2, 返回 6.


 */