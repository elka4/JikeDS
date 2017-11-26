package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;
public class _044_BackTracking_Wildcard_Matching_H {

    //I like this one.
    //Java DP Accepted
    public class Solution3 {
        public boolean isMatch(String s, String p) {
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
    }



/*    dp[n] means the substring s[:n] if match the pattern i

    dp[0] means the empty string '' or s[:0] which only match the pattern '*'

    use the reversed builtin because for every dp[n+1] we use the previous 'dp'

    add Java O(m*n) version code*/

    public boolean isMatch(String s, String p) {
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
                    dp[j][i] = dp[j - 1][i - 1] && (pattern == '?' || pattern == letter);
                } else
                    dp[j][i] = dp[j][i - 1] || dp[j - 1][i];
            }
        }
        return dp[p.length()][s.length()];
    }



//    My java DP solution using 2D table
    public class Solution2 {
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


//--------------------------------------------------------------------------------



//--------------------------------------------------------------------------------
    // 9Ch
// Time: O(|s||p|*log|s|), Space: O(|s|)
// Time can also optimize to O(|s||p|)

    public class jiuzhang {

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
    }


    class JiuzhangDP{
        // 9Ch DP
        public boolean isMatch(String s, String p) {
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
    }


//--------------------------------------------------------------------------------




}
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