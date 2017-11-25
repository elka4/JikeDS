package _05_DFS._Back_Word;


import org.junit.Test;

import java.util.*;

/*

1.  Word Ladder
2.  Word Ladder II
3.  Word Search
4.  Word Search II
5.  Word Break
6.  Word Break II
7.  Word Pattern
8.  Word Pattern II


 */
public class Back_Word {
//-------------------------------------------------------------------------
//1.  Word Ladder
//  127. Word Ladder
//  https://leetcode.com/problems/word-ladder/description/
//  http://www.lintcode.com/zh-cn/problem/word-ladder/
//  BFS

/*
给出两个单词（start和end）和一个字典，找到从start到end的最短转换序列

比如： 每次只能改变一个字母。 变换过程中的中间单词必须在字典中出现。

start = "hit"

end = "cog"

dict = ["hot","dot","dog","lot","log"]

一个最短的变换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog"，

返回它的长度 5

 */

    // 9Ch
    // version: LeetCode
    public int ladderLength1(String start, String end, List<String> wordList) {
        Set<String> dict = new HashSet<>();
        for (String word : wordList) {
            dict.add(word);
        }

        if (start.equals(end)) {
            return 1;
        }

        HashSet<String> hash = new HashSet<String>();
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        hash.add(start);

        int length = 1;
        while (!queue.isEmpty()) {
            length++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                for (String nextWord: getNextWords(word, dict)) {
                    if (hash.contains(nextWord)) {
                        continue;
                    }
                    if (nextWord.equals(end)) {
                        return length;
                    }

                    hash.add(nextWord);
                    queue.offer(nextWord);
                }
            }
        }

        return 0;
    }

    // replace character of a string at given index to a given character
    // return a new string
    private String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }

    // get connections with given word.
    // for example, given word = 'hot', dict = {'hot', 'hit', 'hog'}
    // it will return ['hit', 'hog']
    private ArrayList<String> getNextWords(String word, Set<String> dict) {
        ArrayList<String> nextWords = new ArrayList<String>();
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < word.length(); i++) {
                if (c == word.charAt(i)) {
                    continue;
                }
                String nextWord = replace(word, i, c);
                if (dict.contains(nextWord)) {
                    nextWords.add(nextWord);
                }
            }
        }
        return nextWords;
    }

    @Test
    public void test01(){
        String start = "hit";
        String end = "cog";
        Set<String> dict = new HashSet<>();
        dict.add("hot");
        dict.add("dot");
        dict.add("dog");
        dict.add("lot");
        dict.add("log");
        dict.add("cog");
        List<String> wordlist = new ArrayList<>();
        wordlist.addAll(dict);
        System.out.println(ladderLength1(start, end, wordlist));
        //[[hit, hot, dot, dog, cog], [hit, hot, lot, log, cog]]
    }//5

//-------------------------------------------------------------------------
//2.  Word Ladder II

//  126. Word Ladder II
//  https://leetcode.com/problems/word-ladder-ii/
//  http://www.lintcode.com/zh-cn/problem/word-ladder-ii/
//  Array, String, Backtracking, Breadth-first Search


