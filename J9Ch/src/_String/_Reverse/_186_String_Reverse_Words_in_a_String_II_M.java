package _String._Reverse;
import java.util.*;
import org.junit.Test;

//  186. Reverse Words in a String II
//  https://leetcode.com/problems/reverse-words-in-a-string-ii/description/
//
//  _151_String_Reverse_Words_in_a_String_M
//  _189_Rotate_Array_E
//  4:1 关键还是要考虑清楚for循环的时候array的边界情况
//  和前一题的区别是，这题给的是char[]， in place的在char[]上操作
public class _186_String_Reverse_Words_in_a_String_II_M {
//------------------------------------------------------------------------------
    //1
    //My Java solution with explanation
    //Given s = "the sky is blue", return "blue is sky the".
    class Solution1{
        public void reverseWords(char[] s) {
            // Three step to reverse
            // 1, reverse the whole sentence
            reverse(s, 0, s.length - 1);//[eulb si yks eht]
            // 2, reverse each word
            int start = 0;
            int end = -1;
            for (int i = 0; i < s.length; i++) {//[blue is sky the]
                if (s[i] == ' ') {
                    reverse(s, start, i - 1);
                    start = i + 1;
                }
            }
            // 3, reverse the last word, if there is only one word this will solve the corner case
            reverse(s, start, s.length - 1);
        }

        public void reverse(char[] s, int start, int end) {
            while (start < end) {
                char temp = s[start];
                s[start] = s[end];
                s[end] = temp;
                start++;
                end--;
            }
        }
    }
//------------------------------------------------------------------------------
    //2
    //Java concise in-place solution.
    //Given s = "the sky is blue", return "blue is sky the".
    class Solution2{
        public void reverseWords(char[] s) {
            reverse(s, 0, s.length-1);  // reverse the whole string first
            int r = 0;
            while (r < s.length) {
                int l = r;
                while (r < s.length && s[r] != ' ')
                    r++;
                reverse(s, l, r-1);  // reverse words one by one
                r++;
            }
        }

        public void reverse(char[] s, int l, int r) {
            while (l < r) {
                char tmp = s[l];
                s[l++] = s[r];
                s[r--] = tmp;
            }
        }
    }

//------------------------------------------------------------------------------
    //3
    // JAVA-----------Easy Version To Understand!!!!!!!!!!!!!!
    //Given s = "the sky is blue", return "blue is sky the".
    class Solution3{
        public void reverseWords(char[] s) {
            int len = s.length, i, j;
            reverse(s, 0, len - 1);
            for (i = 0, j = 0; j < len; j++) {
                if (s[j] == ' ') {
                    reverse(s, i, j - 1);
                    i = j + 1;
                }
            }
            reverse(s, i, len - 1);
        }

        public void reverse(char[] s, int start, int end) {
            for (int i = start, j = end; i < j; i++, j--) {
                char tmp = s[i];
                s[i] = s[j];
                s[j] = tmp;
            }
        }
    }
//------------------------------------------------------------------------------
    //4
    // Reverse Twice
    //1.Reverse the whole sentence
    //2.Reverse each word
    //Given s = "the sky is blue", return "blue is sky the".
    class Solution4{
        public void reverseWords(char[] s) {
            int i=0;
            int j=s.length-1;
            // swap full sentence
            while(i<j) {
                swap(s, i, j);
                i++;
                j--;
            }
            i=0;j=0;
            int k=0;
            // swap word by word
            while(j<s.length) {
                while(j<s.length && s[j] != ' ') {
                    j++;
                }
                k=j-1;
                // swap the current word
                while(i<k) {
                    swap(s,i,k);
                    i++;
                    k--;
                }
                j++;
                i=j;
            }
        }

        private void swap(char[] s, int i, int j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }
    }
//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
}
/*
Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

The input string does not contain leading or trailing spaces and the words are always separated by a single space.

For example,
Given s = "the sky is blue", return "blue is sky the".

Could you do it in-place without allocating extra space?

Related problem: Rotate Array

Update (2017-10-16):
We have updated the function signature to accept a character array, so please reset to the default code definition by clicking on the reload button above the code editor. Also, Run Code is now available!

Seen this question in a real interview before?   Yes  No
Companies
Microsoft Amazon Uber

Related Topics
String

Similar Questions
Reverse Words in a String
Rotate Array
 */