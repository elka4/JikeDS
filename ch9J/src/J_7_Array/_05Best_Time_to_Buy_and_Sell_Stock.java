package J_7_Array;

public class _05Best_Time_to_Buy_and_Sell_Stock {
	public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
      //just remember the smallest price
        int profit = 0;
        int min = Integer.MAX_VALUE;  
        for (int i : prices) {
            min = i < min ? i : min;
            profit = (i - min) > profit ? i - min : profit;
        }

        return profit;
    }
}

/*Say you have an array for which the ith element is the price of a
 *  given stock on day i.


If you were only permitted to complete at most one transaction 
(ie, buy one and sell one share of the stock), design an algorithm 
to find the maximum profit.

Example
Given array [3,2,3,1,2], return 1.

Tags 
Greedy Enumeration Array Facebook Uber
Related Problems 
Hard Best Time to Buy and Sell Stock IV 23 %
Medium Maximum Product Subarray 28 %
Medium Best Time to Buy and Sell Stock III 27 %
Hard Maximum Subarray III 23 %*/