/*
给出两个单词（start和end）和一个字典，找出所有从start到end的最短转换序列
比如： 每次只能改变一个字母。 变换过程中的中间单词必须在字典中出现。
注意事项   所有单词具有相同的长度。  所有单词都只包含小写字母。
start = "hit"  end = "cog"  dict = ["hot","dot","dog","lot","log"]
返回[ ["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"] ]
 */

    // 9Ch
    public List<List<String>> findLadders(String start, String end,
                                          Set<String> dict) {
        List<List<String>> ladders = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Map<String, Integer> distance = new HashMap<String, Integer>();

        dict.add(start);
        dict.add(end);

        bfs(map, distance, start, end, dict);

        List<String> path = new ArrayList<String>();

        dfs(ladders, path, end, start, distance, map);

        return ladders;
    }

    @Test
    public void Word_Ladder_II_1(){
        String start = "hit";
        String end = "cog";
        Set<String> dict = new HashSet<>();
        dict.add("hot");
        dict.add("dot");
        dict.add("dog");
        dict.add("lot");
        dict.add("log");
        System.out.println(findLadders(start, end, dict));
        //[[hit, hot, dot, dog, cog], [hit, hot, lot, log, cog]]
    }

    void dfs(List<List<String>> ladders, List<String> path, String crt,
             String start, Map<String, Integer> distance,
             Map<String, List<String>> map) {
        path.add(crt);
        if (crt.equals(start)) {
            Collections.reverse(path);
            ladders.add(new ArrayList<String>(path));
            Collections.reverse(path);
        } else {
            for (String next : map.get(crt)) {
                if (distance.containsKey(next) && distance.get(crt) == distance.get(next) + 1) {
                    dfs(ladders, path, next, start, distance, map);
                }
            }
        }
        path.remove(path.size() - 1);
    }

    void bfs(Map<String, List<String>> map, Map<String, Integer> distance,
             String start, String end, Set<String> dict) {
        Queue<String> q = new LinkedList<String>();
        q.offer(start);
        distance.put(start, 0);
        for (String s : dict) {
            map.put(s, new ArrayList<String>());
        }

        while (!q.isEmpty()) {
            String crt = q.poll();

            List<String> nextList = expand(crt, dict);
            for (String next : nextList) {
                map.get(next).add(crt);
                if (!distance.containsKey(next)) {
                    distance.put(next, distance.get(crt) + 1);
                    q.offer(next);
                }
            }
        }
    }

    List<String> expand(String crt, Set<String> dict) {
        List<String> expansion = new ArrayList<String>();

        for (int i = 0; i < crt.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch != crt.charAt(i)) {
                    String expanded = crt.substring(0, i) + ch
                            + crt.substring(i + 1);
                    if (dict.contains(expanded)) {
                        expansion.add(expanded);
                    }
                }
            }
        }

        return expansion;
    }


//-------------------------------------------------------------------------
//3.  Word Search

//  79. Word Search
//  https://leetcode.com/problems/word-search/description/
//  http://www.lintcode.com/zh-cn/problem/word-search/
//   Array, Backtracking
    
/*
给出一个二维的字母板和一个单词，寻找字母板网格中是否存在这个单词。
单词可以由按顺序的相邻单元的字母组成，其中相邻单元指的是水平或者垂直方向相邻。
每个单元中的字母最多只能使用一次。

给出board = [ "ABCE",  "SFCS",  "ADEE" ]
word = "ABCCED"， ->返回 true,
word = "SEE"，-> 返回 true,
word = "ABCB"， -> 返回 false.

 */


    //    Accepted very short Java solution. No additional space.
    //    Here accepted solution based on recursion. To save memory I decuded to apply bit mask for every visited cell. Please check board[y][x] ^= 256;
    public boolean exist1(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int y=0; y<board.length; y++) {
            for (int x=0; x<board[y].length; x++) {
                if (exist1(board, y, x, w, 0)) return true;
            }
        }
        return false;
    }

    private boolean exist1(char[][] board, int y, int x, char[] word, int i) {
        if (i == word.length) return true;
        if (y<0 || x<0 || y == board.length || x == board[y].length) return false;
        if (board[y][x] != word[i]) return false;
        board[y][x] ^= 256;
        boolean exist = exist1(board, y, x+1, word, i+1)
                || exist1(board, y, x-1, word, i+1)
                || exist1(board, y+1, x, word, i+1)
                || exist1(board, y-1, x, word, i+1);
        board[y][x] ^= 256;
        return exist;
    }

    @Test
    public void WordSearch_1(){
//        int[] arr = new int[]{1,2,3};
//        String[] strs = new String[]{"22"};
        char[][] board = new char[][] {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
//        char[][] board1 = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
//        char[] board2 = new char[4]{'A','B','C','E'};
//        char[][] board = new char[3][4];
//        char[] values2 = { 'a', 'b', 'c' };
//        board[0][1] = 'A';
        System.out.println(exist1(board, "ABCCED"));
        System.out.println(exist1(board, "SEE"));
        System.out.println(exist1(board, "ABCB"));

    }

//------------------------------------------------------------

    //Simple solution
    public boolean exist3(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(exist3(board, i, j, word, 0)) return true;
            }
        }
        return false;
    }

    private boolean exist3(char[][] board, int x, int y, String word, int start) {
        if(start >= word.length()) return true;
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length) return false;
        if (board[x][y] == word.charAt(start++)) {
            char c = board[x][y];
            board[x][y] = '#';
            boolean res = exist3(board, x + 1, y, word, start) ||
                    exist3(board, x - 1, y, word, start) ||
                    exist3(board, x, y + 1, word, start) ||
                    exist3(board, x, y - 1, word, start);
            board[x][y] = c;
            return res;
        }
        return false;
    }

