package J_7_2Pointers.all;

import java.util.Arrays;

/** 533 Two Sum - Closest to target
 * Medium

 * Created by tianhuizhu on 6/28/17.
 */
public class _533_Two_Sum_Closest_to_target {
    /**
     * @param nums an integer array
     * @param target an integer
     * @return the difference between the sum and the target
     */
    public int twoSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return -1;
        }

        Arrays.sort(nums);

        int left = 0, right = nums.length - 1;
        int diff = Integer.MAX_VALUE;

        while (left < right) {
            if (nums[left] + nums[right] < target) {
                diff = Math.min(diff, target - nums[left] - nums[right]);
                left++;
            } else {
                diff = Math.min(diff, nums[left] + nums[right] - target);
                right--;
            }
        }

        return diff;
    }

//------------------------------------------------------------------------------

}
