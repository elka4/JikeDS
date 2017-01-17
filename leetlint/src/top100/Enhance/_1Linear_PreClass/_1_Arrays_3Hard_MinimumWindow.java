package top100.Enhance._1Linear_PreClass;

//https://leetcode.com/problems/minimum-window-substring/

public class _1_Arrays_3Hard_MinimumWindow {
	public String minWindwo(String s, String t) {
		if(s == null || t == null || s.length() == 0 || t.length() == 0 
				|| t.length() > s.length()) {
			return new String();
		}
		int[] map = new int[128];
		int count = t.length(), start = 0, end = 0, 
				minLen = Integer.MAX_VALUE, startIndex = 0;
		for (char c : t.toCharArray()) {
			map[c]++;
		}
		char[] chS = s.toCharArray();
		while(end < chS.length) {
			if (map[chS[end++]]-- > 0) {
				count--;
			}
			while (count == 0) {
				if (end - start < minLen) {
					startIndex = start;
					minLen = end - start;
				}
				if (map[chS[start++]]++ == 0) {
					count++;
				}
			}
		}
		return minLen == Integer.MAX_VALUE ? 
				new String() : new String(chS, startIndex, minLen);
	}
}
//follow up: longest Substring without repeating character
//https://leetcode.com/problems/longest-substring-without-repeating-characters/