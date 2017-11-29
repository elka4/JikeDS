package _String._Palindrome;
import java.util.*;
import org.junit.Test;

//  125. Valid Palindrome
//  https://leetcode.com/problems/valid-palindrome/description/
//  http://www.lintcode.com/problem/valid-palindrome/
//  Two Pointers String
//  给定一个字符串，判断其是否为一个回文串。只包含字母和数字，忽略大小写。
//  234 Palindrome Linked List - LinkedList
//  _680_Valid_Palindrome_II - String
//  6: 1
//  对撞型双指针
public class _125_String_Valid_Palindrome_E {
//------------------------------------------------------------------------------
    //1
    //Java 9ms solution with some of my thoughts
/*
My thoughts on this problem is that the interviewers are not looking at a solution using Regex, it would be too trivial, plus it would take O(n) space to save the new trimmed string. I would just use plain two points to traverse the string and compare them ignore case. It is O(n) time and O(1) space guaranteed and no expensive string concatenation is required.
*/
    // 4 个start <= end 都换成了 start < end
    public class Solution3 {
        public boolean isPalindrome(String s) {
            int start = 0;
            int end = s.length() - 1;
            while(start < end) {
                while(start < end && !Character.isLetterOrDigit(s.charAt(start))) {
                    start++;
                }
                while(start < end && !Character.isLetterOrDigit(s.charAt(end))) {
                    end--;
                }
                if(start < end && Character.toLowerCase(s.charAt(start)) !=
                        Character.toLowerCase(s.charAt(end))) {
                    return false;
                }
                start++;
                end--;
            }
            return true;
        }
    }

//------------------------------------------------------------------------------
    //2
    //Accepted pretty Java solution(271ms)
    //对撞型双指针
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
//------------------------------------------------------------------------------
    //3
    //My three line java solution
    //神奇的正则表达式和神奇的StringBuffer reverse()
    public class Solution2 {
        public boolean isPalindrome(String s) {
            String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
            String rev = new StringBuffer(actual).reverse().toString();
            return actual.equals(rev);
        }
    }

//------------------------------------------------------------------------------
    //4
    //This is my accepted Java code, just for reference only
    public class Solution4 {
        public boolean isPalindrome(String s) {
            s = s.toLowerCase();
            s = s.replaceAll("[^A-Z^a-z^0-9]+", "");
            StringBuffer sb = new StringBuffer(s);
            sb.reverse();
            String reverseString = sb.toString();
            boolean result = s.equals(reverseString);
            return result;
        }
    }

//------------------------------------------------------------------------------
    //5
    //3ms java solution(beat 100% of java solution)
    class Solution5{
    /*simply build an array that map all possible char into integer(if not alphanumeric,mark it as zero)
    this will help to speed up the process a lot.*/

        private  final char[]charMap = new char[256];

        void init(){
            for(int i=0;i<10;i++){
                charMap[i+'0'] = (char)(1+i);  // numeric
            }
            for(int i=0;i<26;i++){
                charMap[i+'a'] = charMap[i+'A'] = (char)(11+i);  //alphabetic, ignore cases
            }
        }
        public boolean isPalindrome(String s) {
            init();
            char[]pChars = s.toCharArray();
            int start = 0,end=pChars.length-1;
            char cS,cE;
            while(start<end){
                cS = charMap[pChars[start]];
                cE = charMap[pChars[end]];
                if(cS!=0 && cE!=0){
                    if(cS!=cE)return false;
                    start++;
                    end--;
                }else{
                    if(cS==0)start++;
                    if(cE==0)end--;
                }
            }
            return true;
        }
    }


//------------------------------------------------------------------------------
    //6
    //9CH
    public class Jiuzhang {
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
                // for emtpy string “.,,,”
                if (front == s.length()) {
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
    }

//------------------------------------------------------------------------------
}
/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.   "race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.

Seen this question in a real interview before?   Yes  No
Companies
Facebook Microsoft Uber Zenefits

Related Topics
Two Pointers String

Similar Questions
234 Palindrome Linked List - LinkedList
_680_Valid_Palindrome_II - String
*/