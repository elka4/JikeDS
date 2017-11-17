package _10_DS.Trie;
import java.util.*;
import java.util.stream.*;

//  648. Replace Words
//  https://leetcode.com/problems/replace-words/description/
//  Hash Table, Trie
public class _648_Replace_Words_M {
    //Java Simple/Classical Trie question/solution (Beat 96%)
    public String replaceWords(List<String> dict, String sentence) {
        String[] tokens = sentence.split(" ");
        TrieNode trie = buildTrie(dict);
        return replaceWords(tokens, trie);
    }

    private String replaceWords(String[] tokens, TrieNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String token : tokens) {
            stringBuilder.append(getShortestReplacement(token, root));
            stringBuilder.append(" ");
        }
        return stringBuilder.substring(0, stringBuilder.length()-1);
    }

    private String getShortestReplacement(String token, final TrieNode root) {
        TrieNode temp = root;
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : token.toCharArray()) {
            stringBuilder.append(c);
            if (temp.children[c - 'a'] != null) {
                if (temp.children[c - 'a'].isWord) {
                    return stringBuilder.toString();
                }
                temp = temp.children[c - 'a'];
            } else {
                return token;
            }
        }
        return token;
    }

    private TrieNode buildTrie(List<String> dict) {
        TrieNode root = new TrieNode(' ');
        for (String word : dict) {
            TrieNode temp = root;
            for (char c : word.toCharArray()) {
                if (temp.children[c - 'a'] == null) {
                    temp.children[c - 'a'] = new TrieNode(c);
                }
                temp = temp.children[c - 'a'];
            }
            temp.isWord = true;
        }
        return root;
    }

    public class TrieNode {
        char val;
        TrieNode[] children;
        boolean isWord;

        public TrieNode(char val) {
            this.val = val;
            this.children = new TrieNode[26];
            this.isWord = false;
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
Simple Java 8 and Trie based solution
The only modification to the standard Trie, is that we need a function getShortestPrefix that returns the shortest prefix of the given word in the trie, if the shortest prefix exists or return the original word. Once we have this, all we have to do is iterate through the sentence and replace each word with the getShortestPrefix(word) in the trie.
 */

class Solution2{
    public String replaceWords(List<String> dict, String sentence) {
        Trie trie = new Trie(256);
        dict.forEach(s -> trie.insert(s));
        List<String> res = new ArrayList<>();
        Arrays.stream(sentence.split(" ")).forEach(str -> res.add(trie.getShortestPrefix(str)));
        return res.stream().collect(Collectors.joining(" "));
    }


    class Trie {
        private int R;
        private TrieNode root;

        public Trie(int R) {
            this.R = R;
            root = new TrieNode();
        }

        // Returns the shortest prefix of the word that is there in the trie
        // If no such prefix exists, return the original word
        public String getShortestPrefix(String word) {
            int len =  getShortestPrefix(root, word, -1);
            return (len < 1) ? word : word.substring(0, len);
        }

        private int getShortestPrefix(TrieNode root, String word, int res) {
            if(root == null || word.isEmpty()) return 0;
            if(root.isWord) return res + 1;
            return getShortestPrefix(root.next[word.charAt(0)], word.substring(1), res+1);
        }

        // Inserts a word into the trie.
        public void insert(String word) {
            insert(root, word);
        }

        private void insert(TrieNode root, String word) {
            if (word.isEmpty()) { root.isWord = true; return; }
            if (root.next[word.charAt(0)] == null) root.next[word.charAt(0)] = new TrieNode();
            insert(root.next[word.charAt(0)], word.substring(1));
        }

        private class TrieNode {
            private TrieNode[] next = new TrieNode[R];
            private boolean isWord;
        }
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////
//Java solution, 12 lines, HashSet
//    Why it is a hard problem? Anyway...

    public class Solution3 {
        public String replaceWords(List<String> dict, String sentence) {
            if (dict == null || dict.size() == 0) return sentence;

            Set<String> set = new HashSet<>();
            for (String s : dict) set.add(s);

            StringBuilder sb = new StringBuilder();
            String[] words = sentence.split("\\s+");

            for (String word : words) {
                String prefix = "";
                for (int i = 1; i <= word.length(); i++) {
                    prefix = word.substring(0, i);
                    if (set.contains(prefix)) break;
                }
                sb.append(" " + prefix);
            }

            return sb.deleteCharAt(0).toString();
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////////////////////////



}
/*
In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor. For example, the root an, followed by other, which can form another word another.

Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence with the root forming it. If a successor has many roots can form it, replace it with the root with the shortest length.

You need to output the sentence after the replacement.

Example 1:
Input: dict = ["cat", "bat", "rat"]
sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"
Note:
The input will only have lower-case letters.
1 <= dict words number <= 1000
1 <= sentence words number <= 1000
1 <= root length <= 100
1 <= sentence words length <= 1000

 */