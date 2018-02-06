package DP.DP6;


//  712. Minimum ASCII Delete Sum for Two Strings
//  https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/description/
//
//
//
public class _712_Minimum_ASCII_Delete_Sum_for_Two_Strings {
//--------------------------------------------------------------------------------
//https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/solution/

//--------------------------------------------------------------------------------
    //1
    //Approach #1: Dynamic Programming [Accepted]
    /*
    Intuition and Algorithm

Let dp[i][j] be the answer to the problem for the strings s1[i:], s2[j:].

When one of the input strings is empty, the answer is the ASCII-sum of the other string. We can calculate this cumulatively using code like dp[i][s2.length()] = dp[i+1][s2.length()] + s1.codePointAt(i).

When s1[i] == s2[j], we have dp[i][j] = dp[i+1][j+1] as we can ignore these two characters.

When s1[i] != s2[j], we will have to delete at least one of them. We'll have dp[i][j] as the minimum of the answers after both deletion options.

The solutions presented will use bottom-up dynamic programming.
     */
    class Solution1 {
        public int minimumDeleteSum(String s1, String s2) {
            int[][] dp = new int[s1.length() + 1][s2.length() + 1];

            for (int i = s1.length() - 1; i >= 0; i--) {
                dp[i][s2.length()] = dp[i+1][s2.length()] + s1.codePointAt(i);
            }
            for (int j = s2.length() - 1; j >= 0; j--) {
                dp[s1.length()][j] = dp[s1.length()][j+1] + s2.codePointAt(j);
            }
            for (int i = s1.length() - 1; i >= 0; i--) {
                for (int j = s2.length() - 1; j >= 0; j--) {
                    if (s1.charAt(i) == s2.charAt(j)) {
                        dp[i][j] = dp[i+1][j+1];
                    } else {
                        dp[i][j] = Math.min(dp[i+1][j] + s1.codePointAt(i),
                                dp[i][j+1] + s2.codePointAt(j));
                    }
                }
            }
            return dp[0][0];
        }
    }


//--------------------------------------------------------------------------------
    //2
    //Concise DP solution
    //The same idea as edit distance. Straightforward 19 lines.
    class Solution {
        public int minimumDeleteSum(String s1, String s2) {
            int[][] count = new int[s1.length() + 1][s2.length() + 1];
            for(int i = 1; i < count.length; i++){
                count[i][0] = count[i-1][0] + s1.charAt(i-1);
            }
            for(int i = 1; i < count[0].length; i++){
                count[0][i] = count[0][i-1] + s2.charAt(i-1);
            }
            for(int i = 1; i < count.length; i++){
                for(int j = 1; j < count[0].length; j++){
                    int cost = (s1.charAt(i-1) == s2.charAt(j-1))?
                            0 : s1.charAt(i-1) + s2.charAt(j-1);

                    count[i][j] = Math.min(count[i-1][j] + s1.charAt(i-1),
                            count[i][j-1] + s2.charAt(j-1));

                    count[i][j] = Math.min(count[i][j], count[i-1][j-1] + cost);
                }
            }
            return count[s1.length()][s2.length()];
        }
    }

//--------------------------------------------------------------------------------



}
/*
Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.

Example 1:
Input: s1 = "sea", s2 = "eat"
Output: 231
Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
Deleting "t" from "eat" adds 116 to the sum.
At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
Example 2:
Input: s1 = "delete", s2 = "leet"
Output: 403
Explanation: Deleting "dee" from "delete" to turn the string into "let",
adds 100[d]+101[e]+101[e] to the sum.  Deleting "e" from "leet" adds 101[e] to the sum.
At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
Note:

0 < s1.length, s2.length <= 1000.
All elements of each string will have an ASCII value in [97, 122].
Seen this question in a real interview before?   Yes  No

Companies
TripleByte

Related Topics
Dynamic Programming

Similar Questions
Edit Distance
Longest Increasing Subsequence
Delete Operation for Two Strings
 */