package DP.DP6;
import org.junit.Test;

public class DP_2String {
    //  Longest Common Subsequence
    //  9Ch DP
    //• 设f[i][j]为A前i个字符A[0..i-1]和B前j个字符[0..j-1]的最长公共子串的长度
    public int longestCommonSubsequence1(String AA, String BB) {
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

    @Test
    public void test01() {
            /*
    For "ABCD" and "EDCA", the LCS is "A" (or "D", "C"), return 1.
    For "ABCD" and "EACB", the LCS is "AC", return 2.
    */
        System.out.println(longestCommonSubsequence1("ABCD", "EDCA"));
        System.out.println(longestCommonSubsequence1("ABCD" , "EACB"));
    }

/////////////////////////////////////////////////////////////////////////////////////////////
    //  97. Interleaving String
    //  mine
    //  determine whether s3 is formed by the interleaving of s1 and s2.
    public boolean isInterleave11(String s1, String s2, String s3) {
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
                    f[i][j] = f[i][j] | f[i - 1][j];//true
                }

                if (j > 0 && c3[i + j - 1] == c2[j - 1]) {
                    f[i][j] = f[i][j] | f[i][j - 1]; //false
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
        System.out.println(isInterleave11("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(isInterleave11("aabcc", "dbbca", "aadbbbaccc"));
    }

/////////////////////////////////////////////////////////////////////////////////////////////
    //  72. Edit Distance
    //  9Ch DP 动态规划版本去掉注释
    //  find the minimum number of steps required to convert word1 to word2.
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


/////////////////////////////////////////////////////////////////////////////////////////////
    //  115. Distinct Subsequences
    //  9Ch DP
    //  Implement regular expression matching with support for '.' and '*'.
    public int numDistinct(String S, String T) {
        char[] c1 = S.toCharArray();
        char[] c2 = T.toCharArray();
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

/////////////////////////////////////////////////////////////////////////////////////////////
    //  10. Regular Expression Matching
    //  9CH DP
    //  Implement regular expression matching with support for '.' and '*'.
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

/////////////////////////////////////////////////////////////////////////////////////////////
    //  44. Wildcard Matching
    //  Java DP Accepted
    //
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


/////////////////////////////////////////////////////////////////////////////////////////////
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

/////////////////////////////////////////////////////////////////////////////////////////////
}
