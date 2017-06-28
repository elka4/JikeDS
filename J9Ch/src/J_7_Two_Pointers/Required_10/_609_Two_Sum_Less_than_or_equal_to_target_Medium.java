package J_7_Two_Pointers.Required_10;
import java.util.*;
/** 609 Two Sum - Less than or equal to target
 * Medium

 * Created by tianhuizhu on 6/28/17.
 */
public class _609_Two_Sum_Less_than_or_equal_to_target_Medium {

    public class Solution {
        /**
         * @param nums an array of integer
         * @param target an integer
         * @return an integer
         */
        public int twoSum5(int[] nums, int target) {
            // Write your code here
            if (nums == null || nums.length < 2)
                return 0;

            Arrays.sort(nums);
            int cnt = 0;
            int left = 0, right = nums.length - 1;
            while (left < right) {
                int v = nums[left] + nums[right];
                if (v > target) {
                    right --;
                } else {
                    cnt += right - left;
                    left ++;
                }
            }
            return cnt;
        }
    }
}
