package _String._Reverse;
import java.util.*;
import org.junit.Test;

//  557. Reverse Words in a String III
//  https://leetcode.com/problems/reverse-words-in-a-string-iii/description/
//
//  String
//  _541_String_Reverse_String_II_E
//  6:1,
//  翻转每一个单词的字母
//  Input: "Let's take LeetCode contest"   Output: "s'teL ekat edoCteeL tsetnoc"
//  最好就是用StringBuilder的reverse方法对每个word进行翻转
public class _557_String_Reverse_Words_in_a_String_III_E {
//------------------------------------------------------------------------------
    //https://leetcode.com/articles/reverse-words-in-a-string/
//------------------------------------------------------------------------------
    //1
    //Approach #1 Simple Solution[Accepted]
    public class Solution1 {
        public String reverseWords(String s) {
            String words[] = s.split(" ");
            StringBuilder res = new StringBuilder();

            for (String word: words)
                res.append(new StringBuilder(word).reverse().toString() + " ");

            return res.toString().trim();
        }
    }



//------------------------------------------------------------------------------
    //2
    //Approach #2 Without using pre-defined split and reverse function [Accepted]
    public class Solution2 {
        public String reverseWords(String s) {
            String words[] = split(s);
            StringBuilder res=new StringBuilder();
            for (String word: words)
                res.append(reverse(word) + " ");
            return res.toString().trim();
        }
        public String[] split(String s) {
            ArrayList < String > words = new ArrayList < > ();
            StringBuilder word = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') {
                    words.add(word.toString());
                    word = new StringBuilder();
                } else
                    word.append( s.charAt(i));
            }
            words.add(word.toString());
            return words.toArray(new String[words.size()]);
        }
        public String reverse(String s) {
            StringBuilder res=new StringBuilder();
            for (int i = 0; i < s.length(); i++)
                res.insert(0,s.charAt(i));
            return res.toString();
        }
    }

//------------------------------------------------------------------------------
    //3
    //Approach #3 Using StringBuilder and reverse method [Accepted]
    public class Solution3 {
        public String reverseWords(String input) {
            final StringBuilder result = new StringBuilder();
            final StringBuilder word = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) != ' ') {
                    word.append(input.charAt(i));
                } else {
                    result.append(word.reverse());
                    result.append(" ");
                    word.setLength(0);
                }
            }
            result.append(word.reverse());
            return result.toString();
        }
    }


//------------------------------------------------------------------------------
    //4
    //short java code without explanation
    class Solution4{
        public String reverseWords(String s) {
            String[] str = s.split(" ");

            for (int i = 0; i < str.length; i++)
                str[i] = new StringBuilder(str[i]).reverse().toString();

            StringBuilder result = new StringBuilder();
            for (String st : str)
                result.append(st + " ");

            return result.toString().trim();
        }
    }

//------------------------------------------------------------------------------
    //5
    //Java Solution
    class Solution5{
        public String reverseWords(String s) {
            String[] strs = s.split(" ");
            StringBuffer sb = new StringBuffer();

            for(String str: strs){
                StringBuffer temp = new StringBuffer(str);
                sb.append(temp.reverse());
                sb.append(" ");
            }
            sb.setLength(sb.length()-1);
            return sb.toString();
        }
    }

//------------------------------------------------------------------------------
    //6
    //Java Solution, StringBuilder
    public class Solution6 {
        public String reverseWords(String s) {
            StringBuilder sb = new StringBuilder();

            int i = 0, j = 0;
            while (i < s.length()) {
                if (s.charAt(i) == ' ') {
                    sb.append(" ");
                    i++;
                }
                else {
                    j = i + 1;
                    while (j < s.length() && s.charAt(j) != ' ') j++;
                    sb.append((new StringBuilder(s.substring(i, j))).reverse());
                    i = j;
                }
            }

            return sb.toString();
        }
    }

//------------------------------------------------------------------------------
}
/*
Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"   Output: "s'teL ekat edoCteeL tsetnoc"

Note: In the string, each word is separated by single space and there will not be any extra space in the string.

Seen this question in a real interview before?   Yes  No
Companies
Zappos
Related Topics
String
Similar Questions
Reverse String II
 */