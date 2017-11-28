package _String._DP;
import java.util.*;
import org.junit.Test;

//  583. Delete Operation for Two Strings
//  https://leetcode.com/problems/delete-operation-for-two-strings
//
//  String
//  9:
public class _583_String_Delete_Operation_for_Two_Strings_M {
//------------------------------------------------------------------------------
//https://leetcode.com/problems/delete-operation-for-two-strings/solution/
//------------------------------------------------------------------------------
    //1
    //Approach #1 Using Longest Common Subsequence [Time Limit Exceeded]
    public class Solution1 {
        public int minDistance(String s1, String s2) {
            return s1.length() + s2.length() - 2 * lcs(s1, s2, s1.length(), s2.length());
        }
        public int lcs(String s1, String s2, int m, int n) {
            if (m == 0 || n == 0)
                return 0;
            if (s1.charAt(m - 1) == s2.charAt(n - 1))
                return 1 + lcs(s1, s2, m - 1, n - 1);
            else
                return Math.max(lcs(s1, s2, m, n - 1), lcs(s1, s2, m - 1, n));
        }
    }




//------------------------------------------------------------------------------
    //2
    //Approach #2 Longest Common Subsequence with Memoization [Accepted]
    public class Solution2 {
        public int minDistance(String s1, String s2) {
            int[][] memo = new int[s1.length() + 1][s2.length() + 1];
            return s1.length() + s2.length() - 2 * lcs(s1, s2, s1.length(), s2.length(), memo);
        }
        public int lcs(String s1, String s2, int m, int n, int[][] memo) {
            if (m == 0 || n == 0)
                return 0;
            if (memo[m][n] > 0)
                return memo[m][n];
            if (s1.charAt(m - 1) == s2.charAt(n - 1))
                memo[m][n] = 1 + lcs(s1, s2, m - 1, n - 1, memo);
            else
                memo[m][n] = Math.max(lcs(s1, s2, m, n - 1, memo),
                                      lcs(s1, s2, m - 1, n, memo));
            return memo[m][n];
        }
    }


//------------------------------------------------------------------------------
    //3
    //Approach #3 Using Longest Common Subsequence- Dynamic Programming [Accepted]
    public class Solution3 {
        public int minDistance(String s1, String s2) {
            int[][] dp = new int[s1.length() + 1][s2.length() + 1];
            for (int i = 0; i <= s1.length(); i++) {
                for (int j = 0; j <= s2.length(); j++) {
                    if (i == 0 || j == 0)
                        continue;
                    if (s1.charAt(i - 1) == s2.charAt(j - 1))
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    else
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
            return s1.length() + s2.length() - 2 * dp[s1.length()][s2.length()];
        }
    }



//------------------------------------------------------------------------------
    //4
    //Approach #4 Without using LCS Dynamic Programmming [Accepted]:
    public class Solution4 {
        public int minDistance(String s1, String s2) {
            int[][] dp = new int[s1.length() + 1][s2.length() + 1];
            for (int i = 0; i <= s1.length(); i++) {
                for (int j = 0; j <= s2.length(); j++) {
                    if (i == 0 || j == 0)
                        dp[i][j] = i + j;
                    else if (s1.charAt(i - 1) == s2.charAt(j - 1))
                        dp[i][j] = dp[i - 1][j - 1];
                    else
                        dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
            return dp[s1.length()][s2.length()];
        }
    }



//------------------------------------------------------------------------------
    //5
    //Approach #5 1-D Dynamic Programming [Accepted]:
    public class Solution5{
        public int minDistance(String s1, String s2) {
            int[] dp = new int[s2.length() + 1];
            for (int i = 0; i <= s1.length(); i++) {
                int[] temp=new int[s2.length()+1];
                for (int j = 0; j <= s2.length(); j++) {
                    if (i == 0 || j == 0)
                        temp[j] = i + j;
                    else if (s1.charAt(i - 1) == s2.charAt(j - 1))
                        temp[j] = dp[j - 1];
                    else
                        temp[j] = 1 + Math.min(dp[j], temp[j - 1]);
                }
                dp=temp;
            }
            return dp[s2.length()];
        }
    }



//------------------------------------------------------------------------------
    //6
    /*Java DP Solution (Longest Common Subsequence)
        To make them identical, just find the longest common subsequence. The rest of the characters have to be deleted from the both the strings, which does not belong to longest common subsequence.*/

    public int minDistance6(String word1, String word2) {
        int dp[][] = new int[word1.length()+1][word2.length()+1];
        for(int i = 0; i <= word1.length(); i++) {
            for(int j = 0; j <= word2.length(); j++) {
                if(i == 0 || j == 0) dp[i][j] = 0;
                else dp[i][j] = (word1.charAt(i-1) == word2.charAt(j-1)) ?
                        dp[i-1][j-1] + 1 : Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        int val =  dp[word1.length()][word2.length()];
        return word1.length() - val + word2.length() - val;
    }


//------------------------------------------------------------------------------
    //7
    //Java DP Solution, same as Edit Distance
    public class Solution7 {
        public int minDistance(String word1, String word2) {
            int len1 = word1.length(), len2 = word2.length();
            if (len1 == 0) return len2;
            if (len2 == 0) return len1;

            // dp[i][j] stands for distance of first i chars of word1 and first j chars of word2
            int[][] dp = new int[len1 + 1][len2 + 1];
            for (int i = 0; i <= len1; i++)
                dp[i][0] = i;
            for (int j = 0; j <= len2; j++)
                dp[0][j] = j;

            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1))
                        dp[i][j] = dp[i - 1][j - 1];
                    else
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1] + 2,
                                dp[i - 1][j] + 1), dp[i][j - 1] + 1);
                }
            }

