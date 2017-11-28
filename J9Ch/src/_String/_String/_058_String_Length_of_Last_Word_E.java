package _String._String;
import java.util.*;
import org.junit.Test;

//  58. Length of Last Word
//  https://leetcode.com/problems/length-of-last-word/description/
//  http://www.lintcode.com/zh-cn/problem/length-of-last-word/
//
//给定一个字符串， 包含大小写字母、空格' '，请返回其最后一个单词的长度。
// 如果不存在最后一个单词，请返回 0
//
//  3:
//
public class _058_String_Length_of_Last_Word_E {
//------------------------------------------------------------------------------
    //1
    //A single line of Code in Java
    public int lengthOfLastWord(String s) {

        return s.trim().length()-s.trim().lastIndexOf(" ")-1;
    }
//------------------------------------------------------------------------------
    //2
    //My 3 line 0 ms java solution
    public int lengthOfLastWord2(String s) {
        s = s.trim();
        int lastIndex = s.lastIndexOf(' ') + 1;
        return s.length() - lastIndex;
    }


//------------------------------------------------------------------------------
    //3
    //9CH
    public class Jiuzhang {
        public int lengthOfLastWord(String s) {
            int length = 0;
            char[] chars = s.toCharArray();
            for (int i = s.length() - 1; i >= 0; i--) {
                if (length == 0) {
                    if (chars[i] == ' ') {
                        continue;
                    } else {
                        length++;
                    }
                } else {
                    if (chars[i] == ' ') {
                        break;
                    } else {
                        length++;
                    }
                }
            }

            return length;
        }
    }

//------------------------------------------------------------------------------
}
/*
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

Example:

Input: "Hello World"
Output: 5
Seen this question in a real interview before?   Yes  No
Related Topics
String
------------------------------------------------------------------------------
*/

/*
422. 最后一个单词的长度

给定一个字符串， 包含大小写字母、空格' '，请返回其最后一个单词的长度。

如果不存在最后一个单词，请返回 0 。

 注意事项

一个单词的界定是，由字母组成，但不包含任何的空格。

您在真实的面试中是否遇到过这个题？ Yes
样例
给定 s = "Hello World"，返回 5。

------------------------------------------------------------------------------
 */