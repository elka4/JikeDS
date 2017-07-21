package chapter4_TwoPointer;

import java.util.HashSet;

public class _08Longest_Substring_Without_Repeating_Characters_1 {
	public int lengthOfLongestSubstring(String s) {
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
