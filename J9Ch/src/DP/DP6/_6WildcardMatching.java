package DP.DP6;

import java.util.TreeSet;
//双序列型动态规划


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


//  44. Wildcard Matching
//  https://leetcode.com/problems/wildcard-matching/description/
//  http://www.lintcode.com/zh-cn/problem/wildcard-matching/
public class _6WildcardMatching {

    //  Linear runtime and constant space solution
    boolean comparison(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*'){
                starIdx = p;
                match = s;
                p++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1){
                p = starIdx + 1;
                match++;
                s = match;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;

        return p == pattern.length();
    }

//-------------------------------------------------------------------------///
    //My java DP solution using 2D table
    public boolean isMatch02(String s, String p) {
        boolean[][] match=new boolean[s.length()+1][p.length()+1];
        match[s.length()][p.length()]=true;
        for(int i=p.length()-1;i>=0;i--){
            if(p.charAt(i)!='*')
                break;
            else
                match[s.length()][i]=true;
        }
        for(int i=s.length()-1;i>=0;i--){
            for(int j=p.length()-1;j>=0;j--){
                if(s.charAt(i)==p.charAt(j)||p.charAt(j)=='?')
                    match[i][j]=match[i+1][j+1];
                else if(p.charAt(j)=='*')
                    match[i][j]=match[i+1][j]||match[i][j+1];
                else
                    match[i][j]=false;
            }
        }
        return match[0][0];
    }

//------------------------------------------------------------------------------/////
//    How much time your solution costs? PS. recently I cannot view my java solution cost time among that of all other solutions, however, time cost graph will present when submitting python solution.
//
//    Here comes my 600ms solution, just amazing long.

    public boolean isMatch03(String s, String p) {
        char[] cs = s.toCharArray();
        char[] cp = p.toCharArray();
        int ls = cs.length;
        int lp = cp.length;

        if(lp == 0) return ls == 0 ? true : false;
        if(ls == 0) for(char c:cp) if(c!='*') return false;

        boolean[][] match = new boolean[ls+1][lp+1];
        match[0][0] = true;
        for(int i = 1; i <= ls; i++) match[i][0] = false;
        for(int j = 1; j <= lp; j++) match[0][j] = cp[j-1] == '*' ? match[0][j-1] : false;

        for(int is = 1; is <= ls; is++)
            for(int ip = 1; ip <= lp; ip++){
                if     (cp[ip-1]=='*')  for(int tmp = 0; tmp <= is; tmp++) match[is][ip] |= match[tmp][ip-1];
                else if(cp[ip-1]=='?')  match[is][ip] = match[is-1][ip-1];
                else                    match[is][ip] = cs[is-1] == cp[ip-1] ? match[is-1][ip-1] : false;
            }

        return match[ls][lp];
    }

//------------------------------------------------------------------------------/////

/*    The original post has DP 2d array index from high to low, which is not quite intuitive.

    Below is another 2D dp solution. Ideal is identical.

            dp[i][j] denotes whether s[0....i-1] matches p[0.....j-1],

    First, we need to initialize dp[i][0], i= [1,m]. All the dp[i][0] should be false because p has nothing in it.

    Then, initialize dp[0][j], j = [1, n]. In this case, s has nothing, to get dp[0][j] = true, p must be '*', '*', '**',etc. Once p.charAt(j-1) != '*', all the dp[0][j] afterwards will be false.

    Then start the typical DP loop.

    Though this solution is clear and easy to understand. It is not good enough in the interview. it takes O(mn) time and O(mn) space.

            Improvement: 1) optimize 2d dp to 1d dp, this will save space, reduce space complexity to O(N). 2) use iterative 2-pointer.*/

    public boolean isMatch_2d_method04(String s, String p) {
        int m=s.length(), n=p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i=1; i<=m; i++) {
            dp[i][0] = false;
        }

        for(int j=1; j<=n; j++) {
            if(p.charAt(j-1)=='*'){
                dp[0][j] = true;
            } else {
                break;
            }
        }

        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if (p.charAt(j-1)!='*') {
                    dp[i][j] = dp[i-1][j-1] && (s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='?');
                } else {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }
        return dp[m][n];
    }

