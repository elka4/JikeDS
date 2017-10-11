package DP.DP6;

//双序列型动态规划


/*
• 给定三个字符串A, B, X
• 判断X是否是由A, B交错在一起形成
– 即A是X的子序列，去掉A后，剩下的字符组成B
• 例子:
• 输入:A=“aabcc” B=“dbbac”, X=“aadbbcbcac” • 输出:True( X=“aadbbcbcac” )

• 首先，如果X的长度不等于A的长度+B的长度，直接输出False
• 设A长度是m, B长度是n，X的长度是m+n
• 最后一步:假设X是由A和B交错形成的，那么X的最后一个字符X[m+n-1]
– 要么是A[m-1]
    • 那么X[0..m+n-2]是由A[0..m-2]和B[0..n-1]交错形成的
– 要么是B[n-1]
    • 那么X[0..m+n-2]是由A[0..m-1]和B[0..n-2]交错形成的

• 要求X[0..m+n-1]是否由A[0..m-1]和B[0..n-1]交错形成
• 需要知道X[0..m+n-2]是否由A[0..m-2]和B[0..n-1]交错形成，以及 X[0..m+n-2]是否由A[0..m-1]和B[0..n-2]交错形成
• 子问题
• 状态:设f[s][i][j]为X前s个字符是否由A前i个字符A[0..i-1]和B前j个字符B[0..j-1]交错形成
• 但是s=i+j，所以可以简化为:设f[i][j]为X前i+j个字符是否由A前i个字符 A[0..i-1]和B前j个字符B[0..j-1]交错形成

• 设f[i][j]为X前i+j个字符是否由A前i个字符A[0..i-1]和B前j个字符B[0..j-1]交错形成
f[i][j] = (f[i-1][j] AND X[i+j-1]==A[i-1]) OR (f[i][j-1] AND X[i+j-1]==B[j-1])

f[i][j]                         : X前i+j个字符是否由A 前i个字符和B前j个字 符交错形成
f[i-1][j] AND X[i+j-1]==A[i-1]  : 情况一:X前i+j-1个字符由A前i-1 个字符和B前j个字符交错形成, X 第i+j个字符等于A第i个字符
f[i][j-1] AND X[i+j-1]==B[j-1]  : 情况二:X前i+j-1个字符由A前i个 字符和B前j-1个字符交错形成, X第 i+j个字符等于B第j个字符

• 设f[i][j]为X前i+j个字符是否由A前i个字符A[0..i-1]和B前j个字符B[0..j-1]交
错形成
• 转移方程
– f[i][j] = (f[i-1][j] AND X[i+j-1]==A[i-1]) OR (f[i][j-1] AND X[i+j-1]==B[j-1])
• 初始条件:空串由A的空串和B的空串交错形成àf[0][0] = True
• 边界情况:如果i=0，不考虑情况一;如果j=0，不考虑情况二
 */


import org.junit.Test;

// Interleaving String
public class _2InterleavingString {
    //9Ch DP  ??? 有些通不过
    public boolean isInterleave(String s1, String s2, String s3) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] c3 = s3.toCharArray();

        int m = c1.length;
        int n = c2.length;
        if (c3.length != m + n) {
            return false;
        }

        boolean[][] f = new boolean[m + 1][n + 1];
        int i, j;
        for (i = 0; i <= m; i++) {
            for (j = 0; j <= n; j++) {
                //init
                if (i == 0 || j == 0) {
                    f[i][j] = true;
                    continue;
                }

                f[i][j] = false;
                if (j > 0 && c3[i + j - 1] == c1[i - 1]) {
                    f[i][j] = f[i - 1][j];//true
                }

                if (j > 0 && c3[i + j - 1] == c2[j - 1]) {
                    f[i][j] = f[i][j - 1]; //false
                }
            }
        }
        return f[m][n];
    }

    @Test
    public void test01() {
        /*
        For s1 = "aabcc", s2 = "dbbca"

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
         */
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbbaccc"));

    }


