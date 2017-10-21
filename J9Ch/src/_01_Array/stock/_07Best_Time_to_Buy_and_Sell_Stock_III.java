package _01_Array.stock;

public class _07Best_Time_to_Buy_and_Sell_Stock_III {
	public int maxProfit(int[] prices) {
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
}

/*Say you have an array for which the ith element is the price of a
 *  given stock on day i.
 

Design an algorithm to find the maximum profit. You may complete at 
most two transactions.

 Notice

You may not engage in multiple transactions at the same time 
(ie, you must sell the stock before you buy again).

Example
Given an example [4,4,6,1,1,4,2,5], return 6.

Tags 
Enumeration Forward-Backward Traversal Array
Related Problems 
Hard Best Time to Buy and Sell Stock IV 23 %
Medium Best Time to Buy and Sell Stock III 27 %
Medium Best Time to Buy and Sell Stock II 49 %
Medium Best Time to Buy and Sell Stock 41 %
Medium Maximum Subarray Difference 23 %
Hard Maximum Subarray III 23 %
Medium Maximum Subarray II 24 %
Easy Maximum Subarray 37 %*/