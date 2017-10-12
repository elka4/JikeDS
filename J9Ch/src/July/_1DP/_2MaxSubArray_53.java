package July._1DP;

import org.junit.Test;

public class _2MaxSubArray_53 {
    //1. Dynamic Programming Solution

    //The changing condition for dynamic programming is "We should ignore the sum of
    // the previous n-1 elements if nth element is greater than the sum."
    public int maxSubArray(int[] A) {
        int max = A[0];
        int[] sum = new int[A.length];
        sum[0] = A[0];

        for (int i = 1; i < A.length; i++) {
            sum[i] = Math.max(A[i], sum[i - 1] + A[i]);
            max = Math.max(max, sum[i]);
        }

        return max;
    }

    @Test
    public void test01(){
        int[] A = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(A));
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    //1. Simple Solution

    //Mehdi provided the following solution in his comment. The time space is optimized to be O(1).

    public int maxSubArray2(int[] A) {
        int newsum = A[0];
        int max = A[0];

        for(int i=1;i<A.length;i++){
            newsum = Math.max(newsum + A[i], A[i]);
            max = Math.max(max, newsum);
        }
        return max;
    }

    @Test
    public void test02(){
        int[] A = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray2(A));
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////////////////////



//////////////////////////////////////////////////////////////////////////////////////////////////////

}
/*
LeetCode – Maximum Subarray (Java)

Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4], the contiguous subarray [4,−1,2,1] has the largest sum = 6.


Given the array [−2,2,−3,4,−1,2,1,−5,3], the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 */