//-------------------------------------------------------------------------
//4.  Word Search II


//  212. Word Search II
//  https://leetcode.com/problems/word-search-ii/description/
//  http://www.lintcode.com/zh-cn/problem/word-search-ii/
//  Backtracking, Trie
    
/*
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
*/

/*My simple and clean Java code using DFS and Trie
    Compared with Word Search, I make my DFS with a tire but a word. The Trie is formed by all the words in given words. Then during the DFS, for each current formed word, I check if it is in the Trie.*/
/////////////
    class TrieNode {
        public TrieNode[] children = new TrieNode[26];
        public String item = "";

        // Initialize your data structure here.
        public TrieNode() {
        }
    }

    class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the trie.
        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.item = word;
        }

        // Returns if the word is in the trie.
        public boolean search(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) return false;
                node = node.children[c - 'a'];
            }
            return node.item.equals(word);
        }

        // Returns if there is any word in the trie
        // that starts with the given prefix.
        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for (char c : prefix.toCharArray()) {
                if (node.children[c - 'a'] == null) return false;
                node = node.children[c - 'a'];
            }
            return true;
        }
    }
//////////
    public class Solution04 {
        Set<String> res = new HashSet<String>();

        public List<String> findWords(char[][] board, String[] words) {
            Trie trie = new Trie();
            for (String word : words) {
                trie.insert(word);
            }

            int m = board.length;
            int n = board[0].length;
            boolean[][] visited = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dfs(board, visited, "", i, j, trie);
                }
            }

            return new ArrayList<String>(res);
        }

        public void dfs(char[][] board, boolean[][] visited, String str, int x, int y, Trie trie) {
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) return;
            if (visited[x][y]) return;

            str += board[x][y];
            if (!trie.startsWith(str)) return;

            if (trie.search(str)) {
                res.add(str);
            }

            visited[x][y] = true;
            dfs(board, visited, str, x - 1, y, trie);
            dfs(board, visited, str, x + 1, y, trie);
            dfs(board, visited, str, x, y - 1, trie);
            dfs(board, visited, str, x, y + 1, trie);
            visited[x][y] = false;
        }
    }

//-------------------------------------------------------------------------
//5.  Word Break

//  139. Word Break
//  https://leetcode.com/problems/word-break/description/
//  http://www.lintcode.com/zh-cn/problem/word-break/
//  DP

/*
lint   BFS

给出两个单词（start和end）和一个字典，找到从start到end的最短转换序列

比如：   每次只能改变一个字母。  变换过程中的中间单词必须在字典中出现。

注意事项

    如果没有转换序列则返回0。
    所有单词具有相同的长度。
    所有单词都只包含小写字母。

您在真实的面试中是否遇到过这个题？
样例

给出数据如下：

start = "hit"

end = "cog"

dict = ["hot","dot","dog","lot","log"]

一个最短的变换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog"，

返回它的长度 5

 */


