package DP.Classified._2Sequence;

//Best Time To Buy And Sell Stock
public class _4BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
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

}
/*Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

 Notice

You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Have you met this question in a real interview? Yes
Example
Given prices = [4,4,6,1,1,4,2,5], and k = 2, return 6.
 */
