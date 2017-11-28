package _String._Math;
import java.util.*;
import org.junit.Test;


//43. Multiply Strings
//  https://leetcode.com/problems/multiply-strings/
//  http://www.lintcode.com/zh-cn/problem/big-integer-multiplication/
//
//以字符串的形式给定两个非负整数 num1 和 num2，返回 num1 和 num2 的乘积。


public class _043_String_Multiply_Strings_M {
//------------------------------------------------------------------------------

//------------------------------------------------------------------------------



//------------------------------------------------------------------------------
//9Ch
public class Jiuzhang {
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return null;
        }

        int len1 = num1.length(), len2 = num2.length();
        int len3 = len1 + len2;
        int i, j, product, carry;

        int[] num3 = new int[len3];
        for (i = len1 - 1; i >= 0; i--) {
            carry = 0;
            for (j = len2 - 1; j >= 0; j--) {
                product = carry + num3[i + j + 1] +
                        Character.getNumericValue(num1.charAt(i)) *
                                Character.getNumericValue(num2.charAt(j));
                num3[i + j + 1] = product % 10;
                carry = product / 10;
            }
            num3[i + j + 1] = carry;
        }

        StringBuilder sb = new StringBuilder();
        i = 0;

        while (i < len3 - 1 && num3[i] == 0) {
            i++;
        }

        while (i < len3) {
            sb.append(num3[i++]);
        }

        return sb.toString();
    }
}


//------------------------------------------------------------------------------
}
/*
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.

Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
Seen this question in a real interview before?   Yes  No
Companies
Facebook Twitter
Related Topics
Math String
Similar Questions
Add Two Numbers Plus One Add Binary Add Strings
 */


/*
656. 大整数乘法

 描述
 笔记
 数据
 评测
以字符串的形式给定两个非负整数 num1 和 num2，返回 num1 和 num2 的乘积。

您在真实的面试中是否遇到过这个题？ Yes
样例
num1 和 num2 的长度都小于110。
num1 和 num2 都只包含数字 0 - 9。
num1 和 num2 都不包含任意前导零。
您不能使用任何内置的BigInteger库内方法或直接将输入转换为整数。
标签
数学 字符串处理 脸书 推特
相关题目
困难 阶乘 16 %
中等 链表求和 II 24 %
容易 大整数加法 26 %
容易 链表求和
 */