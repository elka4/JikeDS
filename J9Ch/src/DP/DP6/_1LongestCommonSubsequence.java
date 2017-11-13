package DP.DP6;
import org.junit.Test;

//双序列型动态规划

/*
A=“jiuzhang”,
B=“liji ang”
   j  i ang
• 输出:5(最长公共子串是jiang )

动态规划组成部分一:确定状态
• 设A长度是m, B长度是n
• 现在我们考虑最优策略产生出的最长公共子串(虽然还不知道是什么)
• 最后一步:观察A[m-1]和B[n-1]这两个字符是否作为一个对子在最优策 略中

情况一:对子中没有A[m-1]。     推论:A和B的最长公共子串就是A前m-1个字符和B前n个字符的最长公共子串
情况二:对子中没有B[n-1]。     推论:A和B的最长公共子串就是A前m个字符和B前n-1个字符的最长公共子串
情况三:对子中有A[m-1]-B[n-1]。推论:A和B的最长公共子串就是A前m-1个字符和B前n-1个字符的最长公共子串+A[m-1]
-----------------------------------------------------------------------------------------------

子问题：
• 要求A[0..m-1]和B[0..n-2]的最长公共子串，A[0..m-2]和B[0..n-1]的最长
公共子串和A[0..m-2]和B[0..n-2]的最长公共子串
• 原来是求A[0..m-1]和B[0..n-1]的最长公共子串
• 子问题
• 状态:设f[i][j]为A前i个字符A[0..i-1]和B前j个字符[0..j-1]的最长公共子串 的长度
-----------------------------------------------------------------------------------------------
动态规划组成部分二:转移方程

• 设f[i][j]为A前i个字符A[0..i-1]和B前j个字符[0..j-1]的最长公共子串的长度
• 要求f[m][n]

f[i][j] = max{f[i-1][j], f[i][j-1], f[i-1][j-1]+1|A[i-1]=B[j-1]}

情况一:A[0..i-2]和B[0..j- 1]的最长公共子串；

情况二:A[0..i-1]和 B[0..j-2]的最长公共子串

情况三:A[0..i-2]和B[0..j-2]的最长 公共子串+A[i-1]
-----------------------------------------------------------------------------------------------
动态规划组成部分三:初始条件和边界情况

• f[i][j]为A前i个字符A[0..i-1]和B前j个字符[0..j-1]的最长公共子串的长度
• 转移方程:f[i][j] = max{f[i-1][j], f[i][j-1], f[i-1][j-1]+1|A[i-1]=B[j-1]}
• 初始条件:空串和任何串的最长公共子串长度是0
    – f[0][j] = 0, j=0..n
    – f[i][0] = 0, i=0..m
-----------------------------------------------------------------------------------------------
动态规划组成部分四:计算顺序

• f[0][0], f[0][1], ..., f[0][n]
• f[1][0], f[1][1], ..., f[1][n]
•...
• f[m][0], f[m][1], ..., f[m][n]
• 答案是f[m][n]
• 时间复杂度(计算步数)O(MN)，空间复杂度(数组大小)O(MN) • 可以用滚动数组优化空间至O(N)
-----------------------------------------------------------------------------------------------

 */

/*
Longest Common Subsequence
 */
public class _1LongestCommonSubsequence {
    // 9Ch DP
    public int longestCommonSubsequence1(String AA, String BB) {
        char[] A = AA.toCharArray();
        char[] B = BB.toCharArray();

        int m = A.length;
        int n = B.length;
        //• 设f[i][j]为A前i个字符A[0..i-1]和B前j个字符[0..j-1]的最长公共子串的长度
        int[][] f = new int[m + 1][n + 1];
        int i, j;

        for (i = 0; i <= m; i++) {
            for (j = 0; j <= n; j++) {
                //init
                if (i == 0 || j ==0) {
                    f[i][j] = 0;//默认就是0，做了这个只是逻辑更清晰
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
        System.out.println(longestCommonSubsequence1("ABCD", "EDCA"));
        System.out.println(longestCommonSubsequence1("ABCD" , "EACB"));
    }

////////////////////////////////////////////////////////////////
    // 9Ch DP
    //和上面的相同，多了记录状态的数组，可以得到longestCommonSubsequence的String
    public int longestCommonSubsequence2(String AA, String BB) {
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
                    pai[i][j] = 1;
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

        //recover (reversely)为什么非要倒着处理？
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
                } else {//pai[i][j] == 3
                    res[p--] = A[i - 1];
                    --i;
                    --j;
                }
            }
        }

        for (p = 0; p < f[m][n]; p++) {
            System.out.print(res[p]);
        }
        System.out.println();

        return f[m][n];
    }

    @Test
    public void test02() {
        System.out.println(longestCommonSubsequence2("jiuzhang", "lijiang"));
//        System.out.println(longestCommonSubsequence1("ABCD" , "EACB"));
    }

////////////////////////////////////////////////////////////////
    /**
     * @param A, B: Two strings.
     * @return: The length of longest common subsequence of A and B.
     */
    public int longestCommonSubsequence3(String A, String B) {
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

    @Test
    public void test03() {
        System.out.println(longestCommonSubsequence3("ABCD", "EDCA"));
        System.out.println(longestCommonSubsequence3("ABCD" , "EACB"));
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
