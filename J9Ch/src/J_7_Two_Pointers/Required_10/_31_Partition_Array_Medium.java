package J_7_Two_Pointers.Required_10;
import java.util.*;
/** 31 Partition Array
 * Medium

 * Created by tianhuizhu on 6/28/17.
 */
public class _31_Partition_Array_Medium {


    public class Solution {
        /**
         *@param nums: The integer array you should partition
         *@param k: As description
         *return: The index after partition
         */
        public int partitionArray(int[] nums, int k) {
            if(nums == null || nums.length == 0){
                return 0;
            }

            int left = 0, right = nums.length - 1;
            while (left <= right) {

                while (left <= right && nums[left] < k) {
                    left++;
                }

                while (left <= right && nums[right] >= k) {
                    right--;
                }

                if (left <= right) {
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;

                    left++;
                    right--;
                }
            }
            return left;
        }
    }
}
