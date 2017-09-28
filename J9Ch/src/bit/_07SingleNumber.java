package bit;

public class _07SingleNumber {
    public int singleNumber(int[] A) {
        if(A == null || A.length == 0) {
            return -1;
        }
        int rst = 0;
        for (int i = 0; i < A.length; i++) {
            rst ^= A[i];
        }
        return rst;
    }

///////////////////////////////////////////////////???///////

    public int singleNumber2(int[] nums) {
        int result = 0, n = nums.length;
        for (int i = 0; i < n; i++)
        {
            result ^= nums[i];
        }
        return result;
    }
}
/*
Given 2*n + 1 numbers, every numbers occurs twice except one, find it.

Example
Given [1,2,2,1,3,4,3], return 4


 */