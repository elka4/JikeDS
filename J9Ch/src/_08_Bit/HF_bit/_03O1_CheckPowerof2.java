package _08_Bit.HF_bit;

//  142. O(1) Check Power of 2
//  http://www.lintcode.com/en/problem/o1-check-power-of-2/
//
public class _03O1_CheckPowerof2 {
    /*
         * @param n: An integer
         * @return: True or false
         */
    public boolean checkPowerOf2(int n) {
        if (n <= 0) {
            return false;
        }
        return (n & (n-1)) == 0;
    }

//-------------------------------------------------------------------------/

}
/*
Using O(1) time to check whether an integer n is a power of 2.

Example
For n=4, return true;

For n=5, return false;
 */


/*-------------------------------------------------------
142. O(1)时间检测2的幂次

 描述
 笔记
 数据
 评测
用 O(1) 时间检测整数 n 是否是 2 的幂次。

样例
n=4，返回 true;

n=5，返回 false.

挑战
O(1) time

标签
比特位操作
相关题目
容易 二进制中有多少个1
 */