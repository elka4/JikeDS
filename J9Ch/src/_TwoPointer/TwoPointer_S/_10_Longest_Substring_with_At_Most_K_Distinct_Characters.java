package _TwoPointer.TwoPointer_S;
import java.util.*;

//  340. Longest Substring with At Most K Distinct Characters
//  https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/
//
public class _10_Longest_Substring_with_At_Most_K_Distinct_Characters {
//15 lines java solution using slide window
//    feel it is not a new question, just use num to track the number of distinct characters within the slide window

    public class Solution1 {
        public int lengthOfLongestSubstringKDistinct(String s, int k) {
            int[] count = new int[256];
            int num = 0, i = 0, res = 0;
            for (int j = 0; j < s.length(); j++) {
                if (count[s.charAt(j)]++ == 0) num++;
                if (num > k) {
                    while (--count[s.charAt(i++)] > 0);
                    num--;
                }
                res = Math.max(res, j - i + 1);
            }
            return res;
        }
    }

//    A more generic solution as follows, can be solution for Unicode string:
    public int lengthOfLongestSubstringKDistinct2(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int best = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            while (map.size() > k) {
                char leftChar = s.charAt(left);
                if (map.containsKey(leftChar)) {
                    map.put(leftChar, map.get(leftChar) - 1);
                    if (map.get(leftChar) == 0) {
                        map.remove(leftChar);
                    }
                }
                left++;
            }
            best = Math.max(best, i - left + 1);
        }
        return best;
    }

//    Simplified the code a bit. Made the sliding window a bit more readable :)
    public int lengthOfLongestSubstringKDistinct3(String s, int k) {
        int[] count = new int[256];     // there are 256 ASCII characters in the world

        int i = 0;  // i will be behind j
        int num = 0;
        int res = 0;

        for (int j = 0; j < s.length(); j++) {
            if (count[s.charAt(j)]++ == 0) {
                num++;
            }
            while (num > k && i < s.length()) {     // sliding window
                count[s.charAt(i)]--;
                if (count[s.charAt(i)] == 0){
                    num--;
                }
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }

///////////////////////////////////////////////////////////////////////////////////
	/**
     * @param s : A string
     * @return : The length of the longest substring 
     *           that contains at most k distinct characters.
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
          // write your code here
        int maxLen = 0;

        // Key: letter; value: the number of occurrences.
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int i, j = 0;
        char c;
        for (i = 0; i < s.length(); i++) {
            while (j < s.length()) {
                c = s.charAt(j);
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                } else {
                    if(map.size() ==k)
                        break;
                    map.put(c, 1);
                }
                j++;
            }

            maxLen = Math.max(maxLen, j - i);
            c = s.charAt(i);
            if(map.containsKey(c)){
                int count = map.get(c);
                if (count > 1) {
                    map.put(c, count - 1);
                } else {
                    map.remove(c);
                }
            }
        }
        return maxLen;
    }
///////////////////////////////////////////////////////////////////////////////////
}
/*
给定一个字符串，找到最多有k个不同字符的最长子字符串。


 */

/*
Given a string, find the length of the longest substring T that contains at most k distinct characters.

For example, Given s = “eceba” and k = 2,

T is "ece" which its length is 3.
 */