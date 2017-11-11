package _05_DFS._Back_Word;
import java.util.*;

//  139. Word Break
//  https://leetcode.com/problems/word-break/description/
//  http://www.lintcode.com/zh-cn/problem/word-break/
//  DP
public class _139_Word_Break {

    //https://leetcode.com/articles/word-break/

    //Approach #1 Brute Force [Time Limit Exceeded]
    public class Solution01 {
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

    //Approach #2 Recursion with memoization [Accepted]
    public class Solution02 {
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

    //Approach #3 Using Breadth-First-Search [Accepted]
    public class Solution03 {
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


//////////////////////////////////////////////////////////////////
    //Java implementation using DP in two ways
    public class Solution04 {
        public boolean wordBreak(String s, Set<String> dict) {

            boolean[] f = new boolean[s.length() + 1];

            f[0] = true;


        /* First DP
        for(int i = 1; i <= s.length(); i++){
            for(String str: dict){
                if(str.length() <= i){
                    if(f[i - str.length()]){
                        if(s.substring(i-str.length(), i).equals(str)){
                            f[i] = true;
                            break;
                        }
                    }
                }
            }
        }*/

            //Second DP
            for(int i=1; i <= s.length(); i++){
                for(int j=0; j < i; j++){
                    if(f[j] && dict.contains(s.substring(j, i))){
                        f[i] = true;
                        break;
                    }
                }
            }

            return f[s.length()];
        }
    }

//////////////////////////////////////////////////////////////////
    /*
    DFS with Path Memorizing Java Solution

    26
        S siyang3
        Reputation:  429
        I write this method by what I learned from @mahdy in his post Decode Ways

        Use a set to record all position that cannot find a match in dict. That cuts down the run time of DFS to O(n^2)
    */
    public class Solution05 {
        public boolean wordBreak(String s, Set<String> dict) {
            // DFS
            Set<Integer> set = new HashSet<Integer>();
            return dfs(s, 0, dict, set);
        }

        private boolean dfs(String s, int index, Set<String> dict, Set<Integer> set){
            // base case
            if(index == s.length()) return true;
            // check memory
            if(set.contains(index)) return false;
            // recursion
            for(int i = index+1;i <= s.length();i++){
                String t = s.substring(index, i);
                if(dict.contains(t))
                    if(dfs(s, i, dict, set))
                        return true;
                    else
                        set.add(i);
            }
            set.add(index);
            return false;
        }
    }

//////////////////////////////////////////////////////////////////
    //    A concise Java solution. (11-line in wordBreak function)
    public class Solution06 {
        public boolean wordBreak(String s, Set<String> wordDict) {
            int len = s.length();
            boolean[] f = new boolean[len + 1];
            f[0] = true;
            for (int i = 1; i < len + 1; i++)
                for (int j = 0; j < i; j++)
                    if (f[j] && wordDict.contains(s.substring(j, i))) {
                        f[i] = true;
                        break;
                    }
            return f[len];
        }
    }

//////////////////////////////////////////////////////////////////
    //Several solutions comparison
    public class Solution07 {
        //dp without count => 2ms
        public boolean wordBreakX(String s, Set<String> wordDict) {
            int n = s.length();
            boolean [] dp = new boolean [n+1];
            dp[0] = true;
            for(int i=0;i<n;i++){
                for(int j=i;j>=0;j--){
                    if(!dp[j])continue;  //check this first
                    if(wordDict.contains(s.substring(j,i+1))){
                        dp[i+1]=true;
                        break;  //break!!!!
                    }
                }
            }
            return dp[n];
        }
        //dp with count, this will give us how many ways we can break => 11ms
        public boolean wordBreak(String s, Set<String> wordDict) {
            int n = s.length();
            int [] dp = new int [n+1];
            dp[0] = 1;
            for(int i=0;i<n;i++){
                for(int j=i;j>=0;j--){
                    if(dp[j]==0)continue;
                    if(wordDict.contains(s.substring(j,i+1))){
                        dp[i+1]++;
                    }
                }
            }
            return dp[n]!=0;
        }

        //simple dfs => TLE
        public boolean wordBreakxx(String s, Set<String> wordDict) {
            if(s.equals(""))return true;
            for(int i=0;i<s.length();i++){
                if(wordDict.contains(s.substring(0,i+1))){
                    if(wordBreak(s.substring(i+1),wordDict))return true;
                }
            }
            return false;
        }
    }

//////////////////////////////////////////////////////////////////
//Jiuzhang
public class Jiuzhang {
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

        int maxLength = getMaxLength(dict);
        boolean[] canSegment = new boolean[s.length() + 1];

        canSegment[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            canSegment[i] = false;
            for (int lastWordLength = 1;
                 lastWordLength <= maxLength && lastWordLength <= i;
                 lastWordLength++) {
                if (!canSegment[i - lastWordLength]) {
                    continue;
                }
                String word = s.substring(i - lastWordLength, i);
                if (dict.contains(word)) {
                    canSegment[i] = true;
                    break;
                }
            }
        }

        return canSegment[s.length()];
    }
}

//////////////////////////////////////////////////////////////////
}
/*
单词拆分 I

给出一个字符串s和一个词典，判断字符串s是否可以被空格切分成一个或多个出现在字典中的单词。

样例
给出

s = "lintcode"

dict = ["lint","code"]

返回 true 因为"lintcode"可以被空格切分成"lint code"

标签
动态规划 字符串处理
 */

/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".

UPDATE (2017/1/4):
The wordDict parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.


 */