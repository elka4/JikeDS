package _BinarySearch.Position_J; import org.junit.Test;

// Search for a Range
// leetcode 34. Search for a Range

public class _61_Search_for_a_Range {
//------------------------------------------------------------------------------

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        
        int start, end, mid;
        int[] bound = new int[2]; 
        
        // search for left bound
        start = 0; 
        end = nums.length - 1;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] == target) {
            bound[0] = start;
        } else if (nums[end] == target) {
            bound[0] = end;
        } else {
            bound[0] = bound[1] = -1;
            return bound;
        }
        
        // search for right bound
        start = 0;
        end = nums.length - 1;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                start = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[end] == target) {
            bound[1] = end;
        } else if (nums[start] == target) {
            bound[1] = start;
        } else {
            bound[0] = bound[1] = -1;
            return bound;
        }
        
        return bound;
    }

    @Test
    public void test01(){
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] result = searchRange(nums, target);
        for (int i : result) {
            System.out.println(i);
        }
    }
//------------------------------------------------------------------------------
}


/*
 * Given a sorted array of n integers, find the starting 
 * and ending position of a given target value.

If the target is not found in the array, return [-1, -1].

Have you met this question in a real interview? Yes
Example
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].

Challenge 
O(log n) time.

Tags 
Binary Search Sorted numsrray numsrray
Related Problems 
Easy Total Occurrence of Target 25 %
 * 
 * */
