package _String._String;
import java.util.*;
import org.junit.Test;

//
//  383. Ransom Note
//  https://leetcode.com/problems/ransom-note/
//
public class _383_String_Ransom_Note_E {
//------------------------------------------------------------------------------
    //Java O(n) Solution---Easy to understand
    public class Solution1 {
        public boolean canConstruct(String ransomNote, String magazine) {
            int[] arr = new int[26];
            for (int i = 0; i < magazine.length(); i++) {
                arr[magazine.charAt(i) - 'a']++;
            }
            for (int i = 0; i < ransomNote.length(); i++) {
                if(--arr[ransomNote.charAt(i)-'a'] < 0) {
                    return false;
                }
            }
            return true;
        }
    }
//------------------------------------------------------------------------------
    //Java Map solution
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> magM = new HashMap<>();
        for (char c:magazine.toCharArray()){
            int newCount = magM.getOrDefault(c, 0)+1;
            magM.put(c, newCount);
        }
        for (char c:ransomNote.toCharArray()){
            int newCount = magM.getOrDefault(c,0)-1;
            if (newCount<0)
                return false;
            magM.put(c, newCount);
        }
        return true;
    }


//------------------------------------------------------------------------------
//Share My Easy to Understand 5 lines of Java Code, 13ms beats 96%
//    The code is fast mainly because it uses the ASCII value of the char in the string as the index of an array, resulting in direct access and thus significantly increases efficiency of the algorithm.

    public boolean canConstruct3(String ransomNote, String magazine) {
        int[] table = new int[128];
        for (char c : magazine.toCharArray())   table[c]++;
        for (char c : ransomNote.toCharArray())
            if (--table[c] < 0) return false;
        return true;
    }
//    If you think the above code has created some unnecessary spaces, the following code may be what you are looking for

    public boolean canConstruct4(String ransomNote, String magazine) {
        int[] table = new int[26];
        for (char c : magazine.toCharArray())   table[c - 'a']++;
        for (char c : ransomNote.toCharArray())
            if (--table[c - 'a'] < 0) return false;
        return true;
    }
//    Here's another solution using Hash Map. Same idea, just using different data structures. And this solution obviously is less efficient than previous two.

    public boolean canConstruct5(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : magazine.toCharArray()) {
            int count = map.containsKey(c) ? map.get(c) + 1 : 1;
            map.put(c, count);
        }
        for (char c : ransomNote.toCharArray()) {
            int newCount = map.containsKey(c) ? map.get(c) - 1 : -1;
            if (newCount == -1) return false;
            map.put(c, newCount);
        }
        return true;
    }


//------------------------------------------------------------------------------
}
/*
Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true
Seen this question in a real interview before?   Yes  No
Companies
Apple
Related Topics
String
Similar Questions
Stickers to Spell Word
Java
 */