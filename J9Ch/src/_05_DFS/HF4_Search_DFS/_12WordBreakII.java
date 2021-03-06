package _05_DFS.HF4_Search_DFS;

import org.junit.Test;

import java.util.*;

// Word Break II
public class _12WordBreakII {
    // version 1:
    private void search(int index, String s, List<Integer> path,
                        boolean[][] isWord, boolean[] possible,
                        List<String> result) {
        if (!possible[index]) {
            return;
        }

        if (index == s.length()) {
            StringBuilder sb = new StringBuilder();
            int lastIndex = 0;
            for (int i = 0; i < path.size(); i++) {
                sb.append(s.substring(lastIndex, path.get(i)));
                if (i != path.size() - 1) sb.append(" ");
                lastIndex = path.get(i);
            }
            result.add(sb.toString());
            return;
        }

        for (int i = index; i < s.length(); i++) {
            if (!isWord[index][i]) {
                continue;
            }
            path.add(i + 1);
            search(i + 1, s, path, isWord, possible, result);
            path.remove(path.size() - 1);
        }
    }

    public List<String> wordBreak(String s, Set<String> wordDict) {
        ArrayList<String> result = new ArrayList<String>();
        if (s.length() == 0) {
            return result;
        }

        boolean[][] isWord = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String word = s.substring(i, j + 1);
                isWord[i][j] = wordDict.contains(word);
            }
        }

        boolean[] possible = new boolean[s.length() + 1];
        possible[s.length()] = true;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (isWord[i][j] && possible[j + 1]) {
                    possible[i] = true;
                    break;
                }
            }
        }

        List<Integer> path = new ArrayList<Integer>();
        search(0, s, path, isWord, possible, result);
        return result;
    }

//-----------------------------------------------------------------------------

    // version 2:
    public ArrayList<String> wordBreak2(String s, Set<String> dict) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        return wordBreakHelper(s,dict,map);
    }

    public ArrayList<String> wordBreakHelper(String s, Set<String> dict, Map<String, ArrayList<String>> memo){
            if(memo.containsKey(s)) return memo.get(s);
            ArrayList<String> result = new ArrayList<String>();
            int n = s.length();
            if(n <= 0) return result;
            for(int len = 1; len <= n; ++len){
                String subfix = s.substring(0,len);
                if(dict.contains(subfix)){
                    if(len == n){
                        result.add(subfix);
                    }else{
                        String prefix = s.substring(len);
                        ArrayList<String> tmp = wordBreakHelper(prefix, dict, memo);
                        for(String item:tmp){
                            item = subfix + " " + item;
                            result.add(item);
                        }
                    }
                }
            }
            memo.put(s, result);
            return result;
        }

    @Test
    public void test02(){
        Set<String> dict = new HashSet<>();


        dict.add("cat");
        dict.add("cats");
        dict.add("and");
        dict.add("sand");
        dict.add("dog");

        String s = "catsanddog";

        System.out.println(wordBreak(s, dict));
    }

}
/*
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
Return all such possible sentences.
For example, given s = "catsanddog", dict = ["cat", "cats", "and", "sand", "dog"].
A solution is ["cats and dog", "cat sand dog"].
 */

