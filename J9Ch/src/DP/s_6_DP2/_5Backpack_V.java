package DP.s_6_DP2;

public class _5Backpack_V {
	/**
     * @param nums an integer array and all positive numbers
     * @param target an integer
     * @return an integer
     */
    public int backPackV(int[] nums, int target) {
        // Write your code here
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int i = 0; i < nums.length; ++i)
            for (int  j = target; j >= nums[i]; --j)
                f[j] += f[j - nums[i]];

        return f[target];
    }
    
    
    /////////////////////////////////////
  //背包九讲
    public int backPackV_2(int[] nums, int target) {
     	int n = nums.length;
     	int[][] f = new int[n + 1][target + 1];
     	f[0][0] = 1;
     	for (int i = 1; i <= n; i++) {
     		for (int j = 0; j <= target; j++) {
     			f[i][j] = f[i - 1][j];
     			if (j - nums[i - 1] >= 0) {
     				f[i][j] += f[i- 1][j - nums[i - 1]];
     			}
     		}
     	}
     	return f[n][target];
    }
}

/*

Given n items with size nums[i] which an integer array and all positive numbers, 
no duplicates. An integer target denotes the size of a backpack. 
Find the number of possible fill the backpack.

Each item may be chosen unlimited number of times

Have you met this question in a real interview? Yes
Example
Given candidate items [2,3,6,7] and target 7,

A solution set is: 
[7]
[2, 2, 3]
return 2

Tags 
Dynamic Programming
Related Problems 
Medium Backpack VI 30 %
Medium Backpack V 39 %
Hard Backpack III 50 %
Medium Backpack II 37 %
Medium Backpack 23 %
*/