            return dp[len1][len2];
        }
    }
//------------------------------------------------------------------------------
    //8
    //Longest Common Subsequence DP Java Solution
    //Since the only operation allowed is deletion, this problem actually becomes finding the longest common subsequence.
    class Solution8{
        public int minDistance(String word1, String word2) {
            int longest = findLongestCommonSubSequence(word1, word2);
            return word1.length() - longest + word2.length() - longest;
        }

        private int findLongestCommonSubSequence(String word1, String word2) {
            int[][] matrix = new int[word1.length() + 1][word2.length() + 1];
            int re = 0;
            for (int i = 1; i <= word1.length(); i++) {
                for (int j = 1; j <= word2.length(); j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        matrix[i][j] = matrix[i - 1][j - 1] + 1;
                    } else {
                        matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                    }
                    re = Math.max(matrix[i][j], re);
                }
            }
            return re;
        }
    }

//------------------------------------------------------------------------------
    //9
    //[Java/C++] Clean Code
    //DP Formula

/**
 * dp[i][j] = a[i] == b[j] ? dp[i + 1][j + 1] :
 *            min(1 + dp[i + 1][j],  // delete a[i] + mindist between a.substr(i+1), b.substr(j)
 *                1 + dp[i][j + 1])  // delete b[j] + mindist between a.substr(i), b.substr(j+1)
 */

    class Solution9 {
        public int minDistance(String word1, String word2) {
            int m = word1.length(), n = word2.length(), MAX = Integer.MAX_VALUE;
            char[] a = word1.toCharArray(), b = word2.toCharArray();
            int[][] dp = new int[m + 1][n + 1];
            for (int i = m; i >= 0; i--) {
                for (int j = n; j >= 0; j--) {
                    if (i < m || j < n)
                        dp[i][j] = i < m && j < n && a[i] == b[j] ?
                                dp[i + 1][j + 1] : 1 + Math.min((i < m ? dp[i + 1][j] : MAX), (j < n ? dp[i][j + 1] : MAX));
                }
            }
            return dp[0][0];
        }
    }

//------------------------------------------------------------------------------
}
/*
Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.

Example 1:
Input: "sea", "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".

Note:
The length of given words won't exceed 500.
Characters in given words can only be lower-case letters.
Seen this question in a real interview before?   Yes  No
Difficulty:Medium
Total Accepted:12.3K
Total Submissions:27.8K
Contributor: 今が最高

Companies
Google

Related Topics
String

Similar Questions
72. Edit Distance - DP
712. Minimum ASCII Delete Sum for Two Strings - DP
 */