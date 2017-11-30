package _TwoPointer.Window;
import org.junit.Test;
import java.util.*;


//
//  727. Minimum Window Subsequence
//  https://leetcode.com/problems/minimum-window-subsequence/description/
//  DP
//  _076_Minimum_Window_Substring_H
//  674 Longest Continuous Increasing Subsequence
//
//  4:
//
public class _727_Minimum_Window_Subsequence_H {
//-------------------------------------------------------------------------
//https://leetcode.com/problems/minimum-window-subsequence/solution/

//-------------------------------------------------------------------------
    //1
    //Approach #1: Dynamic Programming (Postfix Variation) [Accepted]
    class Solution1 {
        public String minWindow(String S, String T) {
            int[][] dp = new int[2][S.length()];

            for (int i = 0; i < S.length(); ++i)
                dp[0][i] = S.charAt(i) == T.charAt(0) ? i : -1;

            for (int j = 1; j < T.length(); ++j) {
                int last = -1;
                Arrays.fill(dp[j & 1], -1);
                for (int i = 0; i < S.length(); ++i) {
                    if (last >= 0 && S.charAt(i) == T.charAt(j))
                        dp[j & 1][i] = last;
                    if (dp[~j & 1][i] >= 0)
                        last = dp[~j & 1][i];
                }
            }

            int start = 0, end = S.length();
            for (int e = 0; e < S.length(); ++e) {
                int s = dp[~T.length() & 1][e];
                if (s >= 0 && e - s < end - start) {
                    start = s;
                    end = e;
                }
            }
            return end < S.length() ? S.substring(start, end+1) : "";
        }
    }

//-------------------------------------------------------------------------
    //2
    //Approach #2: Dynamic Programming (Next Array Variation) [Accepted]
    class Solution2 {
        public String minWindow(String S, String T) {
            int N = S.length();
            int[] last = new int[26];
            int[][] nxt = new int[N][26];
            Arrays.fill(last, -1);

            for (int i = N - 1; i >= 0; --i) {
                last[S.charAt(i) - 'a'] = i;
                for (int k = 0; k < 26; ++k) {
                    nxt[i][k] = last[k];
                }
            }

            List<int[]> windows = new ArrayList();
            for (int i = 0; i < N; ++i) {
                if (S.charAt(i) == T.charAt(0))
                    windows.add(new int[]{i, i});
            }
            for (int j = 1; j < T.length(); ++j) {
                int letterIndex = T.charAt(j) - 'a';
                for (int[] window: windows) {
                    if (window[1] < N-1 && nxt[window[1]+1][letterIndex] >= 0) {
                        window[1] = nxt[window[1]+1][letterIndex];
                    }
                    else {
                        window[0] = window[1] = -1;
                        break;
                    }
                }
            }

            int[] ans = {-1, S.length()};
            for (int[] window: windows) {
                if (window[0] == -1) break;
                if (window[1] - window[0] < ans[1] - ans[0]) {
                    ans = window;
                }

            }
            return ans[0] >= 0 ? S.substring(ans[0], ans[1] + 1) : "";
        }
    }
//-------------------------------------------------------------------------
    //3
    //Trying to translate the author's idea into a less efficient but easier to understand version.
    class Solution3 {
        public String minWindow(String S, String T) {
            int[][] dp = new int[T.length()][S.length()];
            for(int i = 0; i < T.length(); i++) {
                for(int j = 0; j < S.length(); j++) {
                    dp[i][j] = -1;
                }
            }

            for(int j = 0; j < S.length(); j++) {
                dp[0][j] = (S.charAt(j) == T.charAt(0)) ? j : -1;
            }

            for(int i = 1; i < T.length(); i++) {
                int last = -1;
                for(int j = 0; j < S.length(); j++) {
                    if(last >= 0 && S.charAt(j) == T.charAt(i)) {
                        dp[i][j] = last;
                    }
                    if(dp[i - 1][j] >= 0) {
                        last = dp[i - 1][j];
                    }
                }
            }

            int start = -1;
            int length = Integer.MAX_VALUE;

            for(int j = 0; j < S.length(); j++) {
                if(dp[T.length() - 1][j] >= 0 && (j - dp[T.length() - 1][j] + 1 < length)) {
                    start = dp[T.length() - 1][j];
                    length = j - dp[T.length() - 1][j] + 1;
                }
            }

            return (start == -1) ? "" : S.substring(start, start + length);
        }
    }


//-------------------------------------------------------------------------
    //4
    //Java solution, sliding window
    class Solution4 {
        public String minWindow(String S, String T) {
            String output = "";
            int minLen = 20001;
            for (int i = 0; i <= S.length() - T.length(); i++) {
                while (i < S.length() && S.charAt(i) != T.charAt(0)) {
                    i++;
                }
                int l = find(S.substring(i, Math.min(i + minLen, S.length())), T);
                if (l != -1 && l < minLen) {
                    minLen = l;
                    output = S.substring(i, i + l);
                }
            }
            return output;
        }

        private int find(String S, String T) {
            for (int i = 0, j = 0; i < S.length() && j < T.length();) {
                if (S.charAt(i) == T.charAt(j)) {
                    i++;
                    j++;
                    if (j == T.length()) {
                        return i;
                    }
                } else {
                    i++;
                }
            }
            return -1;
        }
    }

//-------------------------------------------------------------------------



//-------------------------------------------------------------------------
}
/*
Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.

Example 1:
Input:
S = "abcdebdde", T = "bde"
Output: "bcde"
Explanation:
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of T in the window must occur in order.
Note:

All the strings in the input will only contain lowercase letters.
The length of S will be in the range [1, 20000].
The length of T will be in the range [1, 100].

Companies
Google eBay

Related Topics
Dynamic Programming

Similar Questions
_076_Minimum_Window_Substring_H
 674 Longest Continuous Increasing Subsequence
 */