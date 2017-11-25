package _10_DS.Trie;
import java.util.*;

//  676. Implement Magic Dictionary
//  https://leetcode.com/problems/implement-magic-dictionary/description/
//  Hash Table, Trie
public class _676_Implement_Magic_Dictionary_M {
    //  https://leetcode.com/problems/implement-magic-dictionary/solution/

    //Approach #1: Brute Force with Bucket-By-Length [Accepted]
    /*
Intuition and Algorithm

Call two strings neighbors if exactly one character can be changed in one to make the strings equal (ie. their hamming distance is 1.)

Strings can only be neighbors if their lengths are equal. When searching a new word, let's check only the words that are the same length.
     */
    class MagicDictionary1 {
        Map<Integer, ArrayList<String>> buckets;
        public MagicDictionary1() {
            buckets = new HashMap();
        }

        public void buildDict(String[] words) {
            for (String word: words) {
                buckets.computeIfAbsent(word.length(), x -> new ArrayList()).add(word);
            }
        }

        public boolean search(String word) {
            if (!buckets.containsKey(word.length())) return false;
            for (String candidate: buckets.get(word.length())) {
                int mismatch = 0;
                for (int i = 0; i < word.length(); ++i) {
                    if (word.charAt(i) != candidate.charAt(i)) {
                        if (++mismatch > 1) break;
                    }
                }
                if (mismatch == 1) return true;
            }
            return false;
        }
    }

//-----------------------------------------------------------------------------
    //Approach #2: Generalized Neighbors [Accepted]
    /*
    Intuition

Recall in Approach #1 that two words are neighbors if exactly one character can be changed in one word to make the strings equal.

Let's say a word 'apple' has generalized neighbors '*pple', 'a*ple', 'ap*le', 'app*e', and 'appl*'. When searching for whether a word like 'apply' has a neighbor like 'apple', we only need to know whether they have a common generalized neighbor.

Algorithm

Continuing the above thinking, one issue is that 'apply' is not a neighbor with itself, yet it has the same generalized neighbor '*pply'. To remedy this, we'll count how many sources generated '*pply'. If there are 2 or more, then one of them won't be 'apply'. If there is exactly one, we should check that it wasn't 'apply'. In either case, we can be sure that there was some magic word generating '*pply' that wasn't 'apply'.
     */
    public class MagicDictionary2 {
        Set<String> words;
        Map<String, Integer> count;

        public MagicDictionary2() {
            words = new HashSet();
            count = new HashMap();
        }

        private ArrayList<String> generalizedNeighbors(String word) {
            ArrayList<String> ans = new ArrayList();
            char[] ca = word.toCharArray();
            for (int i = 0; i < word.length(); ++i) {
                char letter = ca[i];
                ca[i] = '*';
                String magic = new String(ca);
                ans.add(magic);
                ca[i] = letter;
            }
            return ans;
        }

        public void buildDict(String[] words) {
            for (String word: words) {
                this.words.add(word);
                for (String nei: generalizedNeighbors(word)) {
                    count.put(nei, count.getOrDefault(nei, 0) + 1);
                }
            }
        }

        public boolean search(String word) {
            for (String nei: generalizedNeighbors(word)) {
                int c = count.getOrDefault(nei, 0);
                if (c > 1 || c == 1 && !words.contains(word)) return true;
            }
            return false;
        }
    }


//-----------------------------------------------------------------------------

//    Easy 14 lines Java solution, HashMap
//    For each word in dict, for each char, remove the char and put the rest of the word as key, a pair of index of the removed char and the char as part of value list into a map. e.g.
//"hello" -> {"ello":[[0, 'h']], "hllo":[[1, 'e']], "helo":[[2, 'l'],[3, 'l']], "hell":[[4, 'o']]}
//    During search, generate the keys as in step 1. When we see there's pair of same index but different char in the value array, we know the answer is true. e.g.
//            "healo" when remove a, key is "helo" and there is a pair [2, 'l'] which has same index but different char. Then the answer is true;
    class MagicDictionar3 {

        Map<String, List<int[]>> map = new HashMap<>();
        /** Initialize your data structure here. */
//        public MagicDictionary3() {
//        }

        /** Build a dictionary through a list of words */
        public void buildDict(String[] dict) {
            for (String s : dict) {
                for (int i = 0; i < s.length(); i++) {
                    String key = s.substring(0, i) + s.substring(i + 1);
                    int[] pair = new int[] {i, s.charAt(i)};

                    List<int[]> val = map.getOrDefault(key, new ArrayList<int[]>());
                    val.add(pair);

                    map.put(key, val);
                }
            }
        }

