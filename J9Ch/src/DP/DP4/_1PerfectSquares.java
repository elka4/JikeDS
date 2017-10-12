package DP.DP4;

import java.util.Arrays;

//• 划分型动态规划

/*



-----------------------------------------------------------------------------------------------
LintCode 513 Perfect Squares


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------
• 设f[i]表示i最少被分成几个完全平方数之和

f[i] = min 1<=j*j<=i {f[i-j*j] + 1}
f[i]        i最少被分成几个完全平方数之和
1<=j*j<=i 最后一个完全平方数是j*j
f[i-j*j]   i-j*j 最少被分成几个完全平方数之和

• 设f[i]表示i最少被分成几个完全平方数之和
• f[i] = min1<=j*j<=i{f[i-j2] + 1}
• 初始条件:0被分成0个完全平方数之和 – f[0] = 0


• 初始化f[0]
• 计算f[1], ..., f[N]
• 答案是f[N]



-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

 */
//Perfect Squares
public class _1PerfectSquares {

    // 9Ch DP class
    public int numSquares4(int n){
        int[] f = new int[n + 1];
        f[0] = 0;
//        int i, j;
        for (int i = 0; i <= n; i++) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 0; j * j <= i; j++) {
//                if(f[i - j * j] != Integer.MAX_VALUE && f[i - j * j] + 1 < f[i]){
                if(f[i - j * j] + 1 < f[i]){
                    f[i] = f[i - j * j] + 1;
                }

            }
        }
        return f[n];
    }

/////////////////////////////////////////////////////////////////////////

    // version 0 DP
    /**
     * @param n a positive integer
     * @return an integer
     */
    public int numSquares(int n) {
        // Write your code here
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for(int i = 0; i * i <= n; ++i) {
            dp[i * i] = 1;
        }

        for (int i = 0; i <= n; ++i) {
            for (int j = 1; j * j <= i; ++j) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }


/////////////////////////////////////////////////////////////////////////

    // version 1 DP
    /**
     * @param n a positive integer
     * @return an integer
     */
    public int numSquares2(int n) {
        // Write your code here
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for(int i = 0; i * i <= n; ++i)
            dp[i * i] = 1;

        for (int i = 0; i <= n; ++i)
            for (int j = 0; i + j * j <= n; ++j)
                dp[i + j * j] = Math.min(dp[i] + 1, dp[i + j * j]);

        return dp[n];
    }

/////////////////////////////////////////////////////////////////////////

    // version 2  Math
    /**
     * @param n a positive integer
     * @return an integer
     */
    public int numSquares3(int n) {
        // Write your code here
        while (n % 4 == 0)
            n /= 4;
        if (n % 8 == 7)
            return 4;
        for (int i = 0; i * i <= n; ++i) {
            int j = (int)Math.sqrt(n * 1.0 - i * i);
            if (i * i + j * j == n) {
                int res = 0;
                if (i > 0)
                    res += 1;
                if (j > 0)
                    res += 1;
                return res;
            }
        }
        return 3;
    }
/////////////////////////////////////////////////////////////////////////


}
/*
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Have you met this question in a real interview? Yes
Example
Given n = 12, return 3 because 12 = 4 + 4 + 4
Given n = 13, return 2 because 13 = 4 + 9
 */
