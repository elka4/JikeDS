package DP_Classified._2Sequence;

//任意次交易
//不能同时参与多个交易

/*
线性，累加所有增长
 */

//Best Time To Buy And Sell Stock II
public class _5BestTimeToBuyAndSellStockII {
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i+1] - prices[i];
            if (diff > 0) {
                profit += diff;
            }
        }
        return profit;
    }
}
/*
假设有一个数组，它的第i个元素是一个给定的股票在第i天的价格。设计一个算法来找到最大的利润。
你可以完成尽可能多的交易(多次买卖股票)。然而,你不能同时参与多个交易(你必须在再次购买前出售股票)。

您在真实的面试中是否遇到过这个题？ Yes
样例
给出一个数组样例[2,1,2,0,1], 返回 2
 */
