package _08_Bit;
import java.util.*;
import org.junit.Test;

//  389. Find the Difference
//  https://leetcode.com/problems/find-the-difference/description/
//  9:
public class _389_Bit_Find_the_Difference_E {
//------------------------------------------------------------------------------
    //1
    //Java solution using bit manipulation
    public char findTheDifference1(String s, String t) {
        char c = 0;
        for (int i = 0; i < s.length(); ++i) {
            c ^= s.charAt(i);
        }
        for (int i = 0; i < t.length(); ++i) {
            c ^= t.charAt(i);
        }
        return c;
    }
//------------------------------------------------------------------------------
    //2
    //    maybe a more elegant version:
    public char findTheDifference2(String s, String t) {
        int n = t.length();
        char c = t.charAt(n - 1);
        for (int i = 0; i < n - 1; ++i) {
            c ^= s.charAt(i);
            c ^= t.charAt(i);
        }
        return c;
    }
//------------------------------------------------------------------------------
    //3
//    @acheiver If you use c = c ^ s.chatAt(i) instead of c ^= s.charAt(i),you have to code like:
//            (because use " ^= " Java code can change type automatically)

    public char findTheDifference3(String s, String t) {
        int c = 0;
        for (int i = 0; i < s.length(); ++i) {
            c = c ^ s.charAt(i);
        }
        for (int i = 0; i < t.length(); ++i) {
            c = c ^ t.charAt(i);
        }
        return (char)c;
    }
//------------------------------------------------------------------------------
    //4
//    @lujingyang1029 If you not use " ^= ",you can code like this:

    public char findTheDifference4(String s, String t) {
        int c = 0;
        for (int i = 0; i < s.length(); ++i) {
            c = c ^ s.charAt(i);
        }
        for (int i = 0; i < t.length(); ++i) {
            c = c ^ t.charAt(i);
        }
        return (char)c;
    }
//------------------------------------------------------------------------------
    //5
    //Simple JAVA 8ms solution, 4 lines
    public class Solution5 {
        public char findTheDifference(String s, String t) {
            // Initialize variables to store sum of ASCII codes for
            // each string
            int charCodeS = 0, charCodeT = 0;
            // Iterate through both strings and char codes
            for (int i = 0; i < s.length(); ++i) charCodeS += (int)s.charAt(i);
            for (int i = 0; i < t.length(); ++i) charCodeT += (int)t.charAt(i);
            // Return the difference between 2 strings as char
            return (char)(charCodeT - charCodeS);
        }
    }
//------------------------------------------------------------------------------
    //6
/*    UPDATE:
    Thanks to @zzhai for providing this optimization! :)
            "1 optimization: As t.length() is just 1 character longer than s.length(), we can use 1 pass to process both strings (20% better runtime performance)."*/
    public char findTheDifference6(String s, String t) {
        int charCode = t.charAt(s.length());
        // Iterate through both strings and char codes
        for (int i = 0; i < s.length(); ++i) {
            charCode -= (int)s.charAt(i);
            charCode += (int)t.charAt(i);
        }
        return (char)charCode;
    }

//------------------------------------------------------------------------------
    //7
   /* The idea is so clever.
1 optimization: As t.length() is just 1 character longer than s.length(), we can use 1 pass to process both strings (20% better runtime performance).*/

    public char findTheDifference7(String s, String t) {
        int charCode = t.charAt(s.length());
        // Iterate through both strings and char codes
        for (int i = 0; i < s.length(); ++i) {
            charCode -= (int)s.charAt(i);
            charCode += (int)t.charAt(i);
        }
        return (char)charCode;
    }

//------------------------------------------------------------------------------
    //8
//    my 7ms solution with similar thought,but much longer :-(

    public static char findTheDifference8(String s,String t){
        byte []table=new byte[26];//only contains lowercase letters
        int length=s.length();//use the shorter one as length
        int i;
        for(i=0;i!=length;i++){
            table[s.charAt(i)-'a']++;
            table[t.charAt(i)-'a']--;
        }

        while(i<t.length()){
            table[t.charAt(i)-'a']--;
            i++;
        }
        for(int j=0;j!=26;j++){//if array contains a element!=0,then we found the added letter
            if(table[j]!=0){
                return (char) ('a'+j);
            }
        }
        return ' ';
    }
//------------------------------------------------------------------------------
    //9
//Java solution with 6ms
//It seams turning String into Char Array is quicker.

    public class Solution9 {
        public char findTheDifference(String s, String t) {
            char[] sArray = s.toCharArray();
            char[] tArray = t.toCharArray();
            char t1 = 0;
            for(char c1:sArray)
                t1^=c1;
            for(char c2:tArray)
                t1^=c2;
            return(char)t1;
        }
    }


//------------------------------------------------------------------------------
}
/*
Given two strings s and t which consist of only lowercase letters.

String t is generated by random shuffling string s and then add one more letter at a random position.

Find the letter that was added in t.

Example:

Input:
s = "abcd"
t = "abcde"

Output:
e

Explanation:
'e' is the letter that was added.
 */
