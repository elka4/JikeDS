package _01_Array.stock_leet;

//121 Best Time to Buy and Sell Stock

/*
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

//找一个前面的最小和一个后面的最大
public class _121_Best_Time_to_Buy_and_Sell_Stock {

    //Approach #1 (Brute Force) [Time Limit Exceeded]
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

///////////////////////////////////////////////////////////

    //Approach #2 (One Pass) [Accepted]

    /*
    每轮都重设minprice和maxprofit
    minprice先设为Integer.MAX_VALUE
    maxprofit先设为0

     */
    public int maxProfit2(int prices[]) {
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

///////////////////////////////////////////////////////////

    //Kadane's Algorithm - Since no one has mentioned about
    // this so far :) (In case if interviewer twists the input)
    /*
    这个做法有意思，就是每对macCur都增加i位置的price和i-1位置的price。
    是增加，不是重置！
     */
    public int maxProfit3(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for(int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }



///////////////////////////////////////////////////////////
    //A O(1*n) solution

    /*
    和solution第二种方法相同
     */
    public int maxProfit4(int[] prices) {
        if (prices.length == 0){
            return 0;
        }
        int max = 0, min = prices[0];
        int profit = 0;

        for (int i = 1; i < prices.length; i++){
            if (prices[i] < min){

                min = prices[i];
            } else{
                if (prices[i] - min > profit){
                    profit = prices[i] - min;
                }
            }
        }
        return profit;
    }



///////////////////////////////////////////////////////////

    //My jave accepted solution with O(N) time and O(1) space
    /*
    和solution2第二种方法相同
     */
    public int maxProfit5(int[] prices) {
        if (prices.length == 0) {
            return 0 ;
        }
        int max = 0 ;
        int sofarMin = prices[0] ;
        for (int i = 0 ; i < prices.length ; ++i) {
            if (prices[i] > sofarMin) {
                max = Math.max(max, prices[i] - sofarMin) ;
            } else{
                sofarMin = prices[i];
            }
        }
        return  max ;
    }




///////////////////////////////////////////////////////////




///////////////////////////////////////////////////////////

}
