package J_2_Binary_Search.Required_10;
import java.util.*;
/**459. Closest Number in Sorted Array
 * Easy
 * Created by tianhuizhu on 6/27/17.
 */
public class _459_Closest_Number_in_Sorted_Array_Easy {

    // version 1: with jiuzhang template
    public class Solution1 {
        /**
         * @param nums: An integer array sorted in ascending order
         * @param target: An integer
         * @return an integer
         */
        public int lastPosition(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return -1;
            }

            int start = 0, end = nums.length - 1;
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] == target) {
                    start = mid;
                } else if (nums[mid] < target) {
                    start = mid;
                    // or start = mid + 1
                } else {
                    end = mid;
                    // or end = mid - 1
                }
            }

            if (nums[end] == target) {
                return end;
            }
            if (nums[start] == target) {
                return start;
            }
            return -1;
        }
    }

    // version 2: without jiuzhang template
    public class Solution2 {
        /**
         * @param nums: An integer array sorted in ascending order
         * @param target: An integer
         * @return an integer
         */
        public int lastPosition(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return -1;
            }

            int start = 0, end = nums.length - 1;
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] == target) {
                    start = mid + 1;
                } else if (nums[mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

            if (nums[start] == target) {
                return start;
            }
            else if(start != 0 && nums[start - 1] == target){
                return start - 1;
            }
            return -1;
        }
    }
}
