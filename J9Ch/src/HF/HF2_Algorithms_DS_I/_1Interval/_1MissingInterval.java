package HF.HF2_Algorithms_DS_I._1Interval;

import java.util.*;

public class _1MissingInterval {
    /**
     * @param nums a sorted integer array
     * @param lower an integer
     * @param upper an integer
     * @return a list of its missing ranges
     */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        // Write your code here
        List<String> results = new ArrayList<String>();

        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == Integer.MIN_VALUE) {
                lower = nums[i] + 1;
                continue;
            }
            if (lower == nums[i] - 1) {
                results.add(lower + "");
            } else if (lower < nums[i] - 1) {
                results.add(lower + "->" + (nums[i] - 1));
            }
            if (nums[i] == Integer.MAX_VALUE) {
                return results;
            }
            lower = nums[i] + 1;
        }
        if (lower == upper) {
            results.add(lower + "");
        } else if (lower < upper) {
            results.add(lower + "->" + upper);
        }
        return results;
    }
}
