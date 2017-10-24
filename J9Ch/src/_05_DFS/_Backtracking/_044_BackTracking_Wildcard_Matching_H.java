package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;
public class _044_BackTracking_Wildcard_Matching_H {

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
}
