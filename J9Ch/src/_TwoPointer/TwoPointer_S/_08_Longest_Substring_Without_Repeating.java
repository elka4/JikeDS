package _TwoPointer.TwoPointer_S;

import java.util.Arrays;
import java.util.HashSet;

public class _08_Longest_Substring_Without_Repeating {
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
}
