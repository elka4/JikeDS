package J_2_Binary_Search.Required_10;
import java.util.*;
/**585. Maximum Number in Mountain Sequence
 * Medium
 * Created by tianhuizhu on 6/27/17.
 */
public class _585_Maximum_Number_in_Mountain_Sequence_Medium {

    // version 1：二分法
    public class Solution1 {
        /**
         * @param nums a mountain sequence which increase firstly and then decrease
         * @return then mountain top
         */
        public int mountainSequence(int[] nums) {
            // Write your code here
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int start = 0;
            int end = nums.length - 1;
            while (start + 1 < end) {
                int mid = (start + end) / 2;
                if (nums[mid] > nums[mid + 1]) {
                    end = mid;
                } else {
                    start = mid;
                }
            }
            return Math.max(nums[start], nums[end]);
        }
    }

    // version 2: 一个比较啰嗦的版本，本质也是2分法，每次取两个点
    public class Solution2 {
        /**
         * @param nums a mountain sequence which increase firstly and then decrease
         * @return then mountain top
         */
        public int mountainSequence(int[] nums) {
            // Write your code here
            int left = 0, right = nums.length - 1;
            while (left + 1 < right) {
                int m1 = left + (right - left) / 2;
                int m2 = right - (right - m1) / 2;
                if (nums[m1] < nums[m2]) {
                    left = m1 + 1;
                } else if (nums[m1] > nums[m2]) {
                    right = m2 - 1;
                } else {
                    left = m1;
                    right = m2;
                }
            }
            return nums[left] > nums[right] ? nums[left] : nums[right];
        }
    }
}
