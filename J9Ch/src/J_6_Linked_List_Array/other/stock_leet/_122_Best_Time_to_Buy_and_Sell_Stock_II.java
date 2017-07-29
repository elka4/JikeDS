package J_6_Linked_List_Array.other.stock_leet;

//122 Best Time to Buy and Sell Stock II

/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).


 */
public class _122_Best_Time_to_Buy_and_Sell_Stock_II {

    //Approach #1 Brute Force [Time Limit Exceeded]

    public int maxProfit(int[] prices) {
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

//////////////////////////////////////////////////////////////

    //Approach #2 (Peak Valley Approach) [Accepted]
    public int maxProfit2(int[] prices) {
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

//////////////////////////////////////////////////////////////

    //Approach #3 (Simple One Pass) [Accepted]

    public int maxProfit3(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }

//////////////////////////////////////////////////////////////

    //Is this question a joke?

    public int maxProfit4(int[] prices) {
        int total = 0;
        for (int i=0; i< prices.length-1; i++) {
            if (prices[i+1]>prices[i]) total += prices[i+1]-prices[i];
        }

        return total;
    }
//////////////////////////////////////////////////////////////

    //Java O(n) solution if we're not greedy

    public int maxProfit5(int[] prices) {
        int profit = 0, i = 0;
        while (i < prices.length) {
            // find next local minimum
            while (i < prices.length-1 && prices[i+1] <= prices[i]) i++;
            // need increment to avoid infinite loop for "[1]"
            int min = prices[i++];
            // find next local maximum
            while (i < prices.length-1 && prices[i+1] >= prices[i]) i++;
            profit += i < prices.length ? prices[i++] - min : 0;
        }
        return profit;
    }

//////////////////////////////////////////////////////////////

    //A simple solution with O(n) time and O(1) space

    public int maxProfit6(int[] prices) {
        if(prices.length==0|| prices.length==1) return 0;
        int max_pro=0;
        for(int i=prices.length-1;i>0;i--){
            if(prices[i]-prices[i-1]>0) max_pro+=prices[i]-prices[i-1];
        }
        return max_pro;
    }


//////////////////////////////////////////////////////////////

    //Shortest and fastest solution with explanation. You can never beat this.
    public int maxProfit7(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i=1;i<n;i++)
            ans += Math.max(0,prices[i]-prices[i-1]);
        return ans;
    }


//////////////////////////////////////////////////////////////

    //A concise solution in Java

    public int maxProfit8(int[] prices) {
        int len = prices.length;
        int profit = 0;
        for(int i = 1; i < len ; i++){
            if(prices[i]-prices[i-1] <= 0){
                continue;
            }
            profit += prices[i]-prices[i-1];
        }
        return profit;
    }
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////

}
