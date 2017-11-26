package J_2_Binary_Search.Required_10;

/** 159. Find Minimum in Rotated Sorted Array
 * Medium
 * Created by tianhuizhu on 6/27/17.
 */
public class _159_Find_Minimum_in_Rotated_Sorted_Array {

    public class Solution {
        /**
         * @param nums: a rotated sorted array
         * @return: the minimum number in the array
         */
        public int findMin(int[] nums) {
            if (nums == null || nums.length == 0) {
                return -1;
            }

            int start = 0, end = nums.length - 1;
            int target = nums[nums.length - 1];

            // find the first element <= target
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] <= target) {
                    end = mid;
                } else {
                    start = mid;
                }
            }
            if (nums[start] <= target) {
                return nums[start];
            } else {
                return nums[end];
            }
        }
    }
    //-------------------------------------------------------------------------------
    public int findMin_my(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[end]){
                start = mid;
            } else if (nums[mid] < nums[end]){
                end = mid;
            } else {//nums[mid] == nums[end]
                end = mid; //start = mid also AC
            }
        }

        return nums[start] < nums[end] ? nums[start] : nums[end];
    }
}
