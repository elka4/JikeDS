package _TwoPointer._TwoPointer;
import java.util.*;
import org.junit.Test;

//  125. Valid Palindrome
//  https://leetcode.com/problems/valid-palindrome/description/
//  http://www.lintcode.com/zh-cn/problem/valid-palindrome/
public class _125_TwoPointer_Valid_Palindrome_E {

    //Accepted pretty Java solution(271ms)
    public class Solution {
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

//    Thanks for sharing! But I think it could be more concise.

    public boolean isPalindrome(String s) {
        char[] c = s.toCharArray();
        for (int i = 0, j = c.length - 1; i < j; ) {
            if (!Character.isLetterOrDigit(c[i])) i++;
            else if (!Character.isLetterOrDigit(c[j])) j--;
            else if (Character.toLowerCase(c[i++]) != Character.toLowerCase(c[j--]))
                return false;
        }
        return true;
    }


//    Could be a little bit faster:

    public class Solution3 {
        public boolean isPalindrome(String s) {
            s = s.trim().toLowerCase();
            if(s.length() == 0) return true;
            int p1 = 0;
            int p2 = s.length()-1;

            while(p1 < p2){
                while(!alphanumeric(s.charAt(p1)) && p1 < s.length() - 1) p1++;
                while(!alphanumeric(s.charAt(p2)) && p2 >0) p2--;
                if(p1 >= p2) return true;
                if(s.charAt(p1) == s.charAt(p2)){
                    p1++; p2--;
                }else return false;
            }
            return true;
        }

        private boolean alphanumeric(char c){
            if( c - '0' >= 0 && c - '0' <= 9 ) return true;
            if( c - 'a' >= 0 && c - 'z' <= 0 ) return true;
            return false;
        }
    }


/*    Taking inspiration from your answer I also coded the solution.
    You can actually get rid of extra space in terms of char head; char tail;
    Have a look at my solution. It looks cleaner and smaller. Easy to Understand.*/

    public class Solution4 {
        public boolean isPalindrome(String s) {
            if(s==null || s.trim().length()==0)	return true;
            int i=0, j=s.length()-1;

            while(i<=j){
                while(i<j && !Character.isLetterOrDigit(s.charAt(i)))	i++;
                while(i<j && !Character.isLetterOrDigit(s.charAt(j)))	j--;
                if( Character.toLowerCase(s.charAt(i))!=Character.toLowerCase(s.charAt(j)) )	return false;
                i++; j--;
            }
            return true;
        }
    }

    public class Solution5 {
        public boolean isPalindrome(String s) {
            s = s.toLowerCase();
            int l = 0, r = s.length()-1;
            while(l < r)
                if((s.charAt(l) > 'z' || s.charAt(l) < 'a') && (s.charAt(l) > '9' || s.charAt(l) < '0')) l++;
                else if((s.charAt(r) > 'z' || s.charAt(r) < 'a') && (s.charAt(r) > '9' || s.charAt(r) < '0')) r--;
                else if(s.charAt(l++) != s.charAt(r--)) return false;
            return true;
        }
    }

    boolean isPalindrome6(String s) {
        s = s.toLowerCase().replaceAll("\\W+", "");
        return s.equals(new StringBuilder(s).reverse().toString());
    }

//-------------------------------------------------------------------------------

    //My three line java solution
    public class Solution7 {
        public boolean isPalindrome(String s) {
            String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
            String rev = new StringBuffer(actual).reverse().toString();
            return actual.equals(rev);
        }
    }

//    Used the same approach, but instead of reversing and comparing, I checked characters from both ends using one pointer.
//
//            Also, converting to lower case in not always necessary, For example : "Coding rocks"

    public static boolean isPalindrome8(String s) {
        String regex = "([^A-Za-z0-9])";
        String replacement = "";
        s = s.replaceAll(regex, replacement);
        for(int i =0;i<s.length()/2;i++){
            if(Character.toLowerCase(s.charAt(i))==Character.toLowerCase(s.charAt(s.length()-1-i)))
                continue;
            else
                return false;
        }
        return true;
    }

    //3ms java solution(beat 100% of java solution)

    class Solution9{
        private final char[]charMap = new char[256];

        public boolean isPalindrome(String s) {

            for(int i=0;i<10;i++){
                charMap[i+'0'] = (char)(1+i);  // numeric
            }
            for(int i=0;i<26;i++){
                charMap[i+'a'] = charMap[i+'A'] = (char)(11+i);  //alphabetic, ignore cases
            }

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

    //Java 9ms solution with some of my thoughts
//    My thoughts on this problem is that the interviewers are not looking at a solution using Regex, it would be too trivial, plus it would take O(n) space to save the new trimmed string. I would just use plain two points to traverse the string and compare them ignore case. It is O(n) time and O(1) space guaranteed and no expensive string concatenation is required.

    public class Solution10 {
        public boolean isPalindrome(String s) {
            int start = 0;
            int end = s.length() - 1;
            while(start <= end) {
                while(start <= end && !Character.isLetterOrDigit(s.charAt(start))) {
                    start++;
                }
                while(start <= end && !Character.isLetterOrDigit(s.charAt(end))) {
                    end--;
                }
                if(start <= end && Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                    return false;
                }
                start++;
                end--;
            }
            return true;
        }
    }

//-------------------------------------------------------------------------------
    // 9Ch
public class Jiuzhang {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int front = 0;
        int end = s.length() - 1;
        while (front < end) {
            while (front < s.length() && !isvalid(s.charAt(front))){ // nead to check range of a/b
                front++;
            }

            if (front == s.length()) { // for emtpy string “.,,,”
                return true;
            }

            while (end >= 0 && ! isvalid(s.charAt(end))) { // same here, need to check border of a,b
                end--;
            }

            if (Character.toLowerCase(s.charAt(front)) != Character.toLowerCase(s.charAt(end))) {
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

}
/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
 */

/*
给定一个字符串，判断其是否为一个回文串。只包含字母和数字，忽略大小写。

 注意事项

你是否考虑过，字符串有可能是空字符串？这是面试过程中，面试官常常会问的问题。

在这个题目中，我们将空字符串判定为有效回文。

样例
"A man, a plan, a canal: Panama" 是一个回文。

"race a car" 不是一个回文。


 */