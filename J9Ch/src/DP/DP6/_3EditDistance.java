package DP.DP6;

//双序列型动态规划


/*
LintCode 119 Edit Distance
• 题意:
• 给定两个字符串A，B
• 要求把A变成B，每次可以进行下面一种操作:
– 增加一个字符 – 去掉一个字符 – 替换一个字符
• 最少需要多少次操作，即最小编辑距离
• 例子:
• 输入:A=“mart”, B=“karma”
• 输出:3 (m换成k，t换成m，加上a)

-----------------------------------------------------------------------------------------------
动态规划组成部分一:确定状态

• 设A长度是m, B长度是n
• 全部操作完成后A的长度也是n，并且A[n-1]=B[n-1]
• 于是最优策略(以及所有合法策略)最终都是让A的最后一个字符变成B 的最后一个字符

情况一:A在最后插入B[n-1]        • 要将A[0..m-1]变成B[0..n-2]
情况二:A最后一个字符替换成B[n-1] • 要将A[0..m-2]变成B[0..n-2]
情况三:A删掉最后一个字符         • 要将A[0..m-2]变成B[0..n-1]
情况四:A和B最后一个字符相等      • 要将A[0..m-2]变成B[0..n-2]

-----------------------------------------------------------------------------------------------
子问题

• 要求A[0..m-1]和B[0..n-2]的最小编辑距离，A[0..m-2]和B[0..n-1]的最小
编辑距离和A[0..m-2]和B[0..n-2]的最小编辑距离
• 原来是求A[0..m-1]和B[0..n-1]的最小编辑距离
• 子问题
• 状态:设f[i][j]为A前i个字符A[0..i-1]和B前j个字符B[0..j-1]的最小编辑距离
-----------------------------------------------------------------------------------------------
动态规划组成部分二:转移方程

• 设f[i][j]为A前i个字符A[0..i-1]和B前j个字符B[0..j-1]的最小编辑距离
• 要求f[m][n]
f[i][j] = min{f[i][j-1]+1, f[i-1][j-1]+1, f[i-1][j]+1, f[i-1][j-1]|A[i-1]=B[j-1]}
f[i][j-1]+1   情况一:A在最后插入 B[j-1]
f[i-1][j-1]+1 情况二:A最后一个字符 替换成B[j-1]
f[i-1][j]+1   情况三:A删掉最后一个 字符
f[i-1][j-1]   情况四:A和B最后一个 字符相等
-----------------------------------------------------------------------------------------------
动态规划组成部分三:初始条件和边界情况

• 设f[i][j]为A前i个字符A[0..i-1]和B前j个字符B[0..j-1]的最小编辑距离
• 转移方程: f[i][j] = min{f[i][j-1]+1, f[i-1][j-1]+1, f[i-1][j]+1, f[i-1][j-1]|A[i- 1]=B[j-1]}
• 初始条件:一个空串和一个长度为L的串的最小编辑距离是L
    – f[0][j] = j (j = 0, 1, 2, ..., n)
    – f[i][0] = i (i = 0, 1, 2, ..., m)

-----------------------------------------------------------------------------------------------
动态规划组成部分四:计算顺序

• f[0][0], f[0][1], ..., f[0][n]
• f[1][0], f[1][1], ..., f[1][n]
•...
• f[m][0], f[m][1], ..., f[m][n]
• 答案是f[m][n]
• 时间复杂度(计算步数)O(MN)，空间复杂度(数组大小)O(MN) • 可以用滚动数组优化空间至O(N)
-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------
 */


//  72. Edit Distance
//  https://leetcode.com/problems/edit-distance/description/
//
public class _3EditDistance {


/*
    Java DP solution - O(nm)
    Let following be the function definition :-

    f(i, j) := minimum cost (or steps) required to convert first i characters of word1 to first j characters of word2

    Case 1: word1[i] == word2[j], i.e. the ith the jth character matches.

    f(i, j) = f(i - 1, j - 1)
    Case 2: word1[i] != word2[j], then we must either insert, delete or replace, whichever is cheaper

    f(i, j) = 1 + min { f(i, j - 1), f(i - 1, j), f(i - 1, j - 1) }
    f(i, j - 1) represents insert operation
    f(i - 1, j) represents delete operation
    f(i - 1, j - 1) represents replace operation
    Here, we consider any operation from word1 to word2. It means, when we say insert operation, we insert a new character after word1 that matches the jth character of word2. So, now have to match i characters of word1 to j - 1 characters of word2. Same goes for other 2 operations as well.

    Note that the problem is symmetric. The insert operation in one direction (i.e. from word1 to word2) is same as delete operation in other. So, we could choose any direction.

    Above equations become the recursive definitions for DP.

    Base Case:

    f(0, k) = f(k, 0) = k
    Below is the direct bottom-up translation of this recurrent relation. It is only important to take care of 0-based index with actual code :-
*/

