package S_1_Follow_up_in_CodeInterview.all;

/** 384 Longest Substring Without Repeating Characters


 * Created by tianhuizhu on 6/28/17.
 */
public class _384_Longest_Substring_Without_Repeating_Characters {

    public class Solution {
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
}