        /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
        public boolean search(String word) {
            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0, i) + word.substring(i + 1);
                if (map.containsKey(key)) {
                    for (int[] pair : map.get(key)) {
                        if (pair[0] == i && pair[1] != word.charAt(i)) return true;
                    }
                }
            }
            return false;
        }
    }

//-----------------------------------------------------------------------------

//    Easiest JAVA with Trie, no need to count the number of changes
//    Below is my accepted java code.
//    First build a trie tree, and in search(String word) function, we just edit every character from 'a' to 'z' and search the new string.
//            (This process is like "word ladder")

    class MagicDictionary4 {
        class TrieNode {
            TrieNode[] children = new TrieNode[26];
            boolean isWord;
            public TrieNode() {}
        }
        TrieNode root;
        /** Initialize your data structure here. */
        public MagicDictionary4() {
            root = new TrieNode();
        }

        /** Build a dictionary through a list of words */
        public void buildDict(String[] dict) {
            for (String s : dict) {
                TrieNode node = root;
                for (char c : s.toCharArray()) {
                    if (node.children[c - 'a'] == null) {
                        node.children[c - 'a'] = new TrieNode();
                    }
                    node = node.children[c - 'a'];
                }
                node.isWord = true;
            }
        }

        /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
        public boolean search(String word) {
            char[] arr = word.toCharArray();
            for (int i = 0; i < word.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    if (arr[i] == c) {
                        continue;
                    }
                    char org = arr[i];
                    arr[i] = c;
                    if (helper(new String(arr), root)) {
                        return true;
                    }
                    arr[i] = org;
                }
            }
            return false;
        }
        public boolean helper(String s, TrieNode root) {
            TrieNode node = root;
            for (char c : s.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    return false;
                }
                node = node.children[c - 'a'];
            }
            return node.isWord;
        }
    }

//-----------------------------------------------------------------------------


//    Efficient Trie and Java 8 w/ Explanation
//    The implementation is a simple Trie, with the method relaxedSearch.
//
//    relaxedSearch searches for a word, with one deviation from a normal trie.
//
//    If there is a match with the current character, it proceeds as usual in that branch.
//            But for all the non matched characters, it still continues searching, by incrementing the changedTimes variable, which maintains how many times a character was changed in the word search from the root.
//
//    Any search that involves changedTimes > 1, is immediately terminated by returning false as we are allowed to change only one character.
//
//    The solution is reached, when we find word in the trie and the changedTimes is exactly == 1.

    class MagicDictionary5{

        Trie trie;
        public MagicDictionary5() {
            trie = new Trie(256);
        }

        public void buildDict(String[] dict) {
            Arrays.stream(dict).forEach(s -> trie.insert(s));
        }

        public boolean search(String word) {
            return trie.relaxedSearch(word);
        }

        class Trie {
            private int R;
            private TrieNode root;

            public Trie(int R) {
                this.R = R;
                root = new TrieNode();
            }

            public boolean relaxedSearch(String word) {
                return relaxedSearch(root, word, 0);
            }

            private boolean relaxedSearch(TrieNode root, String word, int changedTimes) {
                if (root == null || (!root.isWord && word.isEmpty()) || changedTimes > 1) return false;
                if (root.isWord && word.isEmpty()) return changedTimes == 1;
                return Arrays.stream(root.next).anyMatch(nextNode -> relaxedSearch(nextNode, word.substring(1),
                        root.next[word.charAt(0)] == nextNode ? changedTimes : changedTimes+1));
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
//-----------------------------------------------------------------------------




}
/*
Implement a magic directory with buildDict, and search methods.

For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.

For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word, the modified word is in the dictionary you just built.

Example 1:
Input: buildDict(["hello", "leetcode"]), Output: Null
Input: search("hello"), Output: False
Input: search("hhllo"), Output: True
Input: search("hell"), Output: False
Input: search("leetcoded"), Output: False
Note:
You may assume that all the inputs are consist of lowercase letters a-z.
For contest purpose, the test data is rather small by now. You could think about highly efficient algorithm after the contest.
Please remember to RESET your class variables declared in class MagicDictionary, as static/class variables are persisted across multiple test cases. Please see here for more details.
 */