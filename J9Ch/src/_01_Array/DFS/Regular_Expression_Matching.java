package _01_Array.DFS;

/*
LeetCode – Regular Expression Matching in Java

Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") return false
isMatch("aa","aa") return true
isMatch("aaa","aa") return false
isMatch("aa", "a*") return true
isMatch("aa", ".*") return true
isMatch("ab", ".*") return true
isMatch("aab", "c*a*b") return true
 */

/*
1. Analysis

First of all, this is one of the most difficulty problems. It is hard to think through all different cases. The problem should be simplified to handle

2 basic cases:

the second char of pattern is "*"


the second char of pattern is not "*"


For the 1st case, if the first char of pattern is not ".", the first char of pattern and string should be the same. Then continue to match the remaining part.

For the 2nd case, if the first char of pattern is "." or first char of pattern == the first i char of string, continue to match the remaining part.
 */


public class Regular_Expression_Matching {
//2. Java Solution 1 (Short)

//    The following Java solution is accepted.
    public boolean isMatch(String s, String p) {

        if(p.length() == 0)
            return s.length() == 0;

        //p's length 1 is special case
        if(p.length() == 1 || p.charAt(1) != '*'){
            if(s.length() < 1 || (p.charAt(0) != '.' && s.charAt(0) != p.charAt(0)))
                return false;
            return isMatch(s.substring(1), p.substring(1));

        }else{
            int len = s.length();

            int i = -1;
            while(i<len && (i < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))){
                if(isMatch(s.substring(i+1), p.substring(2)))
                    return true;
                i++;
            }
            return false;
        }
    }



//3. Java Solution 2 (More Readable)

    public boolean isMatch2(String s, String p) {
        // base case
        if (p.length() == 0) {
            return s.length() == 0;
        }

        // special case
        if (p.length() == 1) {

            // if the length of s is 0, return false
            if (s.length() < 1) {
                return false;
            }

            //if the first does not match, return false
            else if ((p.charAt(0) != s.charAt(0)) && (p.charAt(0) != '.')) {
                return false;
            }

            // otherwise, compare the rest of the string of s and p.
            else {
                return isMatch(s.substring(1), p.substring(1));
            }
        }

        // case 1: when the second char of p is not '*'
        if (p.charAt(1) != '*') {
            if (s.length() < 1) {
                return false;
            }
            if ((p.charAt(0) != s.charAt(0)) && (p.charAt(0) != '.')) {
                return false;
            } else {
                return isMatch(s.substring(1), p.substring(1));
            }
        }

        // case 2: when the second char of p is '*', complex case.
        else {
            //case 2.1: a char & '*' can stand for 0 element
            if (isMatch(s, p.substring(2))) {
                return true;
            }

            //case 2.2: a char & '*' can stand for 1 or more preceding element,
            //so try every sub string
            int i = 0;
            while (i<s.length() && (s.charAt(i)==p.charAt(0) || p.charAt(0)=='.')){
                if (isMatch(s.substring(i + 1), p.substring(2))) {
                    return true;
                }
                i++;
            }
            return false;
        }
    }


//-------------------------------------------------------------------------////////////////

    //Easy DP Java Solution with detailed Explanation


   //This Solution use 2D DP. beat 90% solutions, very simple.

    //Here are some conditions to figure out, then the logic can be very straightforward.

    /*1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
      2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
      3, If p.charAt(j) == '*':

        here are two sub conditions:
            1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]
            //in this case, a* only counts as empty

            2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
                dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a
                 or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
                 or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty*/


    //Here is the solution

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



//-------------------------------------------------------------------------////////////////





//-------------------------------------------------------------------------////////////////





//-------------------------------------------------------------------------////////////////





//-------------------------------------------------------------------------////////////////





//-------------------------------------------------------------------------////////////////





//-------------------------------------------------------------------------////////////////






}
