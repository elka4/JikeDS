package J_6_Linked_List_Array.other.stock_leet;

//123 Best Time to Buy and Sell Stock III

/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */

public class _123_Best_Time_to_Buy_and_Sell_Stock_III {
    //Is it Best Solution with O(n), O(1).

    public int maxProfit(int[] prices) {
        int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
        int release1 = 0, release2 = 0;
        // Assume we only have 0 money at first
        for(int i:prices){
            // The maximum if we've just sold 2nd stock so far.
            release2 = Math.max(release2, hold2+i);
            // The maximum if we've just buy  2nd stock so far.
            hold2    = Math.max(hold2,    release1-i);
            // The maximum if we've just sold 1nd stock so far.
            release1 = Math.max(release1, hold1+i);
            // The maximum if we've just buy  1st stock so far.
            hold1    = Math.max(hold1,    -i);
        }
        ///Since release1 is initiated as 0, so release2 will always higher than release1.
        return release2;
    }


///////////////////////////////////////////////////////////////////////

    //My explanation for O(N) solution!

    public int maxProfit2(int[] prices) {
        int sell1 = 0, sell2 = 0, buy1 = Integer.MIN_VALUE,
                buy2 = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }


///////////////////////////////////////////////////////////////////////

    //2ms Java DP Solution

    public int maxProfit3(int[] prices) {
        // these four variables represent your profit after executing corresponding transaction
        // in the beginning, your profit is 0.
        // when you buy a stock ,the profit will be deducted of the price of stock.
        int firstBuy = Integer.MIN_VALUE, firstSell = 0;
        int secondBuy = Integer.MIN_VALUE, secondSell = 0;

        for (int curPrice : prices) {
            // the max profit after you buy first stock
            if (firstBuy < -curPrice) firstBuy = -curPrice;
            // the max profit after you sell it
            if (firstSell < firstBuy + curPrice) firstSell = firstBuy + curPrice;
            // the max profit after you buy the second stock
            if (secondBuy < firstSell - curPrice) secondBuy = firstSell - curPrice;
            // the max profit after you sell the second stock
            if (secondSell < secondBuy + curPrice) secondSell = secondBuy + curPrice;
        }
        // secondSell will be the max profit after passing the prices
        return secondSell;
    }


///////////////////////////////////////////////////////////////////////

    //Java solution with just two traverses.

    public int maxProfit4(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int lenght = prices.length;

        int[] leftProfit = new int[lenght];
        int leftMaxProfit = 0;
        int leftMin = prices[0];
        for (int i=0; i<lenght; i++) {
            if (prices[i] < leftMin) leftMin = prices[i];
            if (prices[i] - leftMin > leftMaxProfit) leftMaxProfit = prices[i]-leftMin;
            leftProfit[i] = leftMaxProfit;
        }

        int maxProfit = 0;
        int rightMaxProfit = 0;
        int rightMax = prices[lenght-1];
        for (int i=lenght-1; i>=0; i--) {
            if (prices[i] > rightMax) rightMax = prices[i];
            if (rightMax - prices[i] > rightMaxProfit) rightMaxProfit = rightMax - prices[i];
            int currentProfit = rightMaxProfit + (i>0 ? leftProfit[i-1] : 0);
            if (currentProfit > maxProfit) {
                maxProfit = currentProfit;
            }
        }

        return maxProfit;
    }



///////////////////////////////////////////////////////////////////////

    //A clear o(n) time and space Java Solution

    public int maxProfit5(int[] prices) {
        int len = prices.length;
        if (len < 2)
            return 0;
        int [] maxBefore = new int[len];
        int min = prices[0];
        for(int i=1; i<len; i++)
        {
            maxBefore[i] = Math.max(maxBefore[i-1], prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        int max = prices[len-1];
        int ret = 0;
        for (int i=len-2; i>=0; i--)
        {
            max = Math.max(prices[i], max);
            ret = Math.max(ret, max - prices[i] + maxBefore[i]);
        }
        return ret;
    }

///////////////////////////////////////////////////////////////////////

    //Java solution, one iteration (O(n) time), constant space, with explanation

    public int maxProfit6(int[] prices) {
        int len = prices.length;
        if(len <= 1) return 0;
        int a, b, c, d;
        d = Math.max(prices[len-1], prices[len-2]);
        c = Math.max(prices[len-1] - prices[len-2], 0);
        b = d;
        a = c;
        for(int i=len-3; i>=0; i--) {
            a = Math.max(b - prices[i], a);
            b = Math.max(prices[i] + c, b);
            c = Math.max(d - prices[i], c);
            d = Math.max(prices[i], d);
        }
        return a;
    }



///////////////////////////////////////////////////////////////////////



///////////////////////////////////////////////////////////////////////




}
