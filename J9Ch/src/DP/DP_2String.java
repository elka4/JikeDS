package DP;
import org.junit.Test;

/*
1. Longest Common Subsequence
2. Interleaving String
3. Edit Distance
4. Distinct Subsequences
5. Regular Expression Matching
6. Wildcard Matching
7. Ones and Zeroes

 */
public class DP_2String {
//111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111//

    // 1
    //  Longest Common Subsequence
    //  9Ch DP
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
    //无注释
    public int longestCommonSubsequence1(String AA, String BB) {
        char[] A = AA.toCharArray();
        char[] B = BB.toCharArray();
        int m = A.length;
        int n = B.length;
        int[][] f = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j ==0) { f[i][j] = 0; continue; }
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                if (A[i - 1] == B[j - 1]) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + 1);
                }
            }
        }
        return f[m][n];
    }

    @Test
    public void testI_01() {
    /*
    For "ABCD" and "EDCA", the LCS is "A" (or "D", "C"), return 1.
    For "ABCD" and "EACB", the LCS is "AC", return 2.
    */
        System.out.println(longestCommonSubsequence1("ABCD", "EDCA"));
        System.out.println(longestCommonSubsequence1("ABCD" , "EACB"));
    }

    @Test
    public void testI_02() {
        System.out.println(longestCommonSubsequence1("134679" , "12357823"));
    }//137: 3


    //有注释
    public int longestCommonSubsequence11(String AA, String BB) {
        char[] A = AA.toCharArray();
        char[] B = BB.toCharArray();
        int m = A.length;
        int n = B.length;
        int[][] f = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                //init 默认就是0，做了这个只是逻辑更清晰
                if (i == 0 || j ==0) { f[i][j] = 0; continue; }
                // normal transiton function
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                if (A[i - 1] == B[j - 1]) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + 1);
                }
            }
        }
        return f[m][n];
    }

    //mine， 这个是完整清晰的版本
    //• 设f[i][j]为A前i个字符A[0..i-1]和B前j个字符[0..j-1]的最长公共子串的长度
    //f[i][j] = max{f[i-1][j], f[i][j-1], f[i-1][j-1]+1|A[i-1]=B[j-1]}
    public int longestCommonSubsequence111(String A, String B) {
        char[] ac = A.toCharArray();
        char[] bc = B.toCharArray();
        int m = ac.length;
        int n = bc.length;
        int[][] f = new int[m + 1][n + 1];
        //A, B都是空串
        f[0][0] = 0;

        // i == 0， 就是A为空串
        for (int j = 1; j <= n; j++) {
            f[0][j] = 0;
        }
        // j == 0， 就是B为空串
        for (int i = 1; i <= m; i++) {
            f[i][0] = 0;
        }
        //A, B都不是空串
        //• 最长公共子串也是公共子串:长度是L->选定了L个对应的对子
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //每次处理A中第i个字符ac[i - 1]和B中第j个字符bc[j - 1]
                //将结果存储在f[i][j]

                //情况一:对子中没有A[m-1]。     推论:A和B的最长公共子串就是A前m-1个字符和B前n个字符的最长公共子串
                //情况二:对子中没有B[n-1]。     推论:A和B的最长公共子串就是A前m个字符和B前n-1个字符的最长公共子串

                //情况一:A[0..i-2]和B[0..j- 1]的最长公共子串: f[i - 1][j]
                //情况二:A[0..i-1]和 B[0..j-2]的最长公共子串: f[i][j - 1]
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);//这两个值之前已经算出来了


                //情况三:对子中有A[m-1]-B[n-1]。推论:A和B的最长公共子串就是A前m-1个字符和B前n-1个字符的最长公共子串+A[m-1]
                //情况三:A[0..i-2]和B[0..j-2]的最长公共子串+A[i-1]: f[i - 1][j - 1] + 1
                if (ac[i - 1] == bc[j - 1]) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + 1);//1指的是A[i-1]
                }
            }
        }
        return f[m][n];
    }


//111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111//

//22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222//

    // 2
    //  97. Interleaving String
    //  mine
    //  determine whether s3 is formed by the interleaving of s1 and s2.

