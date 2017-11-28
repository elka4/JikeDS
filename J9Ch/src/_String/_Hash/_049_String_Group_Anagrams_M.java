package _String._Hash;
import java.util.*;
import org.junit.Test;

//  49. Group Anagrams
//  https://leetcode.com/problems/group-anagrams/description/
//  4:1
//  Hash, String
//  242. Valid Anagram - Hash, Sort
//  249. Group Shifted Strings - Hash, String
//
public class _049_String_Group_Anagrams_M {
//------------------------------------------------------------------------------
//  https://leetcode.com/articles/group-anagrams/
//------------------------------------------------------------------------------
    //1
    //Approach #1: Categorize by Sorted String [Accepted]
    //  Two strings are anagrams if and only if their sorted strings are equal.

    /*
    Maintain a map ans : {String -> List} where each key \text{K}K is a sorted string, and each value is the list of strings from the initial input that when sorted, are equal to \text{K}K.

In Java, we will store the key as a string, eg. code. In Python, we will store the key as a hashable tuple, eg. ('c', 'o', 'd', 'e').
     */
    //就是把每个string都sort一下作为key，然后把没有sort的string作为value存在这个key的位置
    //好简单的想法
    class Solution1 {
        public List<List<String>> groupAnagrams(String[] strs) {
            if (strs.length == 0) return new ArrayList();
            Map<String, List> ans = new HashMap<String, List>();
            for (String s : strs) {
                char[] ca = s.toCharArray();
                Arrays.sort(ca);
                String key = String.valueOf(ca);
                if (!ans.containsKey(key)) ans.put(key, new ArrayList());
                ans.get(key).add(s);
            }
            return new ArrayList(ans.values());
        }
    }
/*
Time Complexity: O(NK \log (K) )O(NKlog(K)), where NN is the length of strs, and KK is the maximum length of a string in strs. The outer loop has complexity O(N)O(N) as we iterate through each string. Then, we sort each string in O(K \log K)O(KlogK) time.

Space Complexity: O(N*K)O(N∗K), the total information content stored in ans.
 */

//------------------------------------------------------------------------------
    //2
    //Approach #2: Categorize by Count [Accepted]
    //Two strings are anagrams if and only if their character counts (respective number of occurrences of each character) are the same.
    /*
    We can transform each string \text{s}s into a character count, \text{count}count, consisting of 26 non-negative integers representing the number of \text{a}a's, \text{b}b's, \text{c}c's, etc. We use these counts as the basis for our hash map.

In Java, the hashable representation of our count will be a string delimited with '#' characters. For example, abbccc will be #1#2#3#0#0#0...#0 where there are 26 entries total. In python, the representation will be a tuple of the counts. For example, abbccc will be (1, 2, 3, 0, 0, ..., 0), where again there are 26 entries total.
     */
    //用#和这个string里26个字母从a到b的个数作为key
    class Solution2{
        public List<List<String>> groupAnagrams(String[] strs) {
            if (strs.length == 0) return new ArrayList();
            Map<String, List> ans = new HashMap<String, List>();
            int[] count = new int[26];
            for (String s : strs) {
                Arrays.fill(count, 0);
                for (char c : s.toCharArray()) count[c - 'a']++;

                StringBuilder sb = new StringBuilder("");
                for (int i = 0; i < 26; i++) {
                    sb.append('#');
                    sb.append(count[i]);
                }
                String key = sb.toString();
                if (!ans.containsKey(key)) ans.put(key, new ArrayList());
                ans.get(key).add(s);
            }
            return new ArrayList(ans.values());
        }
    }
/*
Complexity Analysis

Time Complexity: O(N * K)O(N∗K), where NN is the length of strs, and KK is the maximum length of a string in strs. Counting each string is linear in the size of the string, and we count every string.

Space Complexity: O(N*K)O(N∗K), the total information content stored in ans.
 */
//------------------------------------------------------------------------------
    //3
    //Simple java solution
    class Solution3{
        public List<List<String>> groupAnagrams(String[] strs) {
            List<List<String>> res = new ArrayList<>();
            HashMap<String, List<String>> map = new HashMap<>();

            Arrays.sort(strs);
            for (int i = 0; i < strs.length; i++) {
                String temp = strs[i];
                char[] ch = temp.toCharArray();
                Arrays.sort(ch);
                if (map.containsKey(String.valueOf(ch))) {
                    map.get(String.valueOf(ch)).add(strs[i]);
                } else {
                    List<String> each = new ArrayList<>();
                    each.add(strs[i]);
                    map.put(String.valueOf(ch), each);
                }
            }
            for (List<String> item: map.values()) {
                res.add(item);
            }
            return res;
        }
    }

//------------------------------------------------------------------------------
    //4
    // Clean and understandable java solution
    class Solution4{
        public List<List<String>> groupAnagrams(String[] strs) {
            HashMap<String, List<String>> words = new HashMap<String, List<String>>();
            for(String str : strs){
                String key = getKey(str);
                if(words.containsKey(key)){
                    words.get(key).add(str);
                } else {
                    List<String> l = new ArrayList<String>();
                    l.add(str);
                    words.put(key, l);
                }
            }
            List<List<String>> results = new ArrayList<List<String>>();
            for(String k : words.keySet()){
                List<String> r = words.get(k);
                Collections.sort(r);
                results.add(r);
            }
            return results;
        }

        public String getKey(String str){
            char[] c = str.toCharArray();
            Arrays.sort(c);
            return String.valueOf(c);
        }
    }

//------------------------------------------------------------------------------
}
/*
Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note: All inputs will be in lower-case.
------------------------------------------------------------------------------
Companies
Facebook Amazon Bloomberg Uber Yelp

Related Topics
Hash Table String

Similar Questions
242. Valid Anagram - Hash, Sort
249. Group Shifted Strings - Hash, String
 */


/*

 */