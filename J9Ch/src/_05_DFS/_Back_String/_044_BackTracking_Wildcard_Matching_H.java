package _05_DFS._Back_String;
import java.util.*;

//  44. Wildcard Matching
//  https://leetcode.com/problems/wildcard-matching/description/
//  http://www.lintcode.com/zh-cn/problem/wildcard-matching/
public class _044_BackTracking_Wildcard_Matching_H {

    //I like this one.
    //Java DP Accepted
    public boolean isMatch01(String s, String p) {
        int m = s.length(), n = p.length();
        char[] ws = s.toCharArray();
        char[] wp = p.toCharArray();

        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        for (int j = 1; j <= n; j++)
            dp[0][j] = dp[0][j-1] && wp[j-1] == '*';

        for (int i = 1; i <= m; i++)
            dp[i][0] = false;

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

///////////////////////////////////////////////////////////////////////////////////////////

/*    dp[n] means the substring s[:n] if match the pattern i

    dp[0] means the empty string '' or s[:0] which only match the pattern '*'

    use the reversed builtin because for every dp[n+1] we use the previous 'dp'

    add Java O(m*n) version code*/

    public boolean isMatch02(String s, String p) {
        int count = 0;
        for (char c : p.toCharArray()) {
            if (c == '*')
                count++;
        }
        if (p.length() - count > s.length())
            return false;

        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        dp[0][0] = true;

        for (int j = 1; j <= p.length(); j++) {
            char pattern = p.charAt(j - 1);
            dp[j][0] = dp[j - 1][0] && pattern == '*';

            for (int i = 1; i <= s.length(); i++) {
                char letter = s.charAt(i - 1);
                if (pattern != '*') {
                    dp[j][i] = dp[j - 1][i - 1] &&
                            (pattern == '?' || pattern == letter);
                } else
                    dp[j][i] = dp[j][i - 1] || dp[j - 1][i];
            }
        }
        return dp[p.length()][s.length()];
    }

///////////////////////////////////////////////////////////////////////////////////////////

    //My java DP solution using 2D table
    public boolean isMatch03(String s, String p) {
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


//////////////////////////////////////////////////////////////////////////////////
    //Java DFA solution, with better structure and easy understand

    public boolean isMatch04(String s, String p) {
        return matchHere(s,p,0,0);
    }

    private boolean matchHere(String s, String p, int indexS, int indexP){
        if(indexS>=s.length()){
            return isPatternTailingMatch(p,indexP);
        }
        if(indexP>=p.length()){
            return isPatternEndWithWildCard(p);
        }
        if(s.charAt(indexS)==p.charAt(indexP)||p.charAt(indexP)=='?'){
            return matchHere(s,p,indexS+1,indexP+1);
        }
        else if(p.charAt(indexP)=='*'){
            return matchWildCard(s,p,++indexS,indexP+1);
        }
        return false;
    }
    private boolean isPatternTailingMatch(String p, int indexP){
        for(int index=indexP; index<p.length();index++){
            if(p.charAt(index)!='*'){
                return false;
            }
        }
        return true;
    }
    private boolean isPatternEndWithWildCard(String p){

        return p.endsWith("*");
    }
    private boolean matchWildCard(String s, String p, int indexS, int indexP){
        for(int index=indexS;index<s.length();index++){
            if(matchHere(s,p,index,indexP)){
                return true;
            }
        }
        return false;
    }
//////////////////////////////////////////////////////////////////////////////////
    //Linear runtime and constant space solution
    //    Here is my re-write in Java

    boolean comparison05(String str, String pattern) {
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

//////////////////////////////////////////////////////////////////////////////////
        //jiuzhang
        // Time: O(|s||p|*log|s|), Space: O(|s|)
        // Time can also optimize to O(|s||p|)
        public boolean isMatch_J1(String s, String p) {
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
                        if (s.charAt(si) == p.charAt(pi) ||
                                p.charAt(pi) == '?') dp[si] = dp[si-1];
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


///////////////////////////////////////////////////////////////////////////////////////////
    // 9Ch DP
    public boolean isMatch_J2(String s, String p) {
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


//////////////////////////////////////////////////////////////////////////////////
}

/*
通配符匹配

判断两个可能包含通配符“？”和“*”的字符串是否匹配。匹配规则如下：

'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符串（包括空字符串）。

两个串完全匹配才算匹配成功。

函数接口如下:
bool isMatch(const char *s, const char *p)
样例
一些例子：

isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
标签
动态规划 字符串处理 回溯法 贪心 谷歌
相关题目
困难
 */


/*
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