package J_6_Linked_List_Array.Array;

/** 620 Maximum Subarray IV
 * Created by tianhuizhu on 6/28/17.
 */
public class _620_Maximum_Subarray_IV {
    /**
     * @param nums an array of integers
     * @param k an integer
     * @return the largest sum
     */
    public int maxSubarray4(int[] nums, int k) {
        // Write your code here
        int n = nums.length;
        if (n < k)
            return 0;

        int result = 0;
        for (int i = 0; i < k; ++i)
            result += nums[i];

        int[] sum = new int[n + 1];
        sum[0] = 0;

        int min_prefix = 0;
        for (int i = 1; i <= n; ++i) {
            sum[i] = sum[i - 1] + nums[i - 1];
            if (i >= k && sum[i] - min_prefix > result) {
                result = Math.max(result, sum[i] - min_prefix);
            }
            if (i >= k) {
                min_prefix = Math.min(min_prefix, sum[i - k + 1]);
            }
        }
        return result;
    }

}
/*
Given an integer arrays, find a contiguous subarray which has the largest sum and length should be greater or equal to given length k.
Return the largest sum, return 0 if there are fewer than k elements in the array.

 Notice

Ensure that the result is an integer type.

Have you met this question in a real interview? Yes
Example
Given the array [-2,2,-3,4,-1,2,1,-5,3] and k = 5, the contiguous subarray [2,-3,4,-1,2,1] has the largest sum = 5.
 */