/*
    LintCode 29 Interleaving String

    • 题意:
    • 给定三个字符串A, B, X
    • 判断X是否是由A, B交错在一起形成
    – 即A是X的子序列，去掉A后，剩下的字符组成B
    • 例子:
    • 输入:A=“aabcc” B=“dbbac”, X=“aadbbcbcac” • 输出:True( X=“aadbbcbcac” )

    -----------------------------------------------------------------------------------------------

    动态规划组成部分一:确定状态

    • 首先，如果X的长度不等于A的长度+B的长度，直接输出False
    • 设A长度是m, B长度是n，X的长度是m+n
    • 最后一步:假设X是由A和B交错形成的，那么X的最后一个字符X[m+n-1]
    – 要么是A[m-1]
    • 那么X[0..m+n-2]是由A[0..m-2]和B[0..n-1]交错形成的
    – 要么是B[n-1]
    • 那么X[0..m+n-2]是由A[0..m-1]和B[0..n-2]交错形成的

    -----------------------------------------------------------------------------------------------

    子问题

    • 要求X[0..m+n-1]是否由A[0..m-1]和B[0..n-1]交错形成
    • 需要知道X[0..m+n-2]是否由A[0..m-2]和B[0..n-1]交错形成，以及 X[0..m+n-2]是否由A[0..m-1]和B[0..n-2]交错形成
    • 子问题
    • 状态:设f[s][i][j]为X前s个字符是否由A前i个字符A[0..i-1]和B前j个字符B[0..j-1]交错形成
    • 但是s=i+j，所以可以简化为:设f[i][j]为X前i+j个字符是否由A前i个字符 A[0..i-1]和B前j个字符B[0..j-1]交错形成

    -----------------------------------------------------------------------------------------------

    动态规划组成部分二:转移方程

    • 设f[i][j]为X前i+j个字符是否由A前i个字符A[0..i-1]和B前j个字符B[0..j-1]交错形成
    f[i][j] = (f[i-1][j] AND X[i+j-1]==A[i-1]) OR (f[i][j-1] AND X[i+j-1]==B[j-1])

    f[i][j]                         : X前i+j个字符是否由A 前i个字符和B前j个字 符交错形成
    f[i-1][j] AND X[i+j-1]==A[i-1]  : 情况一:X前i+j-1个字符由A前i-1 个字符和B前j个字符交错形成, X 第i+j个字符等于A第i个字符
    f[i][j-1] AND X[i+j-1]==B[j-1]  : 情况二:X前i+j-1个字符由A前i个 字符和B前j-1个字符交错形成, X第 i+j个字符等于B第j个字符


    -----------------------------------------------------------------------------------------------
    动态规划组成部分三:初始条件和边界情况

    • 设f[i][j]为X前i+j个字符是否由A前i个字符A[0..i-1]和B前j个字符B[0..j-1]交错形成
    • 转移方程
        – f[i][j] = (f[i-1][j] AND X[i+j-1]==A[i-1]) OR (f[i][j-1] AND X[i+j-1]==B[j-1])
    • 初始条件:空串由A的空串和B的空串交错形成àf[0][0] = True
    • 边界情况:如果i=0，不考虑情况一;如果j=0，不考虑情况二

    -----------------------------------------------------------------------------------------------

    动态规划组成部分四:计算顺序

    • f[0][0], f[0][1], ..., f[0][n]
    • f[1][0], f[1][1], ..., f[1][n] •...
    • f[m][0], f[m][1], ..., f[m][n]
    • 答案是f[m][n]
    • 时间复杂度(计算步数)O(MN)，空间复杂度(数组大小)O(MN)，可 以用滚动数组优化空间至O(N)
    -----------------------------------------------------------------------------------------------
*/
    //无注释
    public boolean isInterleaveI_1(String s1, String s2, String s3) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] c3 = s3.toCharArray();
        int m = c1.length;
        int n = c2.length;
        if (c3.length != m + n) { return false; }
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i > 0 && c3[i + j - 1] == c1[i - 1]) {
                    f[i][j] = f[i][j] | f[i - 1][j];
                }

                if (j > 0 && c3[i + j - 1] == c2[j - 1]) {
                    f[i][j] = f[i][j] | f[i][j - 1];
                }
            }
        }
        return f[m][n];
    }

    @Test
    public void test02() {
        /*
        For s1 = "aabcc", s2 = "dbbca"
        When s3 = "aadbbcbcac", return true.
        When s3 = "aadbbbaccc", return false.
         */
        System.out.println(isInterleaveI_1("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(isInterleaveI_1("aabcc", "dbbca", "aadbbbaccc"));
    }

    /*
        // i == 0， 就是A为空串
        for (int j = 1; j <= n; j++) {
            f[0][j] = 0;
        }
        // j == 0， 就是B为空串
        for (int i = 1; i <= m; i++) {
            f[i][0] = 0;
        }
     */

    //完整清晰版本
    //• 设f[i][j]为X前i+j个字符是否由A前i个字符A[0..i-1]和B前j个字符B[0..j-1]交错形成
    //f[i][j] = (f[i-1][j] AND X[i+j-1]==A[i-1]) OR (f[i][j-1] AND X[i+j-1]==B[j-1])
    public boolean isInterleaveI_11(String s1, String s2, String s3) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] c3 = s3.toCharArray();
        int m = c1.length;
        int n = c2.length;
        if (c3.length != m + n) { return false; }
        boolean[][] f = new boolean[m + 1][n + 1];

        //c1, c2都是空串
        f[0][0] = true;

        //c2是空串，c1不是。如果c1和c3中第i个字符c3[i - 1]和c1[i - 1]相同.
        for (int i = 1; i <= m; i++) {
            if (c3[i - 1] == c1[i - 1]) {
                f[i][0] = f[i - 1][0];
            }
        }
        //c1是空串，c2不是。如果c2和c3中第i个字符c3[j - 1]和c2[j - 1]相同
        for (int j = 1; j <= n; j++) {
            if (c3[j - 1] == c2[j - 1]) {
                f[0][j] = f[0][j - 1];
            }
        }

        //s1, s2都不是空串

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //每轮都是看如果c3的第i + j个字符和c1的第i个字符，c2的第j个字符是否相同
                //从而判断为c3前i+j个字符是否由c1前i个字符c1[0..i-1]和c2前j个字符c2[0..j-1]交错形成

                //如果c3的第i + j个字符和c1的第i个字符相同
                //f[i][j]
                if (c3[i + j - 1] == c1[i - 1]) {
                    f[i][j] = f[i][j] | f[i - 1][j];
                    //f[i][j] =   f[i - 1][j]; 这样也能pass, 因为f[i][j]默认为false，只需要看f[i - 1][j]
                }

                //如果c3的第i + j个字符和c2的第个字符相同
                if (c3[i + j - 1] == c2[j - 1]) {
                    f[i][j] = f[i][j] | f[i][j - 1];//这里不能忽略f[i][j], 因为此时f[i][j]在上面可能已经赋值为true。
                }
            }
        }
        return f[m][n];
    }
