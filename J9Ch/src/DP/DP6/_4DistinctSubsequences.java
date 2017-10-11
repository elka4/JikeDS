package DP.DP6;

//双序列型动态规划

/*
• 题意:
• 给定两个字符串A[0..m-1]，B[0..n-1] • 问B在A中出现多少次(可以不连续)
• 例子
• 输入:A=“rabbbit”, B=“rabbit”
• 输出:3


• 设f[i][j]为B前j个字符B[0..j-1]在A前i个字符A[0..i-1]中出现多少次
• 要求f[m][n]
f[i][j] = f[i-1][j-1]|A[i-1]=B[j-1] + f[i-1][j]

f[i-1][j-1]|A[i-1]=B[j-1]  情况1:B[j-1] = A[i-1]，结成对子
f[i-1][j]                  情况2:B[j-1]不和 A[i-1]结成对子

• 设f[i][j]为B前j个字符B[0..j-1]在A前i个字符A[0..i-1]中出现多少次
• 转移方程: f[i][j] = f[i-1][j-1]|A[i-1]=B[j-1] + f[i-1][j]
• 初始条件:
– 如果B是空串，B在A中出现次数是1
– f[i][0] = 1 (i = 0, 1, 2, ..., m)
– 如果A是空串而B不是空串，B在A中出现次数是0 – f[0][j] = 0 (j = 1, 2, ..., n)
 */

//  Distinct Subsequences
public class _4DistinctSubsequences {
    // 9Ch DP
    public int numDistinct(String S, String T) {
        char[] c1 = S.toCharArray();
        char[] c2 = T.toCharArray();
        int m = c1.length;
        int n = c2.length;

        int[][] f = new int[m + 1][n + 1];
        int i, j;
        for (i = 0; i <= m; i++) {
            for (j = 0; j <= n; j++) {
                //s2 is empty
                if (j == 0) {
                    f[i][j] = 1;//very important
                    continue;
                }

                // s1 is empty, s2 is NOT empty
                if (i == 0) {
                    f[i][j] = 0;
                    continue;
                }

                f[i][j] = f[i - 1][j];
                if (c1[i - 1] == c2[j - 1]) {
                    f[i][j] += f[i - 1][j - 1];
                }
            }
        }
        return f[m][n];

    }

//////////////////////////////////////////////////////////////////////
    public int numDistinct2(String S, String T) {
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
//////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////
}
/*
Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Have you met this question in a real interview? Yes
Example
Given S = "rabbbit", T = "rabbit", return 3.
 */

/*
给出字符串S和字符串T，计算S的不同的子序列中T出现的个数。

子序列字符串是原始字符串通过删除一些(或零个)产生的一个新的字符串，并且对剩下的字符的相对位置没有影响。(比如，“ACE”是“ABCDE”的子序列字符串,而“AEC”不是)。

您在真实的面试中是否遇到过这个题？ Yes
样例
给出S = "rabbbit", T = "rabbit"

返回 3
 */