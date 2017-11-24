package DP.DP3;
import org.junit.Test;

//_7BestTimeToBuyAndSellStockIV   01:17:13
//• 有状态的序列型动态规划

/*
-----------------------------------------------------------------------------------------------
LintCode 393 Best Time To Buy and Sell Stock IV

• 题意:
• 给定一支股票N天的价格
• 可以进行最多K次买+K次卖，每次买卖都是一股
• 不能在卖光手中股票前买入，但可以同一天卖完后买入 • 问最大收益
• 例子:
• 输入: [4,4,6,1,1,4,2,5], K = 2
• 输出:6 (4买入，6卖出，1买入，5卖出，收益为(6-4) + (5-1) = 6)
-----------------------------------------------------------------------------------------------
题目分析
• 首先，如果K很大，K>N/2, 则题目可以化简成为Best Time to Buy and Sell Stock II，每天买入当且仅当价格比下一天低
• Best Time to Buy and Sell Stock III 相当于这题中K = 2
• 所以我们可以借鉴之前的解法

-----------------------------------------------------------------------------------------------
动态规划组成部分二:转移方程

• f[i][j]:前i天(第i-1天)结束后，处在阶段j,最大获利

阶段1, 3, 5, ...., 2K+1 --- 手中无股票状态:
f[i][j] = max{f[i-1][j], f[i-1][j-1] + Pi-1 – Pi-2}

f[i-1][j]:                 昨天没有持有股票
f[i-1][j-1] + Pi-1 – Pi-2: 昨天持有股票，今天卖出清仓



阶段2, 4, ...., 2K --- 手中有股票状态:
f[i][j] = max{f[i-1][j] + Pi-1 – Pi-2, f[i-1][j-1], f[i-1][j-2] + Pi-1 – Pi-2}

f[i-1][j] + Pi-1 – Pi-2:   昨天就持有股票，继续持有并获利
f[i-1][j-1]:               昨天没有持有股票，今天买入
f[i-1][j-2] + Pi-1 – Pi-2: 昨天持有上一次买的股票， 今天卖出并立即买入



-----------------------------------------------------------------------------------------------
动态规划组成部分三:初始条件和边界情况

• 刚开始(前0天)处于阶段1 – f[0][1] = 0
– f[0][2] = f[0][3] = ... = f[0][2K+1] = +∞
• 阶段1, 3, 5, .., 2K+1: f[i][j] = max{f[i-1][j], f[i-1][j-1] + Pi-1 – Pi-2}
• 阶段2, 4, .., 2K: f[i][j] = max{f[i-1][j] + Pi-1 – Pi-2, f[i-1][j-1], f[i-1][j-2] + Pi-1 – Pi-2}
• 如果j – 1 < 1或j – 2 < 1或i – 2 < 0, 对应项不计入max
• 因为最多买卖K次，所以答案是max{f[N][1], f[N][3], .., f[N][2K+1]}, 即 清仓状态下最后一天最大获利
-----------------------------------------------------------------------------------------------
动态规划组成部分四:计算顺序
• 初始化f[0][1], ..., f[0][2K+1] • f[1][1], ..., f[1][2K+1]
•...
• f[N][1], ..., f[N][2K+1]
• 时间复杂度:O(NK),空间复杂度:O(NK)，优化后可以O(K)，因为 f[i][1.. 2K+1]只依赖于f[i-1][1.. 2K+1]
-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

 */


//  188. Best Time to Buy and Sell Stock IV
//  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/
//  http://lintcode.com/zh-cn/problem/best-time-to-buy-and-sell-stock-iv/
public class _7BestTimeToBuyAndSellStockIV {

    //https://discuss.leetcode.com/topic/107998/most-consistent-ways-of-dealing-with-the-series-of-stock-problems

//------------------------------------------------------------------------------/////////


/*    A Concise DP Solution in Java

    The general idea is DP, while I had to add a "quickSolve" function to tackle some corner cases to avoid TLE.

            DP: t(i,j) is the max profit for up to i transactions by time j (0<=i<=K, 0<=j<=T).*/

