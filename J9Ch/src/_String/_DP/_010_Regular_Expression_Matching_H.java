package _String._DP;
import org.junit.Test;

//双序列型动态规划

/*
-----------------------------------------------------------------------------------------------
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

-----------------------------------------------------------------------------------------------
*/


//  10. Regular Expression Matching
//  https://leetcode.com/problems/regular-expression-matching/description/
//  6:
public class _010_Regular_Expression_Matching_H {
//--------------------------------------------------------------------------------
    //https://leetcode.com/problems/regular-expression-matching/solution/
//--------------------------------------------------------------------------------
    //1
    //Approach #1: Recursion [Accepted]
    class Solution01 {
        public boolean isMatch(String text, String pattern) {
            if (pattern.isEmpty()) return text.isEmpty();
            boolean first_match = (!text.isEmpty() &&
                    (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

            if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
                return (isMatch(text, pattern.substring(2)) ||
                        (first_match && isMatch(text.substring(1), pattern)));
            } else {
                return first_match && isMatch(text.substring(1), pattern.substring(1));
            }
        }
    }

//--------------------------------------------------------------------------------
    //2
    //Approach #2: Dynamic Programming [Accepted]
    //Top-Down Variation
    enum Result {
        TRUE, FALSE
    }

    class Solution02 {
        Result[][] memo;

        public boolean isMatch(String text, String pattern) {
            memo = new Result[text.length() + 1][pattern.length() + 1];
            return dp(0, 0, text, pattern);
        }

        public boolean dp(int i, int j, String text, String pattern) {
            if (memo[i][j] != null) {
                return memo[i][j] == Result.TRUE;
            }
            boolean ans;
            if (j == pattern.length()){
                ans = i == text.length();
            } else{
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));

                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    ans = (dp(i, j+2, text, pattern) || first_match && dp(i+1, j, text, pattern));
                } else {
                    ans = first_match && dp(i+1, j+1, text, pattern);
                }
            }
            memo[i][j] = ans ? Result.TRUE : Result.FALSE;
            return ans;
        }
    }

//--------------------------------------------------------------------------------
    //3
    //Bottom-Up Variation
    class Solution03 {
        public boolean isMatch(String text, String pattern) {
            boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
            dp[text.length()][pattern.length()] = true;

            for (int i = text.length(); i >= 0; i--){
                for (int j = pattern.length() - 1; j >= 0; j--){
                    boolean first_match = (i < text.length() &&
                            (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));

                    if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                        dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                    } else {
                        dp[i][j] = first_match && dp[i+1][j+1];
                    }
                }
            }
            return dp[0][0];
        }
    }

//--------------------------------------------------------------------------------
    //4
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

//--------------------------------------------------------------------------------
    //5
    //Clean Java Solution
    public boolean isMatch5(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }

        if (p.length() == 1 || p.charAt(1) != '*') {
            if (s.isEmpty() || (p.charAt(0) != '.' && p.charAt(0) != s.charAt(0))) {
                return false;
            } else {
                return isMatch5(s.substring(1), p.substring(1));
            }
        }

        //P.length() >=2
        while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
            if (isMatch5(s, p.substring(2))) {
                return true;
            }
            s = s.substring(1);
        }

        return isMatch5(s, p.substring(2));
    }


//--------------------------------------------------------------------------------
    //6
    // 9CH DP
    public boolean isMatch1(String s, String p) {
        char[] c1 = s.toCharArray();
        char[] c2 = p.toCharArray();
        int m = c1.length;
        int n = c2.length;
        boolean[][] f = new boolean[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) { f[i][j] = true; continue; }
                if (j == 0) { f[i][j] = false; continue; }

                f[i][j] = false;

                if (c2[j - 1] != '*') {
                    if (i >0 && (c2[j - 1] == '.' || c1[i - 1] == c2[j - 1])) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                } else {                        // c2[j - 1] == '*'
                    if (j - 2 >= 0) {
                        f[i][j] |= f[i][j - 2];
                    }
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
    public void test(){
        boolean result = true;
        result |= false;
        System.out.println(result);//true

        result = false;
        result |= false;
        System.out.println(result);//false

        result = false;
        result |= true;
        System.out.println(result);//true
    }

//--------------------------------------------------------------------------------
    //6
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


//--------------------------------------------------------------------------------
}
/*
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(string s, string p)
Have you met this question in a real interview? Yes
Example
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
 */