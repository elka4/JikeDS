package J_8_Data_Structure.all;

import java.util.HashSet;

/** 124 Longest Consecutive Sequence

 * Created by tianhuizhu on 6/28/17.
 */
public class _124_Longest_Consecutive_Sequence {

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
}
