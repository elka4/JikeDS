package lintcode._2BinarySearch;

import org.junit.Test;

/**
 * Created by tzh on 1/16/17.
 */
public class SearchRange {

    public int[] searchRange(int[] nums, int target) {


        int[] result = new int[2];
        if (nums == null || nums.length <= 1) {
            result[0] = -1;
            result[1] = -1;
            return result;
        }
        int start = 0;
        int end = nums.length - 1;
        //int first = start;
        //int last = end;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] == target) {
            //first = start;
            result[0] = start;
        } else if (nums[end] == target) {
            //first = end;
            result[0] = end;
        } else {
            result[0] = -1;

        }

        start = 0;
        end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                start = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[end] == target) {
            //last = end;
            result[1] = end;
        } else if (nums[start] == target) {
            // last = start;
            result[1] = start;
        } else {
            result[1] = -1;
        }

        return result;
    }

    @Test
    public void test01() {


    }
}
