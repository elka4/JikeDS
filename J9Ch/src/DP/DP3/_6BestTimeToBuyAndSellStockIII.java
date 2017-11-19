package DP.DP3;
import org.junit.Test;

//_6BestTimeToBuyAndSellStockIII  31:38
//• 有状态的序列型动态规划



/*

-----------------------------------------------------------------------------------------------
LintCode 151: Best Time to Buy and Sell Stock III
• 题意:
• 给定一支股票N天的价格
• 可以进行最多两次买+两次卖，每次买卖都是一股
• 不能在卖光手中股票前买入，但可以同一天卖完后买入 • 问最大收益
• 例子:
• 输入:[4,4,6,1,1,4,2,5]
• 输出:6 (4买入，6卖出，1买入，5卖出，收益为(6-4) + (5-1) = 6)

-----------------------------------------------------------------------------------------------
题目分析

• 题目大意和I, II基本相似
• 只能最多两次买卖
• 所以需要记录已经买卖了多少次
-----------------------------------------------------------------------------------------------
动态规划组成部分一:确定状态

• 最后一步:最优策略中，最后一次卖发生在第j天
• 枚举最后一次买发生在第几天
• 但是不知道之前有没有买卖过
-----------------------------------------------------------------------------------------------
记录阶段

• 阶段可以保持:即不进行买卖操作
• 阶段可以变化:买或卖
    – 在阶段2，卖了一股后，进入阶段3
    – 在阶段2，卖了一股后当天买一股，进入阶段4
-----------------------------------------------------------------------------------------------
动态规划组成部分一:确定状态

• 最优策略一定是前N天(第N-1天)结束后，处于 – 阶段1:没买卖过
– 阶段3:买卖过一次
– 阶段5:买卖过两次

• 例如，如果要求前N天(第N-1天)结束后，在阶段5的最大获利，设为f[N][5]
– 情况1:第N-2天就在阶段5 --- f[N-1][5]
– 情况2:第N-2天还在阶段4(第二次持有股票)，第N-1天卖掉
  • f[N-1][4] + (PN-1-PN-2)
思考:为什么是f[N-1][4]+(PN-1-PN-2)

• 例如，如果要求前N天(第N-1天)结束后，在阶段4的最大获利，设为f[N][4]
– 情况1:第N-2天就在阶段4 --- f[N-1][4] + (PN-1-PN-2)
– 情况2:第N-2天还在阶段3 --- f[N-1][3]
– 情况3:第N-2天还在阶段2，第N-1天卖完了立即买 --- f[N-1][2] + (PN-1-PN-2)
-----------------------------------------------------------------------------------------------
子问题
• 要求f[N][1], ..., f[N][5]
• 需要知道f[N-1][1], ..., f[N-1][5]
• 子问题
• 状态:f[i][j]表示前i天(第i-1天)结束后，在阶段j的最大获利
-----------------------------------------------------------------------------------------------
动态规划组成部分二:转移方程

• f[i][j]:前i天(第i-1天)结束后，处在阶段j,最大获利

阶段1, 3, 5 --- 手中无股票状态:
f[i][j] = max{f[i-1][j], f[i-1][j-1] + Pi-1 – Pi-2}

f[i-1][j]:                 昨天没有持有股票
f[i-1][j-1] + Pi-1 – Pi-2: 昨天持有股票，今天卖出清仓



阶段2, 4 --- 手中有股票状态:
f[i][j] = max{f[i-1][j] + Pi-1 – Pi-2, f[i-1][j-1], f[i-1][j-2] + Pi-1 – Pi-2}

f[i-1][j] + Pi-1 – Pi-2:   昨天就持有股票，继续持有并获利
f[i-1][j-1]:               昨天没有持有股票，今天买入
f[i-1][j-2] + Pi-1 – Pi-2: 昨天持有上一次买的股票， 今天卖出并立即买入




-----------------------------------------------------------------------------------------------
动态规划组成部分三:初始条件和边界情况

• 刚开始(前0天)处于阶段1 – f[0][1] = 0
– f[0][2] = f[0][3] = f[0][4] = f[0][5] = -∞
• 阶段1, 3, 5: f[i][j] = max{f[i-1][j], f[i-1][j-1] + Pi-1 – Pi-2}
• 阶段2, 4: f[i][j] = max{f[i-1][j] + Pi-1 – Pi-2, f[i-1][j-1], f[i-1][j-2] + Pi-1 – Pi-2}
• 如果j – 1 < 1或j – 2 < 1或i – 2 < 0, 对应项不计入max
• 因为最多买卖两次，所以答案是max{f[N][1], f[N][3], f[N][5]}, 即清仓状态 下最后一天最大获利
-----------------------------------------------------------------------------------------------
动态规划组成部分四:计算顺序
• 初始化f[0][1], ..., f[0][5] • f[1][1], ..., f[1][5]
•...
• f[N][1], ..., f[N][5]
• 时间复杂度:O(N),空间复杂度:O(N)，优化后可以O(1)，因为f[i][1..5] 只依赖于f[i-1][1..5]

-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------
 */


