package _String._Math;
import java.util.*;
import org.junit.Test;

//  65. Valid Number
//  https://leetcode.com/problems/valid-number/description/
//  http://www.lintcode.com/zh-cn/problem/valid-number/
//
//  给定一个字符串，验证其是否为数字。
//  Math String
//  _008_String_String_to_Integer_atoi_E
//  3:
//
public class _065_String_Valid_Number_H {
//------------------------------------------------------------------------------
    //1
    /*Clear Java solution with ifs
    All we need is to have a couple of flags so we can process the string in linear time:*/

    public boolean isNumber(String s) {
        s = s.trim();

        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        boolean numberAfterE = true;
        for(int i=0; i<s.length(); i++) {
            if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
                numberAfterE = true;
            } else if(s.charAt(i) == '.') {
                if(eSeen || pointSeen) {
                    return false;
                }
                pointSeen = true;
            } else if(s.charAt(i) == 'e') {
                if(eSeen || !numberSeen) {
                    return false;
                }
                numberAfterE = false;
                eSeen = true;
            } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
                if(i != 0 && s.charAt(i-1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }
        }

        return numberSeen && numberAfterE;
    }
/*    We start with trimming.

    If we see [0-9] we reset the number flags.
    We can only see . if we didn't see e or ..
    We can only see e if we didn't see e but we did see a number. We reset numberAfterE flag.
    We can only see + and - in the beginning and after an e
    any other character break the validation.
    At the and it is only valid if there was at least 1 number and if we did see an e then a number after it as well.

    So basically the number should match this regular expression:

            [-+]?(([0-9]+(.[0-9]*)?)|.[0-9]+)(e[-+]?[0-9]+)?*/
//------------------------------------------------------------------------------



//------------------------------------------------------------------------------
    //2
    //9Ch
    // Non-regex version

    public class Solution {
        public boolean isNumber(String s) {
            int len = s.length();
            int i = 0, e = len - 1;
            while (i <= e && Character.isWhitespace(s.charAt(i))) i++;
            if (i > len - 1) return false;
            while (e >= i && Character.isWhitespace(s.charAt(e))) e--;
            // skip leading +/-
            if (s.charAt(i) == '+' || s.charAt(i) == '-') i++;
            boolean num = false; // is a digit
            boolean dot = false; // is a '.'
            boolean exp = false; // is a 'e'
            while (i <= e) {
                char c = s.charAt(i);
                if (Character.isDigit(c)) {
                    num = true;
                }
                else if (c == '.') {
                    if(exp || dot) return false;
                    dot = true;
                }
                else if (c == 'e') {
                    if(exp || num == false) return false;
                    exp = true;
                    num = false;
                }
                else if (c == '+' || c == '-') {
                    if (s.charAt(i - 1) != 'e') return false;
                }
                else {
                    return false;
                }
                i++;
            }
            return num;
        }
    }



//------------------------------------------------------------------------------
}
/*
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.

Seen this question in a real interview before?   Yes  No

Companies
LinkedIn

Related Topics
Math String

Similar Questions
String to Integer (atoi)
------------------------------------------------------------------------------
 */



/*
417. 有效数字

 描述
 笔记
 数据
 评测
给定一个字符串，验证其是否为数字。

您在真实的面试中是否遇到过这个题？ Yes
样例
"0" => true

" 0.1 " => true

"abc" => false

"1 a" => false

"2e10" => true

标签
领英 字符串处理
------------------------------------------------------------------------------
 */