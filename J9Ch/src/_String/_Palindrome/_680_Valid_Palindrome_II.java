package _String._Palindrome;

//  680. Valid Palindrome II
//  https://leetcode.com/problems/valid-palindrome-ii/description/
//  125. Valid Palindrome
//  String
//  125. Valid Palindrome - Two Pointers String
//
//
public class _680_Valid_Palindrome_II {
//-----------------------------------------------------------------------------
    //1
    //Accepted pretty Java solution(271ms)
    public class Solution1 {
        public boolean isPalindrome(String s) {
            if (s.isEmpty()) {
                return true;
            }
            int head = 0, tail = s.length() - 1;
            char cHead, cTail;
            while(head <= tail) {
                cHead = s.charAt(head);
                cTail = s.charAt(tail);
                if (!Character.isLetterOrDigit(cHead)) {
                    head++;
                } else if(!Character.isLetterOrDigit(cTail)) {
                    tail--;
                } else {
                    if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
                        return false;
                    }
                    head++;
                    tail--;
                }
            }

            return true;
        }
    }
//-----------------------------------------------------------------------------
}
/*
Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
-----------------------------------------------------------------------------
Example 1: Input: "aba"  Output: True
-----------------------------------------------------------------------------
Example 2:  Input: "abca"  Output: True
Explanation: You could delete the character 'c'.
-----------------------------------------------------------------------------
Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.

String
Valid Palindrome
 */