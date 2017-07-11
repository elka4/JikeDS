package J_7_Two_Pointers.all;

import java.util.Arrays;

/** 443 Two Sum - Greater than target
 * Created by tianhuizhu on 6/28/17.
 */
public class _443_Two_Sum_Greater_than_target {

    public class Solution {
        /**
         * @param nums: an array of integer
         * @param target: an integer
         * @return: an integer
         */
        public int twoSum2(int[] nums, int target) {
            if (nums == null || nums.length < 2) {
                return 0;
            }

            Arrays.sort(nums);

            int left = 0, right = nums.length - 1;
            int count = 0;
            while (left < right) {
                if (nums[left] + nums[right] <= target) {
                    left++;
                } else {
                    count += right - left;
                    right--;
                }
            }

            return count;
        }
    }
}
