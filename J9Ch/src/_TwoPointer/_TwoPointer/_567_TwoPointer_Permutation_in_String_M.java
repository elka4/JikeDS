package _TwoPointer._TwoPointer;
import java.util.*;
import org.junit.Test;

//  567. Permutation in String
//  https://leetcode.com/problems/permutation-in-string/description/
public class _567_TwoPointer_Permutation_in_String_M {
//https://leetcode.com/problems/permutation-in-string/solution/
    //Approach #1 Brute Force [Time Limit Exceeded]
    public class Solution1 {
        boolean flag = false;

        public boolean checkInclusion(String s1, String s2) {
            permute(s1, s2, 0);
            return flag;
        }

        public String swap(String s, int i0, int il) {
            if (i0 == il) return s;
            String sl = s.substring(0, i0);
            String s2 = s.substring(i0 + 1, il);
            String s3 = s.substring(il + 1);
            return sl + s.charAt(il) + s2 + s.charAt(i0) + s3;
        }

        void permute(String s1, String s2, int l) {
            if (1 == s1.length()) {
                if (s2.indexOf(s1) >= 0)
                    flag = true;
            } else {
                for (int i = 1; i < s1.length(); i++) {
                    s1 = swap(s1, 1, i);
                    permute(s1, s2, 1 + 1);
                    s1 = swap(s1, 1, i);
                }
            }
        }
    }

    //Approach #2 Using sorting [Time Limit Exceeded]:
    public class Solution2 {
        public boolean checkInclusion(String sl, String s2) {
            sl = sort(sl);
            for (int i = 0; i <= s2.length() - sl.length(); i++) {
                if (sl.equals(sort(s2.substring(i, i + sl.length())))) return true;
            }
            return false;
        }

        public String sort(String s) {
            char[] t = s.toCharArray();
            Arrays.sort(t);
            return new String(t);
        }
    }

    //Approach #3 Using Hashmap [Time Limit Exceeded]
    public class Solution3 {
        public boolean checkInclusion(String sl, String s2) {
            if (sl.length() > s2.length()) return false;
            HashMap<Character, Integer> slmap = new HashMap<>();
            for (int i = 0; i < sl.length(); i++)
                slmap.put(sl.charAt(i), slmap.getOrDefault(sl.charAt(i), 0) + 1);
            for (int i = 0; i <= s2.length() - sl.length();
                 i++) {
                HashMap<Character, Integer> s2map = new HashMap<>();
                for (int j = 0; j < sl.length(); j++) {
                    s2map.put(s2.charAt(i + j), s2map.getOrDefault(s2.charAt(i + j), 0) + 1);
                }
                if (matches(slmap, s2map)) return true;
            }
            return false;
        }

        public boolean matches(HashMap<Character, Integer> slmap, HashMap<Character, Integer> s2map) {
            for (char key : slmap.keySet()) {
                if (slmap.get(key) - s2map.getOrDefault(key, -1) != 0) return false;
            }
            return true;
        }
    }

    //Approach #4 Using Array [Accepted]
    public class Solution4 {
        public boolean checkInclusion(String sl, String s2) {
            if (sl.length() > s2.length()) return false;
            int[] s1map = new int[26];
            for (int i = 0; i < sl.length(); i++)
                s1map[sl.charAt(i) - 'a']++;

            for (int i = 0; i <= s2.length() - sl.length(); i++) {
                int[] s2map = new int[26];
                for (int j = 0; j < sl.length(); j++) {
                    s2map[s2.charAt(i + j) - 'a']++;
                }
                if (matches(s1map, s2map)) return true;
            }
            return false;
        }


        public boolean matches(int[] slmap, int[] s2map) {
            for (int i = 0; i < 26; i++) {
                if (slmap[i] != s2map[i]) return false;
            }
            return true;
        }
    }

    //Approach #5 Sliding Window [Accepted]:
    public class Solution5 {
        public boolean checkInclusion(String sl, String s2) {
            if (sl.length() > s2.length()) return false;
            int[] slmap = new int[26];
            int[] s2map = new int[26];
            for (int i = 0; i < sl.length(); i++) {
                slmap[sl.charAt(i) - 'a']++;
                s2map[s2.charAt(i) - 'a']++;
            }
            for (int i = 0; i < s2.length() - sl.length(); i++) {
                if (matches(slmap, s2map)) return true;
                s2map[s2.charAt(i + sl.length()) - 'a']++;
                s2map[s2.charAt(i) - 'a']--;
            }
            return matches(slmap, s2map);
        }

        public boolean matches(int[] slmap, int[] s2map) {
            for (int i = 0; i < 26; i++) {
                if (slmap[i] != s2map[i]) return false;
            }
            return true;
        }
    }

    //Approach #6 Optimized Sliding Window [Accepted]:
    public class Solution6 {
        public boolean checkInclusion(String sl, String s2) {
            if (sl.length() > s2.length()) return false;
            int[] slmap = new int[26];
            int[] s2map = new int[26];
            for (int i = 0; i < sl.length(); i++) {
                slmap[sl.charAt(i) - 'a']++;
                s2map[s2.charAt(i) - 'a']++;
            }
            int count = 0;
            for (int i = 0; i < 26; i++)
                if (slmap[i] == s2map[i]) count++;

            for (int i = 0; i < s2.length() - sl.length(); i++) {
                int r = s2.charAt(i + sl.length()) - 'a', l = s2.charAt(i) - 'a';
                if (count == 26) return true;
                s2map[r]++;
                if (s2map[r] == slmap[r])
                    count++;
                else if (s2map[r] == slmap[r] + 1)
                    count--;
                s2map[l]--;
                if (s2map[l] == slmap[l])
                    count++;
                else if (s2map[l] == slmap[l] - 1)
                    count--;
            }
            return count == 26;
        }
    }
}
/*
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.

Example 1:
Input:s1 = "ab" s2 = "eidbaooo"
Output:True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:
Input:s1= "ab" s2 = "eidboaoo"
Output: False
Note:
The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].
 */