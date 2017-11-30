package _String._Abbreviation;
import java.util.*;
import org.junit.Test;
@SuppressWarnings("ALL")

//  408. Valid Word Abbreviation
//  https://leetcode.com/problems/valid-word-abbreviation/description/
//  http://www.lintcode.com/zh-cn/problem/check-word-abbreviation/
//
//  5:  1 双前向指针，看我重写的最好。2 regx, 要熟练掌握正则表达式才行。
public class _408_Valid_Word_Abbreviation_E {
//------------------------------------------------------------------------------
    //1
    //Short and easy to understand Java Solution
    //双前向指针
    //三种情况：
    // 1，word.charAt(i) == abbr.charAt(j) word和abbr对应字母一样, continue.
    // 2, abbr.charAt(j) 是0或者是letter， return false.
    // 3，abbr.charAt(j)是数字，移动j直到数字结束。
    // 最后看i是否走到word末尾，j是否走到abbr末尾。

    //我根据1重写的
    public boolean validWordAbbreviation_mine(String word, String abbr) {
        int i = 0, j = 0;
        while (i < word.length() && j < abbr.length()) {
            //case 1
            if (word.charAt(i) == abbr.charAt(j)) {
                ++i;++j;
                continue;
            }
            //case 2
            if (abbr.charAt(j) == '0' || Character.isLetter(abbr.charAt(j))) return false;

            //case 3
            int start = j;
            while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) j++;
            int num = Integer.valueOf(abbr.substring(start, j));
            i += num;
        }
        return i == word.length() && j == abbr.length();
    }
//------------------------------------------------------------------------------

    public boolean validWordAbbreviation1(String word, String abbr) {
        int i = 0, j = 0;
        while (i < word.length() && j < abbr.length()) {
            //case 1
            if (word.charAt(i) == abbr.charAt(j)) {
                ++i;++j;
                continue;
            }
            //case 2
            if (abbr.charAt(j) <= '0' || abbr.charAt(j) > '9') return false;

            //case 3
            int start = j;
            while (j < abbr.length() && abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') ++j;

            int num = Integer.valueOf(abbr.substring(start, j));
            i += num;
        }
        return i == word.length() && j == abbr.length();
    }


    @Test
    public void test01(){
        System.out.println(validWordAbbreviation1("a", "01"));//false
    }

    @Test
    public void test02(){
        for (int i = 0; i < 256; i++) {
            System.out.print(i + ":" + (char)i + "; ");
        }
    }
    @Test
    public void test03(){
        System.out.println((int)'0');//48
        System.out.println((int)'9');//57
        System.out.println((int)'a');//97
        System.out.println((int)'A');//65
    }
//------------------------------------------------------------------------------
    private void p(String s){
        System.out.println(s);
    }
//------------------------------------------------------------------------------
    //2
    //Simple Regex One-liner (Java, Python)
    //Much nicer, I just turn an abbreviation like "i12iz4n" into a regular expression like "i.{12}iz.{4}n". Duh.
    /*
    I turn each number into that many dots to get a regular expression. For example, when asked whether "3t2de" is a valid abbreviation for word "leetcode", I turn "3t2de" into "...t..de" and check whether that regular expression matches "leetcode", which it does. I also need to rule out the number "0" and leading zeros, which I do with another regular expression.
     */
    public boolean validWordAbbreviation2(String word, String abbr) {
        return word.matches(abbr.replaceAll("[1-9]\\d*", ".{$0}"));
    }


//-------------------------------------------------------------------------
    //3
    //Java straightforward, easy understand solution.
    public boolean validWordAbbreviation3(String word, String abbr) {
        if(word == null || abbr == null) return false;
        int num = 0;
        int idx = 0;

        for(char c: abbr.toCharArray()){
            if(c == '0' && num == 0) return false;
            if(c >= '0' && c <= '9'){
                num = num*10 + (c-'0');
            }else{
                idx += num;
                if(idx >= word.length() || c != word.charAt(idx)) return false;
                num = 0;
                idx++;
            }
        }

        return idx+num == word.length() ? true : false;
    }

//-------------------------------------------------------------------------
    //4
    //Simple Java Solution
    public boolean validWordAbbreviation4(String word, String abbr) {
        int lengthOfWord = word.length();
        int count = 0;
        int index = 0;
        while(index<abbr.length()){
            int digit = 0;
            while(index<abbr.length() && Character.isDigit(abbr.charAt(index))){
                if(abbr.charAt(index)=='0' && digit==0) return false;
                else{
                    digit = digit*10 + Integer.parseInt(abbr.charAt(index)+"");
                    index++;
                }
            }
            count+=digit;
            if(index<abbr.length() && Character.isLetter(abbr.charAt(index))){
                if(count>=word.length()) return false;
                if(abbr.charAt(index)!=word.charAt(count)) return false;
                else{
                    count++;
                    index++;
                }
            }
        }
        return lengthOfWord==count;
    }


//------------------------------------------------------------------------------
    //5
    //9Ch
    public class Jiuzhang {
        public boolean validWordAbbreviation(String word, String abbr) {
            int i = 0, j = 0;
            while (i < word.length() && j < abbr.length()) {
                if (word.charAt(i) == abbr.charAt(j)) {
                    i++;
                    j++;
                }
                //notice that 0 cannot be included
                else if ((abbr.charAt(j) > '0') && (abbr.charAt(j) <= '9')) {
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
_411_BackTracking_Minimum_Unique_Word_Abbreviation_M
_527_Word_Abbreviation_H
//------------------------------------------------------------------------------
 */

/*
给定一个非空字符串 word 和缩写 abbr，返回字符串是否可以和给定的缩写匹配。
比如一个 “word” 的字符串仅包含以下有效缩写：

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
//------------------------------------------------------------------------------
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
困难 单词缩//------------------------------------------------------------------------------
 */