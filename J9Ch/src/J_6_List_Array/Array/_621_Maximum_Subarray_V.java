package J_6_List_Array.Array;

import java.util.LinkedList;

/** 621  Maximum Subarray V


 * Created by tianhuizhu on 6/28/17.
 */
public class _621_Maximum_Subarray_V {
    /**
     * @param nums an array of integers
     * @param k1 an integer
     * @param k2 an integer
     * @return the largest sum
     */
    public int maxSubarray5(int[] nums, int k1, int k2) {
        // Write your code here
        int n = nums.length;
        if (n < k1)
            return 0;

        int result = Integer.MIN_VALUE;

        int[] sum = new int[n + 1];
        sum[0] = 0;
        LinkedList<Integer> queue = new LinkedList<Integer>();

        for (int i = 1; i <= n; ++i) {
            sum[i] = sum[i - 1] + nums[i - 1];

            if (!queue.isEmpty() && queue.getFirst() < i - k2) {
                queue.removeFirst();
            }
            if (i >= k1) {
                while (!queue.isEmpty() && sum[queue.getLast()] > sum[i - k1]) {
                    queue.removeLast();
                }
                queue.add(i - k1);
            }

            // [i - k2, i - k1]
            if (!queue.isEmpty() && sum[i] - sum[queue.getFirst()] > result) {
                result = Math.max(result, sum[i] - sum[queue.getFirst()]);
            }


        }
        return result;
    }

}

/*
Given an integer arrays, find a contiguous subarray which has the largest sum and length should be between k1 and k2 (include k1 and k2).
Return the largest sum, return 0 if there are fewer than k1 elements in the array.

 Notice

Ensure that the result is an integer type.

Have you met this question in a real interview? Yes
Example
Given the array [-2,2,-3,4,-1,2,1,-5,3] and k1 = 2, k2 = 4, the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 */