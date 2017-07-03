package lintcode._2BinarySearch;

import org.junit.Test;

/**
 * Created by tzh on 1/15/17.
 */
public class MaximumNumberInMountainSequence {
    public int mountainSequence(int[] nums) {
        // Write your code here
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid - 1] < nums[mid]) {
                start = mid;
            } else if (nums[mid] < nums[mid + 1]) {
                end = mid;
            } else {
                end =  mid;
            }
        }
        return Math.max(nums[start], nums[end]);
    }

    @Test
    public void test01() {
        int[] nums = {1, 2, 4, 8, 6, 3};
        System.out.print(mountainSequence(nums));
    }

}