//-----------------------------------------------------------------------------
    //Approach #2 Recursion with memoization [Accepted]

    public boolean wordBreak2(String s, List<String> wordDict) {
        return word_Break2(s, new HashSet(wordDict), 0, new Boolean[s.length()]);
    }
    public boolean word_Break2(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && word_Break2(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }


//-----------------------------------------------------------------------------
    //Approach #3 Using Breadth-First-Search [Accepted]

    public boolean wordBreak3(String s, List<String> wordDict) {
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


//-----------------------------------------------------------------------------
    //Java implementation using DP in two ways

    public boolean wordBreak4(String s, Set<String> dict) {

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


//-------------------------------------------------------------------------
//6.  Word Break II

//  140. Word Break II
//  https://leetcode.com/problems/word-break-ii/description/
//
//  backtracking, DP

/*
给一字串s和单词的字典dict,在字串中增加空格来构建一个句子，并且所有单词都来自字典。
返回所有有可能的句子。
您在真实的面试中是否遇到过这个题？
样例

给一字串lintcode,字典为["de", "ding", "co", "code", "lint"]
则结果为["lint code", "lint co de"]。

UPDATE (2017/1/4):
The wordDict parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
 */


    //Approach #2 Recursion with memoization [Accepted]
    public List<String> wordBreak2(String s, Set<String> wordDict) {

        return word_Break2(s, wordDict, 0);
    }
    HashMap<Integer, List<String>> map = new HashMap<>();

    public List<String> word_Break2(String s, Set<String> wordDict, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
            res.add("");
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end))) {
                List<String> list = word_Break2(s, wordDict, end);
                for (String l : list) {
                    res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
                }
            }
        }
        map.put(start, res);
        return res;
    }

    //------------------------------------------------------------------------------///
    //Approach #3 Using Dynamic Programming [Time Limit Exceeded]:
    public List<String> wordBreak3(String s, Set<String> wordDict) {
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



    // 9Ch
    // version 1:
    public List<String> wordBreak_J1(String s, Set<String> wordDict) {
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
//-------------------------------------------------------------------------
//7.  Word Pattern

//  290. Word Pattern
//  https://leetcode.com/problems/word-pattern/description/
//  Hash Table
/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
 */

    //8 lines simple Java
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        Map index = new HashMap();
        for (Integer i=0; i<words.length; ++i)
            if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
                return false;
        return true;
    }

    //My 3ms java solution using only one hashmap
    public boolean wordPattern3(String pattern, String str) {
        if (pattern == null || str == null) {
            return false;
        }
        char[] patterns = pattern.toCharArray();
        String[] strs = str.split(" ");
        if (patterns.length != strs.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<Character, String>();
        for (int i=0; i<patterns.length; i++) {
            if (map.containsKey(patterns[i])) {
                if (!map.get(patterns[i]).equals(strs[i])) {
                    return false;
                }
            } else if (map.containsValue(strs[i])) {
                return false;
            }
            map.put(patterns[i], strs[i]);
        }
        return true;
    }

    @Test
    public void wordPattern_1(){
        System.out.println(wordPattern("abba", "dog cat cat dog"));
        System.out.println(wordPattern("abba", "dog cat cat fish"));
        System.out.println(wordPattern("aaaa", "dog cat cat dog"));
        System.out.println(wordPattern("abba", "dog dog dog dog"));
    }

//-------------------------------------------------------------------------
//8.  Word Pattern II

//  291. Word Pattern II
//  https://leetcode.com/problems/word-pattern-ii/description/
//  Backtracking

    //Share my Java backtracking solution
    public boolean wordPatternMatch1(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        return isMatch(str, 0, pattern, 0, map, set);
    }

    boolean isMatch(String str, int i, String pat, int j, Map<Character, String> map, Set<String> set) {
        // base case
        if (i == str.length() && j == pat.length()) return true;
        if (i == str.length() || j == pat.length()) return false;

        // get current pattern character
        char c = pat.charAt(j);

        // if the pattern character exists
        if (map.containsKey(c)) {
            String s = map.get(c);

            // then check if we can use it to match str[i...i+s.length()]
            if (!str.startsWith(s, i)) {
                return false;
            }

            // if it can match, great, continue to match the rest
            return isMatch(str, i + s.length(), pat, j + 1, map, set);
        }

        // pattern character does not exist in the map
        for (int k = i; k < str.length(); k++) {
            String p = str.substring(i, k + 1);

            if (set.contains(p)) {
                continue;
            }

            // create or update it
            map.put(c, p);
            set.add(p);

            // continue to match the rest
            if (isMatch(str, k + 1, pat, j + 1, map, set)) {
                return true;
            }

            // backtracking
            map.remove(c);
            set.remove(p);
        }

        // we've tried our best but still no luck
        return false;
    }

    //    pattern = "abab", str = "redblueredblue" should return true.
//    pattern = "aaaa", str = "asdasdasdasd" should return true.
//    pattern = "aabb", str = "xyzabcxzyabc" should return false.
    @Test
    public void WordPatternII_1(){
        System.out.println(wordPatternMatch1("abab", "redblueredblue"));
        System.out.println(wordPatternMatch1("aaaa", "asdasdasdasd"));
        System.out.println(wordPatternMatch1("aabb", "xyzabcxzyabc"));
    }


//-------------------------------------------------------------------------


}
