package _08_Bit.HF_bit;


import org.junit.Test;

//  1. A + B Problem
//  http://www.lintcode.com/zh-cn/problem/a-b-problem/
//  Cracking The Coding Interview
public class _02AB_Problem {
//-------------------------------------------------------------------------
    //1
    /*
         * param a: The first integer
         * param b: The second integer
         * return: The sum of a and b
         */
    public int aplusb(int a, int b) {
        // 主要利用异或运算来完成
        // 异或运算有一个别名叫做：不进位加法
        // 那么a ^ b就是a和b相加之后，该进位的地方不进位的结果
        // 然后下面考虑哪些地方要进位，自然是a和b里都是1的地方
        // a & b就是a和b里都是1的那些位置，a & b << 1 就是进位
        // 之后的结果。所以：a + b = (a ^ b) + (a & b << 1)
        // 令a' = a ^ b, b' = (a & b) << 1
        // 可以知道，这个过程是在模拟加法的运算过程，进位不可能
        // 一直持续，所以b最终会变为0。因此重复做上述操作就可以
        // 求得a + b的值。
        while (b != 0) {
            System.out.printf("%10s%32s\n", "a: ", Integer.toBinaryString(a));
            System.out.printf("%10s%32s\n", "b: ", Integer.toBinaryString(b));

            int _a = a ^ b;
            System.out.printf("%10s%32s\n", "a ^ b: ", Integer.toBinaryString(a ^ b));

            int _b = (a & b) << 1;
            System.out.printf("%10s%32s\n", "a & b: ", Integer.toBinaryString(a & b));
            System.out.printf("%10s%32s\n", "(a&b)<<1:", Integer.toBinaryString((a & b) << 1));

            a = _a;
            b = _b;
            System.out.println("---------------------------------------");
        }
        return a;
    }

    @Test
    public void test1_1(){
        System.out.println(aplusb(1,3));
    }
    /*
       a:                                1
       b:                               11
   a ^ b:                               10
   a & b:                                1
 (a&b)<<1:                              10
---------------------------------------
       a:                               10
       b:                               10
   a ^ b:                                0
   a & b:                               10
 (a&b)<<1:                             100
---------------------------------------
       a:                                0
       b:                              100
   a ^ b:                              100
   a & b:                                0
 (a&b)<<1:                               0
---------------------------------------
4

     */

//-------------------------------------------------------------------------
    //2
    /*
        * param a: The first integer
        * param b: The second integer
        * return: The sum of a and b
        */
    public int aplusb2(int a, int b) {
        while (b != 0) {
            int _a = a ^ b;
            int _b = (a & b) << 1;
            a = _a;
            b = _b;
        }
        return a;
    }
//-------------------------------------------------------------------------

}
/*
Write a function that add two numbers A and B. You should not use + or any arithmetic operators.

 Notice

There is no need to read data from standard input stream. Both parameters are given in function aplusb, you job is to calculate the sum and return it.

Clarification
Are a and b both 32-bit integers?

Yes.
Can I use bit operation?

Sure you can.
Example
Given a=1 and b=2 return 3


 */

/*
给出两个整数a和b, 求他们的和, 但不能使用 + 等数学运算符。

 注意事项

你不需要从输入流读入数据，只需要根据aplusb的两个参数a和b，计算他们的和并返回就行。

说明
a和b都是 32位 整数么？

是的
我可以使用位运算符么？

当然可以
样例
如果 a=1 并且 b=2，返回3

挑战
显然你可以直接 return a + b，但是你是否可以挑战一下不这样做？

标签
比特位操作 Cracking The Coding Interview
 */