//22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222//

//33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333//
    // 3
    //  72. Edit Distance
    //  9Ch DP 动态规划版本去掉注释
    //  find the minimum number of steps required to convert word1 to word2.

    /*
    • 给定两个字符串A，B
    • 要求把A变成B，每次可以进行下面一种操作:  – 增加一个字符 – 去掉一个字符 – 替换一个字符
    • 最少需要多少次操作，即最小编辑距离
    • 输入:A=“mart”, B=“karma”        • 输出:3 (m换成k，t换成m，加上a)

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

    • 要求A[0..m-1]和B[0..n-2]的最小编辑距离，A[0..m-2]和B[0..n-1]的最小编辑距离和A[0..m-2]和B[0..n-2]的最小编辑距离
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
 */
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
                //                            delete        insert         replace
                f[i][j] = Math.min(Math.min(f[i - 1][j], f[i][j - 1]), f[i - 1][j - 1]) + 1;

                if (s1[i - 1] == s2[j - 1]) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 1]);
                }
            }
        }
        return f[m][n];
    }

    @Test
    public void test03() {
        //Given word1 = "mart" and word2 = "karma", return 3.
        System.out.println(minDistance("mart", "karma"));
    }

    //完整清晰
    //• 设f[i][j]为X前i+j个字符是否由A前i个字符A[0..i-1]和B前j个字符B[0..j-1]交错形成
    //f[i][j] = (f[i-1][j] AND X[i+j-1]==A[i-1]) OR (f[i][j-1] AND X[i+j-1]==B[j-1])
    public int minDistance2(String word1, String word2) {
        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();
        int m = s1.length;
        int n = s2.length;
        int[][] f = new int[m + 1][n + 1];

        f[0][0] = 0;

        //j == 0, word2是空串，对word1进行i步insert以变成word2
        for (int i = 1; i <= m; ++i) {
            f[i][0] = i;
        }

        //i == 0，word1是空串，对word1进行j步delete以变成word2
        for (int j = 1; j <= n; ++j) {
            f[0][j] = j;
        }

        //• 于是最优策略(以及所有合法策略)最终都是让A的最后一个字符变成B 的最后一个字符
        //• 设A长度是m, B长度是n
        //• 全部操作完成后A的长度也是n，并且A[n-1]=B[n-1]
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                //大情况1：需要进行一步操作，插入，删除，或者替换

                //情况一:A在最后插入B[n-1]        • 要将A[0..m-1]变成B[0..n-2]
                //情况二:A最后一个字符替换成B[n-1] • 要将A[0..m-2]变成B[0..n-2]
                //情况三:A删掉最后一个字符         • 要将A[0..m-2]变成B[0..n-1]

                //f[i][j-1]+1   情况一:A在最后插入 B[j-1]
                //f[i-1][j-1]+1 情况二:A最后一个字符替换成B[j-1]
                //f[i-1][j]+1   情况三:A删掉最后一个字符
                //                            delete        insert         replace
                f[i][j] = Math.min(Math.min(f[i - 1][j], f[i][j - 1]), f[i - 1][j - 1]) + 1;//1指1步操作


                //大情况1：不需要进行任何操作
                //情况四:A和B最后一个字符相等      • 要将A[0..m-2]变成B[0..n-2]
                //f[i-1][j-1]   情况四:A和B最后一个 字符相等
                if (s1[i - 1] == s2[j - 1]) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 1]);
                }
            }
        }
        return f[m][n];
    }