    public int maxProfit01(int k, int[] prices) {
        int len = prices.length;
        //k >= len / 2就是对每个price都可以买一次或者卖一次
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


    private int quickSolve(int[] prices) {//这个就是stock2吧
        int len = prices.length, profit = 0;

        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1])
                profit += prices[i] - prices[i - 1];

        return profit;
    }

//------------------------------------------------------------------------------/////////

//    Clean Java DP solution with comment

    /**
     * dp[i, j] represents the max profit up until prices[j] using at most i transactions.
     * dp[i, j] = max(dp[i, j-1], prices[j] - prices[jj] + dp[i-1, jj]) { jj in range of [0, j-1] }
     *          = max(dp[i, j-1], prices[j] + max(dp[i-1, jj] - prices[jj]))
     * dp[0, j] = 0; 0 transactions makes 0 profit
     * dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
     */

    public int maxProfit02(int k, int[] prices) {
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

        /*
            for (int i = 1; i <= k; i++) {
                int tmpMax =  -prices[0];

                for (int j = 1; j < len; j++) {

                    t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);

                    tmpMax =  Math.max(tmpMax, t[i - 1][j - 1] - prices[j]);
                }
            }
         */
        for (int i = 1; i <= k; i++) {
            int localMax = dp[i-1][0] - prices[0];

            for (int j = 1; j < n; j++) {

                dp[i][j] = Math.max(dp[i][j-1],  prices[j] + localMax);

                localMax = Math.max(localMax, dp[i-1][j] - prices[j]);
            }
        }
        return dp[k][n-1];
    }
//------------------------------------------------------------------------------/////////
/*    Easy understanding and can be easily modified to different situations Java Solution

    The basic idea is to create two tables. hold and unhold.

            hold[i][j] means the maximum profit with at most j transaction for 0 to i-th day. hold means you have a stock in your hand.

            unhold[i][j] means the maximum profit with at most j transaction for 0 to i-th day. unhold means you don't have a stock in your hand.

    The equation is

    hold[i][j] = Math.max(unhold[i-1][j]-prices[i],hold[i-1][j]);

    unhold[i][j] = Math.max(hold[i-1][j-1]+prices[i],unhold[i-1][j]);

    when you sell your stock this is a transaction but when you buy a stock, it is not considered as a full transaction. so this is why the two equation look a little different.

    And we have to initiate hold table when k = 0.

    When the situation is you can not buy a new stock at the same day when you sell it. For example you can only buy a new stock after one day you sell it. The same idea. Another situation is when you have to pay a transaction fee for each transaction, just make a modification when you sell it, So just change the unhold equation a little.*/

    public class Solution {
        //hold[i][k]  ith day k transaction have stock and maximum profit
        //unhold[i][k] ith day k transaction do not have stock at hand and maximum profit
        public int maxProfit03(int k, int[] prices) {
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
    }


    //------------------------------------------------------------------------------////
    // 9Ch DP
    public int maxProfit(int K, int[] A) {
        int n = A.length;
        if (n == 0) {
            return 0;
        }

        int i, j, k;
        if (K > n / 2) {
            int result = 0;
            for (i = 0; i < n - 1; i++) {
                result += Math.max(A[i + 1] - A[i], 0);
            }
            return result;
        }

        int[][] f = new int[n + 1][2 * K + 1 + 1];

        //init
        //first 0 days, phase 1, max profit = 0
        f[0][1] = 0;
        for (k = 2; k <= 2 * K + 1; k++) {
            f[0][k] = Integer.MIN_VALUE;
        }


        for (i = 1; i <= n; i++) {
            // 1, 3, 5
            for (j = 1; j <= 2 * K + 1; j += 2) {
                f[i][j] = f[i - 1][j];
                // 判断j i，为了之后f[i - 1][j - 1] index 不越界
                if (j > 1 && i > 1 && f[i - 1][j - 1] != Integer.MIN_VALUE) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + A[i - 1] - A[i - 2]);
                }
            }

            // 2, 4
            for (j = 2; j <= 2 * K + 1; j += 2) {
                //max
                f[i][j] = f[i - 1][j - 1];
                if (i > 1 && f[i - 1][j] != Integer.MIN_VALUE) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j] + A[i - 1] - A[i - 2]);
                }

                if (j > 2 && i > 1 && f[i - 1][j - 2] != Integer.MIN_VALUE) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 2] + A[i - 1] - A[i - 2]);
                }
            }
        }

        int res = Integer.MIN_VALUE;
        for (i = 1; i <= 2 * K + 1 ; i += 2) {
            res = Math.max(res, f[n][i]);
        }
        return res;
    }

    @Test
    public void test01() {
//        给定价格 = [4,4,6,1,1,4,2,5], 且 k = 2, 返回 6.
        int[] A = {4,4,6,1,1,4,2,5};
        int k = 2;
        System.out.println(maxProfit(k, A));
    }

