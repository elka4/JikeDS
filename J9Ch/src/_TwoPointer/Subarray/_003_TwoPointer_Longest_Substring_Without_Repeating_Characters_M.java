package _TwoPointer.Subarray;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//  leetcode    3. Longest Substring Without Repeating Characters
//  https://leetcode.com/problems/longest-substring-without-repeating-characters/solution/
//
public class _003_TwoPointer_Longest_Substring_Without_Repeating_Characters_M {
//-------------------------------------------------------------------------
    //https://leetcode.com/problems/longest-substring-without-repeating-characters/solution/
//-------------------------------------------------------------------------
    //1
    //Approach #1 Brute Force [Time Limit Exceeded]
    public class Solution1 {
        public int lengthOfLongestSubstring(String s) {
            int n = s.length();
            int ans = 0;
            for (int i = 0; i < n; i++)
                for (int j = i + 1; j <= n; j++)
                    if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
            return ans;
        }

        public boolean allUnique(String s, int start, int end) {
            Set<Character> set = new HashSet<>();
            for (int i = start; i < end; i++) {
                Character ch = s.charAt(i);
                if (set.contains(ch)) return false;
                set.add(ch);
            }
            return true;
        }
    }
//-------------------------------------------------------------------------
    //2
    //Approach #2 Sliding Window [Accepted]
    public class Solution2 {
        public int lengthOfLongestSubstring(String s) {
            int n = s.length();
            Set<Character> set = new HashSet<>();
            int ans = 0, i = 0, j = 0;
            while (i < n && j < n) {
                // try to extend the range [i, j]
                if (!set.contains(s.charAt(j))){
                    set.add(s.charAt(j++));
                    ans = Math.max(ans, j - i);
                }
                else {
                    set.remove(s.charAt(i++));
                }
            }
            return ans;
        }
    }
//-------------------------------------------------------------------------
    //3
    //Approach #3 Sliding Window Optimized [Accepted]
    public class Solution3 {
        public int lengthOfLongestSubstring(String s) {
            int n = s.length(), ans = 0;
            Map<Character, Integer> map = new HashMap<>(); // current index of character
            // try to extend the range [i, j]
            for (int j = 0, i = 0; j < n; j++) {
                if (map.containsKey(s.charAt(j))) {
                    i = Math.max(map.get(s.charAt(j)), i);
                }
                ans = Math.max(ans, j - i + 1);
                map.put(s.charAt(j), j + 1);
            }
            return ans;
        }
    }
//-------------------------------------------------------------------------
    //4
    /*
    Java (Assuming ASCII 128)

The previous implements all have no assumption on the charset of the string s.

If we know that the charset is rather small, we can replace the Map with an integer array as direct access table.

Commonly used tables are:

int[26] for Letters 'a' - 'z' or 'A' - 'Z'
int[128] for ASCII
int[256] for Extended ASCII
     */
    public class Solution4 {
        public int lengthOfLongestSubstring(String s) {
            int n = s.length(), ans = 0;
            int[] index = new int[128]; // current index of character
            // try to extend the range [i, j]
            for (int j = 0, i = 0; j < n; j++) {
                i = Math.max(index[s.charAt(j)], i);
                ans = Math.max(ans, j - i + 1);
                index[s.charAt(j)] = j + 1;
            }
            return ans;
        }
    }
//-------------------------------------------------------------------------
    //5
    //Java Solution 1

    //The first solution is like the problem of "determine if a string has all unique characters" in CC 150. We can use a flag array to track the existing characters for the longest substring without repeating characters.

    public int lengthOfLongestSubstring(String s) {
        if(s==null)
            return 0;
        boolean[] flag = new boolean[256];

        int result = 0;
        int start = 0;
        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            char current = arr[i];
            if (flag[current]) {
                result = Math.max(result, i - start);
                // the loop update the new start point
                // and reset flag array
                // for example, abccab, when it comes to 2nd c,
                // it update start from 0 to 3, reset flag for a,b
                for (int k = start; k < i; k++) {
                    if (arr[k] == current) {
                        start = k + 1;
                        break;
                    }
                    flag[arr[k]] = false;
                }
            } else {
                flag[current] = true;
            }
        }

        result = Math.max(arr.length - start, result);

        return result;
    }
//-------------------------------------------------------------------------
    //6
    //Java Solution 2 - HashSet
    public int lengthOfLongestSubstring2(String s) {
        if(s==null || s.length()==0)
            return 0;

        HashSet<Character> set = new HashSet<Character>();

        int max=0;

        int i=0;
        int start=0;
        while(i<s.length()){
            char c = s.charAt(i);
            if(!set.contains(c)){
                set.add(c);
            }else{
                max = Math.max(max, set.size());

                while(start<i&&s.charAt(start)!=c){
                    set.remove(s.charAt(start));
                    start++;
                }
                start++;
            }

            i++;
        }

        max = Math.max(max, set.size());

        return max;
    }
//-------------------------------------------------------------------------
    //7
    public int lengthOfLongestSubstring3(String s) {
        if(s==null || s.length()==0){
            return 0;
        }

        int start=0;
        int max = 0;

        HashSet<Character> set = new HashSet<Character>();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);

            if(!set.contains(c)){
                set.add(c);

                max = Math.max(max, i-start+1);
            }else{
                for(int j=start; j<i; j++){
                    set.remove(s.charAt(j));

                    if(s.charAt(j)==c){
                        start=j+1;
                        break;
                    }
                }

                set.add(c);
            }
        }

        return max;
    }


//-------------------------------------------------------------------------
    //8
    // 9Ch
    public class Jiuzhang {
        /**
         * @param s: a string
         * @return: an integer
         */
        //方法一：
        public int lengthOfLongestSubstring(String s) {
            int[] map = new int[256]; // map from character's ASCII to its last occured index

            int j = 0;
            int i = 0;
            int ans = 0;
            for (i = 0; i < s.length(); i++) {
                while (j < s.length() && map[s.charAt(j)]==0) {
                    map[s.charAt(j)] = 1;
                    ans = Math.max(ans, j-i + 1);
                    j ++;
                }
                map[s.charAt(i)] = 0;
            }

            return ans;
        }
    }
//-------------------------------------------------------------------------
}
//给定一个字符串，请找出其中无重复字符的最长子字符串。

/*
LeetCode – Longest Substring Without Repeating Characters (Java)

Given a string, find the length of the longest substring without repeating characters. For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
 */