// 2次交易


//  123. Best Time to Buy and Sell Stock III
//  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/
//  http://lintcode.com/zh-cn/problem/best-time-to-buy-and-sell-stock-iii/
public class _6BestTimeToBuyAndSellStockIII {

    //https://discuss.leetcode.com/topic/107998/most-consistent-ways-of-dealing-with-the-series-of-stock-problems

////////////////////////////////////////////////////////////////////////////

    /*
    Is it Best Solution with O(n), O(1).

The thinking is simple and is inspired by the best solution from Single Number II (I read through the discussion after I use DP).
Assume we only have 0 money at first;
4 Variables to maintain some interested 'ceilings' so far:
The maximum of if we've just buy 1st stock, if we've just sold 1nd stock, if we've just buy 2nd stock, if we've just sold 2nd stock.
Very simple code too and work well. I have to say the logic is simple than those in Single Number II.

     */

    public int maxProfit01(int[] prices) {
        int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
        int release1 = 0, release2 = 0;

        for(int price:prices){                              // Assume we only have 0 money at first

            release2 = Math.max(release2, hold2 + price);     // The maximum if we've just sold 2nd stock so far.

            hold2    = Math.max(hold2,    release1 - price);  // The maximum if we've just buy  2nd stock so far.

            release1 = Math.max(release1, hold1 + price);     // The maximum if we've just sold 1nd stock so far.

            hold1    = Math.max(hold1,    -price);          // The maximum if we've just buy  1st stock so far.
        }

        return release2; ///Since release1 is initiated as 0, so release2 will always higher than release1.
    }


    //这方法真好啊
    //初始值很重要
    public int maxProfit011(int[] prices) {
        int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
        int release1 = 0, release2 = 0;

        for(int price:prices){                              // Assume we only have 0 money at first

            hold1    = Math.max(hold1,    -price);            // The maximum if we've just buy  1st stock so far.

            release1 = Math.max(release1, hold1 + price);     // The maximum if we've just sold 1nd stock so far.

            hold2    = Math.max(hold2,    release1 - price);  // The maximum if we've just buy  2nd stock so far.

            release2 = Math.max(release2, hold2 + price);     // The maximum if we've just sold 2nd stock so far.
        }

        return release2; ///Since release1 is initiated as 0, so release2 will always higher than release1.
    }

////////////////////////////////////////////////////////////////////////////


    public int maxProfit0111(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int len = prices.length;
        //k >= len / 2就是对每个price都可以买一次或者卖一次
        int k = 2;
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
////////////////////////////////////////////////////////////////////////////

    //2ms Java DP Solution
    public int maxProfit02(int[] prices) {
        // these four variables represent your profit after executing corresponding transaction
        // in the beginning, your profit is 0.
        // when you buy a stock ,the profit will be deducted of the price of stock.
        int firstBuy = Integer.MIN_VALUE, firstSell = 0;
        int secondBuy = Integer.MIN_VALUE, secondSell = 0;

        for (int curPrice : prices) {

            // the max profit after you buy first stock
            //一开始firstbuy是MIN，这个肯定满足，意义就是买了股票盈利为负值
            //写了0就好理解了
            if (firstBuy < 0 - curPrice) firstBuy = 0 - curPrice;

            // the max profit after you sell it
            //firstBuy + curPrice 为在curPrice时卖了股票的盈利，firstSell一开始为零
            //就是说如果卖了股票可以盈利，那么就卖了，把盈利赋值给firstSell
            if (firstSell < firstBuy + curPrice) firstSell = firstBuy + curPrice;

            // the max profit after you buy the second stock
            //第二次购买股票的盈利，也就是第一次的盈利firstSell 减去当前股价curPrice，
            // 如果这个盈利比secondBuy大，就把这个值给secondBuy
            if (secondBuy < firstSell - curPrice) secondBuy = firstSell - curPrice;

            // the max profit after you sell the second stock
            if (secondSell < secondBuy + curPrice) secondSell = secondBuy + curPrice;
        }

        return secondSell; // secondSell will be the max profit after passing the prices
    }

////////////////////////////////////////////////////////////////////////////

    public int maxProfit022(int[] prices) {
        // these four variables represent your profit after executing corresponding transaction
        // in the beginning, your profit is 0.
        // when you buy a stock ,the profit will be deducted of the price of stock.
        int firstBuy = Integer.MIN_VALUE, firstSell = 0;
        int secondBuy = Integer.MIN_VALUE, secondSell = 0;


        // (-firstBuy) is min value beteen [0, curPrice.index], firstBuy itself is a negative value

        // (firstSell) is max profit between [0, current.index], before update it is max profit between [0, current.index-1], after update is max(firstSell.before, curPrice + firstBuy(e.g. - minValue[0, curPrice.index]))

        // (secondBuy) is max profit between [0,curPrice.index] under seen prices if you hold/buy a stock between[0, curPrice.index] and haven't sell it yet.

        // (secondSell) is max profit between [0,curPrice.index] under seen prices if you buy a second stock between [0,curPrice.index];

        for (int curPrice : prices) {
            if (firstBuy < -curPrice) firstBuy = -curPrice; // the max profit after you buy first stock
            if (firstSell < firstBuy + curPrice) firstSell = firstBuy + curPrice; // the max profit after you sell it
            if (secondBuy < firstSell - curPrice) secondBuy = firstSell - curPrice; // the max profit after you buy the second stock
            if (secondSell < secondBuy + curPrice) secondSell = secondBuy + curPrice; // the max profit after you sell the second stock
        }

        return secondSell; // secondSell will be the max profit after passing the prices
    }

////////////////////////////////////////////////////////////////////////////

    // 9Ch DP
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }

