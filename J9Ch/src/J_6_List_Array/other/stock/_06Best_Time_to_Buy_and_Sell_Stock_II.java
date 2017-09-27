package J_6_List_Array.other.stock;

public class _06Best_Time_to_Buy_and_Sell_Stock_II {
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
/*Say you have an array for which the ith element is the price of a 
 * given stock on day i.
 

Design an algorithm to find the maximum profit. You may complete as
 many transactions as you like (ie, buy one and sell one share of 
 the stock multiple times). However, you may not engage in multiple 
 transactions at the same time (ie, you must sell the stock before
  you buy again).

Example
Given an example [2,1,2,0,1], return 2

Tags 
Greedy Enumeration Array
Related Problems 
Hard Best Time to Buy and Sell Stock IV 23 %
Medium Best Time to Buy and Sell Stock III 27 %
Hard Maximum Subarray III 23 %*/