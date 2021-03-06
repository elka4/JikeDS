package J_6_List_Array.Required_10;

/** 41 Maximum Subarray
 * Easy

 * Created by tianhuizhu on 6/28/17.
 */
public class _41_Maximum_Subarray {

// Version 1: Greedy

    public class Solution1 {
        public int maxSubArray(int[] A) {
            if (A == null || A.length == 0){
                return 0;
            }

            int max = Integer.MIN_VALUE, sum = 0;
            for (int i = 0; i < A.length; i++) {
                sum += A[i];
                max = Math.max(max, sum);
                sum = Math.max(sum, 0);
            }

            return max;
        }
    }

// Version 2: Prefix Sum

    public class Solution2 {
        public int maxSubArray(int[] A) {
            if (A == null || A.length == 0){
                return 0;
            }

            int max = Integer.MIN_VALUE, sum = 0, minSum = 0;
            for (int i = 0; i < A.length; i++) {
                sum += A[i];
                max = Math.max(max, sum - minSum);
                minSum = Math.min(minSum, sum);
            }

            return max;
        }
    }



    public class Solution {
        /**
         * @param nums: a list of integers
         * @return: A integer indicate the sum of minimum subarray
         */
        public int maxSubArray(int[] nums) {
            // write your code
            if(nums.length == 0){
                return 0;
            }
            int n = nums.length;
            int[] global = new int[2];
            int[] local = new int[2];
            global[0] = nums[0];
            local[0] = nums[0];
            for(int i = 1; i < n; i ++) {
                local[i % 2] = Math.max(nums[i], local[(i - 1) % 2] + nums[i]);
                global[i % 2] = Math.max(local[i % 2], global[(i - 1) % 2]);
            }
            return global[(n-1) % 2];
        }
    }
}
