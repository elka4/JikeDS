package _TwoPointer.TwoPointer_S;
import java.util.*;

//  3. Longest Substring Without Repeating Characters
//  https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
//  http://www.lintcode.com/zh-cn/problem/longest-substring-without-repeating-characters/
public class _08_Longest_Substring_Without_Repeating {
    //https://leetcode.com/problems/longest-substring-without-repeating-characters/solution/
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

    //Java (Assuming ASCII 128)
/*    Commonly used tables are:

    int[26] for Letters 'a' - 'z' or 'A' - 'Z'
    int[128] for ASCII
    int[256] for Extended ASCII*/
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


//-------------------------------------------------------------------------//////////////

    //jiuzhang
	public int lengthOfLongestSubstring2016(String s) {
		int[] map = new int[256];
		Arrays.fill(map, 0);
		int j = 0;
		int i = 0;
		int ans = 0;
		for (i = 0; i < s.length(); i++) {
			while (j < s.length() && map[s.charAt(j)] == 0) {
				map[s.charAt(j)] = 1;
				ans = Math.max(ans, j - i + 1);
				j++;
			}
			map[s.charAt(i)] = 0;
		}
		return ans;
	}

    /**
     * @param s: a string
     * @return: an integer
     */
    //方法一：
    public int lengthOfLongestSubstring(String s) {
        int[] map = new int[256]; // map from character's ASCII to its last occured index
        Arrays.fill(map, -1);

        int slow = 0;
        int fast = 0;
        int ans = 0;
        for (fast = 0; fast < s.length(); fast++) {
            int ch = s.charAt(fast);
            while (map[ch]!=-1 && slow < fast) {
                int temp = s.charAt(slow);
                map[temp] = -1;
                slow ++;
            }
            map[ch] = 0;
            ans = Math.max(ans, fast-slow + 1);
        }

        return ans;
    }
    // 方法二：
    // public int lengthOfLongestSubstring(String s) {
    //     int[] map = new int[256]; // map from character's ASCII to its last occured index
    //     int ans = 0;
    //     int slow = 0;

    //     Arrays.fill(map, -1);

    //     for (int fast = 0; fast < s.length(); fast++) {
    //         int ch = s.charAt(fast);
    //         if (map[ch] >= slow) {
    //             ans = Math.max(ans, fast - slow);
    //             slow = map[ch] + 1;
    //         }
    //         map[ch] = fast;
    //     }

    //     return Math.max(ans, s.length() - slow);
    // }

    public int lengthOfLongestSubstring3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        HashSet<Character> set = new HashSet<Character>();

        int leftBound = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                while (leftBound < i && s.charAt(leftBound) != s.charAt(i)) {
                    set.remove(s.charAt(leftBound));
                    leftBound ++;
                }
                leftBound ++;
            } else {
                set.add(s.charAt(i));
                max = Math.max(max, i - leftBound + 1);
            }
        }

        return max;
    }
//-------------------------------------------------------------------------//////////////
}
/*

 */

/*
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */

/*
最长无重复字符的子串

 描述
 笔记
 数据
 评测
给定一个字符串，请找出其中无重复字符的最长子字符串。

样例
例如，在"abcabcbb"中，其无重复字符的最长子字符串是"abc"，其长度为 3。

对于，"bbbbb"，其无重复字符的最长子字符串为"b"，长度为1。

挑战
O(n) 时间
 */