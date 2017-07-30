package J_6_Linked_List_Array.other.Two_Sum;

import java.util.Arrays;

public class _01Two_Sum_II {
	/**
     * @param nums: an array of integer
     * @param target: an integer
     * @return: an integer
     */
    public int twoSum2(int[] nums, int target) {
        // Write your code here
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        int ans = 0;
        while(left < right) {
            if(nums[left] + nums[right] > target) {
                ans = ans + (right - left);
                right --;
            } else {
                left ++;
            }
        }
        return ans;
    }
}
/*Given an array of integers, find how many pairs in the array such 
 * that their sum is bigger than a specific target number. 
 * Please return the number of pairs.
 

Have you met this question in a real interview? Yes
Example
Given numbers = [2, 7, 11, 15], target = 24. Return 1. (11 + 15 is the only pair)

Challenge 
Do it in O(1) extra space and O(nlogn) time.

Tags 
Two Pointers Sort
Related Problems 
Medium Two Sum 28 %*/