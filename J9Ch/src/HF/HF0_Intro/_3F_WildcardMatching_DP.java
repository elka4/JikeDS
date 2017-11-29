package HF.HF0_Intro;
import org.junit.Test;
import java.util.*;


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
//  4:1
//
public class _3F_WildcardMatching_DP {
//-------------------------------------------------------------------------------
    //1
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
    
        for (int j = 1; j <= n; j++)
            dp[0][j] = dp[0][j - 1] && pc[j - 1] == '*';
    
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 0) {
                    dp[0][j] = dp[0][j - 1] && pc[j - 1] == '*';
                    continue;
                }
                if (pc[j - 1] == '?' || sc[i - 1] == pc[j - 1])
                    dp[i][j] = dp[i - 1][j - 1];
                else if (pc[j - 1] == '*')
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

//-------------------------------------------------------------------------------
    //2
    //Linear runtime and constant space solution
    boolean comparison(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  &&
                    (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
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

//-------------------------------------------------------------------------------
    //3
    //My java DP solution using 2D table
    public boolean isMatch2(String s, String p) {

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
                if(s.charAt(i)==p.charAt(j)||p.charAt(j)=='?'){
                    match[i][j]=match[i+1][j+1];
                } else if(p.charAt(j)=='*') {
                    match[i][j]=match[i+1][j]||match[i][j+1];
                } else {
                    match[i][j]=false;
                }
            }
        }
        return match[0][0];
    }


//-------------------------------------------------------------------------------
    //4
    //9Ch
    // Time: O(|s||p|*log|s|), Space: O(|s|)
    // Time can also optimize to O(|s||p|)

    public class Jiuzhang {

        public boolean isMatch(String s, String p) {
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
                        if (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '?') 
                            dp[si] = dp[si-1];
                        else dp[si] = false;
                    } else {
                        int firstTruePos = firstTrueSet.isEmpty() ? 
                                Integer.MAX_VALUE : firstTrueSet.first();
                        if (si >= firstTruePos) dp[si] = true;
                        else dp[si] = false;
                    }
                    if (dp[si]) firstTrueSet.add(si);
                    else firstTrueSet.remove(si);
                }
            }
            return dp[slen - 1];
        }
    }
//-------------------------------------------------------------------------------

}

/*
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Example
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
-------------------------------------------------------------------------------
 */


/*
判断两个可能包含通配符“？”和“*”的字符串是否匹配。匹配规则如下：

'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符串（包括空字符串）。
两个串完全匹配才算匹配成功。
 */