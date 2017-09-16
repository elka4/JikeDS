package HF.HF2_Algorithms_DS_I._2Hash_String_Char;

import java.util.*;

public class _4LongestConsecutiveSequence {

    public class Solution {
        /**
         * @param nums: A list of integers
         * @return an integer
         */
        public int longestConsecutive(int[] nums) {
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                set.add(nums[i]);
            }

            int longest = 0;
            for (int i = 0; i < nums.length; i++) {
                int down = nums[i] - 1;
                while (set.contains(down)) {
                    set.remove(down);
                    down--;
                }

                int up = nums[i] + 1;
                while (set.contains(up)) {
                    set.remove(up);
                    up++;
                }

                longest = Math.max(longest, up - down - 1);
            }

            return longest;
        }
    }

    // version: 高频题班
    public class Solution2 {
        /**
         * @param num: A list of integers
         * @return an integer
         */
        public int longestConsecutive(int[] num) {
            // write you code here
            Set<Integer> set = new HashSet<>();
            for (int item : num) {
                set.add(item);
            }

            int ans = 0;
            for (int item : num) {
                if (set.contains(item)) {
                    set.remove(item);

                    int pre = item - 1;
                    int next = item + 1;
                    while (set.contains(pre)) {
                        set.remove(pre);
                        pre--;
                    }
                    while (set.contains(next)) {
                        set.remove(next);
                        next++;
                    }
                    ans = Math.max(ans, next - pre - 1);
                }
            }
            return ans;
        }
    }
}
