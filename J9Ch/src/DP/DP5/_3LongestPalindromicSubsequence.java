package DP.DP5;
import org.junit.Test;

//• 区间型动态规划


/*
f[i][j] = max{f[i+1][j], f[i][j-1], f[i+1][j-1] + 2|S[i]=S[j]}

f[i][j]:                    S[i..j]的最长回文 子串的长度
f[i+1][j]:                  S[i+1..j]的最长回 文子串的长度
f[i][j-1]:                  S[i..j-1]的最长回 文子串的长度
f[i+1][j-1] + 2|S[i]=S[j]:  S[i+1..j-1]的最长回文子串 的长度，加上S[i]和S[j]

• 设f[i][j]为S[i..j]的最长回文子串的长度

• 初始条件
– f[0][0] = f[1][1] = ... = f[N-1][N-1] = 1
• 一个字母也是一个长度为1的回文串
– 如果S[i] == S[i+1], f[i][i+1] = 2
– 如果S[i] != S[i+1], f[i][i+1] = 1

• 设f[i][j]为S[i..j]的最长回文子串的长度
• f[i][j] = max{f[i+1][j], f[i][j-1], f[i+1][j-1] + 2|S[i]=S[j]} • 不能按照i的顺序去算
• 区间动态规划:按照长度j-i从小到大的顺序去算


• 长度1:f[0][0], f[1][1], f[2][2], ..., f[N-1][N-1]
• 长度2: f[0][1], ..., f[N-2][N-1]
•...
• 长度N:f[0][N-1]
• 答案是f[0][N-1]
• 时间复杂度O(N2)，空间复杂度O(N2)


-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

 */



//  516. Longest Palindromic Subsequence
//  https://leetcode.com/problems/longest-palindromic-subsequence/description/
//
public class _3LongestPalindromicSubsequence {

/*    Straight forward Java DP solution
    dp[i][j]: the longest palindromic subsequence's length of subString(i, j)
    State transition:
    dp[i][j] = dp[i+1][j-1] + 2 if s.charAt(i) == s.charAt(j)
    otherwise, dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1])
    Initialization: dp[i][i] = 1*/

    public class Solution01 {
        public int longestPalindromeSubseq(String s) {
            int[][] dp = new int[s.length()][s.length()];

            for (int i = s.length() - 1; i >= 0; i--) {
                dp[i][i] = 1;
                for (int j = i+1; j < s.length(); j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i+1][j-1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                    }
                }
            }
            return dp[0][s.length()-1];
        }
    }


    //    Top bottom recursive method with memoization
    public class Solution02 {
        public int longestPalindromeSubseq(String s) {
            return helper(s, 0, s.length() - 1, new Integer[s.length()][s.length()]);
        }

        private int helper(String s, int i, int j, Integer[][] memo) {
            if (memo[i][j] != null) {
                return memo[i][j];
            }
            if (i > j)      return 0;
            if (i == j)     return 1;

            if (s.charAt(i) == s.charAt(j)) {
                memo[i][j] = helper(s, i + 1, j - 1, memo) + 2;
            } else {
                memo[i][j] = Math.max(helper(s, i + 1, j, memo), helper(s, i, j - 1, memo));
            }
            return memo[i][j];
        }
    }
//-------------------------------------------------------------------------/////////
/*short java solution,beats 99%,with explanation

7
    M magtron_3
    Reputation:  25
    It is a typical DP problem. let's use a[i][j] represent the longest Palindromic Subsequence of S[i:j] (i,j inclusive).
    So a[i][j]=
            (1) a[i+1][j-1]+2 if S[i] == S[j]
            (2) Max( a[i+1][j], a[i][j-1] ) if S[i]! = S[j]*/

    public class Solution {
        public int longestPalindromeSubseq03(String s) {
            int n=s.length();
            int[][] a=new int[n][n];
            for(int i=0;i<n;i++) a[i][i]=1;
            return helper(a,0,n-1,s);
        }
        private int helper(int[][] a,int i,int j,String s){
            if(i>j || a[i][j]!=0) return a[i][j];
            if(s.charAt(i)==s.charAt(j)) a[i][j]=helper(a,i+1,j-1,s)+2;
            else a[i][j]=Math.max(helper(a,i,j-1,s),helper(a,i+1,j,s) );
            return a[i][j];
        }
    }

