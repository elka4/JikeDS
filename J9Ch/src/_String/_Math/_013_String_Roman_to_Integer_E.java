package _String._Math;
import java.util.*;
import org.junit.Test;

//  13. Roman to Integer
//  https://leetcode.com/problems/roman-to-integer/description/
//  Math String
//  _012_String_Integer_to_Roman_M
//  2:2
public class _013_String_Roman_to_Integer_E {
//------------------------------------------------------------------------------

//------------------------------------------------------------------------------
    //1
    // 9Ch
    public int romanToInt(String s) {
        if (s == null || s.length()==0) {
            return 0;
        }
        Map<Character, Integer> m = new HashMap<Character, Integer>();
        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);

        int length = s.length();
        int result = m.get(s.charAt(length - 1));
        for (int i = length - 2; i >= 0; i--) {
            if (m.get(s.charAt(i + 1)) <= m.get(s.charAt(i))) {
                result += m.get(s.charAt(i));
            } else {
                result -= m.get(s.charAt(i));
            }
        }
        return result;
    }

//--------------------------------------------------------------------------------
    //2
    // version: 高频题班
    /**
     * @param s Roman representation
     * @return an integer
     */
    public int romanToInt2(String s) {
        // Write your code here
        int ans;
        char[] sc = s.toCharArray();
        ans = toInt(sc[0]);                        //0 special
        for (int i = 1; i < s.length(); i++) {
            ans += toInt(sc[i]);
            if (toInt(sc[i - 1]) < toInt(sc[i])) {
                ans -= toInt(sc[i - 1]) * 2;
            }
        }
        return ans;
    }

    int toInt(char s) {
        switch(s) {
            case 'I':return 1;
            case 'V':return 5;
            case 'X':return 10;
            case 'L':return 50;
            case 'C':return 100;
            case 'D':return 500;
            case 'M':return 1000;
        }
        return 0;
    }

//--------------------------------------------------------------------------------
}
/*
Given a roman numeral, convert it to an integer.

The answer is guaranteed to be within the range from 1 to 3999.

Example
IV -> 4

XII -> 12

XXI -> 21

XCIX -> 99

Companies
Facebook Microsoft Bloomberg Uber Yahoo

Related Topics
Math String

Similar Questions
Integer to Roman
 */