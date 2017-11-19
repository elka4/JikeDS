package _01_Array.Caching;


/*
LeetCode – Increasing Triplet Subsequence (Java)

Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Examples:
Given [1, 2, 3, 4, 5],
return true.

Given [5, 4, 3, 2, 1],
return false.

Analysis

This problem can be converted to be finding if there is a sequence such that the_smallest_so_far < the_second_smallest_so_far < current. We use x, y and z to denote the 3 number respectively.
 */

public class Increasing_Triplet_Subsequence {
    public boolean increasingTriplet(int[] nums) {
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int z = nums[i];

            if (x >= z) {
                x = z;// update x to be a smaller value
            } else if (y >= z) {
                y = z; // update y to be a smaller value
            } else {
                return true;
            }
        }

        return false;
    }


//-------------------------------------------------------------------------//////





//-------------------------------------------------------------------------//////





//-------------------------------------------------------------------------//////







//-------------------------------------------------------------------------//////





//-------------------------------------------------------------------------//////





//-------------------------------------------------------------------------//////







//-------------------------------------------------------------------------//////





//-------------------------------------------------------------------------//////





//-------------------------------------------------------------------------//////





}
