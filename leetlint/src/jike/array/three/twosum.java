package jike.array.three;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Student on 12/28/16.
 */
public class twosum {
    public int[] twoSum(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        int[] result = new int[2];
        while(i < j) {
            int sum = nums[i] + nums[j];
            if (sum == target) {
                result[0] = nums[i];
                result[1] = nums[j];
                return result;
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return result;
    }
    public int[] twoSumBinarySearch(int[] nums, int target) {
        int[] result = new int[2];
        int n = nums.length;
        for (int i = 0; i < nums.length - 1; i++) {
            int another = target - nums[i];
            if(Arrays.binarySearch(nums, i + 1, n - 1, another) >= i + 1) {
                result[0] = nums[i];
                result[1] = another;
            }

        }
        return result;
    }

    @Test
    public void test01() {
        int[] array = {1, 2, 4, 5, 6, 7, 13};
        int[] result = twoSum(array, 13);
        System.out.print("result: " + result[0] + " and " + result[1]);


        int[] resultBinary = twoSumBinarySearch(array, 13);
        System.out.print("result: " + resultBinary[0] + " and " + resultBinary[1]);
    }
}
