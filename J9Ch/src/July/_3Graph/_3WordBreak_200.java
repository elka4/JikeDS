package July._3Graph;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.*;

public class _3WordBreak_200 {
    // version 1, this is DP I think
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


    /*
Given s = "lintcode", dict = ["lint", "code"].

Return true because "lintcode" can be break as "lint code".
     */

    @Test
    public void test01(){
        //wordBreak(String s, Set<String> wordDict)
        String s = "lintcode";
        String[] arr = {"code", "lint"};
        Set<String> wordDict =  new HashSet<>();
        wordDict.addAll(Arrays.asList(arr));
        //System.out.println(Arrays.asList(arr).getClass());
        System.out.println(wordBreak(s, wordDict));
    }

//------------------------------------------------------------------------------

    // version 2, DP, from leetcode
    public boolean wordBreak2(String s, Set<String> dict) {
        boolean[] f = new boolean[s.length() + 1];
        f[0] = true;

        // First DP
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
        }
        return f[s.length()];
    }
    @Test
    public void test02(){
        //wordBreak(String s, Set<String> wordDict)
        String s = "lintcode";
        String[] arr = {"code", "lint"};
        Set<String> wordDict =  new HashSet<>();
        wordDict.addAll(Arrays.asList(arr));
        //System.out.println(Arrays.asList(arr).getClass());
        System.out.println(wordBreak2(s, wordDict));
    }

//------------------------------------------------------------------------------

    // version 3, DP, from leet
    public boolean wordBreak3(String s, Set<String> dict) {
        boolean[] f = new boolean[s.length() + 1];
        f[0] = true;

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
    @Test
    public void test03(){
        //wordBreak(String s, Set<String> wordDict)
        String s = "lintcode";
        String[] arr = {"code", "lint"};
        Set<String> wordDict =  new HashSet<>(Arrays.asList(arr));
//        wordDict.addAll(Arrays.asList(arr));
        //System.out.println(Arrays.asList(arr).getClass());
        System.out.println(wordBreak3(s, wordDict));
    }

//------------------------------------------------------------------------------

    // version 4, DFS with Path Memorizing Java Solution
    // from leetcode
    public boolean wordBreak4(String s, Set<String> dict) {
        // DFS
        Set<Integer> set = new HashSet<Integer>();
        return dfs(s, 0, dict, set);
    }

    private boolean dfs(String s, int index,
                        Set<String> dict, Set<Integer> set){

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
    @Test
    public void test04(){
        //wordBreak(String s, Set<String> wordDict)
        String s = "lintcode";
        String[] arr = {"code", "lint"};
        Set<String> wordDict =  new HashSet<>();
        wordDict.addAll(Arrays.asList(arr));
        //System.out.println(Arrays.asList(arr).getClass());
        System.out.println(wordBreak4(s, wordDict));
    }

//------------------------------------------------------------------------------
    // leetcode

    // Approach #1 Brute Force [Time Limit Exceeded]
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

//------------------------------------------------------------------------------

    // Approach #2 Recursion with memoization [Accepted]
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

//------------------------------------------------------------------------------

    //  Approach #3 Using Breadth-First-Search [Accepted]
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
//------------------------------------------------------------------------------

    //A pproach #4 Using Dynamic Programming [Accepted]:
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
//------------------------------------------------------------------------------

//------------------------------------------------------------------------------

}
/*
给出一个字符串s和一个词典，判断字符串s是否可以被空格切分成一个或多个出现在字典中的单词。

样例
给出

s = "lintcode"

dict = ["lint","code"]

返回 true 因为"lintcode"可以被空格切分成"lint code"
 */