package J_7_Array;

public class _25Interleaving_Positive_and_Negative_Numbers {
	/**
     * @param A: An integer array.
     * @return: void
     */
    int[] subfun(int[] A,int [] B, int len) {
        int[] ans = new int[len];
        for(int i = 0; i * 2 + 1 < len; i++) {
            ans[i * 2] = A[i];
            ans[i * 2 + 1] = B[i];
            }
        if(len % 2 == 1)
            ans[len - 1] = A[len / 2];
        return ans;
    }
    public void rerange(int[] A) {
        int[] Ap = new int[A.length];
        int totp = 0;
        int totm = 0;
        int[] Am = new int[A.length];
        int[] tmp = new int[A.length];
        for(int i = 0; i < A.length; i++)
            if(A[i] > 0)
                {
                    Ap[totp] = A[i];
                    totp += 1;
                }
            else
                {
                    Am[totm] = A[i];
                    totm += 1;  
                }   
        if(totp > totm)
            tmp = subfun(Ap, Am, A.length);
        else
            tmp = subfun(Am, Ap, A.length);
        for (int i = 0; i < tmp.length; ++i)
            A[i] = tmp[i];
    }
}

/*Given an array with positive and negative integers. 
 * Re-range it to interleaving with positive and negative integers.
 

 Notice

You are not necessary to keep the original order of positive integers 
or negative integers.

Have you met this question in a real interview? Yes
Example
Given [-1, -2, -3, 4, 5, 6], after re-range, it will be 
[-1, 5, -2, 4, -3, 6] or any other reasonable answer.

Challenge 
Do it in-place and without extra memory.

Tags 
Two Pointers
Related Problems 
Medium Partition Array 27 %*/