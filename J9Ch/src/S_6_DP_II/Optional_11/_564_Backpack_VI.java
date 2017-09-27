package S_6_DP_II.Optional_11;

/** 564 Backpack VI
 * Created by tianhuizhu on 6/28/17.
 */
public class _564_Backpack_VI {

    public class Solution {
        /**
         * @param nums an integer array and all positive numbers, no duplicates
         * @param target an integer
         * @return an integer
         */
        public int backPackVI(int[] nums, int target) {
            // Write your code here
            int[] f = new int[target + 1];
            f[0] = 1;
            for (int i = 1; i <= target; ++i)
                for (int  j = 0; j < nums.length; ++j)
                    if (i >= nums[j])
                        f[i] += f[i - nums[j]];

            return f[target];
        }
    }
}
