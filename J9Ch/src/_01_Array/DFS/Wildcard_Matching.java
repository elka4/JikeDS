package _01_Array.DFS;

/*
LeetCode – Wildcard Matching (Java)

Implement wildcard pattern matching with support for '?' and '*'.
 */


/*
'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") ? false
isMatch("aa","aa") ? true
isMatch("aaa","aa") ? false
isMatch("aa", "*") ? true
isMatch("aa", "a*") ? true
isMatch("ab", "?*") ? true
isMatch("aab", "c*a*b") ? false
 */

/*
 DP, Backtracking, Greedy, String

 */
public class Wildcard_Matching {
    //To understand this solution, you can use s="aab" and p="*ab".

    public boolean isMatch(String s, String p) {
        int i = 0;
        int j = 0;
        int starIndex = -1;
        int iIndex = -1;

        while (i < s.length()) {
            if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
                ++i;
                ++j;
            } else if (j < p.length() && p.charAt(j) == '*') {
                starIndex = j;
                iIndex = i;
                j++;
            } else if (starIndex != -1) {
                j = starIndex + 1;
                i = iIndex+1;
                iIndex++;
            } else {
                return false;
            }
        }

        while (j < p.length() && p.charAt(j) == '*') {
            ++j;
        }

        return j == p.length();
    }





//-------------------------------------------------------------------------//////

    //Linear runtime and constant space solution

    boolean comparison(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?'
                    || str.charAt(s) == pattern.charAt(p))){
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




//-------------------------------------------------------------------------//////

    //My java DP solution using 2D table
    public class Solution3 {
        public boolean isMatch(String s, String p) {
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
    }



//-------------------------------------------------------------------------//////

    //My Java DP Solution
    public class Solution4 {
        public boolean isMatch(String s, String p) {
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
    }




//-------------------------------------------------------------------------//////





//-------------------------------------------------------------------------//////





//-------------------------------------------------------------------------//////





//-------------------------------------------------------------------------//////







}