/////////////////////////////////////////////////////////////////////////////////////
    public boolean isInterleave1(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        boolean [][] interleaved = new boolean[s1.length() + 1][s2.length() + 1];
        interleaved[0][0] = true;

        for (int i = 1; i <= s1.length(); i++) {
            if(s3.charAt(i - 1) == s1.charAt(i - 1) && interleaved[i - 1][0])
                interleaved[i][0] = true;
        }

        for (int j = 1; j <= s2.length(); j++) {
            if(s3.charAt(j - 1) == s2.charAt(j - 1) && interleaved[0][j - 1])
                interleaved[0][j] = true;
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if(((s3.charAt(i + j - 1) == s1.charAt(i - 1) && interleaved[i - 1][j]))
                || ((s3.charAt(i + j - 1)) == s2.charAt(j - 1) && interleaved[i][j - 1]))
                    interleaved[i][j] = true;
            }
        }

        return interleaved[s1.length()][s2.length()];
    }

/////////////////////////////////////////////////////////////////////////////////////
    //https://leetcode.com/articles/interleaving-strings/

//Approach #1 Brute Force [Time Limit Exceeded]
public class Solution1 {
    public boolean is_Interleave(String s1,int i,String s2,int j,String res,String s3)
    {
        if(res.equals(s3) && i==s1.length() && j==s2.length())
            return true;
        boolean ans=false;
        if(i<s1.length())
            ans|=is_Interleave(s1,i+1,s2,j,res+s1.charAt(i),s3);
        if(j<s2.length())
            ans|=is_Interleave(s1,i,s2,j+1,res+s2.charAt(j),s3);
        return ans;

    }
    public boolean isInterleave(String s1, String s2, String s3) {
        return is_Interleave(s1,0,s2,0,"",s3);
    }
}


/////////////////////////////////////////////////////////////////////////////////////
    //Approach #2 Recursion with memoization [Accepted]
public class Solution2 {
    public boolean is_Interleave(String s1, int i, String s2, int j, String s3, int k, int[][] memo) {
        if (i == s1.length()) {
            return s2.substring(j).equals(s3.substring(k));
        }
        if (j == s2.length()) {
            return s1.substring(i).equals(s3.substring(k));
        }
        if (memo[i][j] >= 0) {
            return memo[i][j] == 1 ? true : false;
        }
        boolean ans = false;
        if (s3.charAt(k) == s1.charAt(i) && is_Interleave(s1, i + 1, s2, j, s3, k + 1, memo)
                || s3.charAt(k) == s2.charAt(j) && is_Interleave(s1, i, s2, j + 1, s3, k + 1, memo)) {
            ans = true;
        }
        memo[i][j] = ans ? 1 : 0;
        return ans;
    }
    public boolean isInterleave(String s1, String s2, String s3) {
        int memo[][] = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                memo[i][j] = -1;
            }
        }
        return is_Interleave(s1, 0, s2, 0, s3, 0, memo);
    }
}

/////////////////////////////////////////////////////////////////////////////////////
//Approach #3 Using 2-d Dynamic Programming [Accepted]

    public class Solution3 {
        public boolean isInterleave(String s1, String s2, String s3) {
            if (s3.length() != s1.length() + s2.length()) {
                return false;
            }
            boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
            for (int i = 0; i <= s1.length(); i++) {
                for (int j = 0; j <= s2.length(); j++) {
                    if (i == 0 && j == 0) {
                        dp[i][j] = true;
                    } else if (i == 0) {
                        dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                    } else if (j == 0) {
                        dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                    } else {
                        dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                                || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                    }
                }
            }
            return dp[s1.length()][s2.length()];
        }
    }

/////////////////////////////////////////////////////////////////////////////////////
    //Approach #4 Using 1-d Dynamic Programming [Accepted]:

    public class Solution4 {
        public boolean isInterleave(String s1, String s2, String s3) {
            if (s3.length() != s1.length() + s2.length()) {
                return false;
            }
            boolean dp[] = new boolean[s2.length() + 1];
            for (int i = 0; i <= s1.length(); i++) {
                for (int j = 0; j <= s2.length(); j++) {
                    if (i == 0 && j == 0) {
                        dp[j] = true;
                    } else if (i == 0) {
                        dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                    } else if (j == 0) {
                        dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                    } else {
                        dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                                || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                    }
                }
            }
            return dp[s2.length()];
        }
    }


/////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////////
}
/*
Given three strings: s1, s2, s3, determine whether s3 is formed by the interleaving of s1 and s2.

Have you met this question in a real interview? Yes
Example
For s1 = "aabcc", s2 = "dbbca"

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.

 */
