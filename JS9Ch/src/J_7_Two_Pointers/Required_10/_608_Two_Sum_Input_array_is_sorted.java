package J_7_Two_Pointers.Required_10;
import java.util.*;
/** 608 Two Sum - Input array is sorted
 * Medium

 * Created by tianhuizhu on 6/28/17.
 */
public class _608_Two_Sum_Input_array_is_sorted {

    public class Solution {
        /*
         * @param nums an array of Integer
         * @param target = nums[index1] + nums[index2]
         * @return [index1 + 1, index2 + 1] (index1 < index2)
         */
        public int[] twoSum(int[] nums, int target) {
            if (nums == null || nums.length < 2) {
                return null;
            }

            int start = 0, end = nums.length - 1;
            while (start < end) {
                if (nums[start] + nums[end] == target) {
                    int[] pair = new int[2];
                    pair[0] = start + 1;
                    pair[1] = end + 1;
                    return pair;
                }
                if (nums[start] + nums[end] < target) {
                    start++;
                } else {
                    end--;
                }
            }

            return null;
        }
    }
}