//33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333//

//4444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444//

    // 4
    //  115. Distinct Subsequences
    //  9Ch DP
    //  Implement regular expression matching with support for '.' and '*'.

/*
    • 给定两个字符串A[0..m-1]，B[0..n-1] • 问B在A中出现多少次(可以不连续)
    • 输入:A=“rabbbit”, B=“rabbit”  • 输出:3  – rabbbit – rabbbit – rabbbit
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
 */
    public int numDistinct(String s, String t) {
        char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();
        int m = c1.length;
        int n = c2.length;
        int[][] f = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                //s2 is empty //f[i][j] = 1; very important
                if (j == 0) { f[i][j] = 1; continue; }
                // s1 is empty, s2 is NOT empty
                if (i == 0) { f[i][j] = 0; continue; }

                f[i][j] = f[i - 1][j];
                if (c1[i - 1] == c2[j - 1]) {
                    f[i][j] += f[i - 1][j - 1];
                }
            }
        }
        return f[m][n];
    }
    @Test
    public void test04() {
        //Given S = "rabbbit", T = "rabbit", return 3.
        System.out.println(numDistinct("rabbbit", "rabbit"));
    }//3

    //• 问B在A中出现多少次(可以不连续)
    //• 设f[i][j]为B前j个字符B[0..j-1]在A前i个字符A[0..i-1]中出现多少次
    //• 转移方程: f[i][j] = f[i-1][j-1]|A[i-1]=B[j-1] + f[i-1][j]
    public int numDistinct11(String s, String t) {
        char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();
        int m = c1.length;
        int n = c2.length;
        int[][] f = new int[m + 1][n + 1];

        //空串在空串中出现1次
        f[0][0] = 1;

        //– 如果B是空串，B在A中出现次数是1
        //– f[i][0] = 1 (i = 0, 1, 2, ..., m)
        for (int i = 1; i <= m; ++i) {
            f[i][0] = 1;
        }

        //– 如果A是空串而B不是空串，B在A中出现次数是0
        //– f[0][j] = 0 (j = 1, 2, ..., n)
        for (int j = 1; j <= n; ++j) {
            f[0][j] = 0;
        }


        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                //情况1:B[j-1]不和 A[i-1]结成对子, 求B[0..n-1]在A[0..m-2]中出现多少次
                f[i][j] = f[i - 1][j];

                //情况2:B[j-1] = A[i-1]结成对子, 求B[0..n-2]在A[0..m-2]中出现多少次
                if (c1[i - 1] == c2[j - 1]) {
                    f[i][j] += f[i - 1][j - 1];
                }
            }
        }
        return f[m][n];
    }


//4444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444//

