package DP.s_6_DP2;

public class _6Backpack_Sum {
	
//背包九讲
//lintcode 92
//f[i][j]：前i个物品放到体积为j的包里，最大能放多少
//f[i][j] = max(f[i - 1][j], f[i- 1][j - A[i]] + A[i])   范围 1<=i<=n  0<=j<=m
//初始条件: f[0][0] = 0, f[0][1] = 0 ......f[0][m] = 0
//O(n * m)
	
public int backPack_1(int m, int[] A) {
	int n = A.length;
	int[][] f = new int[n + 1][m + 1];
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			f[i][j] = f[i - 1][j];
			if (j >= A[i - 1]) {
				//f[i - 1][j]： 不放 最后一个／当前／，取顶头
				//f[i - 1][j - A[i - 1]]： 放当前+ A[i - 1]， 取斜上方。。。。
				f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - A[i - 1]] + A[i - 1]);
			}
		}
	}
	return f[n][m];
}

//lintcode 125， 有value
//f[i][j] = max(f[i - 1][j], f[i - 1][j - A[i] + V[i]])  范围  1<=i<=n  0<=j<=m
//初始条件: f[0][0] = 0, f[0][1] = 0 ......f[0][m] = 0    O(n * m)

public int backPackII(int m, int[] A, int V[]) {
	int n = A.length;
	int[][] f = new int[n + 1][m + 1];
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			f[i][j] = f[i - 1][j];
			if (j >= A[i - 1]) {
				//f[i - 1][j]： 不放当前，取顶头
				//f[i - 1][j - A[i - 1]]： 放当前+ V[i - 1]， 取斜上方。。。。
				f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - A[i - 1]] + V[i - 1]);
			}
		}
	}
	return f[n][m];
}

//f[i][j] = max(f[i - 1][j], f[i][j - A[i] + V[i]])  范围  1<=i<=n  0<=j<=m
//初始条件: f[0][0] = 0, f[0][1] = 0 ......f[0][m] = 0
//O(n * m)
//背包九讲， 有value，重复
public int backPackIII(int[] A, int[] V, int m) {
    	int n = A.length;
    	int[][] f = new int[n + 1][m + 1];
    	for (int i = 1; i <= n; i++) {
    		for (int j = 0; j <= m; j++) {
    			f[i][j] = f[i - 1][j];
    			if (j - A[i- 1] >= 0) {
    		//f[i - 1][j]： 不放当前，取顶头
    		// + V[i - 1]：放当前，取前一个同一行左边，f[i][j - A[i - 1] 加上当前V[i - 1]
    				f[i][j] = Math.max(f[i - 1][j], f[i][j - A[i - 1]] + V[i - 1]);
    			}
    		}
    	}
    	return f[n][m];
}

/*
Given n items with size nums[i] which an integer array and all positive
numbers, no duplicates. An integer target denotes the size of a backpack. 
Find the number of possible fill the backpack.
Each item may be chosen unlimited number of times
Given candidate items [2,3,6,7] and target 7,
A solution set is: 
[7]
[2, 2, 3]
return 2
*/

//有多少种方法可以放满
public int backPackIV(int[] nums, int target) {
	return target;
	
}

/*Given n items with size nums[i] which an integer array and all 
 * positive numbers. An integer target denotes the size of a backpack.
 *  Find the number of possible fill the backpack.
Each item may only be used once
Given candidate items [1,2,3,3,7] and target 7,
A solution set is: 
[7]
[1, 3, 3]
return 2*/

//初始条件 f[0][0] = 1;
//f[i][j] = f[i - 1][j] + f[i - 1][j - A[i]] 
//背包九讲
public int backPackV(int[] nums, int target) {
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

/*
Given an integer array nums with all positive numbers and no duplicates, 
find the number of possible combinations that add up to a positive integer target.
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
public int backPackVI(int[] nums, int target) {
	return target;
}

}

