package DP;
import org.junit.Test;

public class DP_Stock {
//-------------------------------------------------------------------------------
    //  121. Best Time to Buy and Sell Stock
    //  https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
    //  http://lintcode.com/zh-cn/problem/best-time-to-buy-and-sell-stock/
    // 一次交易

/*
每次用i update min，然后用新的min和i去update profit

-----------------------------------------------------------------------------------------------
LintCode 149 Best Time to Buy and Sell Stock

• 题意:
• 已知后面N天一支股票的每天的价格P0, P1, ..., PN-1
• 可以最多买一股卖一股
• 求最大利润
• 例子:
• 输入:[3, 2, 3, 1, 2]
• 输出:1 (2买入，3卖出)

-----------------------------------------------------------------------------------------------
题目分析

• 保底策略:什么都不做，利润0
• 低买高卖，先买后卖
• 如果买卖一股，一定是第i天买，第j天卖 (j > i)，获利是Pj – Pi
• 枚举j，即第几天卖
• 显然，希望找到最小的买入价格Pi (i < j)

-----------------------------------------------------------------------------------------------
动态规划解法

• 从0到N-1枚举j，即第几天卖
• 时刻保存当前为止(即0~j-1天)的最低价格Pi
• 最大的Pj - Pi即为答案
-----------------------------------------------------------------------------------------------
*/
    //Approach #2 (One Pass) [Accepted]
    public int maxProfitI01(int prices[]) {
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
    //mine
    public int maxProfitI02(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int profit = 0;

        for (int price : prices) {
            min = Math.min(min, price);
            profit = Math.max(profit, price - min);
        }

        return profit;
    }
//-------------------------------------------------------------------------------
    //  122. Best Time to Buy and Sell Stock II
//  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
//  http://lintcode.com/zh-cn/problem/best-time-to-buy-and-sell-stock-ii/
/*

-----------------------------------------------

------------------------------------------------
LintCode 150 Best Time to Buy and Sell Stock II

• 题意:
• 已知后面N天一支股票的每天的价格P0, P1, ..., PN-1
• 可以买卖一股任意多次，但任意时刻手中最多持有一股 • 求最大利润
• 例子:
• 输入:[2, 1, 2, 0, 1]
• 输出:2 (1买入，2卖出，0买入，1卖出)
-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

 */
    // jiuzhang
    public int maxProfit1(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i+1] - prices[i];
                /*if (diff > 0) {
                    profit += diff;
                }*/
            profit += diff > 0 ? diff : 0;
        }
        return profit;
    }

//-------------------------------------------------------------------------------
//  123. Best Time to Buy and Sell Stock III
//  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/
//  http://lintcode.com/zh-cn/problem/best-time-to-buy-and-sell-stock-iii/

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
    //2ms Java DP Solution
    public int maxProfitIII_02(int[] prices) {
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
    public int maxProfitIII_1(int[] prices) {
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



    // version 2
    public int maxProfitIII_2(int[] prices) {
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
        System.out.println(maxProfitIII_2(prices));
    }

//-------------------------------------------------------------------------------

//  188. Best Time to Buy and Sell Stock IV
//  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/
//  http://lintcode.com/zh-cn/problem/best-time-to-buy-and-sell-stock-iv/

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
    // 动态规划专题班版本 Version 1
    private int update4(int a, int b, int delta) {
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
                f[i][j] = update4(f[i][j], f[i-1][j], 0);
                if (j > 1 && i > 1) f[i][j] =
                        update4(f[i][j], f[i - 1][j - 1], prices[i - 1] - prices[i - 2]);
            }

            for (j = 2; j <= 2 * K; j += 2) {
                if (i > 1) f[i][j] =
                        update4(f[i][j], f[i-1][j], prices[i - 1] - prices[i - 2]);

                if (j > 1) f[i][j] = update4(f[i][j], f[i-1][j-1], 0);
            }
        }

        int res = Integer.MIN_VALUE;
        for (j = 1; j <= 2 * K + 1; j += 2) {
            res = update4(res, f[n][j], 0);
        }

        return res;
    }


//-------------------------------------------------------------------------------



//-------------------------------------------------------------------------------




}
