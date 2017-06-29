package S_6_Dynamic_Problem_II.Optional_11;
import java.util.*;
/** 563 Backpack V


 * Created by tianhuizhu on 6/28/17.
 */
public class _563_Backpack_V_Medium {

    public class Solution {
        /**
         * @param nums an integer array and all positive numbers
         * @param target an integer
         * @return an integer
         */
        public int backPackV(int[] nums, int target) {
            // Write your code here
            int[] f = new int[target + 1];
            f[0] = 1;
            for (int i = 0; i < nums.length; ++i)
                for (int  j = target; j >= nums[i]; --j)
                    f[j] += f[j - nums[i]];

            return f[target];
        }
    }
}
