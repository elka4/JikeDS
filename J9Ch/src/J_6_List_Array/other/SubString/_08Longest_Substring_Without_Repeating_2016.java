package J_6_List_Array.other.SubString;

import java.util.Arrays;

public class _08Longest_Substring_Without_Repeating_2016 {
	public int lengthOfLongestSubstring(String s) {
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
}
