package Bit_Manipulation_26;
import java.util.*;

public class a_389_Find_the_Difference {
    public char findTheDifference(String s, String t) {
        char c = 0;
        for (int i = 0; i < s.length(); ++i) {
            c ^= s.charAt(i);
        }
        for (int i = 0; i < t.length(); ++i) {
            c ^= t.charAt(i);
        }
        return c;
    }
    //maybe a more elegant version:

    public char findTheDifference2(String s, String t) {
        int n = t.length();
        char c = t.charAt(n - 1);
        for (int i = 0; i < n - 1; ++i) {
            c ^= s.charAt(i);
            c ^= t.charAt(i);
        }
        return c;
    }

/////////////////////////////////////////////////////////////////
   //Simple JAVA 8ms solution, 4 lines
    public char findTheDifference3(String s, String t) {
        // Initialize variables to store sum of ASCII codes for
        // each string
        int charCodeS = 0, charCodeT = 0;
        // Iterate through both strings and char codes
        for (int i = 0; i < s.length(); ++i) charCodeS += (int)s.charAt(i);
        for (int i = 0; i < t.length(); ++i) charCodeT += (int)t.charAt(i);
        // Return the difference between 2 strings as char
        return (char)(charCodeT - charCodeS);
    }

/////////////////////////////////////////////////////////////////
    //"1 optimization: As t.length() is just 1 character longer than s.length(),
    // we can use 1 pass to process both strings (20% better runtime performance)."
    public char findTheDifference4(String s, String t) {
        int charCode = t.charAt(s.length());
        // Iterate through both strings and char codes
        for (int i = 0; i < s.length(); ++i) {
            charCode -= (int)s.charAt(i);
            charCode += (int)t.charAt(i);
        }
        return (char)charCode;
    }
/////////////////////////////////////////////////////////////////
/*
Hi. There are several methods you can try to solve this. HashMap, Arrays, Bits, etc. Here, we're going to use a simple array of size 26 for alphabets. Then for each character in s, increment the count.

Then for each character in t, you should decrement the count. Now if at any point, the count goes below 0, then the character isn't present in t

        for (int i = 0; i < 26; i++) alpha[i] = 0;
        for (char c : s.toCharArray())
            alpha[ c - 'a' ]++;

        for (char c : t.toCharArray()) {
           //could do decrement first, then check but yeah
            if (--alpha[c - 'a'] < 0)
                return c;
        }

        return 0;
 */
/////////////////////////////////////////////////////////////////


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