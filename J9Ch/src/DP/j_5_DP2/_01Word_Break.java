package DP.j_5_DP2;

import java.util.*;

/*单序列动态规划
￼￼￼• state: f[i]表示前i个位置/数字/字符,第i个... 
• function: f[i] = f[j] ... j 是i之前的一个位置 
• initialize: f[0]..
• answer: f[n]..
• 一般answer是f(n)而不是f(n-1)
//最大的特点就是数组要开n + 1个，结果取第n个
 * 
 */

public class _01Word_Break {
//////////////////////////////////////////////////////////////////////
	private int getMaxLength(Set<String> dict) {
	    int maxLength = 0;
	    for (String word : dict) {
	        maxLength = Math.max(maxLength, word.length());
	    }
	    return maxLength;
	}

    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0) {
            return true;
        }
        //这个确保了不会去找太长的string当作单词
        int maxLength = getMaxLength(dict);
        boolean[] canSegment = new boolean[s.length() + 1];

        canSegment[0] = true;
        //WHY <= here????
        for (int i = 1; i <= s.length(); i++) {
            canSegment[i] = false;
            for (int lastWordLength = 1;
                     lastWordLength <= maxLength && lastWordLength <= i;
                     lastWordLength++) {
                if (!canSegment[i - lastWordLength]) {
                    continue;
                }
    //0 means the 0 + 1 th char
    //1 means the 1 + 1 th char
    //index i - lastWordLength means the (i - lastWordLength + 1)th char

    //i is excluded, so inclued is i-1+1 th char
                String word = s.substring(i - lastWordLength, i);
                if (dict.contains(word)) {
                    canSegment[i] = true;
                    break;
                }
            }
        }

        return canSegment[s.length()];
    }

//////////////////////////////////////////////////////////////////////
    // https://leetcode.com/articles/word-break/

    //Approach #1 Brute Force [Time Limit Exceeded]
    public class Solution1 {
        public boolean wordBreak(String s, List<String> wordDict) {
            return word_Break(s, new HashSet(wordDict), 0);
        }
        public boolean word_Break(String s, Set<String> wordDict, int start) {
            if (start == s.length()) {
                return true;
            }
            for (int end = start + 1; end <= s.length(); end++) {
                if (wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end)) {
                    return true;
                }
            }
            return false;
        }
    }
//////////////////////////////////////////////////////////////////////
    //Approach #2 Recursion with memoization [Accepted]
    public class Solution2 {
        public boolean wordBreak(String s, List<String> wordDict) {
            return word_Break(s, new HashSet(wordDict), 0, new Boolean[s.length()]);
        }
        public boolean word_Break(String s, Set<String> wordDict, int start, Boolean[] memo) {
            if (start == s.length()) {
                return true;
            }
            if (memo[start] != null) {
                return memo[start];
            }
            for (int end = start + 1; end <= s.length(); end++) {
                if (wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end, memo)) {
                    return memo[start] = true;
                }
            }
            return memo[start] = false;
        }
    }
//////////////////////////////////////////////////////////////////////
    //Approach #3 Using Breadth-First-Search [Accepted]
    public class Solution3 {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordDictSet=new HashSet(wordDict);
            Queue<Integer> queue = new LinkedList<>();
            int[] visited = new int[s.length()];
            queue.add(0);
            while (!queue.isEmpty()) {
                int start = queue.remove();
                if (visited[start] == 0) {
                    for (int end = start + 1; end <= s.length(); end++) {
                        if (wordDictSet.contains(s.substring(start, end))) {
                            queue.add(end);
                            if (end == s.length()) {
                                return true;
                            }
                        }
                    }
                    visited[start] = 1;
                }
            }
            return false;
        }
    }
//////////////////////////////////////////////////////////////////////
    //Approach #4 Using Dynamic Programming [Accepted]:
    public class Solution4 {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordDictSet=new HashSet(wordDict);
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[s.length()];
        }
    }
//////////////////////////////////////////////////////////////////////


}

/*
 * Given a string s and a dictionary of words dict, determine if s can be 
 * break into a space-separated sequence of one or more dictionary words.

Have you met this question in a real interview? Yes
Example
Given s = "lintcode", dict = ["lint", "code"].

Return true because "lintcode" can be break as "lint code".

Tags 
String Dynamic Programming
 */