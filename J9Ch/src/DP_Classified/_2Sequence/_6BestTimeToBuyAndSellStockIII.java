package DP_Classified._2Sequence;

//两笔交易

import org.junit.Test;

//  Best Time To Buy And Sell Stock III
public class _6BestTimeToBuyAndSellStockIII {
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

    public int maxProfit(int[] prices) {
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
            left[i] = Math.max(left[i - 1], prices[i] - min);
        }

        //DP from right to left;
        right[prices.length - 1] = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(prices[i], max);
            right[i] = Math.max(right[i + 1], max - prices[i]);
        }

        int profit = 0;
        for (int i = 0; i < prices.length; i++){
            profit = Math.max(left[i] + right[i], profit);
        }

        return profit;
    }

    @Test
    public void test02(){
        int[] nums = {4,4,6,1,1,4,2,5};
        System.out.println(maxProfit2(nums));
    }

/////////////////////////////////////////////////////////////////////

    // 方法二
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

    private int update3(int a, int b, int delta) {
        if (b == Integer.MIN_VALUE) {
            return a;
        }

        if (b + delta > a) {
            return b + delta;
        }

        return a;
    }

/////////////////////////////////////////////////////////////////////

}

/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

 Notice

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Have you met this question in a real interview? Yes
Example
Given an example [4,4,6,1,1,4,2,5], return 6.


 */


/*
假设你有一个数组，它的第i个元素是一支给定的股票在第i天的价格。设计一个算法来找到最大的利润。你最多可以完成两笔交易。

 注意事项

你不可以同时参与多笔交易(你必须在再次购买前出售掉之前的股票)
 */