//------------------------------------------------------------------------------/////////
    // 动态规划专题班版本 Version 1
    private int update(int a, int b, int delta) {
        if (b == Integer.MIN_VALUE) {
            return a;
        }

        if (b + delta > a) {
            return b + delta;
        }

        return a;
    }
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit2(int K, int[] prices) {
        int n = prices.length;
        int i, j, k;
        if (K == 0) {
            return 0;
        }

        if (K >= n - 1) {
            j = 0;
            for (i = 1; i < n; ++i) {
                if (prices[i] > prices[i - 1]) {
                    j += prices[i] - prices[i - 1];
                }
            }

            return j;
        }

        int[][] f = new int[n+1][2*K+1+1];
        for (i = 0; i <= n; ++i) {
            for (j = 1; j <= 2*K+1; ++j) {
                f[i][j] = Integer.MIN_VALUE;
            }
        }

        f[0][1] = 0;
        for (i = 1; i <= n; ++i) {
            for (j = 1; j <= 2 * K + 1; j += 2) {
                f[i][j] = update(f[i][j], f[i-1][j], 0);
                if (j > 1 && i > 1) f[i][j] =
                        update(f[i][j], f[i - 1][j - 1], prices[i - 1] - prices[i - 2]);
            }

            for (j = 2; j <= 2 * K; j += 2) {
                if (i > 1) f[i][j] =
                        update(f[i][j], f[i-1][j], prices[i - 1] - prices[i - 2]);

                if (j > 1) f[i][j] = update(f[i][j], f[i-1][j-1], 0);
            }
        }

        int res = Integer.MIN_VALUE;
        for (j = 1; j <= 2 * K + 1; j += 2) {
            res = update(res, f[n][j], 0);
        }

        return res;
    }



//------------------------------------------------------------------------------/////////

    // version 2
    class Solution2 {
        /**
         * @param k: An integer
         * @param prices: Given an integer array
         * @return: Maximum profit
         */
        public int maxProfit(int k, int[] prices) {
            // write your code here
            if (k == 0) {
                return 0;
            }
            if (k >= prices.length / 2) {
                int profit = 0;
                for (int i = 1; i < prices.length; i++) {
                    if (prices[i] > prices[i - 1]) {
                        profit += prices[i] - prices[i - 1];
                    }
                }
                return profit;
            }
            int n = prices.length;

            // mustSell[i][j] 表示前i天，至多进行j次交易，第i天必须sell的最大获益
            int[][] mustsell = new int[n + 1][n + 1];

            // globalbest[i][j] 表示前i天，至多进行j次交易，第i天可以不sell的最大获益
            int[][] globalbest = new int[n + 1][n + 1];

            mustsell[0][0] = globalbest[0][0] = 0;
            for (int i = 1; i <= k; i++) {
                mustsell[0][i] = globalbest[0][i] = 0;
            }

            for (int i = 1; i < n; i++) {
                int gainorlose = prices[i] - prices[i - 1];
                mustsell[i][0] = 0;
                for (int j = 1; j <= k; j++) {
                    mustsell[i][j] = Math.max(globalbest[(i - 1)][j - 1] + gainorlose,
                            mustsell[(i - 1)][j] + gainorlose);
                    globalbest[i][j] = Math.max(globalbest[(i - 1)][j], mustsell[i ][j]);
                }
            }
            return globalbest[(n - 1)][k];
        }
    };

