package _String._Abbreviation;
import java.util.*;
import org.junit.Test;

//  408. Valid Word Abbreviation
//  https://leetcode.com/problems/valid-word-abbreviation/description/
//  http://www.lintcode.com/zh-cn/problem/check-word-abbreviation/
//
//
public class _408_String_Valid_Word_Abbreviation_E {
//------------------------------------------------------------------------------
    //Short and easy to understand Java Solution
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;
        while (i < word.length() && j < abbr.length()) {
            if (word.charAt(i) == abbr.charAt(j)) {
                ++i;++j;
                continue;
            }
            if (abbr.charAt(j) <= '0' || abbr.charAt(j) > '9') {
                return false;
            }
            int start = j;
            while (j < abbr.length() && abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
                ++j;
            }
            int num = Integer.valueOf(abbr.substring(start, j));
            i += num;
        }
        return i == word.length() && j == abbr.length();
    }

//------------------------------------------------------------------------------



//------------------------------------------------------------------------------
    //9Ch
    public class Jiuzhang {
        public boolean validWordAbbreviation(String word, String abbr) {
            int i = 0, j = 0;
            while (i < word.length() && j < abbr.length()) {
                if (word.charAt(i) == abbr.charAt(j)) {
                    i++;
                    j++;
                } else if ((abbr.charAt(j) > '0') && (abbr.charAt(j) <= '9')) {     //notice that 0 cannot be included
                    int start = j;
                    while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                        j++;
                    }
                    i += Integer.valueOf(abbr.substring(start, j));
                } else {
                    return false;
                }
            }
            return (i == word.length()) && (j == abbr.length());
        }
    }

//------------------------------------------------------------------------------
}
/*
e letters and abbr contains only lowercase letters and digits.

Example 1:
Given s = "internationalization", abbr = "i12iz4n":

Return true.
Example 2:
Given s = "apple", abbr = "a2e":

Return false.
Seen this question in a real interview before?   Yes  No
Companies
Google
Related Topics
String
Similar Questions
Minimum Unique Word Abbreviation Word Abbreviation
 */

/*
给定一个非空字符串 word 和缩写 abbr，返回字符串是否可以和给定的缩写匹配。
比如一个 “word” 的字符串仅包含以下有效缩写：

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 */

/*
637. 检查缩写字

给定一个非空字符串 word 和缩写 abbr，返回字符串是否可以和给定的缩写匹配。
比如一个 “word” 的字符串仅包含以下有效缩写：

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 注意事项

注意只有以上缩写是字符串 word 合法的缩写。任何其他关于 word 的缩写都是不合法的。

您在真实的面试中是否遇到过这个题？ Yes
样例
样例 1:

给出 s = "internationalization", abbr = "i12iz4n":
返回 true。
样例 2:

给出 s = "apple", abbr = "a2e":
返回 false。
标签
字符串处理 谷歌
相关题目
困难 单词缩写
 */