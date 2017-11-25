package _06_BFS._BFS_ALL;

import java.util.Arrays;

//  Add to List
//  279. Perfect Squares
public class _279_BFS_Perfect_Squares_M {

    /*      https://leetcode.com/problems/perfect-squares/discuss/

    An easy understanding DP solution in Java
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
     */
    public int numSquares(int n) {
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

//------------------------------------------------------------------------------

    // 9Ch

    // version 0 DP
    public class Jiuzhang1 {
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
    }

    // version 1 DP
    public class Jiuzhang2 {
        /**
         * @param n a positive integer
         * @return an integer
         */
        public int numSquares(int n) {
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
    }

    // version 2  Math
    public class Jiuzhang3 {
        /**
         * @param n a positive integer
         * @return an integer
         */
        public int numSquares(int n) {
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
    }

//------------------------------------------------------------------------------


}
/*
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 */

/*
给一个正整数 n, 找到若干个完全平方数(比如1, 4, 9, ... )使得他们的和等于 n。你需要让平方数的个数最少。

您在真实的面试中是否遇到过这个题？ Yes
样例
给出 n = 12, 返回 3 因为 12 = 4 + 4 + 4。
给出 n = 13, 返回 2 因为 13 = 4 + 9。


 */