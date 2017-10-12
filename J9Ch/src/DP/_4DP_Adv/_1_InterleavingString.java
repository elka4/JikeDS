package DP._4DP_Adv;
//每选择一个字符，相当于把原来的问题分为子问题


public class _1_InterleavingString {
    //jiuzhang
    public boolean isInterleave(String s1, String s2, String s3) {
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
/*
Given three strings: s1, s2, s3, determine whether s3 is formed by the interleaving of s1 and s2.

Example
For s1 = "aabcc", s2 = "dbbca"

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
 */

//////////////////////////////////////////////////////////
    //https://leetcode.com/articles/interleaving-strings/

//Approach #1 Brute Force [Time Limit Exceeded]
    /*
    Time complexity : O(2^(m+n))O(2(m+n)). mm is the length of s1s1 and nn is the length of s2s2.

Space complexity : O(m+n)O(m+n). The size of stack for recursive calls can go upto m+nm+n.
     */
public boolean is_Interleave2(String s1,int i,String s2,int j,String res,String s3)
{
    if(res.equals(s3) && i==s1.length() && j==s2.length())
        return true;
    boolean ans=false;
    if(i<s1.length())
        ans|=is_Interleave2(s1,i+1,s2,j,res+s1.charAt(i),s3);
    if(j<s2.length())
        ans|=is_Interleave2(s1,i,s2,j+1,res+s2.charAt(j),s3);
    return ans;

}
    public boolean isInterleave2(String s1, String s2, String s3) {
        return is_Interleave2(s1,0,s2,0,"",s3);
    }


//////////////////////////////////////////////////////////

//Approach #2 Recursion with memoization [Accepted]
/*
Time complexity : O(2^(m+n))O(2( m+n)). mm is the length of s1s1 and nn is the length of s2s2.

Space complexity : O(m+n)O(m+n). The size of stack for recursive calls can go upto m+nm+n.
 */
    public boolean is_Interleave3(String s1, int i, String s2, int j, String s3, int k, int[][] memo) {
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
        if (s3.charAt(k) == s1.charAt(i) && is_Interleave3(s1, i + 1, s2, j, s3, k + 1, memo)
                || s3.charAt(k) == s2.charAt(j) && is_Interleave3(s1, i, s2, j + 1, s3, k + 1, memo)) {
            ans = true;
        }
        memo[i][j] = ans ? 1 : 0;
        return ans;
    }
    public boolean isInterleave3(String s1, String s2, String s3) {
        int memo[][] = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                memo[i][j] = -1;
            }
        }
        return is_Interleave3(s1, 0, s2, 0, s3, 0, memo);
    }



//////////////////////////////////////////////////////////

    //Approach #3 Using 2-d Dynamic Programming [Accepted]
    //Time complexity : O(m*n)O(m∗n). dp array of size m*nm∗n is filled.
    //Space complexity : O(m*n)O(m∗n). 2-d DP of size (m+1)*(n+1)(m+1)∗(n+1) is required.
    // mm and nn are the lengths of strings s1s1 and s2s2 repectively.
    public boolean isInterleave4(String s1, String s2, String s3) {
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
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) ||
                            (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

//////////////////////////////////////////////////////////

//Approach #4 Using 1-d Dynamic Programming [Accepted]:
    //Time complexity : O(m*n)O(m∗n). dp array of size nn is filled mm times.
    //Space complexity : O(n)O(n). nn is the length of the string s1s1.
    public boolean isInterleave5(String s1, String s2, String s3) {
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
                    dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) ||
                            (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s2.length()];
    }
//////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////
}
