package _String._Math;
import java.util.*;
import org.junit.Test;

//  8. String to Integer (atoi)
//  https://leetcode.com/problems/string-to-integer-atoi/description/
//  http://www.lintcode.com/zh-cn/problem/string-to-integer-ii/
//
//  2:1
public class _008_String_String_to_Integer_atoi_E {
//------------------------------------------------------------------------------

//-----------------------------------------------------------------------
    //1
    //Java Solution with 4 steps explanations
    public int myAtoi(String str) {
        int index = 0, sign = 1, total = 0;
        //1. Empty string
        if(str.length() == 0) return 0;

        //2. Remove Spaces
        while(str.charAt(index) == ' ' && index < str.length())
            index ++;

        //3. Handle signs
        if(str.charAt(index) == '+' || str.charAt(index) == '-'){
            sign = str.charAt(index) == '+' ? 1 : -1;
            index ++;
        }

        //4. Convert number and avoid overflow
        while(index < str.length()){
            int digit = str.charAt(index) - '0';
            if(digit < 0 || digit > 9) break;

            //check if total will be overflow after 10 times and add digit
            if(Integer.MAX_VALUE/10 < total ||
                    Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE %10 < digit)
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            total = 10 * total + digit;
            index ++;
        }
        return total * sign;
    }
//-----------------------------------------------------------------------
    //2
    //9Ch
    public class Jiuzhang {
        public int myAtoi(String str) {
            // Start typing your Java solution below
            // DO NOT write main() function
            if(str == null) {
                return 0;
            }
            str = str.trim();
            if (str.length() == 0) {
                return 0;
            }

            int sign = 1;
            int index = 0;

            if (str.charAt(index) == '+') {
                index++;
            } else if (str.charAt(index) == '-') {
                sign = -1;
                index++;
            }
            long num = 0;
            for (; index < str.length(); index++) {
                if (str.charAt(index) < '0' || str.charAt(index) > '9')
                    break;
                num = num * 10 + (str.charAt(index) - '0');
                if (num > Integer.MAX_VALUE ) {
                    break;
                }
            }
            if (num * sign >= Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (num * sign <= Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            return (int)num * sign;
        }
    }
//-----------------------------------------------------------------------

}
/*
54. 转换字符串到整数

 描述
 笔记
 数据
 评测
实现atoi这个函数，将一个字符串转换为整数。如果没有合法的整数，返回0。如果整数超出了32位整数的范围，返回INT_MAX(2147483647)如果是正整数，或者INT_MIN(-2147483648)如果是负整数。

样例
"10" =>10

"-1" => -1

"123123123123123" => 2147483647

"1.0" => 1

标签
基本实现 字符串处理 优步
 */

/*
 ---------------------------------------------------------------------------------------------------
Add to List
8. String to Integer (atoi)
DescriptionHintsSubmissionsDiscussSolution
Discuss Pick One
Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.

spoilers alert... click to show requirements for atoi.

Requirements for atoi:
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
 */