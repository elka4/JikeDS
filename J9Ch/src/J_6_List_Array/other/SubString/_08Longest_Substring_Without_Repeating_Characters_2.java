package J_6_List_Array.other.SubString;

import java.util.Arrays;

public class _08Longest_Substring_Without_Repeating_Characters_2 {
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
}
