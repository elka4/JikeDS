package _05_DFS._Back_Word;
import java.util.*;

// backtracking, DP

//
public class _140_BackTracking_Word_Break_II_H {

    public class Solution {
        public List<String> wordBreak(String s, Set<String> dict) {
            List<String> result = new ArrayList<String>();
            for(int j = s.length() - 1; j >= 0; j--){
                if(dict.contains(s.substring(j)))
                    break;
                else{
                    if(j == 0)
                        return result;
                }
            }
            for(int i = 0; i < s.length()-1; i++)
            {
                if(dict.contains(s.substring(0,i+1)))
                {
                    List<String> strs = wordBreak(s.substring(i+1,s.length()),dict);
                    if(strs.size() != 0)
                        for(Iterator<String> it = strs.iterator();it.hasNext();)
                        {
                            result.add(s.substring(0,i+1)+" "+it.next());
                        }
                }
            }
            if(dict.contains(s)) result.add(s);
            return result;
        }
    }
    //Approach #2 Recursion with memoization [Accepted]
    public class Solution2 {

        public List<String> wordBreak(String s, Set<String> wordDict) {
            return word_Break(s, wordDict, 0);
        }
        HashMap<Integer, List<String>> map = new HashMap<>();

        public List<String> word_Break(String s, Set<String> wordDict, int start) {
            if (map.containsKey(start)) {
                return map.get(start);
            }
            LinkedList<String> res = new LinkedList<>();
            if (start == s.length()) {
                res.add("");
            }
            for (int end = start + 1; end <= s.length(); end++) {
                if (wordDict.contains(s.substring(start, end))) {
                    List<String> list = word_Break(s, wordDict, end);
                    for (String l : list) {
                        res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
                    }
                }
            }
            map.put(start, res);
            return res;
        }
    }

    //Approach #3 Using Dynamic Programming [Time Limit Exceeded]:
    public class Solution3 {
        public List<String> wordBreak(String s, Set<String> wordDict) {
            LinkedList<String>[] dp = new LinkedList[s.length() + 1];
            LinkedList<String> initial = new LinkedList<>();
            initial.add("");
            dp[0] = initial;
            for (int i = 1; i <= s.length(); i++) {
                LinkedList<String> list = new LinkedList<>();
                for (int j = 0; j < i; j++) {
                    if (dp[j].size() > 0 && wordDict.contains(s.substring(j, i))) {
                        for (String l : dp[j]) {
                            list.add(l + (l.equals("") ? "" : " ") + s.substring(j, i));
                        }
                    }
                }
                dp[i] = list;
            }
            return dp[s.length()];
        }
    }


/////////////////////////////////////////////////////////////////////////////////////////////
    //Jiuzhang
    // version 1:
    public class Jiuzhang1 {
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


    }

// version 2:

    public class Jiuzhang2 {
        public ArrayList<String> wordBreak(String s, Set<String> dict) {
            // Note: The Solution object is instantiated only once and is reused by each test case.
            Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
            return wordBreakHelper(s,dict,map);
        }

        public ArrayList<String> wordBreakHelper(String s, Set<String> dict,
                                                 Map<String, ArrayList<String>> memo){
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
    }
/////////////////////////////////////////////////////////////////////////////////////////////



}
/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. You may assume the dictionary does not contain duplicate words.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].

UPDATE (2017/1/4):
The wordDict parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
 */

/*
给一字串s和单词的字典dict,在字串中增加空格来构建一个句子，并且所有单词都来自字典。
返回所有有可能的句子。
您在真实的面试中是否遇到过这个题？
样例

给一字串lintcode,字典为["de", "ding", "co", "code", "lint"]
则结果为["lint code", "lint co de"]。

 */