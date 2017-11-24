package _08_Bit;
import java.util.*;
import org.junit.Test;

//  136. Single Number
//  https://leetcode.com/problems/single-number/description/

//  http://www.lintcode.com/zh-cn/problem/single-number/
public class _136_Bit_Single_Number_M {
//    My O(n) solution using XOR
//    known that A XOR A = 0 and the XOR operator is commutative, the solution will be very straightforward.


    int singleNumber1(int A[], int n) {
        int result = 0;
        for (int i = 0; i<n; i++)
        {
            result ^=A[i];
        }
        return result;
    }


/*    Easy Java solution (tell you why using bitwise XOR)
    we use bitwise XOR to solve this problem :

    first , we have to know the bitwise XOR in java

0 ^ N = N
    N ^ N = 0
    So..... if N is the single number

    N1 ^ N1 ^ N2 ^ N2 ^..............^ Nx ^ Nx ^ N

= (N1^N1) ^ (N2^N2) ^..............^ (Nx^Nx) ^ N

= 0 ^ 0 ^ ..........^ 0 ^ N

= N*/

    public int singleNumber2(int[] nums) {
        int ans =0;

        int len = nums.length;
        for(int i=0;i!=len;i++)
            ans ^= nums[i];

        return ans;

    }
//---------------------------------////////////////
    // 9Ch
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
}
/*
给出2*n + 1 个的数字，除其中一个数字之外其他每个数字均出现两次，找到这个数字。



样例
给出 [1,2,2,1,3,4,3]，返回 4


 */

/*
iven an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */