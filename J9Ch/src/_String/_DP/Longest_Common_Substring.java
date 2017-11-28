package _String._DP;

//  http://www.lintcode.com/zh-cn/problem/longest-common-substring/
//
public class Longest_Common_Substring {
//------------------------------------------------------------------------------
    //1
    // 9Ch
    // 严格的说，这个算法是递推，而不是动态规划，但是可以用动态规划的四要素去分析换个解答。
    // 为什么不是动态规划？因为最暴力的方式也就是 O(n^3) 可以找到A所有的Substring然后看看在不在B里。
    public int longestCommonSubstring_J1(String A, String B) {
        // state: f[i][j] is the length of the longest lcs
        // ended with A[i - 1] & B[j - 1] in A[0..i-1] & B[0..j-1]
        int n = A.length();
        int m = B.length();
        int[][] f = new int[n + 1][m + 1];

        // initialize: f[i][j] is 0 by default

        // function: f[i][j] = f[i - 1][j - 1] + 1 or 0
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    f[i][j] = 0;
                }
            }
        }

        // answer: max{f[i][j]}
        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                max = Math.max(max, f[i][j]);
            }
        }

        return max;
    }
//------------------------------------------------------------------------------
    //2
    //mine
    public int longestCommonSubstring_J2(String A, String B) {
        char[] ac = A.toCharArray();
        char[] bc = B.toCharArray();
        int n = ac.length;
        int m = bc.length;
        int[][] f = new int[n + 1][m + 1];
        int max = 0;
        f[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (ac[i - 1] == bc[j - 1]) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    f[i][j] = 0;
                }
                max = Math.max(max, f[i][j]);
            }
        }
        return max;
    }

//------------------------------------------------------------------------------
}
/*
79. 最长公共子串

给出两个字符串，找到最长公共子串，并返回其长度。

 注意事项

子串的字符应该连续的出现在原字符串中，这与子序列有所不同。

样例
给出A=“ABCD”，B=“CBCE”，返回 2

挑战
O(n x m) time and memory.

标签
LintCode 版权所有 字符串处理
相关题目
中等 最长公共子序列
 */