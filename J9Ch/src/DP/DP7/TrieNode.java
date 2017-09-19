package DP.DP7;

public class TrieNode {
    // Initialize your data structure here.
    public TrieNode[] children;
    public boolean hasWord;
    public String str;

    // Initialize your data structure here.
    public TrieNode() {
        children = new TrieNode[26];
        for (int i = 0; i < 26; ++i)
            children[i] = null;
        hasWord = false;
    }

    // Adds a word into the data structure.
    public static void addWord(TrieNode root, String word) {
        TrieNode now = root;
        for(int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (now.children[c - 'a'] == null) {
                now.children[c - 'a'] = new TrieNode();
            }
            now = now.children[c - 'a'];
        }
        now.str = word;
        now.hasWord = true;
    }
}
