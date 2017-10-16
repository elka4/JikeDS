package DP._j_5_DP2;//划分类

public class _15Best_Time_to_Buy_and_Sell_Stock_IV {
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
        	
            mustsell[i][j] = Math.max(
            		globalbest[(i - 1)][j - 1] + gainorlose,
                mustsell[(i - 1)][j] + gainorlose);
            
            globalbest[i][j] = Math.max(
            		globalbest[(i - 1)][j], mustsell[i ][j]);
        }
    }
    return globalbest[(n - 1)][k];
}
}

/*
 * Say you have an array for which the ith element is the price 
 * of a given stock on day i.

Design an algorithm to find the maximum profit. 
You may complete at most k transactions.

 Notice

You may not engage in multiple transactions at the same time 
(i.e., you must sell the stock before you buy again).

Have you met this question in a real interview? Yes
Example
Given prices = [4,4,6,1,1,4,2,5], and k = 2, return 6.

Challenge 
O(nk) time.

Tags 
Dynamic Programming
Related Problems 
Medium Best Time to Buy and Sell Stock III 26 %
Medium Best Time to Buy and Sell Stock II 48 %
Medium Best Time to Buy and Sell Stock 41 %
 */