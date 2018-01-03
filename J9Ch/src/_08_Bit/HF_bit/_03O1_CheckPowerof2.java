package _08_Bit.HF_bit;

import org.junit.Test;

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
        //System.out.printf("%10s%32s\n", "n: ", Integer.toBinaryString(n));
        //System.out.printf("%10s%32s\n", "n-1: ", Integer.toBinaryString(n-1));

        return (n & (n-1)) == 0;
    }
/*
2的幂特征就是1后面都是0
也可以检验第一位是1，后面都是0
 */
    @Test
    public void test(){
        System.out.println(checkPowerOf2(10));
        System.out.println("------------------------------------------------");
        System.out.println(checkPowerOf2(11));
        System.out.println("------------------------------------------------");
    }
/*
       n:                             1010
     n-1:                             1001
false
------------------------------------------------
       n:                             1011
     n-1:                             1010
false
------------------------------------------------
 */

    @Test
    public void test2(){
        for (int i = 0; i < 100; i++) {
            if (checkPowerOf2(i)){
                System.out.println(i);
                System.out.println(Integer.toBinaryString(i));
                System.out.println(checkPowerOf2(i));
                System.out.println("------------------------------------------------");
            }
        }
    }
/*
1
1
true
------------------------------------------------
2
10
true
------------------------------------------------
4
100
true
------------------------------------------------
8
1000
true
------------------------------------------------
16
10000
true
------------------------------------------------
32
100000
true
------------------------------------------------
64
1000000
true
------------------------------------------------


 */
//-------------------------------------------------------------------------------

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