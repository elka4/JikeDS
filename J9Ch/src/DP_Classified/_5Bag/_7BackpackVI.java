package DP_Classified._5Bag;

//Backpack VI
public class _7BackpackVI {
    /**
     * @param nums an integer array and all positive numbers, no duplicates
     * @param target an integer
     * @return an integer
     */
    public int backPackVI(int[] nums, int target) {
        // Write your code here
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int i = 1; i <= target; ++i)
            for (int  j = 0; j < nums.length; ++j)
                if (i >= nums[j])
                    f[i] += f[i - nums[j]];

        return f[target];
    }
//-------------------------------------------------------------------------////
}
/*
Given an integer array nums with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

 Notice

A number in the array can be used multiple times in the combination.
Different orders are counted as different combinations.

Have you met this question in a real interview? Yes
Example
Given nums = [1, 2, 4], target = 4

The possible combination ways are:
[1, 1, 1, 1]
[1, 1, 2]
[1, 2, 1]
[2, 1, 1]
[2, 2]
[4]
return 6
 */
