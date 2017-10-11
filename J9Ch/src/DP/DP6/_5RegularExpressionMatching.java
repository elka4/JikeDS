package DP.DP6;
//双序列型动态规划

/*
• 设f[i][j]为A前i个字符A[0..i-1]和B前j个字符B[0..j-1]能否匹配

f[i][j] = f[i-1][j-1]，如果B[j-1]=‘.’或者A[i-1]=B[j-1]

f[i][j] = f[i][j-2] OR (f[i-1][j] AND (B[j-2]=‘.’ OR B[j-2]=A[i-1]))，如果B[j-1]=‘*’

• 设f[i][j]为A前i个字符A[0..i-1]和B前j个字符B[0..j-1]能否匹配
• 空串和空正则表达式匹配:f[0][0] = TRUE • 空的正则表达式不能匹配长度>0的串
– f[1][0] = ... = f[m][0] = FALSE
• 注意:f[0][1..n]也用动态规划计算，但是因为没有A[-1]，所以只能用第 二种情况中的f[i][j-2]

 */

//Regular Expression Matching
public class _5RegularExpressionMatching {
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