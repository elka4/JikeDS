package DP;

import org.junit.Test;

public class DP_CoinsInALine {
//-------------------------------------------------------------------------//////////
/*
    有 n 个硬币排成一条线。两个参赛者轮流从右边依次拿走 1 或 2 个硬币，直到没有硬币为止。
    拿到最后一枚硬币的人获胜。请判定第一个玩家 是输还是赢？
*/


    //• 区间型动态规划
    // 9Ch DP
    public boolean firstWillWin(int n) {
        if (n == 0)return false;
        if (n <= 2) return true;

        boolean[] f = new boolean[n + 1];
        f[0] = false;
        f[1] = f[2] = true;

        for (int i = 3; i <= n ; i++) {
            /*
            f[i - 1] == false 对手上一步拿1个石头必败
            f[i - 2] == false 对手上一步拿2个石头必败
            以上情况下我都必胜
             */
            f[i] = !f[i - 1] || !f[i - 2];
        }
        return f[n];
    }

    @Test
    public void testI01(){
        for (int i = 1; i <= 5 ; i++) {
            System.out.println(firstWillWin(i));
        }
    }
    /*
        n = 1, 返回 true.
        n = 2, 返回 true.
        n = 3, 返回 false.
        n = 4, 返回 true.
        n = 5, 返回 true.
    */


//-------------------------------------------------------------------------//////////
/*
    有 n 个不同价值的硬币排成一条线。两个参赛者轮流从左边依次拿走 1 或 2 个硬币，直到没有硬币为止。
    计算两个人分别拿到的硬币总价值，价值高的人获胜。请判定第一个玩家是输还是赢？

    给定数组 A = [1,2,2], 返回 true.
    给定数组 A = [1,2,4], 返回 false.
 */

/*  https://aaronice.gitbooks.io/lintcode/content/dynamic_programming/coins_in_a_line_ii.html
Analysis
动态规划4要素

State:
dp[i] 现在还剩i个硬币，现在当前取硬币的人最后最多取硬币价值

Function:
i 是所有硬币数目
sum[i] 是后i个硬币的总和
dp[i] = sum[i]-min(dp[i-1], dp[i-2])

Intialize:
dp[0] = 0
dp[1] = coin[n-1]
dp[2] = coin[n-2] + coin[n-1]

Answer:
dp[n]
可以画一个树形图来解释：
                  [5, 1, 2, 10] dp[4]
        (take 5) /             \ (take 5, 1)
                /               \
        [1, 2, 10] dp[3]         [2, 10] dp[2]
(take 1) /     \ (take 1, 2)  (take 2) / \ (take 2, 10)
        /       \                     /   \
  [2, 10] dp[2]  [10] dp[1]     [10] dp[1] [] dp[0]

也就是说，每次的剩余硬币价值最多值dp[i]，是当前所有剩余i个硬币价值之和sum[i]，减去下一手时对手所能拿到最多的硬币的价值，即 dp[i] = sum[i] - min(dp[i - 1], dp[i - 2])

虽然算法上i是从小到大，但是思维上硬币是从最后一个都不剩，到剩一个，到最开始一个都还没取
 */


    public boolean firstWillWinII(int[] values) {
        int n = values.length;

        //sum[i]是后i个硬币的总和，当前所有剩余i个硬币价值之和sum[i]
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; ++i)//是从右向左的presum
            sum[i] = sum[i - 1] + values[n - i];

        //dp[i] 现在还剩i个硬币，现在当前取硬币的人最后最多取硬币价值
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = values[n - 1];

        for (int i = 2; i <= n; ++i) {
            dp[i] = sum[i] - Math.min(dp[i - 1], dp[i - 2]);
        }
        return dp[n]  > sum[n] / 2;
    }

    @Test
    public void testII01(){
        int[] A = {1,2,4};
        System.out.println(firstWillWinII(A));
    }
    /*  1, 2, 4

        i=1 sum[1] : sum[0] + values[2] = 4
        i=2 sum[2] : sum[1] + values[1] = 4 + 2 = 6
        i=3 sum[3] : sum[2] + values[0] = 6 + 1 = 7
     */

    /*
    dp[1]: 4
    dp[2]: 6
    dp[3]: 3
    false
     */

//-------------------------------------------------------------------------//////////
//• 区间型动态规划
/*
    有 n 个硬币排成一条线，每一枚硬币有不同的价值。两个参赛者轮流从任意一边取一枚硬币，知道没有硬币为止。
    计算拿到的硬币总价值，价值最高的获胜。请判定第一个玩家是输还是赢？
 */

/*
    • 设f[i][j]为一方在面对a[i..j]这些数字时，能得到的最大的与对手的数字差

    f[i][j] = max{a[i] - f[i+1][j], a[j] - f[i][j-1]}
    f[i][j]:            为一方在面对a[i..j]时，能得到的最大的与对手的数字差
    a[i] - f[i+1][j]:   选择a[i]，对手采取最优策略时自己能得到的最大的 与对手的数字差
    a[j] - f[i][j-1]:   选择a[j]，对手采取最优策略时自己能得到的最 大的与对手的数字差

    • 设f[i][j]为一方在面对a[i..j]这些数字时，能得到的最大的与对手的数字差
    • f[i][j] = max{a[i]-f[i+1][j], a[j]-f[i][j-1]}
    • 只有一个数字a[i]时，己方得a[i]分，对手0分，数字差为a[i] – f[i][i] = a[i] (i=0, 1, ..., N-1)
*/

    // 9CH DP
    public boolean firstWillWinIII1 (int[] A) {
        int n = A.length;
        if (n == 0) {
            return true;
        }
        // 设f[i][j]为一方在面对a[i..j]这些数字时，能得到的最大的与对手的数字差
        int[][] f = new int[n][n];

        for (int i = 0; i < n; i++) {
            f[i][i] = A[i];
        }
        int j;

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {//左端点坐标
                j = i + len - 1;//右端点坐标
                f[i][j] = Math.max(A[i] - f[i + 1][j], A[j] - f[i][j - 1]);
            }
        }
        return f[0][n - 1] >= 0;
    }

    @Test
    public void testIII_01() {
        System.out.println(firstWillWinIII1(new int[]{3,2,2}));
        System.out.println(firstWillWinIII1(new int[]{1,2,4}));
        System.out.println(firstWillWinIII1(new int[]{1,20,4}));
    }
    /*
    true
    true
    false
     */


    // Linpz verision
    public boolean firstWillWiIII2(int[] values) {
        // write your code here

        int n = values.length;
        int[] sum = new int[n + 1];
        sum[0] = 0;
        for (int i = 1; i <= n; ++i)
            sum[i] = sum[i - 1] + values[i - 1];

        // s[i][j] = sum[j + 1] -  sum[i];

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i)
            dp[i][i] = values[i];

        for (int len = 2; len <= n; ++len) {
            for (int i = 0; i < n; ++i) {
                int j = i + len - 1;
                if (j >= n)
                    continue;
                int s = sum[j + 1] - sum[i];
                dp[i][j] = Math.max(s - dp[i + 1][j], s - dp[i][j - 1]);
            }
        }

        return dp[0][n - 1]  > sum[n] / 2;
    }

    @Test
    public void testIII_02() {
        System.out.println(firstWillWiIII2(new int[]{3,2,2}));
        System.out.println(firstWillWiIII2(new int[]{1,2,4}));
        System.out.println(firstWillWiIII2(new int[]{1,20,4}));
    }
//-------------------------------------------------------------------------//////////




}
