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

-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

 */


//  122. Best Time to Buy and Sell Stock II
//  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
public class _5BestTimeToBuyAndSellStockII {
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/solution/

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

    //Approach #3 (Simple One Pass) [Accepted]
    public int maxProfit03(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }


/////////////////////////////////////////////////////////////////
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

/////////////////////////////////////////////////////////////////
    // jiuzhang
    public int maxProfit1(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i+1] - prices[i];
            if (diff > 0) {
                profit += diff;
            }
        }
        return profit;
    }

/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
}
/*
假设有一个数组，它的第i个元素是一个给定的股票在第i天的价格。设计一个算法来找到最大的利润。
你可以完成尽可能多的交易(多次买卖股票)。然而,你不能同时参与多个交易(你必须在再次购买前出售股票)。

样例
给出一个数组样例[2,1,2,0,1], 返回 2
 */
