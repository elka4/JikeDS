package _TwoPointer.TwoPointer_J;

/** 415 Valid Palindrome
 * Created by tianhuizhu on 6/28/17.
 */
//
public class _415_Valid_Palindrome {
    /*
    对撞型双指针
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int front = 0;
        int end = s.length() - 1;

        while (front < end) {
            // nead to check range of a/b
            while (front < s.length() && !isvalid(s.charAt(front))){
                front++;
            }

            if (front == s.length()) { // for enmtpy string “.,,,”
                return true;
            }

            // same here, need to check border of a,b
            while (end >= 0 && ! isvalid(s.charAt(end))) {
                end--;
            }

            if (Character.toLowerCase(s.charAt(front)) !=
                Character.toLowerCase(s.charAt(end))) {
                break;
            } else {
                front++;
                end--;
            }
        }

        return end <= front;
    }

    private boolean isvalid (char c) {

        return Character.isLetter(c) || Character.isDigit(c);
    }

/////////////////////////////////////////////////////////////////////


}

/*
给定一个字符串，判断其是否为一个回文串。只包含字母和数字，忽略大小写。

 注意事项

你是否考虑过，字符串有可能是空字符串？这是面试过程中，面试官常常会问的问题。

在这个题目中，我们将空字符串判定为有效回文。

样例
"A man, a plan, a canal: Panama" 是一个回文。

"race a car" 不是一个回文。

挑战
O(n) 时间复杂度，且不占用额外空间。
 */

/*
Given a string, determine if it is a palindrome, considering only
alphanumeric characters and ignoring cases.

 Notice

Have you consider that the string might be empty? This is a good question
 to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.

Have you met this question in a real interview? Yes
Example
"A man, a plan, a canal: Panama" is a palindrome.

"race a car" is not a palindrome.
 */
