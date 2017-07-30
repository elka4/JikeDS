package J_6_Linked_List_Array.other.stock_leet;

// 188 Best Time to Buy and Sell Stock IV

/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Credits:
Special thanks to @Freezen for adding this problem and creating all test cases.
 */
public class _188_Best_Time_to_Buy_and_Sell_Stock_IV {

    //A Concise DP Solution in Java

    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k >= len / 2) return quickSolve(prices);

        int[][] t = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            int tmpMax =  -prices[0];
            for (int j = 1; j < len; j++) {
                t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);
                tmpMax =  Math.max(tmpMax, t[i - 1][j - 1] - prices[j]);
            }
        }
        return t[k][len - 1];
    }


    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1])
                profit += prices[i] - prices[i - 1];
        return profit;
    }

/////////////////////////////////////////////////////////

    ///Clean Java DP solution with comment

    /**
     * dp[i, j] represents the max profit up until prices[j] using at most i transactions.
     * dp[i, j] = max(dp[i, j-1], prices[j] - prices[jj] + dp[i-1, jj]) { jj in range of [0, j-1] }
     *          = max(dp[i, j-1], prices[j] + max(dp[i-1, jj] - prices[jj]))
     * dp[0, j] = 0; 0 transactions makes 0 profit
     * dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
     */

    public int maxProfit2(int k, int[] prices) {
        int n = prices.length;
        if (n <= 1)
            return 0;

        //if k >= n/2, then you can make maximum number of transactions.
        if (k >=  n/2) {
            int maxPro = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i-1])
                    maxPro += prices[i] - prices[i-1];
            }
            return maxPro;
        }

        int[][] dp = new int[k+1][n];
        for (int i = 1; i <= k; i++) {
            int localMax = dp[i-1][0] - prices[0];
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j-1],  prices[j] + localMax);
                localMax = Math.max(localMax, dp[i-1][j] - prices[j]);
            }
        }
        return dp[k][n-1];
    }



/////////////////////////////////////////////////////////

//Easy understanding and can be easily modified to different situations Java Solution
    /*


    hold[i][j] = Math.max(unhold[i-1][j]-prices[i],hold[i-1][j]);

    unhold[i][j] = Math.max(hold[i-1][j-1]+prices[i],unhold[i-1][j]);

     */

    //hold[i][k]  ith day k transaction have stock and maximum profit
    //unhold[i][k] ith day k transaction do not have stock at hand and maximum profit
    public int maxProfit3(int k, int[] prices) {
        if(k>prices.length/2) return maxP(prices);
        int[][] hold = new int[prices.length][k+1];
        int[][] unhold = new int[prices.length][k+1];
        hold[0][0] = -prices[0];
        for(int i=1;i<prices.length;i++) hold[i][0] = Math.max(hold[i-1][0],-prices[i]);
        for(int j=1;j<=k;j++) hold[0][j] = -prices[0];
        for(int i=1;i<prices.length;i++){
            for(int j=1;j<=k;j++){
                hold[i][j] = Math.max(unhold[i-1][j]-prices[i],hold[i-1][j]);
                unhold[i][j] = Math.max(hold[i-1][j-1]+prices[i],unhold[i-1][j]);
            }
        }
        return Math.max(hold[prices.length-1][k],unhold[prices.length-1][k]);
    }
    public int maxP(int[] prices){
        int res =0;
        for(int i=0;i<prices.length;i++){
            if(i>0 && prices[i] > prices[i-1]){
                res += prices[i]-prices[i-1];
            }
        }
        return res;
    }

/////////////////////////////////////////////////////////

//Clean Java DP O(nk) solution with O(k) space



    //Extend small_box's idea for "Best Time to Buy and Sell Stock III" to general case.
    //https://leetcode.com/discuss/18330/is-it-best-solution-with-o-n-o-1

    public int maxProfit4(int k, int[] prices) {
        if(k>=prices.length/2){
            int maxProfit = 0;
            for(int i=1; i<prices.length; i++){
                if(prices[i]>prices[i-1]) maxProfit += prices[i]-prices[i-1];
            }
            return maxProfit;
        }

        int[] maxProfit = new int[k+1];
        int[] lowPrice = new int[k+1];
        for(int i=0; i<lowPrice.length; i++) lowPrice[i]=Integer.MAX_VALUE;
        for(int p : prices){
            for(int i=k; i>=1; i--){
                maxProfit[i] = Math.max(maxProfit[i],p-lowPrice[i]);
                lowPrice[i] = Math.min(lowPrice[i],p-maxProfit[i-1]);
            }
        }
        return maxProfit[k];
    }


/////////////////////////////////////////////////////////



/////////////////////////////////////////////////////////



}