//------------------------------------------------------------------------------/////

/*    Hi, I've submitted this solution and I get ~500ms, whereas the OP solution gets much lower time. They seem to have the same complexity, and do the same thing (one backwards and one forward). Anyone would be able to tell me why mine takes much longer?
    Thanks*/

    public boolean isMatch05(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length() + 1];
        dp[0][0] = true;
        for(int i = 1 ; i <= p.length() ; i++){
            dp[0][i] = dp[0][i-1] && p.charAt(i-1) == '*';
            if(!dp[0][i])
                break;
        }
        for(int i = 1; i <= p.length() ; i++){
            for(int j = 1 ; j <= s.length() ; j++){
                dp[j][i] = dp[j-1][i-1] && (p.charAt(i-1) == s.charAt(j-1) || p.charAt(i-1) == '?');
                if(p.charAt(i-1) == '*')
                    dp[j][i] = dp[j][i-1] || dp[j-1][i];
            }
        }
        return dp[s.length()][p.length()];
    }

//------------------------------------------------------------------------------/////
//    Thanks for sharing. Similar to your solution, here is mine.

    public boolean isMatch06(String s, String p) {
        if (s == null && p == null) {
            return true;
        }
        if (s == null || p == null) {
            return false;
        }
        boolean [][] dp = new boolean [s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); ++i) {
            dp[0][i + 1] = p.charAt(i) == '*' && dp[0][i];
        }
        for (int i = 0; i < s.length(); ++i) {
            for (int j = 0; j < p.length(); ++j) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    dp[i + 1][j + 1] = dp[i + 1][j] || dp[i][j + 1] || dp[i][j];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

//------------------------------------------------------------------------------/////
//    DP solution using 2d tabel, the code similar to problem 10(Regular Expression Matching)

    public boolean isMatch07(String s, String p) {
        if(s==null || p==null) return false;
        int s_len = s.length();
        int p_len = p.length();

        boolean[][] d = new boolean[s_len+1][p_len+1];
        d[0][0] = true;

        for (int i = 0; i < p_len; i++) {
            if ( p.charAt(i) == '*') d[0][i+1] = d[0][i];
        }

        for (int i = 0; i < s_len; i++) {
            for (int j = 0; j < p_len; j++) {
                if(p.charAt(j) == '?') d[i+1][j+1] = d[i][j];
                if(p.charAt(j) == s.charAt(i)) d[i+1][j+1] = d[i][j];
                if(p.charAt(j) == '*') d[i+1][j+1] = d[i+1][j] || d[i][j+1];
            }
        }

        return d[s_len][p_len];
    }

//------------------------------------------------------------------------------/////
    //My Java DP Solution
    //At first I cannot pass the the long 'aaa...' test case. Then I add more check and pass it.
    public boolean isMatch08(String s, String p) {
        int m = s.length(), n = p.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (p.charAt(i) == '*') count++;
        }
        if (count==0 && m != n) return false;
        else if (n - count > m) return false;

        boolean[] match = new boolean[m+1];
        match[0] = true;
        for (int i = 0; i < m; i++) {
            match[i+1] = false;
        }
        for (int i = 0; i < n; i++) {
            if (p.charAt(i) == '*') {
                for (int j = 0; j < m; j++) {
                    match[j+1] = match[j] || match[j+1];
                }
            } else {
                for (int j = m-1; j >= 0; j--) {
                    match[j+1] = (p.charAt(i) == '?' || p.charAt(i) == s.charAt(j)) && match[j];
                }
                match[0] = false;
            }
        }
        return match[m];
    }

//------------------------------------------------------------------------------/////
    public boolean isMatch09(String s, String p) {

        boolean[][] match = new boolean[s.length()+1][p.length()+1];
        match[0][0]= true;

        for(int i=1;i<=p.length();i++)
            if(p.charAt(i-1)=='*')
                match[0][i]= match[0][i-1];

        for(int i=1;i<=s.length();i++)
            for(int j=1;j<=p.length();j++){
                if(p.charAt(j-1)!='*')
                    match[i][j]=match[i-1][j-1] && (p.charAt(j-1)=='?' || s.charAt(i-1)== p.charAt(j-1));
                else
                    match[i][j]=match[i][j-1] || match[i-1][j] ;
            }
        return match[s.length()][p.length()];
    }

