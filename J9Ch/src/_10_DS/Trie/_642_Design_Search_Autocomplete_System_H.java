package _10_DS.Trie;
import java.util.*;

//  642. Design Search Autocomplete System
//  https://leetcode.com/problems/design-search-autocomplete-system/description/
//  Design, Trie
public class _642_Design_Search_Autocomplete_System_H {
    //https://leetcode.com/problems/design-search-autocomplete-system/solution/
    //Approach #1 Brute Force [Time Limit Exceeded]
    public class AutocompleteSystem1 {
        HashMap < String, Integer > map = new HashMap < > ();
        class Node {
            Node(String st, int t) {
                sentence = st;
                times = t;
            }
            String sentence;
            int times;
        }
        public AutocompleteSystem1(String[] sentences, int[] times) {
            for (int i = 0; i < sentences.length; i++)
                map.put(sentences[i], times[i]);
        }
        String cur_sent = "";
        public List < String > input(char c) {
            List < String > res = new ArrayList < > ();
            if (c == '#') {
                map.put(cur_sent, map.getOrDefault(cur_sent, 0) + 1);
                cur_sent = "";
            } else {
                List < Node > list = new ArrayList < > ();
                cur_sent += c;
                for (String key: map.keySet())
                    if (key.indexOf(cur_sent) == 0) {
                        list.add(new Node(key, map.get(key)));
                    }
                Collections.sort(list, (a, b) -> a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times);
                for (int i = 0; i < Math.min(3, list.size()); i++)
                    res.add(list.get(i).sentence);
            }
            return res;
        }
    }

    /**
     * Your AutocompleteSystem object will be instantiated and called as such:
     * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
     * List<String> param_1 = obj.input(c);
     */
//-------------------------------------------------------------------------
    //Approach #2 Using One level Indexing[Accepted]
    public class AutocompleteSystem2 {
        HashMap < String, Integer > [] arr;
        class Node {
            Node(String st, int t) {
                sentence = st;
                times = t;
            }
            String sentence;
            int times;
        }
        public AutocompleteSystem2(String[] sentences, int[] times) {
            arr = new HashMap[26];
            for (int i = 0; i < 26; i++)
                arr[i] = new HashMap < String, Integer > ();
            for (int i = 0; i < sentences.length; i++)
                arr[sentences[i].charAt(0) - 'a'].put(sentences[i], times[i]);
        }
        String cur_sent = "";
        public List < String > input(char c) {
            List < String > res = new ArrayList < > ();
            if (c == '#') {
                arr[cur_sent.charAt(0) - 'a'].put(cur_sent, arr[cur_sent.charAt(0) - 'a'].getOrDefault(cur_sent, 0) + 1);
                cur_sent = "";
            } else {
                List < Node > list = new ArrayList < > ();
                cur_sent += c;
                for (String key: arr[cur_sent.charAt(0) - 'a'].keySet()) {
                    if (key.indexOf(cur_sent) == 0) {
                        list.add(new Node(key, arr[cur_sent.charAt(0) - 'a'].get(key)));
                    }
                }
                Collections.sort(list, (a, b) -> a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times);
                for (int i = 0; i < Math.min(3, list.size()); i++)
                    res.add(list.get(i).sentence);
            }
            return res;
        }
    }

//-------------------------------------------------------------------------
    //Approach #3 Using Trie[Accepted]

