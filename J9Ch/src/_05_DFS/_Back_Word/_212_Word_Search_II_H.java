package _05_DFS._Back_Word;
import java.util.*;

//  212. Word Search II
//  https://leetcode.com/problems/word-search-ii/description/
//  http://www.lintcode.com/zh-cn/problem/word-search-ii/
//  Backtracking, Trie
public class _212_Word_Search_II_H {
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
//--------------------------------------------------------------------------------

    Set<String> res = new HashSet<String>();

    public List<String> findWords1(char[][] board, String[] words) {
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


//-------------------------------------------------------------------------------??

    public class Solution2 {
        public class TrieNode{
            public boolean isWord = false;
            public TrieNode[] child = new TrieNode[26];
            public TrieNode(){

            }
        }

        TrieNode root = new TrieNode();
        boolean[][] flag;
        public List<String> findWords(char[][] board, String[] words) {
            Set<String> result = new HashSet<>();
            flag = new boolean[board.length][board[0].length];

            addToTrie(words);

            for(int i = 0; i < board.length; i++){
                for(int j = 0; j < board[0].length; j++){
                    if(root.child[board[i][j] - 'a'] != null){
                        search(board, i, j, root, "", result);
                    }
                }
            }

            return new LinkedList<>(result);
        }

        private void addToTrie(String[] words){
            for(String word: words){
                TrieNode node = root;
                for(int i = 0; i < word.length(); i++){
                    char ch = word.charAt(i);
                    if(node.child[ch - 'a'] == null){
                        node.child[ch - 'a'] = new TrieNode();
                    }
                    node = node.child[ch - 'a'];
                }
                node.isWord = true;
            }
        }

        private void search(char[][] board, int i, int j, TrieNode node, String word, Set<String> result){
            if(i >= board.length || i < 0 || j >= board[i].length || j < 0 || flag[i][j]){
                return;
            }

            if(node.child[board[i][j] - 'a'] == null){
                return;
            }

            flag[i][j] = true;
            node = node.child[board[i][j] - 'a'];
            if(node.isWord){
                result.add(word + board[i][j]);
            }

            search(board, i-1, j, node, word + board[i][j], result);
            search(board, i+1, j, node, word + board[i][j], result);
            search(board, i, j-1, node, word + board[i][j], result);
            search(board, i, j+1, node, word + board[i][j], result);

            flag[i][j] = false;
        }
    }




//------------------------------------------------------------------------------
    /*  Java 15ms Easiest Solution (100.00%)
    Backtracking + Trie
Intuitively, start from every cell and try to build a word in the dictionary. Backtracking (dfs) is the powerful way to exhaust every possible ways. Apparently, we need to do pruning when current character is not in any word.

How do we instantly know the current character is invalid? HashMap?
How do we instantly know what's the next valid character? LinkedList?
But the next character can be chosen from a list of characters. "Mutil-LinkedList"?
Combing them, Trie is the natural choice. Notice that:

TrieNode is all we need. search and startsWith are useless.
No need to store character at TrieNode. c.next[i] != null is enough.
Never use c1 + c2 + c3. Use StringBuilder.
No need to use O(n^2) extra space visited[m][n].
No need to use StringBuilder. Storing word itself at leaf node is enough.
No need to use HashSet to de-duplicate. Use "one time search" trie.
     */
    class Solution03{
        public List<String> findWords(char[][] board, String[] words) {
            List<String> res = new ArrayList<>();
            TrieNode root = buildTrie(words);
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    dfs (board, i, j, root, res);
                }
            }
            return res;
        }

        public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
            char c = board[i][j];
            if (c == '#' || p.next[c - 'a'] == null) return;
            p = p.next[c - 'a'];
            if (p.word != null) {   // found one
                res.add(p.word);
                p.word = null;     // de-duplicate
            }

            board[i][j] = '#';
            if (i > 0) dfs(board, i - 1, j ,p, res);
            if (j > 0) dfs(board, i, j - 1, p, res);
            if (i < board.length - 1) dfs(board, i + 1, j, p, res);
            if (j < board[0].length - 1) dfs(board, i, j + 1, p, res);
            board[i][j] = c;
        }

        public TrieNode buildTrie(String[] words) {
            TrieNode root = new TrieNode();
            for (String w : words) {
                TrieNode p = root;
                for (char c : w.toCharArray()) {
                    int i = c - 'a';
                    if (p.next[i] == null) p.next[i] = new TrieNode();
                    p = p.next[i];
                }
                p.word = w;
            }
            return root;
        }

        class TrieNode {
            TrieNode[] next = new TrieNode[26];
            String word;
        }
    }
//------------------------------------------------------------------------------
/*My simple and clean Java code using DFS and Trie
    Compared with Word Search, I make my DFS with a tire but a word. The Trie is formed by all the words in given words. Then during the DFS, for each current formed word, I check if it is in the Trie.*/

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

//------------------------------------------------------------------------------
    // 9Ch
    public class Jiuzhang {

        class TrieNode {
            String s;
            boolean isString;
            HashMap<Character, TrieNode> subtree;
            public TrieNode() {
                // TODO Auto-generated constructor stub
                isString = false;
                subtree = new HashMap<Character, TrieNode>();
                s = "";
            }
        };


        class TrieTree{
            TrieNode root ;
            public TrieTree(TrieNode TrieNode) {
                root = TrieNode;
            }
            public void insert(String s) {
                TrieNode now = root;
                for (int i = 0; i < s.length(); i++) {
                    if (!now.subtree.containsKey(s.charAt(i))) {
                        now.subtree.put(s.charAt(i), new TrieNode());
                    }
                    now  =  now.subtree.get(s.charAt(i));
                }
                now.s = s;
                now.isString  = true;
            }
            public boolean find(String s){
                TrieNode now = root;
                for (int i = 0; i < s.length(); i++) {
                    if (!now.subtree.containsKey(s.charAt(i))) {
                        return false;
                    }
                    now  =  now.subtree.get(s.charAt(i));
                }
                return now.isString ;
            }
        };

//------------------------------------------------------------------------------
        public int []dx = {1, 0, -1, 0};
        public int []dy = {0, 1, 0, -1};

        public void search(char[][] board, int x, int y, TrieNode root, List<String> ans) {
            if(root.isString == true)
            {
                if(!ans.contains(root.s)){
                    ans.add(root.s);
                }
            }
            if(x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y]==0 || root == null)
                return ;
            if(root.subtree.containsKey(board[x][y])){
                for(int i = 0; i < 4; i++){
                    char now = board[x][y];
                    board[x][y] = 0;
                    search(board, x+dx[i], y+dy[i], root.subtree.get(now), ans);
                    board[x][y] = now;
                }
            }

        }

        public List<String> wordSearchII(char[][] board, List<String> words) {
            List<String> ans = new ArrayList<String>();

            TrieTree tree = new TrieTree(new TrieNode());
            for(String word : words){
                tree.insert(word);
            }
            for(int i = 0; i < board.length; i++){
                for(int j = 0; j < board[i].length; j++){
                    search(board, i, j, tree.root, ans);
                }
            }
            return ans;
            // write your code here
        }
    }


//------------------------------------------------------------------------------
}
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
Note:
You may assume that all inputs are consist of lowercase letters a-z.

click to show hint.

You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?

If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not? How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.


 */

/*


给出一个由小写字母组成的矩阵和一个字典。找出所有同时在字典和矩阵中出现的单词。一个单词可以从矩阵中的任意位置开始，可以向左/右/上/下四个相邻方向移动。
您在真实的面试中是否遇到过这个题？
样例

给出矩阵：

doaf
agai
dcan

和字典：

{"dog", "dad", "dgdg", "can", "again"}


返回 {"dog", "dad", "can", "again"}


dog:

doaf
agai
dcan

dad:

doaf
agai
dcan

can:

doaf
agai
dcan

again:

doaf
agai
dcan

挑战

使用单词查找树来实现你的算法
 */