//-------------------------------------------------------------------------///
    //I like this one.
    //Java DP Accepted
    public class Solution3 {
        public boolean isMatch1(String s, String p) {
            int m = s.length(), n = p.length();
            char[] ws = s.toCharArray();
            char[] wp = p.toCharArray();

            boolean[][] dp = new boolean[m+1][n+1];
            dp[0][0] = true;

            for (int i = 1; i <= m; i++)
                dp[i][0] = false;

            for (int j = 1; j <= n; j++)
                dp[0][j] = dp[0][j-1] && wp[j-1] == '*';

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (wp[j-1] == '?' || ws[i-1] == wp[j-1])
                        dp[i][j] = dp[i-1][j-1];
                    else if (wp[j-1] == '*')
                        dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
            return dp[m][n];
        }
    }



//-------------------------------------------------------------------------///
    // 9Ch DP
    public boolean isMatch2(String s, String p) {
        char[] s1 = s.toCharArray();
        char[] s2 = p.toCharArray();
        int m = s1.length;
        int n = s2.length;

        boolean[][] f = new boolean[m + 1][n + 1];
        int i, j;
        for (i = 0; i <= m; i++) {
            for (j = 0; j <= n; j++) {
                if (i == 0 && j== 0) {
                    f[i][j] = true;
                    continue;
                }

                // i > 0
                if (j == 0) {
                    f[i][j] = false;
                    continue;
                }

                // i = 0, j = 10
                f[i][j] = false;
                if (s2[j - 1] != '*') {
                    if (i >= 1 && (s2[j - 1] == '?' || s2[j - 1] == s1[i - 1])) {
                        f[i][j] |= f[i - 1][j - 1];
                    }
                } else {
                    // not care *
                    f[i][j] = f[i][j - 1];
                    //important!
                    if (i > 0) {
                        // care *
                        f[i][j] |= f[i - 1][j];
                    }
                }
            }
        }
        return f[m][n];
    }

//------------------------------------------------------------------------------/////
    // 9Ch
    // Time: O(|s||p|*log|s|), Space: O(|s|)
    // Time can also optimize to O(|s||p|)
    public boolean isMatch3(String s, String p) {
        // without this optimization, it will fail for large data set
        int plenNoStar = 0;
        for (char c : p.toCharArray())
            if (c != '*') plenNoStar++;
        if (plenNoStar > s.length()) return false;

        s = " " + s;
        p = " " + p;
        int slen = s.length();
        int plen = p.length();

        boolean[] dp = new boolean[slen];
        TreeSet<Integer> firstTrueSet = new TreeSet<Integer>();
        firstTrueSet.add(0);
        dp[0] = true;

        boolean allStar = true;
        for (int pi = 1; pi < plen; pi++) {
            if (p.charAt(pi) != '*')
                allStar = false;
            for (int si = slen - 1; si >= 0; si--) {
                if (si == 0) {
                    dp[si] = allStar ? true : false;
                } else if (p.charAt(pi) != '*') {
                    if (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '?') dp[si] = dp[si-1];
                    else dp[si] = false;
                } else {
                    int firstTruePos = firstTrueSet.isEmpty() ? Integer.MAX_VALUE : firstTrueSet.first();
                    if (si >= firstTruePos) dp[si] = true;
                    else dp[si] = false;
                }
                if (dp[si]) firstTrueSet.add(si);
                else firstTrueSet.remove(si);
            }
        }
        return dp[slen - 1];
    }

//------------------------------------------------------------------------------/////

//------------------------------------------------------------------------------/////
}

/*  leetcode
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
 */


/*
判断两个可能包含通配符“？”和“*”的字符串是否匹配。匹配规则如下：

'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符串（包括空字符串）。

两个串完全匹配才算匹配成功。

函数接口如下:
bool isMatch(const char *s, const char *p)
您在真实的面试中是否遇到过这个题？ Yes
样例
一些例子：

isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
 */
