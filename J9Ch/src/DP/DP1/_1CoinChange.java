package DP.DP1;

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
        int M = 11;
        System.out.println(coinChange(A,M));
    }

    @Test
    public void test02(){
        int[] A = {1, 2, 5};
        int M = 11;
        System.out.println(coinChange(A,M));
    }

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