//555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555//

    // 5
    //  10. Regular Expression Matching
    //  9CH DP
    //  Implement regular expression matching with support for '.' and '*'.

    /*
    LintCode 154 Regular Expression Matching

    • 题意:
    • 给定两个字符串A，B
    • B是一个正则表达式，里面可能含有‘.’和‘*’
    – ‘.’ 可以匹配任何单个字符
    – ‘*’ 可以匹配0个或多个前一个字符 • 问A和B是否匹配
    • 例子:
    – isMatch("aa","a") → false
    – isMatch("aa","aa") → true
    – isMatch("aaa","aa") → false
    – isMatch("aa", "a*") → true
    – isMatch("aa", ".*") → true
    – isMatch("ab", ".*") → true
    – isMatch("aab", "c*a*b") → true
    -----------------------------------------------------------------------------------------------
    动态规划组成部分一:确定状态

    • 双序列型动态规划
    • 设A长度是m, B长度是n
    • 现在我们考虑A和B如何匹配
    • 最后一步:关注最后的字符
    • 主要取决于正则表达式B中最后的字符B[n-1]是什么

    • 如果B[n-1]是一个正常字符(非.非*)，则如果A[m-1]=B[n-1]，能否匹配
    取决于A[0..m-2]和B[0..n-2]是否匹配;否则不能匹配
    • 如果B[n-1]是’.’，则A[m-1]一定是和’.’匹配，之后能否匹配取决于A[0..m-2]
    和B[0..n-2]是否匹配
    • 如果B[n-1]是’*’，它代表B[n-2]=c可以重复0次或多次，它们是一个整体c*，
    需要考虑A[m-1]是0个c，还是多个c中的最后一个
        – A[m-1]是0个c，能否匹配取决于A[0..m-1]和B[0..n-3]是否匹配
        – A[m-1]是多个c中的最后一个，能否匹配取决于A[0..m-2]和B[0..n-1]是否匹配 • 这种情况必须A[m-1]=c或者c=‘.’
    • 要求A前m个字符和B前n个字符能否匹配，需要知道A前m个字符和B前 n-1个字符，
    A前m-1个字符和B前n个字符以及A前m个字符和B前n-2个 字符能否匹配
    • 子问题
    • 状态:设f[i][j]为A前i个字符A[0..i-1]和B前j个字符B[0..j-1]能否匹配
    -----------------------------------------------------------------------------------------------
    动态规划组成部分二:转移方程

    • 设f[i][j]为A前i个字符A[0..i-1]和B前j个字符B[0..j-1]能否匹配

    f[i][j] = f[i-1][j-1]，如果B[j-1]=‘.’或者A[i-1]=B[j-1]

    f[i][j] = f[i][j-2] OR (f[i-1][j] AND (B[j-2]=‘.’ OR B[j-2]=A[i-1]))，如果B[j-1]=‘*’
    -----------------------------------------------------------------------------------------------
    动态规划组成部分三:初始条件和边界情况

    • 设f[i][j]为A前i个字符A[0..i-1]和B前j个字符B[0..j-1]能否匹配
    • 空串和空正则表达式匹配:f[0][0] = TRUE • 空的正则表达式不能匹配长度>0的串
        – f[1][0] = ... = f[m][0] = FALSE
    • 注意:f[0][1..n]也用动态规划计算，但是因为没有A[-1]，所以只能用第 二种情况中的f[i][j-2]
    -----------------------------------------------------------------------------------------------
    动态规划组成部分四:计算顺序

    • f[0][0], f[0][1], ..., f[0][n]
    • f[1][0], f[1][1], ..., f[1][n]
    •...
    • f[m][0], f[m][1], ..., f[m][n]
    • 答案是f[m][n]
    • 时间复杂度(计算步数)O(MN)，空间复杂度(数组大小)O(MN)
    • 可以用滚动数组优化空间至O(N)
    -----------------------------------------------------------------------------------------------
     */

    //9Ch
    public boolean isMatch1(String s, String p) {
        char[] c1 = s.toCharArray();
        char[] c2 = p.toCharArray();
        int m = c1.length;
        int n = c2.length;
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;

        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (c2[j - 1] != '*') {
                    if (i > 0 && (c2[j - 1] == '.' || c1[i - 1] == c2[j - 1]))
                        f[i][j] = f[i - 1][j - 1];
                } else {                            // c2[j - 1] == '*'
                    if (j - 2 >= 0)
                        f[i][j] |= f[i][j - 2];
                    if (i >= 1 && j >= 2) {
                        if (c2[j - 2] == '.' || c2[j - 2] == c1[i - 1])
                            f[i][j] |= f[i - 1][j];
                    }
                }
            }
        }
        return  f[m][n];
    }

    @Test
    public void test05() {
        //isMatch("aa","a") → false
        //isMatch("aa","aa") → true
        //isMatch("aaa","aa") → false
        //isMatch("aa", "a*") → true
        //isMatch("aa", ".*") → true
        //isMatch("ab", ".*") → true
        //isMatch("aab", "c*a*b") → true
        System.out.println(isMatch1("aab", "c*a*b"));
    }


    //• 设f[i][j]为A前i个字符A[0..i-1]和B前j个字符B[0..j-1]能否匹配
    //f[i][j] = f[i-1][j-1]，如果B[j-1]=‘.’或者A[i-1]=B[j-1]
    //f[i][j] = f[i][j-2] OR (f[i-1][j] AND (B[j-2]=‘.’ OR B[j-2]=A[i-1]))，如果B[j-1]=‘*’




