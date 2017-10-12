package DP.j_5_DP2;//背包类

public class _10Backpack_II {
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
}

/*
 * Given n items with size Ai and value Vi, and a backpack with size m.
 *  What's the maximum value can you put into the backpack?

 Notice

You cannot divide item into small pieces and the total size of 
items you choose should smaller or equal to m.

Have you met this question in a real interview? Yes
Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a 
backpack with size 10. The maximum value is 9.

Challenge 
O(n x m) memory is acceptable, can you do it in O(m) memory?

Tags 
LintCode Copyright Dynamic Programming Backpack
Related Problems 
Medium Backpack VI 36 %
Medium Backpack 22 %
 */