    public class Solution02 {
        public int minDistance(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();

            int[][] cost = new int[m + 1][n + 1];
            for(int i = 0; i <= m; i++)
                cost[i][0] = i;
            for(int i = 1; i <= n; i++)
                cost[0][i] = i;

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(word1.charAt(i) == word2.charAt(j))
                        cost[i + 1][j + 1] = cost[i][j];
                    else {
                        int a = cost[i][j];
                        int b = cost[i][j + 1];
                        int c = cost[i + 1][j];
                        cost[i + 1][j + 1] = a < b ? (a < c ? a : c) : (b < c ? b : c);
                        cost[i + 1][j + 1]++;
                    }
                }
            }
            return cost[m][n];
        }
    }
//    Time complexity : If n is the length of word1, m of word2, because of the two indented loops, it is O(nm)
//-------------------------------------------------------------------------///////////
    //  https://web.stanford.edu/class/cs124/lec/med.pdf

/*My Accepted Java Solution
    Hi:

    This is a very interesting question and I found a youtube video that helps a lot.
    Basically the idea is to build up the solution step by step and keep track of the previous optimal solution in a 2D array. In this 2D array dp, dp[i][j] means the operation needed to transform word1(0, i) to word2(0,j).

    There can be three conditions:

            1, word1[i] == word2[j] : then no operation needed. dp[i][j] == dp[i-1][j-1]

            2, Do one operation on word1[i-1][j]. dp[i][j] = dp[i-1][j] + 1

            3, Do one operation on word2[i][j-1]. dp[i][j] = dp[i][j-1] + 1

            for 2 and 3, the reason it works is that we know the optimal ways to transfrom word1(0,i) to word2(0,j-1) and word1(0,i-1) to word(0,j) ( Delete ("abc" to "ab") or Insert ("ab" to "abc") ). Now all we need to one more operation.

    The code will be:*/

    public int minDistance02(String word1, String word2) {
        if (word1.equals(word2)) {
            return 0;
        }
        if (word1.length() == 0 || word2.length() == 0) {
            return Math.abs(word1.length() - word2.length());
        }
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
//    Remeber that we start from dp[0][0], which is an empty string to an empty string.
//-------------------------------------------------------------------------///////////
    // 9Ch DP 动态规划版本去掉注释
    public int minDistance(String word1, String word2) {
        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();
        int m = s1.length;
        int n = s2.length;

        int[][] f = new int[m + 1][n + 1];

        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                if (i == 0) {f[i][j] = j; continue;}
                if (j == 0) {f[i][j] = i; continue;}

                // +1, important!
                //                       delete        insert         replace
                f[i][j] = Math.min(Math.min(f[i - 1][j], f[i][j - 1]), f[i - 1][j - 1]) + 1;

                if (s1[i - 1] == s2[j - 1]) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 1]);
                }
            }
        }

        return f[m][n];
    }


//-------------------------------------------------------------------------
// 9Ch DP 不能通过
public int minDistance00(String word1, String word2) {
    char[] c1 = word1.toCharArray();
    char[] c2 = word2.toCharArray();
    int m = c1.length;
    int n = c2.length;

    int i, j;
    int[][] f = new int[2][n + 1];
    int old, now = 0;

    for (i = 0; i <= m; i++) {
        old = now;
        now = 1 - now;
        for (j = 0; j <= n; j++) {
            if (i == 0) {
                f[now][j] = j;
                continue;
            }

            if (j == 0) {
                f[now][j] = i;
                continue;
            }
            f[now][j] = Math.min(Math.min(f[old][j], f[now][j - 1]), f[old][j - 1]) + 1;


            // same
            if (c1[i - 1] == c2[j - 1]) {
                f[now][j] = Math.min(f[now][j], f[old][j - 1]);
            }

        }
    }
    return f[now][n];
}