        int[][] f = new int[n + 1][5 + 1];
        int i, j, k;
        //init
        //first 0 days, phase 1, max profit = 0
        f[0][1] = 0;
        f[0][2] = f[0][3] = f[0][4] = f[0][5] = Integer.MIN_VALUE;

        for (i = 1; i <= n; i++) {
            // 1, 3, 5
            for (j = 1; j <= 5 ; j += 2) {
                f[i][j] = f[i - 1][j];
                // 判断j i，为了之后f[i - 1][j - 1] index 不越界
                if (j > 1 && i > 1 && f[i - 1][j - 1] != Integer.MIN_VALUE) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + prices[i - 1] - prices[i - 2]);
                }
            }

            // 2, 4
            for (j = 2; j <= 5; j += 2) {
                //max
                f[i][j] = f[i - 1][j - 1];
                if (i > 1 && f[i - 1][j] != Integer.MIN_VALUE) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j] + prices[i - 1] - prices[i - 2]);
                }

                if (j > 2 && i > 1 && f[i - 1][j - 2] != Integer.MIN_VALUE) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 2] + prices[i - 1] - prices[i - 2]);
                }
            }
        }
        return Math.max(Math.max(f[n][1], f[n][3]), f[n][5]);

    }

//-------------------------------------------------------------------------/////////////
    // 动态规划专题班版本 verison 1
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
    public int maxProfit1(int[] prices) {
        int K = 2;
        int n = prices.length;
        int i, j, k;

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
                if (j > 1 && i > 1)
                    f[i][j] =
                        update(f[i][j], f[i - 1][j - 1], prices[i - 1] - prices[i - 2]);
            }

            for (j = 2; j <= 2 * K; j += 2) {
                if (i > 1) f[i][j] =
                        update(f[i][j], f[i-1][j], prices[i - 1] - prices[i - 2]);

                if (j > 1) f[i][j] =
                        update(f[i][j], f[i-1][j-1], 0);
            }
        }

        int res = Integer.MIN_VALUE;
        for (j = 1; j <= 2 * K + 1; j += 2) {
            res = update(res, f[n][j], 0);
        }

        return res;
    }

/////////////////////////////////////////////////////////////////////


    // version 2
    public int maxProfit2(int[] prices) {
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
            System.out.println("min: " + min);
            left[i] = Math.max(left[i - 1], prices[i] - min);
            System.out.println("left[i]: " + left[i]);

        }

        //DP from right to left;
        right[prices.length - 1] = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(prices[i], max);
            System.out.println("max: " + max);
            right[i] = Math.max(right[i + 1], max - prices[i]);
            System.out.println("right[i]: " + right[i]);

        }

        int profit = 0;
        for (int i = 0; i < prices.length; i++){
            profit = Math.max(left[i] + right[i], profit);
        }

        return profit;
    }

    @Test
    public void test03() {
        //给出一个样例数组 [4,4,6,1,1,4,2,5], 返回 6
        int[] prices = {4,4,6,1,1,4,2,5};
        System.out.println(maxProfit2(prices));
    }

/////////////////////////////////////////////////////////////////////

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

/////////////////////////////////////////////////////////////////////

}
/*
买卖股票的最佳时机 III

假设你有一个数组，它的第i个元素是一支给定的股票在第i天的价格。设计一个算法来找到最大的利润。你最多可以完成两笔交易。

 注意事项

你不可以同时参与多笔交易(你必须在再次购买前出售掉之前的股票)

您在真实的面试中是否遇到过这个题？ Yes
样例
给出一个样例数组 [4,4,6,1,1,4,2,5], 返回 6

标签
枚举法 前后遍历 数组
 */

/* Leetcode
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */