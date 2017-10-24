package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;
public class _212_BackTracking_Word_Search_II_H {

    public class Solution {

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


////////////////////////////////////////////////////////////////////////////////??

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
}