/*
    Easy DP Java Solution with detailed Explanation
    This Solution use 2D DP. beat 90% solutions, very simple.

    Here are some conditions to figure out, then the logic can be very straightforward.

    1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
    2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
    3, If p.charAt(j) == '*':
    here are two sub conditions:
            1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
            2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
    dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a
    or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
    or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
    Here is the solution
*/

    public boolean isMatch4(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        //空串对空pattern
        dp[0][0] = true;
        //
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i-1]) {
                dp[0][i+1] = true;
            }
        }

        for (int i = 0 ; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];

                if (p.charAt(j) == s.charAt(i)) {
                    dp[i+1][j+1] = dp[i][j];
                }

                //2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
                if (p.charAt(j) == '.') {
                    dp[i+1][j+1] = dp[i][j];
                }

                //3, If p.charAt(j) == '*':

                if (p.charAt(j) == '*') {
                    //1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]
                    // in this case, a* only counts as empty
                    if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                        dp[i+1][j+1] = dp[i+1][j-1];
                    }
                    //2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
                    else {
                        dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
                    }
                }
            }
        }
        return dp[m][n];
    }

    // use char[][]
    public boolean isMatch44(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();
        int m = sc.length;
        int n = pc.length;
        //dp[i][j] s的前i个字符sc[i - 1] 与p的前j个字符pc[i - 1]是否匹配
        boolean[][] dp = new boolean[m+1][n+1];
        //空串 对 空pattern 是正确的
        dp[0][0] = true;

        //i == 0 空串对于各种pattern
        //对于pattern中的'*'，
        for (int j = 0; j < n; j++) {
            if (pc[j] == '*') {
                dp[0][j + 1] = dp[0][j - 1];
            }
        }

        for (int i = 0 ; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //1, If sc[i] == pc[j] :  dp[i][j] = dp[i-1][j-1];
                //
                if (sc[i] == pc[j]) {
                    dp[i+1][j+1] = dp[i][j];
                }

                //2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
                if (pc[j] == '.') {
                    dp[i+1][j+1] = dp[i][j];
                }

                //3, If p.charAt(j) == '*':

                if (pc[j] == '*') {
                    //1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]
                    // in this case, a* only counts as empty
                    if (pc[j-1] != sc[i] && pc[j-1] != '.') {
                        dp[i+1][j+1] = dp[i+1][j-1];
                    }
                    //2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
                    else {
                        dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
                    }
                }
            }
        }
        return dp[m][n];
    }

    // use char[][]
    public boolean isMatch444(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();
        int m = sc.length;
        int n = pc.length;
        //dp[i][j] s的前i个字符sc[i - 1] 与p的前j个字符pc[i - 1]是否匹配
        boolean[][] dp = new boolean[m+1][n+1];
        //空串 对 空pattern 是正确的
        dp[0][0] = true;

        //i == 0 空串对于各种pattern
        //对于pattern中的'*'，
        for (int j = 1; j < n + 1; j++) {
            if (pc[j-1] == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1 ; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                //每轮处理s前i个字符和p前j个字符

                //1, If sc[i] == pc[j] :  dp[i][j] = dp[i-1][j-1];
                //s第i个字符和p第j个字符相同
                //dp[i][j]：p前j个字符与s前i个字符是否匹配
                //取决于s前i-1个字符与p前j-1个字符是否匹配dp[i-1][j-1]
                if (pc[j-1] == sc[i-1]) {
                    dp[i][j] = dp[i-1][j-1];
                }

                //2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
                //p第j个字符为'.'，
                //dp[i][j]：那么s前i个字符与p前j个字符是否匹配
                //取决于s前i-1个字符与p前j-1个字符是否匹配dp[i-1][j-1]
                if (pc[j-1] == '.') {
                    dp[i][j] = dp[i-1][j-1];
                }

                //3, If p.charAt(j) == '*':
                //p第j个字符，当前字符为'*'，
                if (pc[j - 1] == '*') {
                    //1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]
                    // in this case, a* only counts as empty
                    //p第j-1个字符和s第i个字符不同，并且p第j-1个字符不是'.'
                    //那么s前i个字符与p前j个字符是否匹配，取决于s前i个字符和p前j-2个字符是否匹配

                    //p的前一个字符和s的最后一个字符不匹配，p的后面两个不用考虑
                    if (pc[j - 2] != sc[i - 1] && pc[j - 2] != '.') {
                        dp[i][j] = dp[i][j-2];
                    }
                    //2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
                    //p第j-1个字符和s第i个字符相同，或者p第j-1个字符是'.'

                    //p的前一个字符pc[j - 2]和s的最后一个字符匹配sc[i - 1]，以下三种情况都可以满足匹配
                    //不考虑p的最后1个dp[i][j-1]
                    //不考虑p的最后2个dp[i][j-2]
                    //不考虑s的最后1个dp[i-1][j]
                    else {
                        dp[i][j] = (dp[i][j-1] || dp[i-1][j] || dp[i][j-2]);
                    }
                }
            }
        }
        return dp[m][n];
    }
