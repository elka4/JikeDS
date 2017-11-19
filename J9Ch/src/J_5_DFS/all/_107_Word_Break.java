package J_5_DFS.all;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** 107 Word Break
 * Created by tianhuizhu on 6/28/17.
 * Given a string s and a dictionary of words dict,
 * determine if s can be break into a space-separated sequence of
 * one or more dictionary words.
 *
 * Dynamic Programming String
 */

//Dynamic Programming Backtracking

public class _107_Word_Break {
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

//-------------------------------------------------------------------------///////////

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

//-------------------------------------------------------------------------/////////////

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
        Set<String> wordDict =  new HashSet<>();
        wordDict.addAll(Arrays.asList(arr));
        //System.out.println(Arrays.asList(arr).getClass());
        System.out.println(wordBreak3(s, wordDict));
    }

//-------------------------------------------------------------------------////////////

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



}
