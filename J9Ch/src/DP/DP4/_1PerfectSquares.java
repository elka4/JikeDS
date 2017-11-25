package DP.DP4;

import org.junit.Test;

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
• f[i] = min1<=j*j<=i{f[i-j^2] + 1}
• 初始条件:0被分成0个完全平方数之和 – f[0] = 0


• 初始化f[0]
• 计算f[1], ..., f[N]
• 答案是f[N]



-----------------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------
 */



//  279. Perfect Squares
//  https://leetcode.com/problems/perfect-squares/description/
//  http://www.lintcode.com/zh-cn/problem/perfect-squares/
public class _1PerfectSquares {
/*  An easy understanding DP solution in Java

    dp[n] indicates that the perfect squares count of the given n, and we have:

    dp[0] = 0
    dp[1] = dp[0]+1 = 1
    dp[2] = dp[1]+1 = 2
    dp[3] = dp[2]+1 = 3
    dp[4] = Min{ dp[4-1*1]+1, dp[4-2*2]+1 }
      = Min{ dp[3]+1, dp[0]+1 }
      = 1
    dp[5] = Min{ dp[5-1*1]+1, dp[5-2*2]+1 }
      = Min{ dp[4]+1, dp[1]+1 }
      = 2
              .
              .
              .
    dp[13] = Min{ dp[13-1*1]+1, dp[13-2*2]+1, dp[13-3*3]+1 }
       = Min{ dp[12]+1, dp[9]+1, dp[4]+1 }
       = 2
               .
               .
               .
    dp[n] = Min{ dp[n - i*i] + 1 },  n - i*i >=0 && i >= 1
    and the sample code is like below:
*/

    public int numSquares01(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i <= n; ++i) {
            int min = Integer.MAX_VALUE;
            int j = 1;
            while(i - j*j >= 0) {
                min = Math.min(min, dp[i - j*j] + 1);
                ++j;
            }
            dp[i] = min;
        }
        return dp[n];
    }

//-----------------------------------------------------------------------------//
    //这可能是最好的一个， 从下面复制上来的
    public int numSquares444(int n){
        int[] f = new int[n + 1];
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            f[i] = Integer.MAX_VALUE;//初始化成i就可以
            for (int j = 1; j * j <= i; j++) {
                f[i] = Math.min(f[i], f[i - j * j] + 1);

            }
        }
        return f[n];
    }

    /* Simple Java DP Solution
    Just regular DP. Time Complexity: n * sqrt(n) Space: O(n)*/
    //这个没有上下的方法简介
    public int numSquares02(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0; i <= n; i++){
            for(int j = 1; i + j * j <= n; j++){
                dp[i  + j * j] = Math.min(dp[i + j * j], dp[i] + 1);
            }
        }
        return dp[n];
    }

//-----------------------------------------------------------------------------//
    //    Beautiful 8 Lines Java Solution
    public int numSquares04(int n) {
        int[] record = new int[n + 1];
        for(int i = 0; i <= n; i++){
            record[i] = i;
            for(int j = 1; j * j <=i ; j++){
                record[i] = Math.min(record[i - j * j] + 1,record[i]);
            }
        }
        return record[n];
    }

//-----------------------------------------------------------------------------//
        //Java DP Solution with explanation
        public int numSquares03(int n) {
            int[] dp = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                dp[i] = Integer.MAX_VALUE;
            }

            for (int i = 1; i <= n; i++) {
                int sqrt = (int)Math.sqrt(i);

                // If the number is already a perfect square,
                // then dp[number] can be 1 directly. This is
                // just a optimization for this DP solution.
                if (sqrt * sqrt == i) {
                    dp[i] = 1;
                    continue;
                }

                // To get the value of dp[n], we should choose the min
                // value from:
                //     dp[n - 1] + 1,
                //     dp[n - 4] + 1,
                //     dp[n - 9] + 1,
                //     dp[n - 16] + 1
                //     and so on...
                for (int j = 1; j <= sqrt; j++) {
                    int dif = i - j * j;
                    dp[i] = Math.min(dp[i], (dp[dif] + 1));
                }
            }

            return dp[n];
        }

//-----------------------------------------------------------------------------//

/*    Explanation of the DP solution

18
    J jim11
    Reputation:  36
    The most common solution for this problem is using DP, BFS or Number theory. Here I will give a brief explanation of the DP solution. The solution is as following:*/

    /*    First of all, we created the DP array as usual. This DP array stands for the least number of perfect square numbers for its index. For example DP[13]=2 stands for if you want to decompose 13 into some perfect square numbers, it will contains at least two terms which are 33 and 22.

        After the initialization of the DP array. We want to iterate through the array to fill all indices. During each iteration we're actually doing this: dp[i] = 1 + min (dp[i-j*j] for j*j<=i). The formula itself is a little bit hard to understand. Here's an example of how it works: (C#)

        Suppose we want to get DP[13] and we already have the previous indices filled.

        DP[13] = DP[13-1x1]+DP[1] = DP[12]+1 = 3;

        DP[13] = DP[13-2x2]+DP[2x2] = DP[9]+1 = 2;

        DP[13] = DP[13-3x3] + DP[3x3] = DP[4] + 1 = 2;

        We pick the smallest one which is 2 so DP[13] = 2. Hope it helps.*/
    public int NumSquares05(int n) {
        int[] DP = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min=  Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min= Math.min(min, DP[i - j * j] + 1);
            }
            DP[i] = min;
        }
        return DP[n];
    }

//-----------------------------------------------------------------------------//
    // 9Ch DP class
    public int numSquares4(int n){
        int[] f = new int[n + 1];
        f[0] = 0;

        for (int i = 1; i <= n; i++) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                if(f[i - j * j] + 1 < f[i]){
                    f[i] = f[i - j * j] + 1;
                }

            }
        }
        return f[n];
    }

    //这可能是最好的一个
    public int numSquares44(int n){
        int[] f = new int[n + 1];
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                f[i] = Math.min(f[i], f[i - j * j] + 1);

            }
        }
        return f[n];
    }


//------------------------------------------------------------------------------
    // 9Ch
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

    @Test
    public void testFill() {
        int[][] dp = new int[3][3];
        Arrays.fill(dp, 4);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

    }
    @Test
    public void testFill2() {
        int[] dp = new int[3];
        Arrays.fill(dp, 4);
        for (int i = 0; i < 3; i++) {
            System.out.print(dp[i] + " ");

        }

    }

//------------------------------------------------------------------------------
    // 9Ch
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

//------------------------------------------------------------------------------
    // 9Ch
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

//------------------------------------------------------------------------------
}
/*
给一个正整数 n, 找到若干个完全平方数(比如1, 4, 9, ... )使得他们的和等于 n。你需要让平方数的个数最少。

您在真实的面试中是否遇到过这个题？ Yes
样例
给出 n = 12, 返回 3 因为 12 = 4 + 4 + 4。
给出 n = 13, 返回 2 因为 13 = 4 + 9。
 */
/*
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 */

/*
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Have you met this question in a real interview? Yes
Example
Given n = 12, return 3 because 12 = 4 + 4 + 4
Given n = 13, return 2 because 13 = 4 + 9
 */

