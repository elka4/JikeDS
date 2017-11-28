package _String._Hash;
import java.util.*;
import org.junit.Test;

//  340. Longest Substring with At Most K Distinct Characters
//  https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
//  http://www.lintcode.com/problem/longest-substring-with-at-most-k-distinct-characters/
//  Hash Table, String
//  159. Longest Substring with At Most Two Distinct Characters - Hash Table Two Pointers String
//  424. Longest Repeating Character Replacement
//  5:
//
public class _340_String_Longest_Substring_with_At_Most_K_Distinct_Characters_H {
//------------------------------------------------------------------------------
    //1
/*15 lines java solution using slide window
    feel it is not a new question, just use num to track the number of distinct characters within the slide window*/

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
//------------------------------------------------------------------------------
    //2
/*
    Java O(nlogk) using TreeMap to keep last occurrence Interview "follow-up" question!
    Solving the problem with O(n) time is not enough, some interviewer may require this solution as a followup. Instead of recording each char's count, we keep track of char's last occurrence. If you consider k as constant, it is also a O(n) algorithm.

    inWindow keeps track of each char in window and its last occurrence position

    lastOccurrence is used to find the char in window with left most last occurrence. A better idea is to use a PriorityQueue, as it takes O(1) to getMin, However Java's PQ does not support O(logn) update a internal node, it takes O(n). TreeMap takes O(logn) to do both getMin and update.
    Every time when the window is full of k distinct chars, we lookup TreeMap to find the one with leftmost last occurrence and set left bound j to be 1 + first to exclude the char to allow new char coming into window.
*/

    public class Solution2 {
        public int lengthOfLongestSubstringKDistinct(String str, int k) {
            if (str == null || str.isEmpty() || k == 0) {
                return 0;
            }
            TreeMap<Integer, Character> lastOccurrence = new TreeMap<>();
            Map<Character, Integer> inWindow = new HashMap<>();
            int j = 0;
            int max = 1;
            for (int i = 0; i < str.length(); i++) {
                char in = str.charAt(i);
                while (inWindow.size() == k && !inWindow.containsKey(in)) {
                    int first = lastOccurrence.firstKey();
                    char out = lastOccurrence.get(first);
                    inWindow.remove(out);
                    lastOccurrence.remove(first);
                    j = first + 1;
                }
                //update or add in's position in both maps
                if (inWindow.containsKey(in)) {
                    lastOccurrence.remove(inWindow.get(in));
                }
                inWindow.put(in, i);
                lastOccurrence.put(i, in);
                max = Math.max(max, i - j + 1);
            }
            return max;
        }
    }

//------------------------------------------------------------------------------
    //3
/*
Generic solution in Java that can be used for Unicode
    This problem can be solved using two pointers. The important part is while (map.size() > k), we move left pointer to make sure the map size is less or equal to k. This can be easily extended to any number of unique characters.
*/
    class Solution3{
        public int lengthOfLongestSubstringKDistinct(String s, int k) {
            Map<Character, Integer> map = new HashMap<>();
            int left = 0;
            int best = 0;
            for(int i = 0; i < s.length(); i++) {
                // character at the right pointer
                char c = s.charAt(i);
                map.put(c, map.getOrDefault(c, 0) + 1);
                // make sure map size is valid, no need to check left pointer less than s.length()
                while (map.size() > k) {
                    char leftChar = s.charAt(left);
                    map.put(leftChar, map.get(leftChar) - 1);
                    if (map.get(leftChar) == 0) {
                        map.remove(leftChar);
                    }
                    left++;
                }
                best = Math.max(best, i - left + 1);
            }
            return best;
        }
    }



//------------------------------------------------------------------------------
    //4
    //Easy to understand java solution
    class Solution4{
        public int lengthOfLongestSubstringKDistinct(String s, int k) {
            if (   s          == null
                    || s.length() == 0)
            {
                return 0;
            }

            int                     ret  = 0;
            Map<Character, Integer> map  = new HashMap<>();
            // the left boundary or start index of each possible substring
            // containing at most k distinct characters
            int                     left = 0;

            for (int i = 0; i < s.length(); i++)
            {
                char visiting = s.charAt(i);
                if (!map.containsKey(visiting))
                {
                    map.put(visiting, 0);
                }
                map.put(visiting, map.get(visiting) + 1);

                while (map.size() > k)
                {
                    char c = s.charAt(left++);
                    map.put(c, map.get(c) - 1);
                    if (map.get(c) == 0)
                    {
                        map.remove(c);
                    }
                }

                ret = Math.max(ret, i - left + 1);
            }

            return ret;
        }
    }



//------------------------------------------------------------------------------
    //5
    //9Ch
    public class Jiuzhang {
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
    }


//------------------------------------------------------------------------------
}
/*
Given a string, find the length of the longest substring T that contains at most k distinct characters.

For example, Given s = “eceba” and k = 2,

T is "ece" which its length is 3.

------------------------------------------------------------------------------
Companies
Google AppDynamics Coupang

Related Topics
Hash Table String

Similar Questions
159. Longest Substring with At Most Two Distinct Characters - Hash Table Two Pointers String
424. Longest Repeating Character Replacement
 */