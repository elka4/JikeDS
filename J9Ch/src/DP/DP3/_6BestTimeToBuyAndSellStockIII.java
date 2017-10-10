package DP.DP3;

//_6BestTimeToBuyAndSellStockIII  31:38
//• 有状态的序列型动态规划

// 2次交易

/*
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


 */



//Best Time To Buy And Sell Stock III
public class _6BestTimeToBuyAndSellStockIII {
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

////////////////////////////////////////////////////////////////////////////////////////////////
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
                if (j > 1 && i > 1) f[i][j] = update(f[i][j], f[i - 1][j - 1], prices[i - 1] - prices[i - 2]);
            }

            for (j = 2; j <= 2 * K; j += 2) {
                if (i > 1) f[i][j] = update(f[i][j], f[i-1][j], prices[i - 1] - prices[i - 2]);
                if (j > 1) f[i][j] = update(f[i][j], f[i-1][j-1], 0);
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
    public class Solution2 {
        public int maxProfit(int[] prices) {
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
                left[i] = Math.max(left[i - 1], prices[i] - min);
            }

            //DP from right to left;
            right[prices.length - 1] = 0;
            int max = prices[prices.length - 1];
            for (int i = prices.length - 2; i >= 0; i--) {
                max = Math.max(prices[i], max);
                right[i] = Math.max(right[i + 1], max - prices[i]);
            }

            int profit = 0;
            for (int i = 0; i < prices.length; i++){
                profit = Math.max(left[i] + right[i], profit);
            }

            return profit;
        }
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
假设你有一个数组，它的第i个元素是一支给定的股票在第i天的价格。设计一个算法来找到最大的利润。你最多可以完成两笔交易。

 注意事项

你不可以同时参与多笔交易(你必须在再次购买前出售掉之前的股票)

您在真实的面试中是否遇到过这个题？ Yes
样例
给出一个样例数组 [4,4,6,1,1,4,2,5], 返回 6
 */