//-------------------------------------------------------------------------/////////

    //    Java DP Solution
    public class Solution04 {
        public int longestPalindromeSubseq(String s) {
            int len = s.length();
            int[][] dp = new int[len][len];
            for(int i = 0;i < len;i++){
                dp[i][i] = 1;
            }
            //for each interval length
            for(int l = 2;l <= len;l++){
                //for each interval with the same length
                for(int st = 0;st <= len-l;st++){
                    int ed = st+l-1;
                    //if left end equals to right end or not
                    dp[st][ed] = s.charAt(st)==s.charAt(ed)?
                            dp[st+1][ed-1]+2 : Math.max(dp[st+1][ed], dp[st][ed-1]);
                }
            }
            return dp[0][len-1];
        }
    }
//-------------------------------------------------------------------------/////////
    //Java DP solution, similar to solving LCS problem
    //
    //    To find the longest palindromic subsequence, we could reverse the string and
    // find the LCS(Longest common subsequence) of these two strings.

    public class Solution05 {
        public int longestPalindromeSubseq(String s) {
            int len = s.length();
            if(len < 2) return len;
            int[][] dp= new int[len+1][len+1];
            String reverse = new StringBuilder(s).reverse().toString();//记住这个用法
            for(int i = 1; i <= len; i++) {
                for(int j = 1; j <= len; j++) {
                    if(s.charAt(i-1) == reverse.charAt(j-1)) dp[i][j] = dp[i-1][j-1] + 1;
                    else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
            return dp[len][len];
        }
    }
//-------------------------------------------------------------------------/////////
    // 9Ch DP

    public int longestPalindromeSubseq(String ss) {
        s = ss.toCharArray();
        int n = s.length;
        if (n == 0) {
            return 0;
        }

        int[][] f = new int[n][n];
        int i, j;

        // init
        // length 1
        for (i = 0; i < n; i++) {
            f[i][i] = 1;
        }

        // length 2
        for (i = 0; i < n - 1; i++) {
            f[i][i + 1] = (s[i] == s[i + 1]) ? 2:1;
        }

        int len;
        // 按照区间长度计算
        for (len = 3; len <= n; len++) {
            // -----len-------
            // n - len ----- n - 1
            // left: i, right : j
            for (i = 0; i <= n - len; i++) {
                j = i + len - 1; //右端点
                f[i][j] = Math.max(f[i][j - 1], f[i + 1][j]);
                if (s[i] == s[j]) {
                    f[i][j] = Math.max(f[i][j], f[i + 1][j - 1] + 2);
                }
            }
        }

        return  f[0][n - 1];

    }

    @Test
    public void test01 () {
        System.out.println(longestPalindromeSubseq("bbbab"));//4 bbbb
        System.out.println(longestPalindromeSubseq("cbbd"));//2  bb
        System.out.println(longestPalindromeSubseq("bbbafbb"));//2  bb
    }
////////////////////////////////////////////////////////////////////
// 9CH
    // 动态规划专题班非递归版：
    public int longestPalindromeSubseq1(String s) {
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
    @Test
    public void test02 () {
        System.out.println(longestPalindromeSubseq1("bbbab"));//4 bbbb
        System.out.println(longestPalindromeSubseq1("cbbd"));//2  bb
        System.out.println(longestPalindromeSubseq1("bbbafbb"));//2  bb
    }
////////////////////////////////////////////////////////////////////
    // 9CH

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

////////////////////////////////////////////////////////////////////
    // 9CH
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
////////////////////////////////////////////////////////////////////

}

/*
Given a String s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".
Example 2:
Input:

"cbbd"
Output:
2
One possible longest palindromic subsequence is "bb".

 */


/*
Given a String s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 */
