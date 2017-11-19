package DP.DP6;

//双序列型动态规划

/*

LintCode 118 Distinct Subsequences
-----------------------------------------------------------------------------------------------
• 题意:
• 给定两个字符串A[0..m-1]，B[0..n-1] • 问B在A中出现多少次(可以不连续)
• 例子
• 输入:A=“rabbbit”, B=“rabbit”
• 输出:3
– rabbbit – rabbbit – rabbbit

-----------------------------------------------------------------------------------------------
题目分析
• 双序列型动态规划
• B在A中出现多少次(可以不连续)
• 如果至少出现一次，那么A和B的最长公共子串就是B，而且也不能更长
• 用最长公共子串的思路:对应对子
• 但不同的是，B的每一个字符都必须出现在一个对子里
• 所以这题的“最后一步”以B为出发点
-----------------------------------------------------------------------------------------------
动态规划组成部分一:确定状态

情况1:B[n-1] = A[m-1]，结成对子    求B[0..n-2]在A[0..m-2]中出现多少次
情况2:B[n-1]不和 A[m-1]结成对子    求B[0..n-1]在A[0..m-2]中出现多少次

-----------------------------------------------------------------------------------------------
子问题

• 要求B[0..n-1]在A[0..m-1]中出现多少次
• 需要知道B[0..n-1]在A[0..m-2]中出现多少次，以及B[0..n-2]在A[0..m-2] 中出现多少次
• 子问题
• 状态:设f[i][j]为B前j个字符B[0..j-1]在A前i个字符A[0..i-1]中出现多少次
-----------------------------------------------------------------------------------------------
动态规划组成部分二:转移方程

• 设f[i][j]为B前j个字符B[0..j-1]在A前i个字符A[0..i-1]中出现多少次
• 要求f[m][n]
f[i][j] = f[i-1][j-1]|A[i-1]=B[j-1] + f[i-1][j]

f[i-1][j-1]|A[i-1]=B[j-1]  情况1:B[j-1] = A[i-1]，结成对子
f[i-1][j]                  情况2:B[j-1]不和 A[i-1]结成对子
-----------------------------------------------------------------------------------------------
动态规划组成部分三:初始条件和边界情况

• 设f[i][j]为B前j个字符B[0..j-1]在A前i个字符A[0..i-1]中出现多少次
• 转移方程: f[i][j] = f[i-1][j-1]|A[i-1]=B[j-1] + f[i-1][j]
• 初始条件:
    – 如果B是空串，B在A中出现次数是1
    – f[i][0] = 1 (i = 0, 1, 2, ..., m)
    – 如果A是空串而B不是空串，B在A中出现次数是0
    – f[0][j] = 0 (j = 1, 2, ..., n)
-----------------------------------------------------------------------------------------------
动态规划组成部分四:计算顺序

• 初始化f[0][0], f[0][1], ..., f[0][n]
• f[1][0], f[1][1], ..., f[1][n]
•...
• f[m][0], f[m][1], ..., f[m][n]
• 答案是f[m][n]
• 时间复杂度(计算步数)O(MN)，空间复杂度(数组大小)O(MN)
• 可以用滚动数组优化空间至O(N)
-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------
 */

//  Distinct Subsequences
//  https://leetcode.com/problems/distinct-subsequences/description/
//
public class _4DistinctSubsequences {
/*
    Easy to understand DP in Java
    The idea is the following:

    we will build an array mem where mem[i+1][j+1] means that S[0..j] contains T[0..i] that many times as distinct subsequences. Therefor the result will be mem[T.length()][S.length()].
    we can build this array rows-by-rows:
    the first row must be filled with 1. That's because the empty string is a subsequence of any string but only 1 time. So mem[0][j] = 1 for every j. So with this we not only make our lives easier, but we also return correct value if T is an empty string.
    the first column of every rows except the first must be 0. This is because an empty string cannot contain a non-empty string as a substring -- the very first item of the array: mem[0][0] = 1, because an empty string contains the empty string 1 time.
    So the matrix looks like this:

    S 0123....j
    T +----------+
            |1111111111|
            0 |0         |
            1 |0         |
            2 |0         |
            . |0         |
            . |0         |
    i |0         |
    From here we can easily fill the whole grid: for each (x, y), we check if S[x] == T[y] we add the previous item and the previous item in the previous row, otherwise we copy the previous item in the same row. The reason is simple:

            if the current character in S doesn't equal to current character T, then we have the same number of distinct subsequences as we had without the new character.
            if the current character in S equal to the current character T, then the distinct number of subsequences: the number we had before plus the distinct number of subsequences we had with less longer T and less longer S.
    An example:
    S: [acdabefbc] and T: [ab]

    first we check with a:

            *  *
    S = [acdabefbc]
    mem[1] = [0111222222]
    then we check with ab:

            *  * ]
    S = [acdabefbc]
    mem[1] = [0111222222]
    mem[2] = [0000022244]
    And the result is 4, as the distinct subsequences are:

    S = [a   b    ]
    S = [a      b ]
    S = [   ab    ]
    S = [   a   b ]
    See the code in Java:
*/

    public int numDistinct01(String S, String T) {
        // array creation
        int[][] mem = new int[T.length()+1][S.length()+1];

        // filling the first row: with 1s
        for(int j=0; j<=S.length(); j++) {
            mem[0][j] = 1;
        }

        // the first column is 0 by default in every other rows but the first, which we need.

        for(int i=0; i<T.length(); i++) {
            for(int j=0; j<S.length(); j++) {
                if(T.charAt(i) == S.charAt(j)) {
                    mem[i+1][j+1] = mem[i][j] + mem[i+1][j];
                } else {
                    mem[i+1][j+1] = mem[i+1][j];
                }
            }
        }

        return mem[T.length()][S.length()];
    }
//-------------------------------------------------------------------------////////

//-------------------------------------------------------------------------////////

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
                if (j == 0) {//s2 is empty
                    f[i][j] = 1;//very important
                    continue;
                }
                if (i == 0) {// s1 is empty, s2 is NOT empty
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

//-------------------------------------------------------------------------////////
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
//-------------------------------------------------------------------------////////

//-------------------------------------------------------------------------////////
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