//555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555//

//66666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666//

    // 6
    //  44. Wildcard Matching
    //  Java DP Accepted

    /*
    -----------------------------------------------------------------------------------------------
    LintCode 192 Wildcard Matching

    • 题意:
    • 给定两个字符串A，B
    • B是一个正则表达式，里面可能含有’?’和’*’
    – ‘?’ 可以匹配任何单个字符
    – ‘*’ 可以匹配0个或多个任意字符 • 问A和B是否匹配
    • 例子:
    – isMatch("aa","a") → false
    – isMatch("aa","aa") → true
    – isMatch("aaa","aa") → false
    – isMatch("aa", "*") → true
    – isMatch("aa", "a*") → true
    – isMatch("ab", "?*") → true
    – isMatch("aab", "c*a*b") → false
    -----------------------------------------------------------------------------------------------
    动态规划组成部分一:确定状态

    • 双序列型动态规划
    • 和Regular Expression Matching很类似，因为’.’和’?’作用相同，但是这 题中’*’可以匹配0个或多个任意字符
    • 设A长度是m, B长度是n
    • 现在我们考虑A和B如何匹配
    • 关注最后的字符
    • 主要取决于Wildcard中B[n-1]是什么

    • 如果B[n-1]是一个正常字符(非?非*)，则如果A[m-1]=B[n-1]，能否匹
    配取决于A[0..m-2]和B[0..n-2]是否匹配;否则不能匹配
    • 如果B[n-1]是’?’，则A[m-1]一定是和’?’匹配，之后能否匹配取决于
    A[0..m-2]和B[0..n-2]是否匹配
    • 如果B[n-1]是’*’，它可以匹配0个或任意多个字符，需要考虑A[m-1]有没
    有被这个*匹配
    – A[m-1]不被‘*’匹配，能否匹配取决于A[0..m-1]和B[0..n-2]是否匹配 – A[m-1]被‘*’匹配，
    能否匹配取决于A[0..m-2]和B[0..n-1]是否匹配

    -----------------------------------------------------------------------------------------------
    子问题
    • 要求A前m个字符和B前n个字符能否匹配，需要知道A前m-1个字符和B 前n-1个字符，
    A前m个字符和B前n-1个字符以及A前m-1个字符和B前n 个字符能否匹配
    • 子问题
    • 状态:设f[i][j]为A前i个字符A[0..i-1]和B前j个字符B[0..j-1]能否匹配
    -----------------------------------------------------------------------------------------------
    动态规划组成部分二:转移方程

    • 设f[i][j]为A前i个字符A[0..i-1]和B前j个字符B[0..j-1]能否匹配

    f[i][j] = f[i-1][j-1]，如果B[j-1]=‘?’或者A[i-1]=B[j-1]

    f[i][j] = f[i-1][j] OR f[i][j-1]，如果B[j-1]=‘*’
    -----------------------------------------------------------------------------------------------
    动态规划组成部分三:初始条件和边界情况

    • 设f[i][j]为A前i个字符A[0..i-1]和B前j个字符B[0..j-1]能否匹配
    • 空串和空Wildcard匹配:f[0][0] = TRUE • 空的Wildcard不能匹配长度>0的串
        – f[1][0] = ... = f[m][0] = FALSE
    • f[0][1..n]也用动态规划计算，但是因为没有A[-1]，所以只能用第二种情 况中的f[i][j-1]
    -----------------------------------------------------------------------------------------------
    动态规划组成部分四:计算顺序

    • f[0][0], f[0][1], ..., f[0][n]
    • f[1][0], f[1][1], ..., f[1][n]
    •...
    • f[m][0], f[m][1], ..., f[m][n]
    • 答案是f[m][n]
    • 时间复杂度(计算步数)O(MN)，空间复杂度(数组大小)O(MN)
    • 可以用滚动数组优化空间至O(N)
    -----------------------------------------------------------------------------------------------
 */
    public boolean isMatch2(String s, String p) {
        int m = s.length(), n = p.length();
        char[] ws = s.toCharArray();
        char[] wp = p.toCharArray();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        for (int j = 1; j <= n; j++)
            dp[0][j] = dp[0][j - 1] && wp[j - 1] == '*';

        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 0) {
                    dp[0][j] = dp[0][j - 1] && wp[j - 1] == '*';
                    continue;
                }
                if (wp[j - 1] == '?' || ws[i - 1] == wp[j - 1])
                    dp[i][j] = dp[i - 1][j - 1];
                else if (wp[j - 1] == '*')
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

    @Test
    public void test06() {
        //isMatch("aa","a") → false
        //isMatch("aa","aa") → true
        //isMatch("aaa","aa") → false
        //isMatch("aa", "*") → true
        //isMatch("aa", "a*") → true
        //isMatch("ab", "?*") → true
        //isMatch("aab", "c*a*b") → false
        System.out.println(isMatch2("aab", "?*"));
    }

    //注释
    public boolean isMatch22(String s, String p) {
        int m = s.length(), n = p.length();
        char[] ws = s.toCharArray();
        char[] wp = p.toCharArray();
        boolean[][] dp = new boolean[m+1][n+1];
        //空串匹配空串
        dp[0][0] = true;
        //如果s是空串，取决于p中当前字符是否为*和之前字符串是否匹配
        for (int j = 1; j <= n; j++)
            dp[0][j] = dp[0][j - 1] && wp[j - 1] == '*';


        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //如果p最后一个字符以?或者相同来匹配s
                //忽略s和p的最后一个字符
                if (wp[j - 1] == '?' || ws[i - 1] == wp[j - 1])
                    dp[i][j] = dp[i - 1][j - 1];
                    //如果p最后一个字符是*
                    //忽略s或者p的最后一个字符
                else if (wp[j - 1] == '*')
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
            }
        }
        return dp[m][n];
    }


//66666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666//

//7777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777//

    // 7
    //find the maximum number of strings that you can form with given m 0s and n 1s.
    // Each 0 and 1 can be used at most once.
    //  474. Ones and Zeroes


    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {
            int[] cost = count(s);
            for (int i = m; i >= cost[0]; i--)
                for (int j = n; j >= cost[1]; j--)
                    dp[i][j] = Math.max(dp[i][j], dp[i - cost[0]][j - cost[1]] + 1);
        }
        return dp[m][n];
    }

    public int[] count(String str) {
        int[] cost = new int[2];
        for (int i = 0; i < str.length(); i++)
            cost[str.charAt(i) - '0']++;
        return cost;
    }

    @Test
    public void test07() {
        /*
        Example 1:
        Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
        Output: 4  “10,”0001”,”1”,”0”

        Example 2:
        Input: Array = {"10", "0", "1"}, m = 1, n = 1
        Output: 2
        */
        System.out.println(findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
        System.out.println(findMaxForm(new String[]{"10", "0", "1"}, 1, 1));
    }
//7777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777//

//-------------------------------------------------------------------------//////////
}
