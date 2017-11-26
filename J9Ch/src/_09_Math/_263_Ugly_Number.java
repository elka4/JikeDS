package _09_Math;



//  263. Ugly Number
//  https://leetcode.com/problems/ugly-number/description/
//  http://www.lintcode.com/zh-cn/problem/ugly-number/
//  Math
//
public class _263_Ugly_Number {
//-----------------------------------------------------------------------


//-----------------------------------------------------------------------
    //9Ch
    public class Solution {
        /**
         * @param num an integer
         * @return true if num is an ugly number or false
         */
        public boolean isUgly(int num) {
            if (num <= 0) return false;
            if (num == 1) return true;

            while (num >= 2 && num % 2 == 0) num /= 2;
            while (num >= 3 && num % 3 == 0) num /= 3;
            while (num >= 5 && num % 5 == 0) num /= 5;

            return num == 1;
        }
    }


//-----------------------------------------------------------------------

}
/*
Write a program to check whether a given number is an ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.

Note that 1 is typically treated as an ugly number.
----------------------------------------------------------------
 */

/*----------------------------------------------------------------
517. 丑数

写一个程序来检测一个整数是不是丑数。

丑数的定义是，只包含质因子 2, 3, 5 的正整数。比如 6, 8 就是丑数，但是 14 不是丑数以为他包含了质因子 7。

 注意事项

可以认为 1 是一个特殊的丑数。

样例
给出 num = 8，返回 true。
给出 num = 14，返回 false。

标签
数学
相关题目
中等 超级丑数 27 %
容易 快乐数
----------------------------------------------------------------
 */