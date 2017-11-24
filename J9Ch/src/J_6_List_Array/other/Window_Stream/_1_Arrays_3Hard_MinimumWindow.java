package J_6_List_Array.other.Window_Stream;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/minimum-window-substring/
//s 里拥有所有t的char的最短string
// two pointer in one array
public class _1_Arrays_3Hard_MinimumWindow {

    public String minWindwo1(String s, String t) {
        //Corner Case Checked
        Map<Character, Integer> frequency = new HashMap<Character, Integer>();
        char[] chS = s.toCharArray();

        //put all characters in t into map, with char frequency
        for (char c : t.toCharArray()){
            Integer freq = frequency.get(c);
            if (freq == null){
                frequency.put(c, 1);
            } else{
                frequency.put(c, freq + 1);
            }
        }

        //初始化数值。目标就是要确定startIndex, minLen。
        //或者说就是要确定距离最短的left 和 right
        int left = 0, right = 0, count = t.length(),
                minLen = Integer.MAX_VALUE, startIndex = -1;

        while(right < chS.length){
            // 1.Move right to find a match
            Integer freq = frequency.get(chS[right]);
            if (freq != null){
                //update freq in frequency hashmap
                frequency.put(chS[right], freq - 1);
                if (freq > 0){// in case of overmatch (overmatch 就是负数了)
                    count--;
                }
            }
            right++;//向右移动一位

            // 2.Move left when a match is found
            //count == 0就是找到里，但是需要右移left来找最短
            while(count == 0){
                //找到更好的结果，update startIndex和minLen
                if(right - left < minLen){
                    minLen = right - left;
                    startIndex = left;
                }
                Integer exist = frequency.get(chS[left]);
                //exist != null就是这个char之前遇到过，当然freq可以是0
                if (exist != null){
                    //此时结果中少了一个目标的char(chS[left])， 所以要updat frequency
                    frequency.put(chS[left], exist + 1);

                    //exist == 0就是说frequency里这个char的数量为0
                    //count++， 是update count。count和frequency必须同步。
                    if(exist == 0){ //in case of overmatch
                        count++;
                    }
                }
                left++;
            }


        }
        return minLen == Integer.MAX_VALUE ? new String() :
                new String(chS, startIndex, minLen);
    }

@Test
public void test01(){
    String s = "abcdefg";
    String t = "bce";
    System.out.println(minWindwo1(s,t));
}

//------------------------------------------------------------------------------/////////////



	public String minWindwo(String s, String t) {
		if(s == null || t == null || s.length() == 0 || t.length() == 0 
				|| t.length() > s.length()) {
			return new String();
		}
		int[] map = new int[128];

		int count = t.length(), start = 0, end = 0, 
				minLen = Integer.MAX_VALUE, startIndex = 0;

        //put all characters in t into map, with char frequency
		for (char c : t.toCharArray()) {
			map[c]++;
		}

		char[] chS = s.toCharArray();

		while(end < chS.length) {
            // 1.Move right to find a match
			if (map[chS[end++]]-- > 0) {
				count--;
			}
			while (count == 0) {
                // 2.Move left when a match is found
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