package _String._String;
import java.util.*;
import org.junit.Test;

//  459. Repeated Substring Pattern
//  https://leetcode.com/problems/repeated-substring-pattern/description/
//  http://www.lintcode.com/problem/repeated-substring-pattern/
//
//  Implement strStr()
//  Repeated String Match
//  4:
//
//
public class _459_String_Repeated_Substring_Pattern_E {
//------------------------------------------------------------------------------
    //1
    //Java Simple Solution with Explanation
    public boolean repeatedSubstringPattern(String str) {
        int l = str.length();
        for(int i=l/2;i>=1;i--) {
            if(l%i==0) {
                int m = l/i;
                String subS = str.substring(0,i);
                StringBuilder sb = new StringBuilder();
                for(int j=0;j<m;j++) {
                    sb.append(subS);
                }
                if(sb.toString().equals(str)) return true;
            }
        }
        return false;
    }
/*    The length of the repeating substring must be a divisor of the length of the input string
    Search for all possible divisor of str.length, starting for length/2
    If i is a divisor of length, repeat the substring from 0 to i the number of times i is contained in s.length
    If the repeated substring is equals to the input str return true*/
//------------------------------------------------------------------------------
    //2
    //Java & O(n)
    public boolean repeatedSubstringPattern2(String str) {
        //This is the kmp issue
        int[] prefix = kmp(str);
        int len = prefix[str.length()-1];
        int n = str.length();
        return (len > 0 && n%(n-len) == 0);
    }
    private int[] kmp(String s){
        int len = s.length();
        int[] res = new int[len];
        char[] ch = s.toCharArray();
        int i = 0, j = 1;
        res[0] = 0;
        while(i < ch.length && j < ch.length){
            if(ch[j] == ch[i]){
                res[j] = i+1;
                i++;
                j++;
            }else{
                if(i == 0){
                    res[j] = 0;
                    j++;
                }else{
                    i = res[i-1];
                }
            }
        }
        return res;
    }


//------------------------------------------------------------------------------
    //3
    //Simple Java solution, 2 lines
    public boolean repeatedSubstringPattern3(String str) {
        String s = str + str;
        return s.substring(1, s.length() - 1).contains(str);
    }

//------------------------------------------------------------------------------
    //4
    //9Ch
    public class Jiuzhang {
        public boolean repeatedSubstringPattern(String s) {
            int l = s.length();
            int[] next = new int[l];
            next[0] = -1;
            int i, j = -1;
            for (i = 1; i < l; i++) {
                while (j >= 0 && s.charAt(i) != s.charAt(j + 1)) {
                    j = next[j];
                }
                if (s.charAt(i) == s.charAt(j + 1)) {
                    j++;
                }
                next[i] = j;
            }
            int lenSub = l - 1 - next[l - 1];
            return lenSub != l && l % lenSub ==0;
        }
    }


//------------------------------------------------------------------------------
}
/*
Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

Example 1:
Input: "abab"

Output: True

Explanation: It's the substring "ab" twice.
Example 2:
Input: "aba"

Output: False
Example 3:
Input: "abcabcabcabc"

Output: True

Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
Seen this question in a real interview before?   Yes  No

Companies
Google Amazon

Related Topics
String

Similar Questions
Implement strStr()
Repeated String Match
 */