package _05_DFS._DFS;
import java.util.*;

import StdLib.StdIn;
import com.sun.org.apache.regexp.internal.RE;
import lib.*;
import org.junit.Test;

//Backtracking   DP
//  10. Regular Expression Matching
public class _010_DFS_Regular_Expression_Matching_E {

    public boolean isMatch(String s, String p) {

        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i-1]) {
                dp[0][i+1] = true;
            }
        }
        for (int i = 0 ; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.') {
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == s.charAt(i)) {
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                        dp[i+1][j+1] = dp[i+1][j-1];
                    } else {
                        dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }




    public boolean isMatch2(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }

        if (p.length() == 1 || p.charAt(1) != '*') {
            if (s.isEmpty() || (p.charAt(0) != '.' && p.charAt(0) != s.charAt(0))) {
                return false;
            } else {
                return isMatch2(s.substring(1), p.substring(1));
            }
        }

        //P.length() >=2
        while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
            if (isMatch2(s, p.substring(2))) {
                return true;
            }
            s = s.substring(1);
        }

        return isMatch2(s, p.substring(2));
    }


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

     */
    public boolean isMatch3(String s, String p) {

        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];

        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i-1]) {
                dp[0][i+1] = true;
            }
        }
        for (int i = 0 ; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.') {
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == s.charAt(i)) {
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                        dp[i+1][j+1] = dp[i+1][j-1];
                    } else {
                        dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }




//----------------------------------------------------------------------------
    //  https://leetcode.com/articles/regular-expression-matching/

    //Approach #1: Recursion [Accepted]
    class leet1 {
        public boolean isMatch(String text, String pattern){
            if (pattern.isEmpty()) {
                return text.isEmpty();
            }
            boolean first_match = (!text.isEmpty() &&
                    (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

            if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
                return (isMatch(text, pattern.substring(2)) ||
                        first_match && isMatch(text.substring(1), pattern));
            } else {
                return first_match && isMatch(text.substring(1), pattern.substring(1));
            }
        }
    }


    //Approach #2: Dynamic Programming [Accepted]
    //Top-Down Variation
        enum Result {
            TRUE, FALSE;
        }
    class leet2{
        Result[][] memo;

        public boolean isMatch(String text, String pattern){
            memo = new Result[text.length() + 1][pattern.length() + 1];
            return dp(0, 0, text, pattern);
        }
        public boolean dp(int i, int j, String text, String pattern) {
            if (memo[i][j] != null) {
                return memo[i][j] == Result.TRUE;
            }
            boolean ans;
            if (j == pattern.length()) {
                ans = i == text.length();
            } else {
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));

                if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                    ans = (dp(i, j+2, text, pattern)) || first_match && dp(i + 1, j, text, pattern);
                } else {
                    ans = first_match && dp(i + 1, j + 1, text, pattern);
                }
            }
            memo[i][j] = ans ? Result.TRUE : Result.FALSE;
            return ans;
        }
    }

    //Bottom-Up Variation
    class leet3{
        public boolean isMatch(String text, String pattern){
            boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
            dp[text.length()][pattern.length()] = true;

            for (int i = text.length(); i >= 0; i--) {
                for (int j = pattern.length() - 1; j >= 0; j--) {
                    boolean first_match = (i < text.length() && (pattern.charAt(j) == text.charAt(i) ||
                            pattern.charAt(j) == '.'));

                    if (j + 1 < pattern.length() && pattern.charAt(j + 1) =='*') {
                        dp[i][j] =dp[i][j + 2] || first_match && dp[i + 1][j];
                    } else {
                        dp[i][j] = first_match && dp[i + 1][j + 1];
                    }
                }
            }
            return dp[0][0];
        }
    }

//----------------------------------------------------------------------------
    // 9Ch
    //DFS
    public class Jiuzhang {
        public boolean isMatch(String s, String p) {
            //Java note: s.substring(n) will be "" if n == s.length(), but if n > s.length(), index oob error

            int i = 0, j = 0;
            //you don't have to construct a state machine for this problem

            if (s.length() == 0) {
                return checkEmpty(p);
            }

            if (p.length() == 0) {
                return false;
            }

            char c1 = s.charAt(0);
            char d1 = p.charAt(0), d2 = '0'; //any init value except '*'for d2 will do

            if (p.length()>1){
                d2 = p.charAt(1);
            }

            if (d2 == '*') {
                if (compare(c1, d1)) {
                    //fork here: 1. consume the character, and use the same pattern again.
                    //2. keep the character, and skip 'd1*' pattern

                    //Here is also an opportunity to use DP, but the idea is the same
                    return isMatch(s.substring(1), p) || isMatch(s, p.substring(2));
                }
                else {
                    return isMatch(s, p.substring(2));
                }
            }
            else {
                if (compare(c1, d1)) {
                    return isMatch(s.substring(1), p.substring(1));
                }
                else {
                    return false;
                }
            }
        }

        public boolean compare(char c1, char d1){
            return d1 == '.' || c1 == d1;
        }

        public boolean checkEmpty(String p) {
            if (p.length()%2 != 0) {
                return false;
            }

            for (int i = 1; i < p.length(); i+=2) {
                if (p.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }
    }

    // 9CH DP
    public class Jiuzhang2{
        public boolean isMatch(String s, String p) {
            char[] c1 = s.toCharArray();
            char[] c2 = p.toCharArray();
            int m = c1.length;
            int n = c2.length;

            boolean[][] f = new boolean[m + 1][n + 1];

            int i, j;
            for (i = 0; i <= m; i++) {
                for (j = 0; j <= n; j++) {
                    if (i == 0 && j == 0) {
                        f[i][j] = true;
                        continue;
                    }

                    if (j == 0) {
                        f[i][j] = false;
                        continue;
                    }

                    f[i][j] = false;
                    if (c2[j - 1] != '*') {
                        if (i >0 && (c2[j - 1] == '.' || c1[i - 1] == c2[j - 1])) {
                            f[i][j] = f[i - 1][j - 1];
                        }
                    } else {
                        // c2[j - 1] == '*'
                        if (j - 2 >= 0) {
                            f[i][j] |= f[i][j - 2];
                        }

                        if (i >= 1 && j >= 2) {
                            f[i][j] |= f[i - 1][j] && (c2[j - 2] == '.' || c2[j - 2] == c1[i - 1]);
                        }
                    }
                }
            }
            return  f[m][n];
        }

    }


//----------------------------------------------------------------------------






}
/*
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true

 */