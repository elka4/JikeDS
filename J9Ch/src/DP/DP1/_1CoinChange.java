package DP.DP1;
import java.util.*;

//求最大最小值动态规划
//• 划分性动态规划


/*
• 动态规划组成部分:

– 1. 确定状态
• 最后一步(最优策略中使用的最后一枚硬币aK)
• 化成子问题(最少的硬币拼出更小的面值27-aK)

– 2. 转移方程
• f[X] = min{f[X-2]+1, f[X-5]+1, f[X-7]+1}

– 3. 初始条件和边界情况
• f[0] = 0, 如果不能拼出Y，f[Y]=正无穷

– 4. 计算顺序
• f[0], f[1], f[2], ...
• 消除冗余，加速计算
 */

import org.junit.Test;

// f[X] = min{f[X-2]+1, f[X-5]+1, f[X-7]+1}

/*
f[X]: 拼出X所需最少 的硬币数

min{f[X-2]+1: 拼出X-2所需最少 的硬币数，加上 最后一枚硬币2

f[X-5]+1: 拼出X-5所需最少 的硬币数，加上 最后一枚硬币5

f[X-7]+1: 拼出X-7所需最少 的硬币数，加上最 后一枚硬币7
 */

// 是否是 划分型 ？？？

// 最值型


/*
• 求最值型动态规划
• 动态规划组成部分:
– 1. 确定状态
• 最后一步(最优策略中使用的最后一枚硬币aK) • 化成子问题(最少的硬币拼出更小的面值27-aK)
– 2. 转移方程
• f[X] = min{f[X-2]+1, f[X-5]+1, f[X-7]+1}
– 3. 初始条件和边界情况
• f[0] = 0, 如果不能拼出Y，f[Y]=正无穷
– 4. 计算顺序
• f[0], f[1], f[2], ...

 */

//Coin Change
public class _1CoinChange {
    public int coinChange(int[] A, int M) {
        int[] f = new int[M + 1];
        int n = A.length;

        f[0] = 0;
        int i, j;

        for (i = 1; i <= M; ++i) {
            f[i] = -1;
            for (j = 0; j < n; ++j) {
                if (i >= A[j] && f[i - A[j]] != -1) {
                    if (f[i] == -1 || f[i - A[j]] + 1 < f[i]) {
                        f[i] = f[i - A[j]] + 1;
                    }
                }
            }
        }
        return f[M];
    }

    @Test
    public void test01(){
        int[] A = {1, 2, 5};
        int M = 89;
        System.out.println(coinChange(A,M));
    }


/////////////////////////////////////////////////////////////////

    //9Ch DP video
    public int coinChangeX(int[] A, int M) {
        int n = A.length;
        int[] f = new int[M + 1]; //0 .... M
        int i, j;
        f[0] = 0;

        for (i = 1; i <= M; ++i) {
            f[i] = Integer.MAX_VALUE;
            //select last coin
            //i >= A[j] 最后一枚硬币必须 小于等于 总面值。 为了下面 i - A[j] 不等于负数
            for (j = 0; j < n; ++j) {
                if (i >= A[j] && f[i - A[j]] != Integer.MAX_VALUE && f[i - A[j]] + 1 < f[i]) {
                    f[i] = f[i - A[j]] + 1;
                }
            }
        }
        return f[M] == Integer.MAX_VALUE ? -1 : f[M];
    }

/////////////////////////////////////////////////////////////////

    //  me based on 9Ch DP video
    public int coinChangeLeet(int[] coins, int amount) {
        int n = coins.length;
        int[] f = new int[amount + 1]; //0 .... M
        int i, j;
        f[0] = 0;

        for (i = 1; i <= amount; ++i) {
            f[i] = Integer.MAX_VALUE;
            //select last coin
            for (j = 0; j < n; ++j) {
                if (i >= coins[j] && f[i - coins[j]] != Integer.MAX_VALUE && f[i - coins[j]] + 1 < f[i]) {
                    f[i] = f[i - coins[j]] + 1;
                }
            }
        }
        return f[amount] == Integer.MAX_VALUE ? -1 : f[amount];
    }
/////////////////////////////////////////////////////////////////

    int f(int X) {
        if (X == 0) return 0;
        int res = Integer.MAX_VALUE;

        if (X >= 2) {
            res = Math.min(f(X - 2) + 1, res);
        }

        if (X >= 5) {
            res = Math.min(f(X - 5) + 1, res);
        }

        if (X >= 7) {
            res = Math.min(f(X - 7) + 1, res);
        }



        return res;
    }

    @Test
    public void test02(){
        int[] A = {1, 2, 5};
        int M = 89;
        System.out.println(f(M));
    }
/////////////////////////////////////////////////////////////////
    //Approach #1 (Brute force) [Time Limit Exceeded]
    public int coinChange11(int[] coins, int amount) {
        return coinChange11(0, coins, amount);
    }

    private int coinChange11(int idxCoin, int[] coins, int amount) {
        if (amount == 0)
            return 0;
        if (idxCoin < coins.length && amount > 0) {
            int maxVal = amount/coins[idxCoin];
            int minCost = Integer.MAX_VALUE;
            for (int x = 0; x <= maxVal; x++) {
                if (amount >= x * coins[idxCoin]) {
                    int res = coinChange11(idxCoin + 1, coins, amount - x * coins[idxCoin]);
                    if (res != -1)
                        minCost = Math.min(minCost, res + x);
                }
            }
            return (minCost == Integer.MAX_VALUE)? -1: minCost;
        }
        return -1;
    }
/////////////////////////////////////////////////////////////////
    //leetcode
    //Approach #2 (Dynamic programming - Top down) [Accepted]

    public int coinChange2(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange2(coins, amount, new int[amount]);
    }

    private int coinChange2(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) return count[rem - 1];
        int min = Integer.MAX_VALUE;

        for (int coin : coins) {
            int res = coinChange2(coins, rem - coin, count);
            if (res >= 0 && res < min)
                min = 1 + res;
        }

        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }
/////////////////////////////////////////////////////////////////

    //Approach #3 (Dynamic programming - Bottom up) [Accepted]
    public int coinChange3(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
/////////////////////////////////////////////////////////////////

    //leet discussion
    //#Iterative Method:#

    public int coinChange4(int[] coins, int amount) {
        if(amount<1) return 0;
        int[] dp = new int[amount+1];
        int sum = 0;

        while(++sum<=amount) {
            int min = -1;
            for(int coin : coins) {
                if(sum >= coin && dp[sum-coin]!=-1) {
                    int temp = dp[sum-coin]+1;
                    min = min<0 ? temp : (temp < min ? temp : min);
                }
            }
            dp[sum] = min;
        }
        return dp[amount];
    }


    public int coinChange5(int[] coins, int amount) {
        if(amount<1) return 0;
        return helper(coins, amount, new int[amount]);
    }

    // rem: remaining coins after the last step;
    // count[rem]: minimum number of coins to sum up to rem
    private int helper(int[] coins, int rem, int[] count) {

        if(rem<0) return -1; // not valid
        if(rem==0) return 0; // completed
        if(count[rem-1] != 0) return count[rem-1]; // already computed, so reuse
        int min = Integer.MAX_VALUE;

        for(int coin : coins) {
            int res = helper(coins, rem-coin, count);
            if(res>=0 && res < min)
                min = 1+res;
        }
        count[rem-1] = (min==Integer.MAX_VALUE) ? -1 : min;
        return count[rem-1];
    }
/////////////////////////////////////////////////////////////////
}

/*
You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin.
 */