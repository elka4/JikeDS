package DP_Classified._4Domain;

//Longest Palindromic Subsequence
public class _3LongestPalindromicSubsequence {

    // 动态规划专题班非递归版：
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        int[][] f = new int[n][n];
        int i, j, len;
        for (i = 0; i < n; ++i) {
            f[i][i] = 1;
        }
        for (i = 0; i < n-1; ++i) {
            if (s.charAt(i) == s.charAt(i+1)) {
                f[i][i+1] = 2;
            }
            else {
                f[i][i+1] = 1;
            }
        }

        for (len = 3; len <= n; ++len) {
            for (i = 0; i <= n-len; ++i) {
                j = i + len - 1;
                f[i][j] = f[i][j-1];
                if (f[i+1][j] > f[i][j]) {
                    f[i][j] = f[i+1][j];
                }
                if (s.charAt(i) == s.charAt(j) && f[i+1][j-1] + 2 > f[i][j]) {
                    f[i][j] = f[i+1][j-1] + 2;
                }
            }
        }

        int res = 0;
        for (i = 0; i < n; ++i) {
            for (j = i; j < n; ++j) {
                if (f[i][j] > res) {
                    res = f[i][j];
                }
            }
        }

        return res;
    }



//-----------------------------------------------------------------------------//

    // 动态规划专题班递归版：
    int[][] f = null;
    char[] s = null;
    int res = 0;

    private void Compute(int i, int j) {
        if (f[i][j] != -1) {
            return;
        }

        if (i == j) {
            f[i][j] = 1;
            return;
        }

        if (i + 1 == j) {
            if (s[i] == s[j]) {
                f[i][j] = 2;
            }
            else {
                f[i][j] = 1;
            }

            return;
        }

        Compute(i + 1, j);
        Compute(i, j - 1);
        Compute(i + 1, j - 1);
        f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
        if (s[i] == s[j]) {
            f[i][j] = Math.max(f[i][j], f[i + 1][j - 1] + 2);
        }
    }

    public int longestPalindromeSubseq2(String ss) {
        s = ss.toCharArray();
        int n = s.length;
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        int i, j;
        f = new int[n][n];
        for (i = 0; i < n; ++i) {
            for (j = i; j < n; ++j) {
                f[i][j] = -1;
            }
        }

        Compute(0, n - 1);

        for (i = 0; i < n; ++i) {
            for (j = i; j < n; ++j) {
                res = Math.max(res, f[i][j]);
            }
        }

        return res;
    }



//-----------------------------------------------------------------------------//

    /**
     * @param s the maximum length of s is 1000
     * @return the longest palindromic subsequence's length
     */
    public int longestPalindromeSubseq3(String s) {
        // Write your code here
        int length = s.length();
        if (length == 0)
            return 0;
        int[][] dp = new int[length][length];
        for (int i = length - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < length; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][length - 1];
    }


//-----------------------------------------------------------------------------//

}
/*
Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 */
