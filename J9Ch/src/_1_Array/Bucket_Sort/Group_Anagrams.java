package _1_Array.Bucket_Sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
LeetCode – Group Anagrams (Java)

Given an array of strings, return all groups of strings that are anagrams.

Analysis

An anagram is a type of word play, the result of rearranging the letters of a word or phrase to produce a new word or phrase, using all the original letters exactly once; for example, Torchwood can be rearranged into Doctor Who.

If two strings are anagram to each other, their sorted sequence is the same.
 */
public class Group_Anagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();

        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for(String str: strs){
            char[] arr = new char[26];
            for(int i=0; i<str.length(); i++){
                arr[str.charAt(i)-'a']++;
            }
            String ns = new String(arr);

            if(map.containsKey(ns)){
                map.get(ns).add(str);
            }else{
                ArrayList<String> al = new ArrayList<String>();
                al.add(str);
                map.put(ns, al);
            }
        }

        result.addAll(map.values());

        return result;
    }
    //If the average length of verbs is m and array length is n, then the time is O(n*m).


}