//-------------------------------------------------------------------------
    public int minDistance0(String word1, String word2) {
        // write your code here
        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n+1][m+1];
        for(int i=0; i< m+1; i++){
            dp[0][i] = i; //一次delete
        }
        for(int i=0; i<n+1; i++){
            dp[i][0] = i;//一次insert
        }


        for(int i = 1; i<n+1; i++){
            for(int j=1; j<m+1; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = 1 +
                            Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
                }
            }
        }
        return dp[n][m];
    }
//-------------------------------------------------------------------------

    public int minDistance1(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n+1][m+1];
        for(int i=0; i< m+1; i++){
            dp[0][i] = i;
        }
        for(int i=0; i<n+1; i++){
            dp[i][0] = i;
        }


        for(int i = 1; i<n+1; i++){
            for(int j=1; j<m+1; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
                }
            }
        }
        return dp[n][m];
    }

//-------------------------------------------------------------------------

    // 动态规划班版本
    /**
     * @param word1 & word2: Two string.
     * @return: The minimum number of steps.
     */
    public int minDistance2(String word1, String word2) {
        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();
        int i, j;
        int m = s1.length;
        int n = s2.length;

        int[][] f = new int[m + 1][n + 1];

        // commented part is for outputting solution
        // 'I', 'D', 'R', 'S'
        //char[][] pi = new char[m + 1][n + 1];
        for (i = 0; i <= m; ++i) {
            for (j = 0; j <= n; ++j) {
                if (i == 0) {
                    f[i][j] = j;
                    continue;
                }

                if (j == 0) {
                    f[i][j] = i;
                    continue;
                }

                // i > 0, j > 0

                // +1, important!
                //                       delete        insert         replace
                f[i][j] = Math.min(Math.min(f[i - 1][j], f[i][j - 1]), f[i - 1][j - 1]) + 1;
                /*if (f[i][j] == f[i - 1][j] + 1) {
                    pi[i][j] = 'D';
                }
                else {
                    if (f[i][j] == f[i][j - 1] + 1) {
                        pi[i][j] = 'I';
                    }
                    else {
                        pi[i][j] = 'R';
                    }
                }*/

                if (s1[i - 1] == s2[j - 1]) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 1]);
                    //  pi[i][j] = 'S';
                }
            }
        }

        /*i = m;
        j = n;

        while (i > 0 || j > 0) {
            if (pi[i][j] == 'D') {
                System.out.println("Delete A's " + i + "-th letter from A");
                --i;
                continue;
            }

            if (pi[i][j] == 'I') {
                System.out.println("Insert B's " + j + "-th letter of B to A");
                --j;
                continue;
            }

            if (pi[i][j] == 'R') {
                System.out.println("Replace the A's " + i + "-th letter to B's " + j + "-th letter");
                --i;
                --j;
                continue;
            }

            if (pi[i][j] == 'S') {
                --i;
                --j;
                continue;
            }
        }*/

        return f[m][n];
    }
//-------------------------------------------------------------------------
    // leet
    //Java DP solution - O(nm)

    public int minDistance3(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] cost = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++)
            cost[i][0] = i;
        for(int i = 1; i <= n; i++)
            cost[0][i] = i;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(word1.charAt(i) == word2.charAt(j))
                    cost[i + 1][j + 1] = cost[i][j];
                else {
                    int a = cost[i][j];
                    int b = cost[i][j + 1];
                    int c = cost[i + 1][j];
                    cost[i + 1][j + 1] = a < b ? (a < c ? a : c) : (b < c ? b : c);
                    cost[i + 1][j + 1]++;
                }
            }
        }
        return cost[m][n];
    }
//-------------------------------------------------------------------------
    // My Accepted Java Solution

    public int minDistance4(String word1, String word2) {
        if (word1.equals(word2)) {
            return 0;
        }
        if (word1.length() == 0 || word2.length() == 0) {
            return Math.abs(word1.length() - word2.length());
        }
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
//-------------------------------------------------------------------------
//-------------------------------------------------------------------------


}
/*
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Have you met this question in a real interview? Yes
Example
Given word1 = "mart" and word2 = "karma", return 3.
 */
