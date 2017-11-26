package DP.DP3;

// 一次交易

/*
每次用i update min，然后用新的min和i去update profit

-----------------------------------------------------------------------------------------------
LintCode 149 Best Time to Buy and Sell Stock

• 题意:
• 已知后面N天一支股票的每天的价格P0, P1, ..., PN-1
• 可以最多买一股卖一股
• 求最大利润
• 例子:
• 输入:[3, 2, 3, 1, 2]
• 输出:1 (2买入，3卖出)

-----------------------------------------------------------------------------------------------
题目分析

• 保底策略:什么都不做，利润0
• 低买高卖，先买后卖
• 如果买卖一股，一定是第i天买，第j天卖 (j > i)，获利是Pj – Pi
• 枚举j，即第几天卖
• 显然，希望找到最小的买入价格Pi (i < j)

-----------------------------------------------------------------------------------------------
动态规划解法

• 从0到N-1枚举j，即第几天卖
• 时刻保存当前为止(即0~j-1天)的最低价格Pi
• 最大的Pj - Pi即为答案
-----------------------------------------------------------------------------------------------
 */


//  121. Best Time to Buy and Sell Stock
//  https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
//  http://lintcode.com/zh-cn/problem/best-time-to-buy-and-sell-stock/
//  5:
public class _4BestTimeToBuyAndSellStock {
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock/solution/
//------------------------------------------------------------------------------
    //https://discuss.leetcode.com/topic/107998/most-consistent-ways-of-dealing-with-the-series-of-stock-problems

//------------------------------------------------------------------------------
    //1
    //Approach #1 (Brute Force) [Time Limit Exceeded]
    public class Solution01 {
        public int maxProfit(int prices[]) {
            int maxprofit = 0;
            for (int i = 0; i < prices.length - 1; i++) {
                for (int j = i + 1; j < prices.length; j++) {
                    int profit = prices[j] - prices[i];
                    if (profit > maxprofit)
                        maxprofit = profit;
                }
            }
            return maxprofit;
        }
    }
//------------------------------------------------------------------------------
    //2
    //Approach #2 (One Pass) [Accepted]
    public class Solution02 {
        public int maxProfit(int prices[]) {
            int minprice = Integer.MAX_VALUE;
            int maxprofit = 0;
            for (int i = 0; i < prices.length; i++) {
                if (prices[i] < minprice)
                    minprice = prices[i];
                else if (prices[i] - minprice > maxprofit)
                    maxprofit = prices[i] - minprice;
            }
            return maxprofit;
        }
    }

//------------------------------------------------------------------------------
    //3
    // 9Ch
    public int maxProfit1(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;

        for (int i = 0; i < prices.length; i++) {

            if (prices[i] < minprice)
                minprice = prices[i];
                //如果当前股价prices[i]是最小，那么肯定不会update maxprofit
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }

//-------------------------------------------------------------------------------
    //4
    // 9Ch
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;  //just remember the smallest price
        int profit = 0;

        for (int i : prices) {
            min = i < min ? i : min;
            profit = (i - min) > profit ? i - min : profit;
        }

        return profit;
    }
//------------------------------------------------------------------------------
    //5
    //mine
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int profit = 0;

        for (int price : prices) {
            min = Math.min(min, price);
            profit = Math.max(profit, price - min);
        }

        return profit;
    }
//------------------------------------------------------------------------------
}

/*
假设有一个数组，它的第i个元素是一支给定的股票在第i天的价格。如果你最多只允许完成一次交易(例如,一次买卖股票),设计一个算法来找出最大利润。

样例
给出一个数组样例 [3,2,3,1,2], 返回 1
 */


/* Leetcdoe
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Example 1:
Input: [7, 1, 5, 3, 6, 4]
Output: 5

max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
Example 2:
Input: [7, 6, 4, 3, 1]
Output: 0

In this case, no transaction is done, i.e. max profit = 0.
 */

/*Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

 Notice

You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Have you met this question in a real interview? Yes
Example
Given prices = [4,4,6,1,1,4,2,5], and k = 2, return 6.
 */

