package J_6_Linked_List_Array.Array;

/** 45 Maximum Subarray Difference

 * Created by tianhuizhu on 6/28/17.
 */
public class _45_Maximum_Subarray_Difference {
    /**
     * @param nums: A list of integers
     * @return: An integer indicate the value of maximum difference between two
     *          Subarrays
     */
    public int maxDiffSubArrays(int[] nums) {
        // write your code here
        int size = nums.length;
        int[] left_max = new int[size];
        int[] left_min = new int[size];
        int[] right_max = new int[size];
        int[] right_min = new int[size];
        int[] copy = new int[size];
        /*Get negative copy*/
        for(int i = 0; i < size; i++){
            copy[i] = -1 * nums[i];
        }
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int minSum = 0;
        /*Forward: get max subarray*/
        for(int i = 0; i < size; i++){
            sum += nums[i];
            max = Math.max(max, sum - minSum);
            minSum = Math.min(sum, minSum);
            left_max[i] = max;
        }
        /*Backward: get max subarray*/
        max = Integer.MIN_VALUE;
        sum = 0;
        minSum = 0;
        for(int i = size - 1; i >= 0; i--){
            sum += nums[i];
            max = Math.max(max, sum - minSum);
            minSum = Math.min(sum, minSum);
            right_max[i] = max;
        }
        /*Forward: get min subarray*/
        max = Integer.MIN_VALUE;
        sum = 0;
        minSum = 0;
        for(int i = 0; i < size; i++){
            sum += copy[i];
            max = Math.max(max, sum - minSum);
            minSum = Math.min(sum, minSum);
            left_min[i] = -1 * max;
        }
        /*Backward: get min subarray*/
        max = Integer.MIN_VALUE;
        sum = 0;
        minSum = 0;
        for(int i = size - 1; i >= 0; i--){
            sum += copy[i];
            max = Math.max(max, sum - minSum);
            minSum = Math.min(sum, minSum);
            right_min[i] = -1 * max;
        }
        int diff = 0;
        for(int i = 0; i < size - 1; i++){
            diff = Math.max(diff, Math.abs(left_max[i] - right_min[i + 1]));
            diff = Math.max(diff, Math.abs(left_min[i] - right_max[i + 1]));
        }
        return diff;
    }

}

/*
Given an array with integers.

Find two non-overlapping subarrays A and B, which |SUM(A) - SUM(B)| is the largest.

Return the largest difference.

 Notice

The subarray should contain at least one number

Have you met this question in a real interview? Yes
Example
For [1, 2, -3, 1], return 6.


 */