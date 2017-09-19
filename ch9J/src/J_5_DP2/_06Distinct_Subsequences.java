package chapter5_DP2;

/* 求方案总数
 * Distinct Subsequence
• state: f[i][j] 表示 S的前i个字符中选取T的前j个字符,有多少种方案
• function: f[i][j] = f[i - 1][j] + f[i - 1][j - 1] // S[i-1] == T[j-1]
• 					= f[i - 1][j]                  // S[i-1] != T[j-1]
// 以上只有i-1是因为目标是j，j不能删除，只能变化i
• initialize: 
f[i][0] = 1, 就是从S里挑一个T（空串）有几种方案，有1种，就是一个字符都不要
f[0][j] = 0 (j > 0) ， 就是从S（空串）里挑一个T有几种方案，有0种，怎么都挑不出来
• answer: f[n][m] (n = sizeof(S), m = sizeof(T))
 */

public class _06Distinct_Subsequences {
	public int numDistinct(String S, String T) {
	    if (S == null || T == null) {
	        return 0;
	    }
	
	    int[][] nums = new int[S.length() + 1][T.length() + 1];
	
	    for (int i = 0; i <= S.length(); i++) {
	        nums[i][0] = 1;
	    }
	    for (int i = 1; i <= S.length(); i++) {
	        for (int j = 1; j <= T.length(); j++) {
	            nums[i][j] = nums[i - 1][j];
	            if (S.charAt(i - 1) == T.charAt(j - 1)) {
	                nums[i][j] += nums[i - 1][j - 1];
	            }
	        }
	    }
	    return nums[S.length()][T.length()];
	}
}

/*
 * Given a string S and a string T, count the number of distinct 
 * subsequences of T in S.

A subsequence of a string is a new string which is formed from 
the original string by deleting some (can be none) of the characters
 without disturbing the relative positions of the remaining characters.
  (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Have you met this question in a real interview? Yes
Example
Given S = "rabbbit", T = "rabbit", return 3.

Challenge 
Do it in O(n2) time and O(n) memory.

O(n2) memory is also acceptable if you do not know how to optimize memory.

Tags 
String Dynamic Programming
Related Problems 
Medium Interleaving String
 */
