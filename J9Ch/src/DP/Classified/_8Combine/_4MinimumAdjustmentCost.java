package DP.Classified._8Combine;

import java.util.ArrayList;

//Minimum Adjustment Cost
public class _4MinimumAdjustmentCost {
    /**
     * @param A: An integer array.
     * @param target: An integer.
     */
    public int MinAdjustmentCost(ArrayList<Integer> A, int target) {
        // write your code here
        int n = A.size();
        int[][] f = new int[n + 1][101];
        for (int i = 0; i <= n ; ++i)
            for (int j = 0; j <=100; ++j)
                f[i][j] = Integer.MAX_VALUE;
        for (int i = 0; i <= 100; ++i)
            f[0][i] = 0;
        for (int i = 1; i <=n; ++i)
            for (int  j = 0; j <= 100;++j)
                if (f[i-1][j] != Integer.MAX_VALUE)
                    for (int k = 0; k <= 100; ++k)
                        if (Math.abs(j-k) <= target)
                            if (f[i][k] > f[i-1][j] + Math.abs(A.get(i-1)-k))
                                f[i][k] = f[i-1][j] + Math.abs(A.get(i-1)-k);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= 100; ++i)
            if (f[n][i] < ans)
                ans = f[n][i];
        return ans;
    }

//////////////////////////////////////////////////////////////////////////

}
/*
Given an integer array, adjust each integers so that the difference of every adjacent integers are not greater than a given number target.

If the array before adjustment is A, the array after adjustment is B, you should minimize the sum of |A[i]-B[i]|

 Notice

You can assume each number in the array is a positive integer and not greater than 100.

Have you met this question in a real interview? Yes
Example
Given [1,4,2,3] and target = 1, one of the solutions is [2,3,2,3], the adjustment cost is 2 and it's minimal.

Return 2.
 */
