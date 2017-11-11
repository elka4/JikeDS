package DP.DP3;


//  309. Best Time to Buy and Sell Stock with Cooldown
//  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
//
public class _309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown {

    //https://discuss.leetcode.com/topic/107998/most-consistent-ways-of-dealing-with-the-series-of-stock-problems



    /*
Share my thinking process
The series of problems are typical dp. The key for dp is to find the variables to represent the states and deduce the transition function.

Of course one may come up with a O(1) space solution directly, but I think it is better to be generous when you think and be greedy when you implement.

The natural states for this problem is the 3 possible transactions : buy, sell, rest. Here rest means no transaction on that day (aka cooldown).

Then the transaction sequences can end with any of these three states.

For each of them we make an array, buy[n], sell[n] and rest[n].

buy[i] means before day i what is the maxProfit for any sequence end with buy.

sell[i] means before day i what is the maxProfit for any sequence end with sell.

rest[i] means before day i what is the maxProfit for any sequence end with rest.

Then we want to deduce the transition functions for buy sell and rest. By definition we have:

buy[i]  = max(rest[i-1]-price, buy[i-1])
sell[i] = max(buy[i-1]+price, sell[i-1])
rest[i] = max(sell[i-1], buy[i-1], rest[i-1])
Where price is the price of day i. All of these are very straightforward. They simply represents :

(1) We have to `rest` before we `buy` and
(2) we have to `buy` before we `sell`
One tricky point is how do you make sure you sell before you buy, since from the equations it seems that [buy, rest, buy] is entirely possible.

Well, the answer lies within the fact that buy[i] <= rest[i] which means rest[i] = max(sell[i-1], rest[i-1]). That made sure [buy, rest, buy] is never occurred.

A further observation is that and rest[i] <= sell[i] is also true therefore

rest[i] = sell[i-1]
Substitute this in to buy[i] we now have 2 functions instead of 3:

buy[i] = max(sell[i-2]-price, buy[i-1])
sell[i] = max(buy[i-1]+price, sell[i-1])
This is better than 3, but

we can do even better

Since states of day i relies only on i-1 and i-2 we can reduce the O(n) space to O(1). And here we are at our final solution:
 */
    public int maxProfit(int[] prices) {
        int sell = 0, prev_sell = 0, buy = Integer.MIN_VALUE, prev_buy;
        for (int price : prices) {
            prev_buy = buy;
            buy = Math.max(prev_sell - price, prev_buy);
            prev_sell = sell;
            sell = Math.max(prev_buy + price, prev_sell);
        }
        return sell;
    }


///////////////////////////////////////////////////////////////////////////
    /*
    Easiest JAVA solution with explanations
    Here I share my no brainer weapon when it comes to this kind of problems.

    1. Define States

    To represent the decision at index i:

    buy[i]: Max profit till index i. The series of transaction is ending with a buy.
    sell[i]: Max profit till index i. The series of transaction is ending with a sell.
    To clarify:

    Till index i, the buy / sell action must happen and must be the last action. It may not happen at index i. It may happen at i - 1, i - 2, ... 0.
    In the end n - 1, return sell[n - 1]. Apparently we cannot finally end up with a buy. In that case, we would rather take a rest at n - 1.
    For special case no transaction at all, classify it as sell[i], so that in the end, we can still return sell[n - 1]. Thanks @alex153 @kennethliaoke @anshu2.
    2. Define Recursion

    buy[i]: To make a decision whether to buy at i, we either take a rest, by just using the old decision at i - 1, or sell at/before i - 2, then buy at i, We cannot sell at i - 1, then buy at i, because of cooldown.
    sell[i]: To make a decision whether to sell at i, we either take a rest, by just using the old decision at i - 1, or buy at/before i - 1, then sell at i.
    So we get the following formula:

    buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
    sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
    3. Optimize to O(1) Space

    DP solution only depending on i - 1 and i - 2 can be optimized using O(1) space.

    Let b2, b1, b0 represent buy[i - 2], buy[i - 1], buy[i]
    Let s2, s1, s0 represent sell[i - 2], sell[i - 1], sell[i]
    Then arrays turn into Fibonacci like recursion:

    b0 = Math.max(b1, s2 - prices[i]);
    s0 = Math.max(s1, b1 + prices[i]);
    4. Write Code in 5 Minutes

    First we define the initial states at i = 0:

    We can buy. The max profit at i = 0 ending with a buy is -prices[0].
    We cannot sell. The max profit at i = 0 ending with a sell is 0.

     */
    public int maxProfit02(int[] prices) {
        if(prices == null || prices.length <= 1) return 0;

        int b0 = -prices[0], b1 = b0;
        int s0 = 0, s1 = 0, s2 = 0;

        for(int i = 1; i < prices.length; i++) {
            b0 = Math.max(b1, s2 - prices[i]);
            s0 = Math.max(s1, b1 + prices[i]);
            b1 = b0; s2 = s1; s1 = s0;
        }
        return s0;
    }


///////////////////////////////////////////////////////////////////////////
/*    7-line Java: only consider sell and cooldown
    Define:

    profit1[i] = max profit on day i if I sell

    profit2[i] = max profit on day i if I do nothing
    How will those profits on day i+1 relate to profits on day i ?

            1. profit1[i+1] means I must sell on day i+1, and there are 2 cases:

    a. If I just sold on day i, then I have to buy again on day i and sell on day i+1

    b. If I did nothing on day i, then I have to buy today and sell today

    Taking both cases into account, profit1[i+1] = max(profit1[i]+prices[i+1]-prices[i], profit2[i])

2. profit2[i+1] means I do nothing on day i+1, so it will be max(profit1[i], profit2[i])
    And the code:*/

    public int maxProfit03(int[] prices) {
        int profit1=0, profit2=0;
        for(int i=1; i<prices.length; i++){
            int copy=profit1;
            profit1=Math.max(profit1+prices[i]-prices[i-1], profit2);
            profit2=Math.max(copy, profit2);
        }
        return Math.max(profit1, profit2);
    }


///////////////////////////////////////////////////////////////////////////
/*    DescriptionHintsSubmissionsDiscussSolution
    Very Easy to Understand One Pass O(n) Solution with No Extra Space
    The idea is as follows:

    First, think about what we can do on day i? You either have one stock or you don't on day i. For each case, you have two options, making a total of four possible actions on day i:

    you have 1 stock and you sell it
    you have 1 stock and you do nothing
    you have 0 stock and you buy stock i
    you have 0 stock and you do nothing
    As you can imagine, these four actions are correlated between day i-1 and day i. For example, if you take action 1 on day i, you then have either taken action 2 or 3 on day i-1 but not 1 or 4. In precise, two consecutive days are related as follows:

            if you take action 1 on day i ==> you have either taken action 2 or 3 on day i-1
            if you take action 2 on day i ==> you have either taken action 2 or 3 on day i-1
            if you take action 3 on day i ==> you must have taken action 4 on day i-1 (you can not sell on day i-1 due to cool down)
            if you take action 4 on day i ==> you have either taken action 1 or 4 on day i-1
    Now you want to maximize your total profit, but you don't know what action to take on day i such that you get the total maximum profit, so you try all 4 actions on every day. Suppose you take action 1 on day i, since there are two possible actions on day i-1, namely actions 2 and 3, you would definitely choose the one that makes your profit on day i more. Same thing for actions 2 and 4. So we now have an iterative algorithm.

    Before coding, one detail to emphasize is that the initial value on day 0 is important. You basically cannot take action 1, so the corresponding profits should be 0. You cannot take action 2 in practice, but you cannot set up the profit to 0, because that means you don't have a stock to sell on day 1. Therefore, the initial profit should be negative value of the first stock. You can also think of it as you buy the stock on day -1 and do nothing on day 0.

    Here comes the code in Java:*/

    public int maxProfit04(int[] prices) {
        int L = prices.length;
        if(L < 2) return 0;

        int has1_doNothing = -prices[0];
        int has1_Sell = 0;
        int has0_doNothing = 0;
        int has0_Buy = -prices[0];
        for(int i=1; i<L; i++) {
            has1_doNothing = has1_doNothing > has0_Buy ? has1_doNothing : has0_Buy;
            has0_Buy = -prices[i] + has0_doNothing;
            has0_doNothing = has0_doNothing > has1_Sell ? has0_doNothing : has1_Sell;
            has1_Sell = prices[i] + has1_doNothing;
        }
        return has1_Sell > has0_doNothing ? has1_Sell : has0_doNothing;
    }


///////////////////////////////////////////////////////////////////////////
}

/* Leetcode
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

prices = [1, 2, 3, 0, 2]
maxProfit = 3
transactions = [buy, sell, cooldown, buy, sell]
 */

/*

 */