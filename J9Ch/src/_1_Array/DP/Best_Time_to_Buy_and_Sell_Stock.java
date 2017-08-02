package _1_Array.DP;

/*
LeetCode – Best Time to Buy and Sell Stock (Java)

Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 */
public class Best_Time_to_Buy_and_Sell_Stock {
    //Instead of keeping track of largest element in the array, we track the maximum profit so far.

    public int maxProfit(int[] prices) {
        if(prices==null||prices.length<=1)
            return 0;

        int min=prices[0]; // min so far
        int result=0;

        for(int i=1; i<prices.length; i++){
            result = Math.max(result, prices[i]-min);
            min = Math.min(min, prices[i]);
        }

        return result;
    }

}
