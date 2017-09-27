package s_6_DP2;

public class _4Backpack_IV {
	
	// 方法一
    /**
     * @param nums an integer array and all positive numbers, no duplicates
     * @param target an integer
     * @return an integer
     */
    public int backPackIV(int[] nums, int target) {
        // Write your code here
        int m = target;
        int []A = nums;
        int f[][] = new int[A.length + 1][m + 1];
        
        f[0][0] = 1;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j <= m; j++) {
                int k = 0; 
                while(k * A[i] <= j) {
                    f[i + 1][j] += f[i][j-A[i]*k];
                    k+=1;
                }
            } // for j
        } // for i
        
        
        return f[A.length][target];
    }
	
 // 方法二
	
	/**
     * @param nums an integer array and all positive numbers, no duplicates
     * @param target an integer
     * @return an integer
     */
	//f[i][j]：前i个物品放到体积为j的包里，最大能放多少

    public int backPackIV_2(int[] nums, int target) {
        // Write your code here
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int i = 0; i < nums.length; ++i)
            for (int  j = nums[i]; j <= target; ++j)
                f[j] += f[j - nums[i]];

        return f[target];
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