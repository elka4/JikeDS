package DP.DP3;

/*

 */

//Best Time To Buy And Sell Stock II
public class _5BestTimeToBuyAndSellStockII {
    // 9Ch DP
    public int maxProfit(int[] A) {
        int res = 0;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i + 1] - A[i] > 0) {
                res += A[i + 1] - A[i];
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
/////////////////////////////////////////////////////////////////
}
/*
假设有一个数组，它的第i个元素是一个给定的股票在第i天的价格。设计一个算法来找到最大的利润。你可以完成尽可能多的交易(多次买卖股票)。然而,你不能同时参与多个交易(你必须在再次购买前出售股票)。

样例
给出一个数组样例[2,1,2,0,1], 返回 2
 */
