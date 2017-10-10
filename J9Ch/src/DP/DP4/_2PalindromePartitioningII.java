package DP.DP4;

//• 划分型动态规划

/*
• 设f[i]为S前i个字符S[0..i-1]最少可以划分成几个回文串

f[i] = minj=0,...,i-1{f[j] + 1 | S[j..i-1]是回文串}


f[i] S前i个字符最少可以 划分成几个回文串
f[j] S前j个字符最少可以 划分成几个回文串
S[j..i-1] 最后一段回文串


• 设f[i]为S前i个字符S[0..i-1]最少可以划分成几个回文串
• f[i] = minj=0,...,i-1{f[j] + 1| S[j..i-1]是回文串}
• 初始条件:空串可以被分成0个回文串 – f[0] = 0

• 计算f[0], f[1], ..., f[N]
• 答案是f[N]

 */

import org.junit.Test;

//  Palindrome Partitioning II
public class _2PalindromePartitioningII {

    // 9Ch DP
    public int minCut(String ss) {
        char[] s = ss.toCharArray();
        int n = s.length;
        if (n == 0) {
            return 0;
        }
        
        boolean[][] isPalin = new boolean[n][n];
        int i, j, t;
        for (i = 0; i < n; i++) {
            for (j = i; j < n; j++) {
                isPalin[i][j] = false;
            }
        }

        //generate palindrome
        for (t = 0; t < n; t++) {
            // odd length
            i = j = t;
            while (i >= 0 && j < n && s[i] == s[j]) {
                isPalin[i][j] = true;
                --i;
                ++j;
            }

            // even length
            i = t;
            j = t + 1;
            while (i >= 0 && j < n && s[i] == s[j]) {
                isPalin[i][j] = true;
                --i;
                ++j;
            }
        }

        int[] f = new int[n + 1];
        f[0] = 0;
        for (i = 1; i <= n; i++) {
            f[i] = Integer.MAX_VALUE;
            for (j = 0; j < i; j++) {
                if (isPalin[j][i - 1]) {
                    f[i] = Math.min(f[j] + 1, f[i]);
                }
            }
        }
        return f[n] - 1;
    }

    @Test
    public void test01(){
        String ss = "aab";
        System.out.println(minCut("aab"));
        System.out.println(minCut("aabcd"));
    }
///////////////////////////////////////////////////////////////////////////

    // version 1
    // f[i] 表示前i个字母，最少可以被分割为多少个回文串
    // 最后return f[n] - 1
    private boolean[][] getIsPalindrome(String s) {
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }

        for (int length = 2; length < s.length(); length++) {
            for (int start = 0; start + length < s.length(); start++) {
                isPalindrome[start][start + length]
                        = isPalindrome[start + 1][start + length - 1]
                        && s.charAt(start) == s.charAt(start + length);
            }
        }

        return isPalindrome;
    }

    public int minCut1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // preparation
        boolean[][] isPalindrome = getIsPalindrome(s);

        // initialize
        int[] f = new int[s.length() + 1];
        f[0] = 0;

        // main
        for (int i = 1; i <= s.length(); i++) {
            f[i] = Integer.MAX_VALUE; // or f[i] = i
            for (int j = 0; j < i; j++) {
                if (isPalindrome[j][i - 1]) {
                    f[i] = Math.min(f[i], f[j] + 1);
                }
            }
        }

        return f[s.length()] - 1;
    }


///////////////////////////////////////////////////////////////////////////

    // version 2
    // f[i] 表示前i个字母，最少被切割几次可以切割为都是回文串。
    // 最后return f[n]
    public class Solution2 {
        private boolean isPalindrome(String s, int start, int end) {
            for (int i = start, j = end; i < j; i++, j--) {
                if (s.charAt(i) != s.charAt(j)) {
                    return false;
                }
            }
            return true;
        }

        private boolean[][] getIsPalindrome(String s) {
            boolean[][] isPalindrome = new boolean[s.length()][s.length()];

            for (int i = 0; i < s.length(); i++) {
                isPalindrome[i][i] = true;
            }
            for (int i = 0; i < s.length() - 1; i++) {
                isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
            }

            for (int length = 2; length < s.length(); length++) {
                for (int start = 0; start + length < s.length(); start++) {
                    isPalindrome[start][start + length]
                            = isPalindrome[start + 1][start + length - 1]
                            && s.charAt(start) == s.charAt(start + length);
                }
            }

            return isPalindrome;
        }

        public int minCut(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            // preparation
            boolean[][] isPalindrome = getIsPalindrome(s);

            // initialize
            int[] f = new int[s.length() + 1];
            for (int i = 0; i <= s.length(); i++) {
                f[i] = i - 1;
            }

            // main
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    if (isPalindrome[j][i - 1]) {
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }

            return f[s.length()];
        }
    }

///////////////////////////////////////////////////////////////////////////

}
/*
Given a string s, cut s into some substrings such that every substring is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Have you met this question in a real interview? Yes
Example
Given s = "aab",

Return 1 since the palindrome partitioning ["aa", "b"] could be produced using 1 cut.
 */

/*
给定一个字符串s，将s分割成一些子串，使每个子串都是回文。

返回s符合要求的的最少分割次数。

您在真实的面试中是否遇到过这个题？ Yes
样例
比如，给出字符串s = "aab"，

返回 1， 因为进行一次分割可以将字符串s分割成["aa","b"]这样两个回文子串
 */