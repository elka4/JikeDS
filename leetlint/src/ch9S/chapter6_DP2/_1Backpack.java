package ch9S.chapter6_DP2;


/*
 * lintcode
 * 92 backpack
 * 125 backpack2,3
 * 564 backpack 4, 5, 6
 */
public class _1Backpack {
	/**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
	
    public int backPack(int m, int[] A) {
        boolean f[][] = new boolean[A.length + 1][m + 1];
        for (int i = 0; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = false;
            }
        }
        f[0][0] = true;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j <= m; j++) {
                f[i + 1][j] = f[i][j];
                if (j >= A[i] && f[i][j - A[i]]) {
                    f[i + 1][j] = true;
                }
            } // for j
        } // for i
        
        for (int i = m; i >= 0; i--) {
            if (f[A.length][i]) {
                return i;
            }
        }
        return 0;
    }
    
    ////////////////////////////////////
    //背包九讲
    //lintcode 92
	//f[i][j]：前i个物品放到体积为j的包里，最大能放多少
    //f[i][j] = max(f[i - 1][j], f[i- 1][j - A[i]] + A[i])
    
    
public int backPack_2(int m, int[] A) {
	int n = A.length;
	int[][] f = new int[n + 1][m + 1];
	for (int i= 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			f[i][j] = f[i - 1][j];
			if (j >= A[i - 1]) {
				f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - A[i - 1]] + A[i - 1]);
			}
		}
	}
	return f[n][m];
}

}

/*
Given n items with size Ai, an integer m denotes the size of a backpack. 
How full you can fill this backpack?

 Notice

You can not divide any item into small pieces.

Have you met this question in a real interview? Yes
Example
If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], so that the max size we can fill this backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.

You function should return the max size we can fill in the given backpack.

Challenge 
O(n x m) time and O(m) memory.

O(n x m) memory is also acceptable if you do not know how to optimize memory.

Tags 
LintCode Copyright Dynamic Programming Backpack
Related Problems 
Medium Backpack VI 30 %
Medium Backpack V 39 %
Medium Backpack IV 35 %
Hard Backpack III 50 %
Medium Backpack II 37 %
*/

/*
在n个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为m，每个物品的大小为A[i]

 注意事项

你不可以将物品进行切割。

您在真实的面试中是否遇到过这个题？ Yes
样例
如果有4个物品[2, 3, 5, 7]

如果背包的大小为11，可以选择[2, 3, 5]装入背包，最多可以装满10的空间。

如果背包的大小为12，可以选择[2, 3, 7]装入背包，最多可以装满12的空间。

函数需要返回最多能装满的空间大小。
*/