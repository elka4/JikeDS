package top100.Enhance._1Linear_PreClass;

import java.util.HashMap;
import java.util.Map;

public class _1_Arrays_3Hard_MinimumWindow_inVideo {
	public String minWindow(String s, String t) {
		//corner case checked
		Map<Character, Integer> frequency = new HashMap<Character, Integer>();
		char[] chS = s.toCharArray();
		
		//Put all characters in t into map
		for (char c : t.toCharArray()) {
			Integer freq = frequency.get(c);
			if (freq == null) {
				frequency.put(c, 1);
			} else {
				frequency.put(c, freq + 1);
			}
		}
		int left = 0, right = 0, count = t.length(), 
				minLen = Integer.MAX_VALUE, startIndex = -1;
		
		while(right < chS.length) {
			//1. Move right to find a match
			Integer freq = frequency.get(chS[right]);
			if (freq != null) {
				frequency.put(chS[right], freq - 1);
				if (freq > 0) {// in case of overmatch
					count--;
				}
			}
			right++;
			
			//2.Move left when a match is found
			while(count == 0) {
				if (right - left < minLen) {
					minLen = right - left;
					startIndex = left;
				}
				Integer exist = frequency.get(chS[left]);
				if (exist != null) {
					frequency.put(chS[left], exist + 1);
					if(exist == 0) {//in case of over match
						count++;
					}
				}
				left++;
			}
		}
		return minLen == Integer.MAX_VALUE ?  new String() : new String(chS, startIndex, minLen);
		
	}
}
