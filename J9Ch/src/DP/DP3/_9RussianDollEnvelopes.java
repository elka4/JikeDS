package DP.DP3;

import a.d.M;

import java.util.Arrays;
import java.util.Comparator;

//• 坐标型

/*
设 f[i] 表示以Ei为最外层信封时最多的嵌套层数

f[i] = max{1, f[j]+1 | j<i 且Ej能放在Ei里 }

f[i] 以Ei为最外层信封时最多的嵌套层数
1 只用Ei这一个信封
f[j]+1 | j<i 以Ej为此外层信封时，最多的嵌套层数，加上Ei

• 计算顺序 f[0], f[1], ..., f[N-1]
•      时间O(N2)      空间O(N)
 */

//Russian Doll Envelopes
public class _9RussianDollEnvelopes {

    // 9Ch DP
    public int maxEnvelopes(int[][] A) {
        // Write your code here
        if(A == null || A.length == 0) {
            return 0;
        }

        Arrays.sort(A, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if(a[0] == b[0])
                    return a[1] - b[1];
                else
                    return a[0] - b[0];
            }
        });
        int n = A.length;
        int[] f = new int[n];
        int i, j, res = 0;

        for (i = 0; i < n; i++) {
            f[i] = 1;
            for (j = 0; j < i; j++) {
                // envelope j can be put inside envelope i
                if (A[j][0] < A[i][0] && A[j][1] < A[i][1]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            res = Math.max(res, f[i]);
        }

        return res;
    }

/////////////////////////////////////////////////////////////////////////////
    /**
     * @param envelopes a number of envelopes with widths and heights
     * @return the maximum number of envelopes
     */
    public int maxEnvelopes2(int[][] envelopes) {
        // Write your code here
        if(envelopes == null || envelopes.length == 0
                || envelopes[0] == null || envelopes[0].length != 2)
            return 0;

        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                if(arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                else
                    return arr1[0] - arr2[0];
            }
        });

        int dp[] = new int[envelopes.length];
        int len = 0;

        for(int[] envelope : envelopes){
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            if(index < 0)
                index = -index - 1;
            dp[index] = envelope[1];
            if (index == len)
                len++;
        }
        return len;
    }

/////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////

}
/*
You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Have you met this question in a real interview? Yes
Example
Given envelopes = [[5,4],[6,4],[6,7],[2,3]],
the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
