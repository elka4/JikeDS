package DP.DP3;

/*

-----------------------------------------------

------------------------------------------------
LintCode 150 Best Time to Buy and Sell Stock II

• 题意:
• 已知后面N天一支股票的每天的价格P0, P1, ..., PN-1
• 可以买卖一股任意多次，但任意时刻手中最多持有一股 • 求最大利润
• 例子:
• 输入:[2, 1, 2, 0, 1]
• 输出:2 (1买入，2卖出，0买入，1卖出)
-----------------------------------------------------------------------------------------------
 */


//  122. Best Time to Buy and Sell Stock II
//  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
//  http://lintcode.com/zh-cn/problem/best-time-to-buy-and-sell-stock-ii/
//  5:
public class _5BestTimeToBuyAndSellStockII {
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/solution/

    //https://discuss.leetcode.com/topic/107998/most-consistent-ways-of-dealing-with-the-series-of-stock-problems
//------------------------------------------------------------------------------
    //1
    //Approach #1 Brute Force [Time Limit Exceeded]
    public int maxProfit01(int[] prices) {
        return calculate(prices, 0);
    }

    public int calculate(int prices[], int s) {
        if (s >= prices.length)
            return 0;
        int max = 0;
        for (int start = s; start < prices.length; start++) {
            int maxprofit = 0;
            for (int i = start + 1; i < prices.length; i++) {
                if (prices[start] < prices[i]) {
                    int profit = calculate(prices, i + 1) + prices[i] - prices[start];
                    if (profit > maxprofit)
                        maxprofit = profit;
                }
            }
            if (maxprofit > max)
                max = maxprofit;
        }
        return max;
    }
//------------------------------------------------------------------------------
    //2
    //  Approach #2 (Peak Valley Approach) [Accepted]
    public int maxProfit02(int[] prices) {
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            peak = prices[i];
            maxprofit += peak - valley;
        }
        return maxprofit;
    }

//------------------------------------------------------------------------------
    //3
    //Approach #3 (Simple One Pass) [Accepted]
    //最好的做法，和下面九章做法类似，关键在于一维数组坐标的熟练操作
    public int maxProfit03(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }


//----------------------------------------------------------------------------
    //4
    // 9Ch DP
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] - prices[i] > 0) {
                res += prices[i + 1] - prices[i];
            }
        }
        return res;
    }

//----------------------------------------------------------------------------
    //5
    // jiuzhang
    public int maxProfit1(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i+1] - prices[i];
            /*if (diff > 0) {
                profit += diff;
            }*/
            profit += diff > 0 ? diff : 0;
        }
        return profit;
    }

//----------------------------------------------------------------------------
}
/*
买卖股票的最佳时机 II

假设有一个数组，它的第i个元素是一个给定的股票在第i天的价格。设计一个算法来找到最大的利润。你可以完成尽可能多的交易(多次买卖股票)。然而,你不能同时参与多个交易(你必须在再次购买前出售股票)。

您在真实的面试中是否遇到过这个题？ Yes
样例
给出一个数组样例[2,1,2,0,1], 返回 2

标签
枚举法 数组 贪心
 */

/*  Leetcode
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */