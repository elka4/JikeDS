package _10_DS.Trie;
import java.util.*;

//  472. Concatenated Words
//  https://leetcode.com/problems/concatenated-words/description/
//  Dynamic Programming,  Depth-first Search,  Trie
public class _472_Concatenated_Words_H {
/*    Java DP Solution
    Do you still remember how did you solve this problem? https://leetcode.com/problems/word-break/

    If you do know one optimized solution for above question is using DP, this problem is just one more step further. We iterate through each word and see if it can be formed by using other words.

    Of course it is also obvious that a word can only be formed by words shorter than it. So we can first sort the input by length of each word, and only try to form one word by using words in front of it.*/

    public class Solution1 {
        public  List<String> findAllConcatenatedWordsInADict(String[] words) {
            List<String> result = new ArrayList<>();
            Set<String> preWords = new HashSet<>();
            Arrays.sort(words, new Comparator<String>() {
                public int compare (String s1, String s2) {
                    return s1.length() - s2.length();
                }
            });

            for (int i = 0; i < words.length; i++) {
                if (canForm(words[i], preWords)) {
                    result.add(words[i]);
                }
                preWords.add(words[i]);
            }

            return result;
        }

        private  boolean canForm(String word, Set<String> dict) {
            if (dict.isEmpty()) return false;
            boolean[] dp = new boolean[word.length() + 1];
            dp[0] = true;
            for (int i = 1; i <= word.length(); i++) {
                for (int j = 0; j < i; j++) {
                    if (!dp[j]) continue;
                    if (dict.contains(word.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[word.length()];
        }
    }
//-------------------------------------------------------------------------////////////
    //102ms java Trie + DFS solution. With explanation, easy to understand.
    class Solution2{
        public List<String> findAllConcatenatedWordsInADict(String[] words) {
            List<String> res = new ArrayList<String>();
            if (words == null || words.length == 0) {
                return res;
            }
            TrieNode root = new TrieNode();
            for (String word : words) { // construct Trie tree
                if (word.length() == 0) {
                    continue;
                }
                addWord(word, root);
            }
            for (String word : words) { // test word is a concatenated word or not
                if (word.length() == 0) {
                    continue;
                }
                if (testWord(word.toCharArray(), 0, root, 0)) {
                    res.add(word);
                }
            }
            return res;
        }
        public boolean testWord(char[] chars, int index, TrieNode root, int count) { // count means how many words during the search path
            TrieNode cur = root;
            int n = chars.length;
            for (int i = index; i < n; i++) {
                if (cur.sons[chars[i] - 'a'] == null) {
                    return false;
                }
                if (cur.sons[chars[i] - 'a'].isEnd) {
                    if (i == n - 1) { // no next word, so test count to get result.
                        return count >= 1;
                    }
                    if (testWord(chars, i + 1, root, count + 1)) {
                        return true;
                    }
                }
                cur = cur.sons[chars[i] - 'a'];
            }
            return false;
        }
        public void addWord(String str, TrieNode root) {
            char[] chars = str.toCharArray();
            TrieNode cur = root;
            for (char c : chars) {
                if (cur.sons[c - 'a'] == null) {
                    cur.sons[c - 'a'] = new TrieNode();
                }
                cur = cur.sons[c - 'a'];
            }
            cur.isEnd = true;
        }
    }
        class TrieNode {
            TrieNode[] sons;
            boolean isEnd;
            public TrieNode() {
                sons = new TrieNode[26];
            }
    }

//-------------------------------------------------------------------------////////////


/*
    Simple Java Trie + DFS solution 144ms
    Most of lines are adding words into Trie Tree
    This solution is like putting two pointers to search through the tree. When find a word, put the other pointer back on root then continue searching.
    But I'm not sure about the time complexity of my solution. Suppose word length is len and there are n words. Is the time complexity O(len * n ^ 2)?
*/

    public class Solution3 {
        class TrieNode {
            TrieNode[] children;
            String word;
            boolean isEnd;
            boolean combo; //if this word is a combination of simple words
            boolean added; //if this word is already added in result
            public TrieNode() {
                this.children = new TrieNode[26];
                this.word = new String();
                this.isEnd = false;
                this.combo = false;
                this.added = false;
            }
        }
        private void addWord(String str) {
            TrieNode node = root;
            for (char ch : str.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new TrieNode();
                }
                node = node.children[ch - 'a'];
            }
            node.isEnd = true;
            node.word = str;
        }
        private TrieNode root;
        private List<String> result;
        public List<String> findAllConcatenatedWordsInADict(String[] words) {
            root = new TrieNode();
            for (String str : words) {
                if (str.length() == 0) {
                    continue;
                }
                addWord(str);
            }
            result = new ArrayList<>();
            dfs(root, 0);
            return result;
        }
        private void dfs(TrieNode node, int multi) {
            //multi counts how many single words combined in this word
            if(node.isEnd && !node.added && multi > 1) {
                node.combo = true;
                node.added = true;
                result.add(node.word);
            }
            searchWord(node, root, multi);
        }
        private void searchWord(TrieNode node1, TrieNode node2, int multi) {
            if (node2.combo) {
                return;
            }
            if (node2.isEnd) {
                //take the pointer of node2 back to root
                dfs(node1, multi + 1);
            }
            for (int  i = 0; i < 26; i++) {
                if (node1.children[i] != null && node2.children[i] != null) {
                    searchWord(node1.children[i], node2.children[i], multi);
                }
            }
        }
    }
//-------------------------------------------------------------------------////////////


//-------------------------------------------------------------------------////////////


//-------------------------------------------------------------------------////////////



}
/*
Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.

A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

Example:
Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
Note:
The number of elements of the given array will not exceed 10,000
The length sum of elements in the given array will not exceed 600,000.
All the input string will only include lower case letters.
The returned elements order does not matter.

 */