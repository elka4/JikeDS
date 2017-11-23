package HF.HF0_Intro;

import org.junit.Test;

// Check Word Abbreviation
//  http://www.lintcode.com/zh-cn/problem/check-word-abbreviation/


//  408. Valid Word Abbreviation
//  https://leetcode.com/problems/valid-word-abbreviation/description/
//  String
//  Minimum Unique Word Abbreviation
//  Word Abbreviation
public class _6G_CheckWordsAbbreviation_String {
//-------------------------------------------------------------------------
    //1
    /**
     * @param word a non-empty string
     * @param abbr an abbreviation
     * @return true if string matches with the given abbr or false
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        // Write your code here
        int number = 0;
        int i = 0, j = 0;
        while (i < word.length() && j < abbr.length()) {
            if (Character.isDigit(abbr.charAt(j))) {
                number = number * 10 + abbr.charAt(j) - '0';
                if (number == 0)
                    return false;
                j ++;
            } else {
                i += number;
                if (i >= word.length() ||
                        word.charAt(i) != abbr.charAt(j))
                    return false;
                number = 0;
                i ++;
                j ++;
            }
        }
        i += number;
        return i == word.length() && j == abbr.length();
    }


//-------------------------------------------------------------------------
    //2
    // version: 高频题班
    /**
     * @param word a non-empty string
     * @param abbr an abbreviation
     * @return true if string matches with the given abbr or false
     */
    public boolean validWordAbbreviation2(String word, String abbr) {
        // Write your code here
        int i = 0, j = 0;
        char[] s = word.toCharArray();
        char[] t = abbr.toCharArray();

        while (i < s.length && j < t.length) {
            if (Character.isDigit(t[j])) {
                if (t[j] == '0') {
                    return false;
                }
                int val = 0;
                while (j < t.length && Character.isDigit(t[j])) {
                    val = val * 10 + t[j] - '0';
                    j++;
                }
                i += val;
            } else {
                if (s[i++] != t[j++]) {
                    return false;
                }
            }
        }
        return i == s.length && j == t.length;
    }

    @Test
    public void test01(){

        System.out.println(validWordAbbreviation2("wqwrtyasdfga", "w10b"));
    }


//-------------------------------------------------------------------------




//-------------------------------------------------------------------------


}

/*
Given a non-empty string word and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 注意事项

Notice that only the above abbreviations are valid abbreviations of the string word. Any other string is not a valid abbreviation of word.


Example 1:

Given s = "internationalization", abbr = "i12iz4n":
Return true.
Example 2:

Given s = "apple", abbr = "a2e":
Return false.
 */

/*
637. 检查缩写字

 描述
 笔记
 数据
 评测
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