    public class AutocompleteSystem3 {
        class Node {
            Node(String st, int t) {
                sentence = st;
                times = t;
            }
            String sentence;
            int times;
        }
        class Trie {
            int times;
            Trie[] branches = new Trie[27];
        }
        public int int_(char c) {
            return c == ' ' ? 26 : c - 'a';
        }
        public void insert(Trie t, String s, int times) {
            for (int i = 0; i < s.length(); i++) {
                if (t.branches[int_(s.charAt(i))] == null)
                    t.branches[int_(s.charAt(i))] = new Trie();
                t = t.branches[int_(s.charAt(i))];
            }
            t.times += times;
        }
        public List < Node > lookup(Trie t, String s) {
            List < Node > list = new ArrayList < > ();
            for (int i = 0; i < s.length(); i++) {
                if (t.branches[int_(s.charAt(i))] == null)
                    return new ArrayList < Node > ();
                t = t.branches[int_(s.charAt(i))];
            }
            traverse(s, t, list);
            return list;
        }
        public void traverse(String s, Trie t, List < Node > list) {
            if (t.times > 0)
                list.add(new Node(s, t.times));
            for (char i = 'a'; i <= 'z'; i++) {
                if (t.branches[i - 'a'] != null)
                    traverse(s + i, t.branches[i - 'a'], list);
            }
            if (t.branches[26] != null)
                traverse(s + ' ', t.branches[26], list);
        }
        Trie root;
        public AutocompleteSystem3(String[] sentences, int[] times) {
            root = new Trie();
            for (int i = 0; i < sentences.length; i++) {
                insert(root, sentences[i], times[i]);
            }
        }
        String cur_sent = "";
        public List < String > input(char c) {
            List < String > res = new ArrayList < > ();
            if (c == '#') {
                insert(root, cur_sent, 1);
                cur_sent = "";
            } else {
                cur_sent += c;
                List < Node > list = lookup(root, cur_sent);
                Collections.sort(list, (a, b) -> a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times);
                for (int i = 0; i < Math.min(3, list.size()); i++)
                    res.add(list.get(i).sentence);
            }
            return res;
        }
    }


//-------------------------------------------------------------------------
    //Java solution, Trie and PriorityQueue
    //Only thing more than a normal Trie is added a map of sentence to count in each of the Trie node to facilitate process of getting top 3 results.

    public class AutocompleteSystem4 {
        class TrieNode {
            Map<Character, TrieNode> children;
            Map<String, Integer> counts;
            boolean isWord;
            public TrieNode() {
                children = new HashMap<Character, TrieNode>();
                counts = new HashMap<String, Integer>();
                isWord = false;
            }
        }

        class Pair {
            String s;
            int c;
            public Pair(String s, int c) {
                this.s = s; this.c = c;
            }
        }

        TrieNode root;
        String prefix;


        public AutocompleteSystem4(String[] sentences, int[] times) {
            root = new TrieNode();
            prefix = "";

            for (int i = 0; i < sentences.length; i++) {
                add(sentences[i], times[i]);
            }
        }

        private void add(String s, int count) {
            TrieNode curr = root;
            for (char c : s.toCharArray()) {
                TrieNode next = curr.children.get(c);
                if (next == null) {
                    next = new TrieNode();
                    curr.children.put(c, next);
                }
                curr = next;
                curr.counts.put(s, curr.counts.getOrDefault(s, 0) + count);
            }
            curr.isWord = true;
        }

        public List<String> input(char c) {
            if (c == '#') {
                add(prefix, 1);
                prefix = "";
                return new ArrayList<String>();
            }

            prefix = prefix + c;
            TrieNode curr = root;
            for (char cc : prefix.toCharArray()) {
                TrieNode next = curr.children.get(cc);
                if (next == null) {
                    return new ArrayList<String>();
                }
                curr = next;
            }

            PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> (a.c == b.c ? a.s.compareTo(b.s) : b.c - a.c));
            for (String s : curr.counts.keySet()) {
                pq.add(new Pair(s, curr.counts.get(s)));
            }

            List<String> res = new ArrayList<String>();
            for (int i = 0; i < 3 && !pq.isEmpty(); i++) {
                res.add(pq.poll().s);
            }
            return res;
        }
    }
//-------------------------------------------------------------------------


//-------------------------------------------------------------------------

//-------------------------------------------------------------------------
}
/*
Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:

The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
If less than 3 hot sentences exist, then just return as many as you can.
When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
Your job is to implement the following functions:

The constructor function:

AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical data.

Now, the user wants to input a new sentence. The following function will provide the next character the user types:

List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.


Example:
Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
The system have already tracked down the following sentences and their corresponding times:
"i love you" : 5 times
"island" : 3 times
"ironman" : 2 times
"i love leetcode" : 2 times
Now, the user begins another search:

Operation: input('i')
Output: ["i love you", "island","i love leetcode"]
Explanation:
There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.

Operation: input(' ')
Output: ["i love you","i love leetcode"]
Explanation:
There are only two sentences that have prefix "i ".

Operation: input('a')
Output: []
Explanation:
There are no sentences that have prefix "i a".

Operation: input('#')
Output: []
Explanation:
The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.

Note:
The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
The number of complete sentences that to be searched won't exceed 100. The length of each sentence including those in the historical data won't exceed 100.
Please use double-quote instead of single-quote when you write test cases even for a character input.
Please remember to RESET your class variables declared in class AutocompleteSystem, as static/class variables are persisted across multiple test cases. Please see here for more details.
 */