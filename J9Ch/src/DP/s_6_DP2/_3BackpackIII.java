package DP.s_6_DP2;

public class _3BackpackIII {
	/**
     * @param A an integer array
     * @param V an integer array
     * @param m an integer
     * @return an array
     */
    public int backPackIII(int[] A, int[] V, int m) {
        // Write your code here
        int n = A.length;
        int[] f = new int[m+1];        
        for (int i = 0; i < n; ++i)
            for (int j = A[i]; j <= m; ++j)
                if (f[j - A[i]] + V[i] > f[j])
                    f[j] = f[j - A[i]] + V[i];
        return f[m];
    }
    
    //////////////////////
    
    //背包九讲
    public int backPackIII_2(int[] A, int[] V, int m) {
	    	int n = A.length;
	    	int[][] f = new int[n + 1][m + 1];
	    	for (int i = 1; i <= n; i++) {
	    		for (int j = 0; j <= m; j++) {
	    			f[i][j] = f[i - 1][j];
	    			if (j - A[i- 1] >= 0) {
	    				f[i][j] = Math.max(f[i - 1][j], f[i][j - A[i - 1]] + V[i - 1]);
	    			}
	    		}
	    	}
	    	return f[n][m];
    }
    
    
}

/*
Given n kind of items with size Ai and value Vi( each item has an infinite
 number available) and a backpack with size m.
  What's the maximum value can you put into the backpack?

Notice

You cannot divide item into small pieces and the total size of items you 
choose should smaller or equal to m.

Have you met this question in a real interview? Yes
Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack
 with size 10. The maximum value is 15.

Tags 
Dynamic Programming
Related Problems 
Medium Backpack VI 30 %
Medium Backpack V 39 %
Medium Backpack IV 35 %
Medium Backpack II 37 %
Medium Backpack 23 %
*/