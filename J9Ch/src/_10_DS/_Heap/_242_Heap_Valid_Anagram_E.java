package _10_DS._Heap;
import java.util.*;
import org.junit.Test;
public class _242_Heap_Valid_Anagram_E {
//Accepted Java O(n) solution in 5 lines

/*
The idea is simple. It creates a size 26 int arrays as buckets for each letter in alphabet. It increments the bucket value with String s and decrement with string t. So if they are anagrams, all buckets should remain with initial value which is zero. So just checking that and return
 */
public class Solution {
    public boolean isAnagram(String s, String t) {
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) alphabet[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++) alphabet[t.charAt(i) - 'a']--;
        for (int i : alphabet) if (i != 0) return false;
        return true;
    }
}

    //Share my java solution

    public class Solution2 {
        public boolean isAnagram(String s, String t) {
            if(s.length()!=t.length()){
                return false;
            }
            int[] count = new int[26];
            for(int i=0;i<s.length();i++){
                count[s.charAt(i)-'a']++;
                count[t.charAt(i)-'a']--;
            }
            for(int i:count){
                if(i!=0){
                    return false;
                }
            }
            return true;
        }
    }

    //Jave simple and efficient solution

    public boolean isAnagram(String s, String t) {
        if(s == null || t == null || s.length() != t.length()) return false;
        int[] count = new int[26];
        int len = t.length();
        for(int i = 0; i < len; i++) {
            count[t.charAt(i) - 'a']++;
        }
        for(int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if(count[c - 'a'] > 0) {
                count[c - 'a']--;
            } else {
                return false;
            }
        }
        return true;
    }

}
/*
Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?


 */