//------------------------------------------------------------------------------/////////

    // 方法二
    class Solution3 {

        private int update(int a, int b, int delta) {
            if (b == Integer.MIN_VALUE) {
                return a;
            }

            if (b + delta > a) {
                return b + delta;
            }

            return a;
        }
        /**
         * @param K: An integer
         * @param prices: Given an integer array
         * @return: Maximum profit
         */
        public int maxProfit(int K, int[] prices) {
            int n = prices.length;
            int i, j, k;
            if (K == 0) {
                return 0;
            }

            if (K >= n - 1) {
                j = 0;
                for (i = 1; i < n; ++i) {
                    if (prices[i] > prices[i - 1]) {
                        j += prices[i] - prices[i - 1];
                    }
                }

                return j;
            }

            int[][] f = new int[n+1][2*K+1];
            for (i = 0; i <= n; ++i) {
                for (j = 0; j <= 2*K; ++j) {
                    f[i][j] = Integer.MIN_VALUE;
                }
            }

            f[0][0] = 0;
            for (i = 1; i <= n; ++i) {
                int delta;
                if (i == 1) {
                    delta = 0;
                }
                else {
                    delta = prices[i-1] - prices[i - 2];
                }

                f[i][0] = update(f[i][0], f[i-1][0], 0);
                for (j = 1; j <= 2 * K; j += 2) {
                    if (i > 1) f[i][j] = update(f[i][j], f[i-1][j], delta);
                    if (i > 1) f[i][j] = update(f[i][j], f[i-1][j-1], delta);
                }

                for (j = 2; j <= 2 * K; j += 2) {
                    f[i][j] = update(f[i][j], f[i-1][j], 0);
                    if (i > 1) f[i][j] = update(f[i][j], f[i-1][j-1], delta);
                    if (i > 1) f[i][j] = update(f[i][j], f[i-1][j-2], delta);
                }
            }

            int res = Integer.MIN_VALUE;
            for (j = 2; j <= 2 * K; j += 2) {
                res = update(res, f[n][j], 0);
            }

            return res;
        }
    }
//------------------------------------------------------------------------------/////////
    // 9Ch J DP
    public int maxProfit5(int k, int[] prices) {
        // write your code here
        if (k == 0) {
            return 0;
        }
        if (k >= prices.length / 2) {
            int profit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }
            return profit;
        }
        int n = prices.length;
        // mustSell[i][j] 表示前i天，至多进行j次交易，第i天必须sell的最大获益
        int[][] mustsell = new int[n + 1][n + 1];
        // globalbest[i][j] 表示前i天，至多进行j次交易，第i天可以不sell的最大获益
        int[][] globalbest = new int[n + 1][n + 1];

        mustsell[0][0] = globalbest[0][0] = 0;
        for (int i = 1; i <= k; i++) {
            mustsell[0][i] = globalbest[0][i] = 0;
        }

        for (int i = 1; i < n; i++) {

            int gainorlose = prices[i] - prices[i - 1];
            mustsell[i][0] = 0;

            for (int j = 1; j <= k; j++) {

                mustsell[i][j] = Math.max(
                        globalbest[(i - 1)][j - 1] + gainorlose,
                        mustsell[(i - 1)][j] + gainorlose);

                globalbest[i][j] = Math.max(
                        globalbest[(i - 1)][j], mustsell[i ][j]);
            }
        }
        return globalbest[(n - 1)][k];
    }
//------------------------------------------------------------------------------/////////

//------------------------------------------------------------------------------/////////
}
/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

 Notice

You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Have you met this question in a real interview? Yes
Example
Given prices = [4,4,6,1,1,4,2,5], and k = 2, return 6.
 */


/*
买卖股票的最佳时机 IV

假设你有一个数组，它的第i个元素是一支给定的股票在第i天的价格。

设计一个算法来找到最大的利润。你最多可以完成 k 笔交易。

 注意事项

你不可以同时参与多笔交易(你必须在再次购买前出售掉之前的股票)

您在真实的面试中是否遇到过这个题？ Yes
样例
给定价格 = [4,4,6,1,1,4,2,5], 且 k = 2, 返回 6.

挑战
O(nk) 时间序列。

标签
动态规划
 */