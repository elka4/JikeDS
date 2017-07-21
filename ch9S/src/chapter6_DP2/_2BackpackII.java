package chapter6_DP2;

public class _2BackpackII {
	/**
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and value V[i]
     */
    public int backPackII(int m, int[] A, int V[]) {
        // write your code here
        int[] f = new int[m+1];
        for (int i = 0; i <=m ; ++i) f[i] = 0;
        int n = A.length , i, j;
        for(i = 0; i < n; i++){
            for(j = m; j >= A[i]; j--){
                if (f[j] < f[j - A[i]] + V[i])
                    f[j] = f[j - A[i]] + V[i];
            }
        }
        return f[m];
    }
    
    //////////////////////////
    
    
//lintcode 125
public int backPackII_2(int m, int[] A, int V[]) {
	int n = A.length;
	int[][] f = new int[n + 1][m + 1];
	for (int i= 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			f[i][j] = f[i - 1][j];
			if (j >= A[i - 1]) {
				f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - A[i - 1]]+ V[i - 1]);
			}
		}
	}
	return f[n][m];
}
    
    
}


/*
Given n items with size Ai and value Vi, and a backpack with size m. 
What's the maximum value can you put into the backpack?

Notice

You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.

Have you met this question in a real interview? Yes
Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 9.

Challenge 
O(n x m) memory is acceptable, can you do it in O(m) memory?

Tags 
LintCode Copyright Dynamic Programming Backpack
Related Problems 
Medium Backpack VI 30 %
Medium Backpack V 39 %
Medium Backpack IV 35 %
Hard Backpack III 50 %
Medium Backpack 23 %
*/

/*
给出n个物品的体积A[i]和其价值V[i]，将他们装入一个大小为m的背包，最多能装入的总价值有多大？

注意事项

A[i], V[i], n, m均为整数。你不能将物品进行切分。你所挑选的物品总体积需要小于等于给定的m。

您在真实的面试中是否遇到过这个题？ Yes
样例
对于物品体积[2, 3, 5, 7]和对应的价值[1, 5, 2, 4], 假设背包大小为10的话，最大能够装入的价值为9。*/