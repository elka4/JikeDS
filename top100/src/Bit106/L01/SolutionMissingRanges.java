package Bit106.L01;

import java.util.ArrayList; // option + return auto-complete imports
import java.util.List;


/**
 * Given a SORTED integer array where the range of elements are in the INCLUSIVE range
 * [lower, upper], return its missing ranges.
 *
 * For example,
 * given [0, 1, 3, 50, 75], lower = 0 and upper = 99,
 * return ["2", "4->49", "51->74", "76->99"]
 */
public class SolutionMissingRanges {
    private static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        // Step1: Define return value
        List<String> rst = new ArrayList<>(); // Don't need to specify type right hand side

        // Step2: Handle corner cases
        if (nums == null || nums.length == 0) {
            addToList(rst, lower, upper);
            return rst;
        }

        // Step3: Fill in business logic
        // First, add the range before lower
        addToList(rst, lower, nums[0]-1);

        // Second, add all the ranges between lower and upper
        int prev = nums[0];
        int i = 1;
        while (i < nums.length) {
            int cur = nums[i];
            if (cur != prev + 1) {
                addToList(rst, prev+1, cur-1);
            }
            prev = cur;
            i++;
        }

        // Third, add the range after upper
        addToList(rst, nums[nums.length-1] + 1, upper);

        // Remember to return result
        return rst;
    }

    // Supporting method
    private static void addToList(List<String> rst, int start, int end) {
        // Cases 1: if lower = upper = 8, return ["8"]
        if (start == end) {
            rst.add(String.valueOf(start)); // Convert integer to string
        } else if (start < end) {
            rst.add(start + "->" + end);
        }
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 3, 50, 75};
        int lower = 0;
        int upper = 99;
        List<String> rst = SolutionMissingRanges.findMissingRanges(nums, lower, upper);
        System.out.println(rst.toString());
    }

    // Time complexity: O(n)

}

