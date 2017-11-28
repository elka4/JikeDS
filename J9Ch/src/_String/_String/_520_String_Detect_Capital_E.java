package _String._String;
import java.util.*;
import org.junit.Test;

//
//  520. Detect Capital
//  https://leetcode.com/problems/detect-capital/description/
//
public class _520_String_Detect_Capital_E {
//------------------------------------------------------------------------------
//3 Lines
public class Solution1 {
    public boolean detectCapitalUse(String word) {
        int cnt = 0;
        for(char c: word.toCharArray()) if('Z' - c >= 0) cnt++;
        return ((cnt==0 || cnt==word.length()) || (cnt==1 && 'Z' - word.charAt(0)>=0));
    }
}
//------------------------------------------------------------------------------
//Java 1-Liner
//Regex again.

    public boolean detectCapitalUse2(String word) {
        return word.matches("[A-Z]+|[a-z]+|[A-Z][a-z]+");
    }


//------------------------------------------------------------------------------
    //Simple Java Solution O(n) time O(1) space
    public boolean detectCapitalUse3(String word) {
        return word.equals(word.toUpperCase()) ||
                word.equals(word.toLowerCase()) ||
                Character.isUpperCase(word.charAt(0)) &&
                        word.substring(1).equals(word.substring(1).toLowerCase());
    }
    //A slightly more verbose, but more efficient solution:

    public boolean detectCapitalUse4(String word) {
        int numUpper = 0;
        for (int i=0;i<word.length();i++)
            if (Character.isUpperCase(word.charAt(i))) numUpper++;
        if (numUpper == 1) return Character.isUpperCase(word.charAt(0));
        return numUpper == 0 || numUpper == word.length();
    }
//------------------------------------------------------------------------------
    /*Java short solution using built-in String methods
    The string can be correctly capitalized if either: it's shorter than 2 characters, or if it's all lower case, or if it's all upper case, or if from position 1 onward there are only lowercase letters.*/

    public boolean detectCapitalUse5(String word) {
        if (word.length() < 2) return true;
        if (word.toUpperCase().equals(word)) return true;
        if (word.substring(1).toLowerCase().equals(word.substring(1))) return true;
        return false;
    }

//------------------------------------------------------------------------------


//------------------------------------------------------------------------------
}
/*
Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital if it has more than one letter, like "Google".
Otherwise, we define that this word doesn't use capitals in a right way.
Example 1:
Input: "USA"
Output: True
Example 2:
Input: "FlaG"
Output: False
Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.

Seen this question in a real interview before?   Yes  No
Companies
Google
Related Topics
String
 */