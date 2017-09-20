package HF.HF3_Algorithms_DS_II._1BinarySearch;

//Last Position of Target
public class _12LastPositionofTarget {

////////////////////////////////////////////////////////////////////////////////
// version 1: with jiuzhang template
    public int lastPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                start = mid;
            } else if (nums[mid] < target) {
                start = mid;
                // or start = mid + 1
            } else {
                end = mid;
                // or end = mid - 1
            }
        }

        if (nums[end] == target) {
            return end;
        }
        if (nums[start] == target) {
            return start;
        }
        return -1;
    }

////////////////////////////////////////////////////////////////////////////////

    // version 2: without jiuzhang template

    public int lastPosition2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                start = mid + 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        if (nums[start] == target) {
            return start;
        }
        else if(start != 0 && nums[start - 1] == target){
            return start - 1;
        }
        return -1;
    }
////////////////////////////////////////////////////////////////////////////////
}
/*
Find the last position of a target number in a sorted array. Return -1 if target does not exist.

 */
