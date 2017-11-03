package _TwoPointer.TwoPointer_J;

/** 415 Valid Palindrome
 * Created by tianhuizhu on 6/28/17.
 */
public class _415_Valid_Palindrome {
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
