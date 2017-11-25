package _08_Bit.HF_bit;


//  181. 将整数A转换为B
//  http://lintcode.com/zh-cn/problem/flip-bits/
//
public class _07SingleNumber {
//------------------------------------------------------------------
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

//------------------------------------------------------------------


    public int singleNumber2(int[] nums) {
        int result = 0, n = nums.length;
        for (int i = 0; i < n; i++)
        {
            result ^= nums[i];
        }
        return result;
    }
//------------------------------------------------------------------
}
/*
Given 2*n + 1 numbers, every numbers occurs twice except one, find it.

Example
Given [1,2,2,1,3,4,3], return 4
 */

/*
181. 将整数A转换为B

 描述
 笔记
 数据
 评测
如果要将整数A转换为B，需要改变多少个bit位？

 注意事项

Both n and m are 32-bit integers.

您在真实的面试中是否遇到过这个题？ Yes
样例
如把31转换为14，需要改变2个bit位。

(31)10=(11111)2

(14)10=(01110)2

标签
比特位操作 Cracking The Coding Interview
相关题目
中等 Rotate Bits - Left 54 %
中等 猜数游戏 II
 */