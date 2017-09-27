package j_8_DataStructure;

import java.util.ArrayList;
import java.util.HashMap;

public class _11Anagrams {
	private int getHash(int[] count) {
        int hash = 0;
        int a = 378551;
        int b = 63689;
        for (int num : count) {
            hash = hash * a + num;
            a = a * b;
        }
        return hash;
    }
    //也就是两个或多个str必须包含相同char，才能有相同的hash
    public ArrayList<String> anagrams(String[] strs) {
        ArrayList<String> result = new ArrayList<String>();
        HashMap<Integer, ArrayList<String>> map = new HashMap<>();

        for (String str : strs) {
            int[] count = new int[26];
            for (int i = 0; i < str.length(); i++) {
                count[str.charAt(i) - 'a']++;
            }

            int hash = getHash(count);
            if (!map.containsKey(hash)) {
                map.put(hash, new ArrayList<String>());
            }

            map.get(hash).add(str);
        }

        for (ArrayList<String> tmp : map.values()) {
            if (tmp.size() > 1) {
                result.addAll(tmp);
            }
        }

        return result;
    }
}

/*Given an array of strings, return all groups of strings that are anagrams.

 Notice

All inputs will be in lower-case

Have you met this question in a real interview? Yes
Example
Given ["lint", "intl", "inlt", "code"], return ["lint", "inlt", "intl"].

Given ["ab", "ba", "cd", "dc", "e"], return ["ab", "ba", "cd", "dc"].

Challenge 
What is Anagram?
- Two strings are anagram if they can be the same after change the order of characters.

Tags 
String Hash Table Facebook Uber
Related Problems 
Easy Two Strings Are Anagrams 30 %*/