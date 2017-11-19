package DP_Classified._2Sequence.DP6;

//Ones and Zeroes
public class _7OnesandZeroes {
    //方法一 未进行空间复杂度优化：
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
        for (int i = 1; i <= strs.length; i++) {
            int[] cost = count(strs[i - 1]);
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (j >= cost[0] && k >= cost[1]) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - cost[0]][k - cost[1]] + 1);
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
        }
        return dp[strs.length][m][n];
    }

    public int[] count(String str) {
        int[] cost = new int[2];
        for (int i = 0; i < str.length(); i++)
            cost[str.charAt(i) - '0']++;
        return cost;
    }



//-------------------------------------------------------------------------/////////

    // 方法二 进行空间复杂度优化：
    public class Solution2 {
        public int findMaxForm(String[] strs, int m, int n) {
            int[][] dp = new int[m + 1][n + 1];
            for (String s : strs) {
                int[] cost = count(s);
                for (int i = m; i >= cost[0]; i--)
                    for (int j = n; j >= cost[1]; j--)
                        dp[i][j] = Math.max(dp[i][j], dp[i - cost[0]][j - cost[1]] + 1);
            }
            return dp[m][n];
        }

        public int[] count(String str) {
            int[] cost = new int[2];
            for (int i = 0; i < str.length(); i++)
                cost[str.charAt(i) - '0']++;
            return cost;
        }
    }

//-------------------------------------------------------------------------/////////
    //Approach #1 Brute Force [Time Limit Exceeded]
    public class Solution3 {
        public int findMaxForm(String[] strs, int m, int n) {
            int maxlen = 0;
            for (int i = 0; i < (1 << strs.length); i++) {
                int zeroes = 0, ones = 0, len = 0;
                for (int j = 0; j < strs.length; j++) {
                    if ((i & (1 << j)) != 0) {
                        int[] count = countzeroesones(strs[j]);
                        zeroes += count[0];
                        ones += count[1];
                        len++;
                    }
                }
                if (zeroes <= m && ones <= n)
                    maxlen = Math.max(maxlen, len);
            }
            return maxlen;

        }
        public int[] countzeroesones(String s) {
            int[] c = new int[2];
            for (int i = 0; i < s.length(); i++) {
                c[s.charAt(i)-'0']++;
            }
            return c;
        }
    }


//-------------------------------------------------------------------------/////////

    //Approach #2 Better Brute Force [Time Limit Exceeded]
    public class Solution4 {
        public int findMaxForm(String[] strs, int m, int n) {
            int maxlen = 0;
            for (int i = 0; i < (1 << strs.length); i++) {
                int zeroes = 0, ones = 0, len = 0;
                for (int j = 0; j < 32; j++) {
                    if ((i & (1 << j)) != 0) {
                        int[] count = countzeroesones(strs[j]);
                        zeroes += count[0];
                        ones += count[1];
                        if (zeroes > m || ones > n)
                            break;
                        len++;
                    }
                }
                if (zeroes <= m && ones <= n)
                    maxlen = Math.max(maxlen, len);
            }
            return maxlen;
        }
        public int[] countzeroesones(String s) {
            int[] c = new int[2];
            for (int i = 0; i < s.length(); i++) {
                c[s.charAt(i)-'0']++;
            }
            return c;
        }
    }



//-------------------------------------------------------------------------/////////

    //Approach #3 Using Recursion [Time Limit Exceeded]

    public class Solution5 {
        public int findMaxForm(String[] strs, int m, int n) {
            return calculate(strs, 0, m, n);
        }
        public int calculate(String[] strs, int i, int zeroes, int ones) {
            if (i == strs.length)
                return 0;
            int[] count = countzeroesones(strs[i]);
            int taken = -1;
            if (zeroes - count[0] >= 0 && ones - count[1] >= 0)
                taken = calculate(strs, i + 1, zeroes - count[0], ones - count[1]) + 1;
            int not_taken = calculate(strs, i + 1, zeroes, ones);
            return Math.max(taken, not_taken);
        }
        public int[] countzeroesones(String s) {
            int[] c = new int[2];
            for (int i = 0; i < s.length(); i++) {
                c[s.charAt(i)-'0']++;
            }
            return c;
        }
    }


//-------------------------------------------------------------------------/////////

    //Approach #4 Using Memoization [Accepted]
    public class Solution6 {
        public int findMaxForm(String[] strs, int m, int n) {
            int[][][] memo = new int[strs.length][m + 1][n + 1];
            return calculate(strs, 0, m, n, memo);
        }
        public int calculate(String[] strs, int i, int zeroes, int ones, int[][][] memo) {
            if (i == strs.length)
                return 0;
            if (memo[i][zeroes][ones] != 0)
                return memo[i][zeroes][ones];
            int[] count = countzeroesones(strs[i]);
            int taken = -1;
            if (zeroes - count[0] >= 0 && ones - count[1] >= 0)
                taken = calculate(strs, i + 1, zeroes - count[0], ones - count[1], memo) + 1;
            int not_taken = calculate(strs, i + 1, zeroes, ones, memo);
            memo[i][zeroes][ones] = Math.max(taken, not_taken);
            return memo[i][zeroes][ones];
        }
        public int[] countzeroesones(String s) {
            int[] c = new int[2];
            for (int i = 0; i < s.length(); i++) {
                c[s.charAt(i)-'0']++;
            }
            return c;
        }
    }



//-------------------------------------------------------------------------/////////

    //Approach #5 Dynamic Programming [Accepted]
    public class Solution7 {
        public int findMaxForm(String[] strs, int m, int n) {
            int[][] dp = new int[m + 1][n + 1];
            for (String s: strs) {
                int[] count = countzeroesones(s);
                for (int zeroes = m; zeroes >= count[0]; zeroes--)
                    for (int ones = n; ones >= count[1]; ones--)
                        dp[zeroes][ones] = Math.max(1 + dp[zeroes - count[0]][ones - count[1]], dp[zeroes][ones]);
            }
            return dp[m][n];
        }
        public int[] countzeroesones(String s) {
            int[] c = new int[2];
            for (int i = 0; i < s.length(); i++) {
                c[s.charAt(i)-'0']++;
            }
            return c;
        }
    }




//-------------------------------------------------------------------------/////////



//-------------------------------------------------------------------------/////////





}
/*
In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.

For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.

Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.

Note:
The given numbers of 0s and 1s will both not exceed 100
The size of given string array won't exceed 600.

Example 1:
Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
Output: 4

Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”

Example 2:
Input: Array = {"10", "0", "1"}, m = 1, n = 1
Output: 2

Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".



 */