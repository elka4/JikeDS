package _08_Bit.HF_bit;


//  365. Count 1 in Binary
//  http://lintcode.com/en/problem/count-1-in-binary/
//
public class _04Count1_inBinary {
    /**
     * @param num: an integer
     * @return: an integer, the number of ones in num
     */
    public int countOnes(int num) {
        int count = 0;
        while (num != 0) {
            num = num & (num - 1);
            count++;
        }
        return count;
    }

//---------------------------------------------------
}
/*---------------------------------------------------
Count how many 1 in binary representation of a 32-bit integer.

Example
Given 32, return 1

Given 5, return 2

Given 1023, return 9
 */

/*---------------------------------------------------
365. 二进制中有多少个1

 描述
 笔记
 数据
 评测
计算在一个 32 位的整数的二进制表示中有多少个 1.

您在真实的面试中是否遇到过这个题？ Yes
样例
给定 32 (100000)，返回 1

给定 5 (101)，返回 2

给定 1023 (1111111111)，返回 10

挑战
If the integer is n bits with m 1 bits. Can you do it in O(m) time?

标签
比特位操作 二进制
相关题目
容易 O(1)时间检测2的幂次
 */