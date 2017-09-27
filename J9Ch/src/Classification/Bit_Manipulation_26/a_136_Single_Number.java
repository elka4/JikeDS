package Classification.Bit_Manipulation_26;
//所有数字除向两次，找到只出现一次的数
// 0 ^ N = N
// N ^ N = 0

public class a_136_Single_Number {
    int singleNumber(int A[], int n) {
        int result = 0;
        for (int i = 0; i<n; i++)
        {
            result ^= A[i];
        }
        return result;
    }

    /*

    we use bitwise XOR to solve this problem :

    first , we have to know the bitwise XOR in java

    0 ^ N = N
    N ^ N = 0
    So..... if N is the single number

    N1 ^ N1 ^ N2 ^ N2 ^..............^ Nx ^ Nx ^ N

    = (N1^N1) ^ (N2^N2) ^..............^ (Nx^Nx) ^ N

    = 0 ^ 0 ^ ..........^ 0 ^ N

    = N
    */

    public int singleNumber(int[] nums) {
        int ans =0;

        int len = nums.length;
        for(int i=0;i!=len;i++)
            ans ^= nums[i];

        return ans;

    }

}

/*
Given an array of integers, every element appears twice except for one.
Find that single one.

Note:
Your algorithm should have a linear runtime complexity.
Could you implement it without using extra memory?
 */
