package _10_DS.Trie;
import java.util.*;


//  720. Longest Word in Dictionary
//  https://leetcode.com/problems/longest-word-in-dictionary/description/
public class _720_Longest_Word_in_Dictionary_E {

    //https://leetcode.com/problems/longest-word-in-dictionary/solution/

    //Approach #1: Brute Force [Accepted]
/*
Intuition

For each word, check if all prefixes word[:k] are present. We can use a Set structure to check this quickly.

Algorithm

Whenever our found word would be superior, we check if all it's prefixes are present, then replace our answer.

Alternatively, we could have sorted the words beforehand, so that we know the word we are considering would be the answer if all it's prefixes are present.


 */
    class Solution1 {
        public String longestWord(String[] words) {
            String ans = "";
            Set<String> wordset = new HashSet();
            for (String word: words) wordset.add(word);
            for (String word: words) {
                if (word.length() > ans.length() ||
                        word.length() == ans.length() && word.compareTo(ans) < 0) {
                    boolean good = true;
                    for (int k = 1; k < word.length(); ++k) {
                        if (!wordset.contains(word.substring(0, k))) {
                            good = false;
                            break;
                        }
                    }
                    if (good) ans = word;
                }
            }
            return ans;
        }
    }
    //Alternate Implementation

    class Solution2 {
        public String longestWord(String[] words) {
            Set<String> wordset = new HashSet();
            for (String word: words) wordset.add(word);
            Arrays.sort(words, (a, b) -> a.length() == b.length()
                    ? a.compareTo(b) : b.length() - a.length());
            for (String word: words) {
                boolean good = true;
                for (int k = 1; k < word.length(); ++k) {
                    if (!wordset.contains(word.substring(0, k))) {
                        good = false;
                        break;
                    }
                }
                if (good) return word;
            }

            return "";
        }
    }
//------------------------------------------------------------------------------
    //Approach #2: Trie + Depth-First Search [Accepted]

/*
Intuition

As prefixes of strings are involved, this is usually a natural fit for a trie (a prefix tree.)

Algorithm

Put every word in a trie, then depth-first-search from the start of the trie, only searching nodes that ended a word. Every node found (except the root, which is a special case) then represents a word with all it's prefixes present. We take the best such word.

In Python, we showcase a method using defaultdict, while in Java, we stick to a more general object-oriented approach.


 */

    class Solution {
        public String longestWord(String[] words) {
            Trie trie = new Trie();
            int index = 0;
            for (String word: words) {
                trie.insert(word, ++index); //indexed by 1
            }
            trie.words = words;
            return trie.dfs();
        }
    }
    class Node {
        char c;
        HashMap<Character, Node> children = new HashMap();
        int end;
        public Node(char c){
            this.c = c;
        }
    }

    class Trie {
        Node root;
        String[] words;
        public Trie() {
            root = new Node('0');
        }

        public void insert(String word, int index) {
            Node cur = root;
            for (char c: word.toCharArray()) {
                cur.children.putIfAbsent(c, new Node(c));
                cur = cur.children.get(c);
            }
            cur.end = index;
        }

        public String dfs() {
            String ans = "";
            Stack<Node> stack = new Stack();
            stack.push(root);
            while (!stack.empty()) {
                Node node = stack.pop();
                if (node.end > 0 || node == root) {
                    if (node != root) {
                        String word = words[node.end - 1];
                        if (word.length() > ans.length() ||
                                word.length() == ans.length() && word.compareTo(ans) < 0) {
                            ans = word;
                        }
                    }
                    for (Node nei: node.children.values()) {
                        stack.push(nei);
                    }
                }
            }
            return ans;
        }
    }
//------------------------------------------------------------------------------

/*[Java/C++] Clean Code
Sort the words alphabetically, therefore shorter words always comes before longer words;
Along the sorted list, populate the words that can be built;
Any prefix of a word must comes before that word.
Java*/

    class Solution4 {
        public String longestWord(String[] words) {
            Arrays.sort(words);
            Set<String> built = new HashSet<String>();
            String res = "";
            for (String w : words) {
                if (w.length() == 1 || built.contains(w.substring(0, w.length() - 1))) {
                    res = w.length() > res.length() ? w : res;
                    built.add(w);
                }
            }
            return res;
        }
    }

//------------------------------------------------------------------------------

    //Java solution with Trie
    class Solution5 {
        class TrieNode{
            public char val;
            public TrieNode[] hash;
            public boolean isWord;
            public TrieNode(){
                this.val='\u0000';
                this.hash=new TrieNode[26];
                this.isWord=false;
            }
            public TrieNode(char c){
                this.val=c;
                this.hash=new TrieNode[26];
                this.isWord=false;
            }
            public StringBuilder dfs(StringBuilder res){

                StringBuilder max=new StringBuilder();
                for(int i=0;i<26;i++){
                    if(hash[i]!=null && hash[i].isWord){
                        StringBuilder temp=new StringBuilder();
                        temp.append(hash[i].val);
                        temp.append(hash[i].dfs(temp));
                        if(temp.length() > max.length())
                            max=temp;
                    }
                }
                return max;
            }

        }
        public String longestWord(String[] words) {
            TrieNode root=new TrieNode();
            for(String word:words){
                TrieNode curr=root;
                for(int i=0;i<word.length();i++){
                    if(curr.hash[word.charAt(i)-'a']==null){
                        TrieNode temp=new TrieNode(word.charAt(i));
                        curr.hash[word.charAt(i)-'a']=temp;
                    }
                    curr=curr.hash[word.charAt(i)-'a'];
                    if(i==word.length()-1)
                        curr.isWord=true;
                }
            }
            StringBuilder res=new StringBuilder();
            res=root.dfs(res);

            return res.toString();
        }
    }

//------------------------------------------------------------------------------




}
/*
Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.

If there is no answer, return the empty string.
Example 1:
Input:
words = ["w","wo","wor","worl", "world"]
Output: "world"
Explanation:
The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
Example 2:
Input:
words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
Output: "apple"
Explanation:
Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
Note:

All the strings in the input will only contain lowercase letters.
The length of words will be in the range [1, 1000].
The length of words[i] will be in the range [1, 30].
 */