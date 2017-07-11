package J_6_Linked_List_Array.all;

import java.util.ArrayList;

/** 42 Maximum Subarray II

 * Created by tianhuizhu on 6/28/17.
 */
public class _42_Maximum_Subarray_II {

    public class Solution {
        /**
         * @param nums: A list of integers
         * @return: An integer denotes the sum of max two non-overlapping subarrays
         */
        public int maxTwoSubArrays(ArrayList<Integer> nums) {
            // write your code
            int size = nums.size();
            int[] left = new int[size];
            int[] right = new int[size];
            int sum = 0;
            int minSum = 0;
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < size; i++){
                sum += nums.get(i);
                max = Math.max(max, sum - minSum);
                minSum = Math.min(sum, minSum);
                left[i] = max;
            }
            sum = 0;
            minSum = 0;
            max = Integer.MIN_VALUE;
            for(int i = size - 1; i >= 0; i--){
                sum += nums.get(i);
                max = Math.max(max, sum - minSum);
                minSum = Math.min(sum, minSum);
                right[i] = max;
            }
            max = Integer.MIN_VALUE;
            for(int i = 0; i < size - 1; i++){
                max = Math.max(max, left[i] + right[i + 1]);
            }
            return max;
        }
    }
}
