package _String._Reverse;
import java.util.*;
import org.junit.Test;

//  151. Reverse Words in a String
//  https://leetcode.com/problems/reverse-words-in-a-string/description/
//  http://www.lintcode.com/problem/reverse-words-in-a-string/
//  给定一个字符串，逐个翻转字符串中的每个单词。
//  Given s = "the sky is blue",return "blue is sky the".
//  6:6 自己写的。
//
public class _151_String_Reverse_Words_in_a_String_M {
//------------------------------------------------------------------------------
    //6
    //mine based on 9Ch
    //用trim().split(" +")去掉两边的所有空格，而且把中间的连续空格当做一个分隔符
    //倒着处理array生成sb
    //sb去掉最后一个多加的空格，生成String返回
    public class Jiuzhang2 {
        public String reverseWords(String s) {
            String[] array = s.trim().split(" +");//"\\s+"
            StringBuilder sb = new StringBuilder();

            for (int i = array.length - 1; i >= 0; --i) {
                sb.append(array[i]).append(' ');
            }

            return sb.substring(0, sb.length() - 1);
        }
    }
//------------------------------------------------------------------------------
    //1
    //Clean Java two-pointers solution (no trim( ), no split( ), no StringBuilder)
    public class Solution1 {

        public String reverseWords(String s) {
            if (s == null) return null;

            char[] a = s.toCharArray();
            int n = a.length;

            // step 1. reverse the whole string
            reverse(a, 0, n - 1);
            // step 2. reverse each word
            reverseWords(a, n);
            // step 3. clean up spaces
            return cleanSpaces(a, n);
        }

        void reverseWords(char[] a, int n) {
            int i = 0, j = 0;

            while (i < n) {
                while (i < j || i < n && a[i] == ' ') i++; // skip spaces
                while (j < i || j < n && a[j] != ' ') j++; // skip non spaces
                reverse(a, i, j - 1);                      // reverse the word
            }
        }

        // trim leading, trailing and multiple spaces
        String cleanSpaces(char[] a, int n) {
            int i = 0, j = 0;

            while (j < n) {
                while (j < n && a[j] == ' ') j++;             // skip spaces
                while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces
                while (j < n && a[j] == ' ') j++;             // skip spaces
                if (j < n) a[i++] = ' ';                      // keep only one space
            }

            return new String(a).substring(0, i);
        }

        // reverse a[] from a[i] to a[j]
        private void reverse(char[] a, int i, int j) {
            while (i < j) {
                char t = a[i];
                a[i++] = a[j];
                a[j--] = t;
            }
        }

    }
//------------------------------------------------------------------------------
    //2
    //My accepted Java solution
    //Given s = "the sky is blue",return "blue is sky the".
    //其实是利用了正则表达式将string转为string[]，然后倒过来
    public class Solution2 {
        public String reverseWords(String s) {
            String[] parts = s.trim().split("\\s+");
            String out = "";
            for (int i = parts.length - 1; i > 0; i--) {
                out += parts[i] + " ";
            }
            return out + parts[0];
        }
    }
/*
I'm splitting on the regex for one-or-more whitespace, this takes care of multiple spaces/tabs/newlines/etc in the input. Since the input could have leading/trailing whitespace, which would result in empty matches, I first trim the input string.

Now there could be three possibilities:

The input is empty: "", parts will contain [""]. The for loop is skipped and "" + "" is returned.
The input contains only one part: "a", parts will contain ["a"]. The for loop is skipped and "" + "a" is returned.
The input contains multiple parts: "a b c", reverse the order of all but the first part: "c b " in the for loop and return "c b " + "a".
Obviously this is not the fastest or most memory efficient way to solve the problem, but optimizations should only be done when they are needed. Readable code is usually more important than efficient code.

How to make it efficient?

Use a StringBuilder to concatenate the string parts, instead of concatenating strings directly. This will (I assume) build something like a linked-list of string parts, and only allocate the new string when you need it, instead of on each concatenation.
Iterate over the string, instead of using trim/split. Store the index of the last character in the word, when you find the first character, copy the substring to the output string.
Instead of using substring, insert the word-characters directly in the StringBuilder. Assuming they're using a linked-list or tree, this could be a whole last faster.
 */

//------------------------------------------------------------------------------
    //3
    //  Java 3-line builtin solution
    //这解的关键是熟悉Collections和String的操作
    class Solution3{
        public String reverseWords(String s) {
            String[] words = s.trim().split(" +");
            Collections.reverse(Arrays.asList(words));
            return String.join(" ", words);
        }
    }


//------------------------------------------------------------------------------
    //4
/*
Here is my concise and fast code that beats 73% of Java submissions.

I scan from the end to make the concatenation logic clear and use StringBuilder to make the String concatenation faster. I also use trim when returning the results to avoid boundary checking.

Hope it helps.*/
    //这也算是双指针吧
    class Solution4 {
        public String reverseWords(String s) {
            StringBuilder result = new StringBuilder();
            for (int start = s.length() - 1; start >= 0; start--) {
                if (s.charAt(start) == ' ') continue;
                int end = start;
                while (start >= 0 && s.charAt(start) != ' ') start--;
                result.append(s.substring(start + 1, end + 1)).append(" ");
            }
            return result.toString().trim();
        }
    }
//------------------------------------------------------------------------------
    //5
    //9CH
    //这个和2类似，就是用了StringBuilder提高性能
    public class Jiuzhang {
        public String reverseWords(String s) {
            if (s == null || s.length() == 0) {
                return "";
            }

            String[] array = s.split(" ");
            StringBuilder sb = new StringBuilder();

            for (int i = array.length - 1; i >= 0; --i) {
                if (!array[i].equals("")) {
                    sb.append(array[i]).append(' ');
                }
            }

            return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
        }
    }

//------------------------------------------------------------------------------
}
/*
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",return "blue is sky the".


Update (2015-02-12):
For C programmers: Try to solve it in-place in O(1) space.

click to show clarification.

Clarification:
What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.
Seen this question in a real interview before?   Yes  No
Companies
Microsoft Bloomberg Apple Snapchat Yelp

Related Topics
String

Similar Questions
Reverse Words in a String II
 */