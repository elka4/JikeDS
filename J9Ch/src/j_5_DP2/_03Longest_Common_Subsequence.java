package j_5_DP2;

public class _03Longest_Common_Subsequence {
	 /**
	 * @param A, B: Two strings.
	 * @return: The length of longest common subsequence of A and B.
	 */
	/*
	 * ￼￼Longest Common Subsequence
	• state: f[i][j]表示前i个字符配上前j个字符的LCS的长度
	• function: f[i][j] = MAX(f[i-1][j], f[i][j-1], f[i-1][j-1] + 1)
						 // A[i - 1] == B[j - 1]
	• 					= MAX(f[i-1][j], f[i][j-1])
						// A[i - 1] != B[j - 1]
	• intialize: f[i][0] = 0 f[0][j] = 0
	• answer: f[n][m]
	为什么是i-1? A的第i个字符的是A[i-1]
	• Related Question:
	• http://www.lintcode.com/problem/longest-common-substring/
	 */
	
	//重要：f[i][j]里的i，j指的是String A B里的i-1和j-1 char ！！！！

	public int longestCommonSubsequence(String A, String B) {
		int n = A.length();
		int m = B.length();
		int f[][] = new int[n + 1][m + 1];//because there is ""
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= m; j++){
				f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
				if(A.charAt(i - 1) == B.charAt(j - 1))
					f[i][j] = f[i - 1][j - 1] + 1;
			}
		}
		return f[n][m];
	}


	//9CH summer 2016
	public int longestCommonSubsequence2(String A, String B){
		int n = A.length();
		int m = B.length();

		//state: lcs[i][j] 代表A的前i个字符和B的前j个字符的LCS的长度是多少
		int[][] lcs = new int[n + 1][m + 1];

		//initialize
		for(int i = 0; i <= n; i++){
			lcs[i][0] = 0;
		}
		for(int j = 0; j <= m; j++){
			lcs[0][j] = 0;
		}

		//function
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= m; j++){
				if(A.charAt(i - 1) == B.charAt(j - 1)){
					//lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
					//lcs[i][j] = Math.max(lcs[i][j], lcs[i - 1][j - 1] + 1);
					lcs[i][j] = lcs[i - 1][j - 1] + 1; //＋1指的是charAt(i - 1)
				} else {
					lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
					//lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
					//lcs[i][j] = Math.max(lcs[i][j], lcs[i - 1][j - 1]);
				}
			}
		}

		//answer
		return lcs[n][m];
	}
}



/*
 * Given two strings, find the longest common subsequence (LCS).

Your code should return the length of LCS.

Have you met this question in a real interview? Yes
Clarification
What's the definition of Longest Common Subsequence?

https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
http://baike.baidu.com/view/2020307.htm
Example
For "ABCD" and "EDCA", the LCS is "A" (or "D", "C"), return 1.

For "ABCD" and "EACB", the LCS is "AC", return 2.

Tags 
LintCode Copyright Longest Common Subsequence Dynamic Programming
Related Problems 
Medium Edit Distance 28 %
Medium Longest Common Substring
 */