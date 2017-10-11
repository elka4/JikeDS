package DP.DP6;
import org.junit.Test;

//双序列型动态规划

/*
• 输入:A=“jiuzhang”, B=“lijiang”
• 输出:5(最长公共子串是jiang )

• 设f[i][j]为A前i个字符A[0..i-1]和B前j个字符[0..j-1]的最长公共子串的长度
• 要求f[m][n]

f[i][j] = max{f[i-1][j], f[i][j-1], f[i-1][j-1]+1|A[i-1]=B[j-1]}

情况一:A[0..i-2]和B[0..j- 1]的最长公共子串

情况二:A[0..i-1]和 B[0..j-2]的最长公共子串

情况三:A[0..i-2]和B[0..j-2]的最长 公共子串+A[i-1]

• f[i][j]为A前i个字符A[0..i-1]和B前j个字符[0..j-1]的最长公共子串的长度
• 转移方程:f[i][j] = max{f[i-1][j], f[i][j-1], f[i-1][j-1]+1|A[i-1]=B[j-1]}
• 初始条件:空串和任何串的最长公共子串长度是0 – f[0][j] = 0, j=0..n
– f[i][0] = 0, i=0..m
 */

//Longest Common Subsequence
public class _1LongestCommonSubsequence {
    // 9Ch DP
    public int longestCommonSubsequence(String AA, String BB) {
        char[] A = AA.toCharArray();
        char[] B = BB.toCharArray();

        int m = A.length;
        int n = B.length;
        int[][] f = new int[m + 1][n + 1];
        int i, j;

        for (i = 0; i <= m; i++) {
            for (j = 0; j <= n; j++) {
                //init
                if (i == 0 || j ==0) {
                    f[i][j] = 0;
                    continue;
                }

                // normal transiton function
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                if (A[i - 1] == B[j - 1]) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + 1);
                }
            }
        }

        return f[m][n];
    }

    /*
    For "ABCD" and "EDCA", the LCS is "A" (or "D", "C"), return 1.

For "ABCD" and "EACB", the LCS is "AC", return 2.
     */
    @Test
    public void test01() {
        System.out.println(longestCommonSubsequence("ABCD", "EDCA"));
        System.out.println(longestCommonSubsequence("ABCD" , "EACB"));
    }

////////////////////////////////////////////////////////////////

    // 9Ch DP
    public int longestCommonSubsequence1(String AA, String BB) {
        char[] A = AA.toCharArray();
        char[] B = BB.toCharArray();

        int m = A.length;
        int n = B.length;
        int[][] f = new int[m + 1][n + 1];
        int[][] pai = new int[m + 1][n + 1];
        int i, j;

        for (i = 0; i <= m; i++) {
            for (j = 0; j <= n; j++) {
                //init
                if (i == 0 || j ==0) {
                    f[i][j] = 0;
                    continue;
                }

                // normal transiton function
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);

                if (f[i][j] == f[i - 1][j]) {
                    pai[i][j] = -1;
                } else {
                    pai[i][j] = 2;
                }

                if (A[i - 1] == B[j - 1]) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + 1);
                    if (f[i][j] == f[i - 1][j - 1] + 1) {
                        pai[i][j] = 3;
                    }
                }
            }
        }

        //recover (reversely)
        // f[m][n] --> ....---> f[0][0]
        char[] res = new char[f[m][n]];
        int p = f[m][n] - 1;
        i = m;
        j = n;
        while (i > 0 && j > 0) {
            if (pai[i][j] == 1) {
                --i;
            } else {
                if (pai[i][j] == 2) {
                    --j;
                } else {
                    res[p--] = A[i - 1];
                    --i;
                    --j;
                }
            }
        }

        for (p = 0; p < f[m][n]; p++) {
            System.out.println(res[p]);
        }
        System.out.println();

        return f[m][n];
    }

    @Test
    public void test02() {
        System.out.println(longestCommonSubsequence1("ABCD", "EDCA"));
        System.out.println(longestCommonSubsequence1("ABCD" , "EACB"));
    }

////////////////////////////////////////////////////////////////
    /**
     * @param A, B: Two strings.
     * @return: The length of longest common subsequence of A and B.
     */
    public int longestCommonSubsequence2(String A, String B) {
        int n = A.length();
        int m = B.length();
        int f[][] = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                if(A.charAt(i - 1) == B.charAt(j - 1))
                    f[i][j] = f[i - 1][j - 1] + 1;
            }
        }
        return f[n][m];
    }
////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////
}
/*
Given two strings, find the longest common subsequence (LCS).

Your code should return the length of LCS.

Have you met this question in a real interview? Yes
Clarification
What's the definition of Longest Common Subsequence?

https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
http://baike.baidu.com/view/2020307.htm
Example
For "ABCD" and "EDCA", the LCS is "A" (or "D", "C"), return 1.

For "ABCD" and "EACB", the LCS is "AC", return 2.
 */
