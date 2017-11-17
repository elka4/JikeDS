package _10_DS.Trie;
import java.util.*;

//  677. Map Sum Pairs
//  https://leetcode.com/problems/map-sum-pairs/description/
//  Trie
public class _677_Map_Sum_Pairs_M {
    //https://leetcode.com/problems/map-sum-pairs/solution/
    //Approach #1: Brute Force [Accepted]
    //For each key in the map, if that key starts with the given prefix, then add it to the answer.


    class MapSum1 {
        HashMap<String, Integer> map;
        public MapSum1() {
            map = new HashMap<>();
        }
        public void insert(String key, int val) {
            map.put(key, val);
        }
        public int sum(String prefix) {
            int ans = 0;
            for (String key: map.keySet()) {
                if (key.startsWith(prefix)) {
                    ans += map.get(key);
                }
            }
            return ans;
        }
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Approach #2: Prefix Hashmap [Accepted]
    /*
    We can remember the answer for all possible prefixes in a HashMap score. When we get a new (key, val) pair, we update every prefix of key appropriately: each prefix will be changed by delta = val - map[key], where map is the previous associated value of key (zero if undefined.)
     */
class MapSum2 {
    Map<String, Integer> map;
    Map<String, Integer> score;
    public MapSum2() {
        map = new HashMap();
        score = new HashMap();
    }
    public void insert(String key, int val) {
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, val);
        String prefix = "";
        for (char c: key.toCharArray()) {
            prefix += c;
            score.put(prefix, score.getOrDefault(prefix, 0) + delta);
        }
    }
    public int sum(String prefix) {
        return score.getOrDefault(prefix, 0);
    }
}


//////////////////////////////////////////////////////////////////////////////////////////////////////////
//Approach #3: Trie [Accepted]

/*
Since we are dealing with prefixes, a Trie (prefix tree) is a natural data structure to approach this problem. For every node of the trie corresponding to some prefix, we will remember the desired answer (score) and store it at this node. As in Approach #2, this involves modifying each node by delta = val - map[key].
 */
class MapSum3 {
    HashMap<String, Integer> map;
    TrieNode root;
    public MapSum3() {
        map = new HashMap();
        root = new TrieNode();
    }
    public void insert(String key, int val) {
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, val);
        TrieNode cur = root;
        cur.score += delta;
        for (char c: key.toCharArray()) {
            cur.children.putIfAbsent(c, new TrieNode());
            cur = cur.children.get(c);
            cur.score += delta;
        }
    }
    public int sum(String prefix) {
        TrieNode cur = root;
        for (char c: prefix.toCharArray()) {
            cur = cur.children.get(c);
            if (cur == null) return 0;
        }
        return cur.score;
    }
}
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap();
        int score;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Java solution, Trie
    class MapSum4 {
        class TrieNode {
            Map<Character, TrieNode> children;
            boolean isWord;
            int value;

            public TrieNode() {
                children = new HashMap<Character, TrieNode>();
                isWord = false;
                value = 0;
            }
        }

        TrieNode root;

        /** Initialize your data structure here. */
        public MapSum4() {
            root = new TrieNode();
        }

        public void insert(String key, int val) {
            TrieNode curr = root;
            for (char c : key.toCharArray()) {
                TrieNode next = curr.children.get(c);
                if (next == null) {
                    next = new TrieNode();
                    curr.children.put(c, next);
                }
                curr = next;
            }
            curr.isWord = true;
            curr.value = val;
        }

        public int sum(String prefix) {
            TrieNode curr = root;
            for (char c : prefix.toCharArray()) {
                TrieNode next = curr.children.get(c);
                if (next == null) {
                    return 0;
                }
                curr = next;
            }

            return dfs(curr);
        }

        private int dfs(TrieNode root) {
            int sum = 0;
            for (char c : root.children.keySet()) {
                sum += dfs(root.children.get(c));
            }
            return sum + root.value;
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////

    class MapSum5 {
        Map<String,Integer> map = new HashMap<>();
        /** Initialize your data structure here. */
        public MapSum5() {

        }

        public void insert(String key, int val) {
            map.put(key,val);
        }

        public int sum(String prefix) {
            int sum = 0;
            for(String str:map.keySet()){
                if(str.startsWith(prefix)){
                    sum+=map.get(str);
                }
            }

            return sum;
        }
    }

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */

//////////////////////////////////////////////////////////////////////////////////////////////////////////



}
/*
Implement a MapSum class with insert, and sum methods.

For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer represents the value. If the key already existed, then the original key-value pair will be overridden to the new one.

For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value whose key starts with the prefix.

Example 1:
Input: insert("apple", 3), Output: Null
Input: sum("ap"), Output: 3
Input: insert("app", 2), Output: Null
Input: sum("ap"), Output: 5
 */