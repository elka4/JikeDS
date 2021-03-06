package S_1_FollowUp.Required_5;

/** 406 Minimum Size Subarray Sum


 * Created by tianhuizhu on 6/28/17.
 */
public class _406_Minimum_Size_Subarray_Sum {

    public class Solution {
        /**
         * @param nums: an array of integers
         * @param s: an integer
         * @return: an integer representing the minimum size of subarray
         */
        public int minimumSize(int[] nums, int s) {
            // write your code here
            int j = 0, i = 0;
            int sum =0;
            int ans = Integer.MAX_VALUE;
            for(i = 0; i < nums.length; i++) {
                while(j < nums.length && sum < s) {
                    sum += nums[j];
                    j ++;
                }
                if(sum >=s) {
                    ans = Math.min(ans, j - i);
                }
                sum -= nums[i];
            }
            if(ans == Integer.MAX_VALUE)
                ans = -1;
            return ans;
        }
    }
}
