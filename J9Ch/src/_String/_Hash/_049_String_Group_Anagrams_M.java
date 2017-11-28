package _String._Hash;
import java.util.*;
import org.junit.Test;

//  49. Group Anagrams
//  https://leetcode.com/problems/group-anagrams/description/
//
public class _049_String_Group_Anagrams_M {
//------------------------------------------------------------------------------
//https://leetcode.com/articles/group-anagrams/
//------------------------------------------------------------------------------
//Approach #1: Categorize by Sorted String [Accepted]

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


//------------------------------------------------------------------------------
    //Approach #2: Categorize by Count [Accepted]
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

//------------------------------------------------------------------------------


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

Seen this question in a real interview before?   Yes  No
Companies
Facebook Amazon Bloomberg Uber Yelp
Related Topics
Hash Table String
Similar Questions
Valid Anagram Group Shifted Strings
 */


/*

 */