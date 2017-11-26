package _09_Math;
import java.util.HashMap;


//  171. Excel Sheet Column Number
//  https://leetcode.com/problems/excel-sheet-column-number/description/
//  Math, Similar Questions
//  Excel Sheet Column Title
//  5:
public class _171_Excel_Sheet_Column_Number {
//-----------------------------------------------------------------------

    /*
    Java Solution 1

    This problem is related to Excel Sheet Column Title. The key is to use a hashmap to store the mapping between character and integer. Starting from the right-hand side, the converted value for each character is the mapping integer * 26 to the t-th power, where t starts from 0.
     */
    public int titleToNumber(String s) {
        if(s==null || s.length() == 0){
            throw new IllegalArgumentException("Input is not valid!");
        }

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        char c = 'A';
        for(int i=1; i<=26; i++){
            map.put(c, i);
            c += 1;
        }

        int result = 0;
        int i = s.length()-1;
        int t = 0;
        while(i >= 0){
            char curr = s.charAt(i);
            result = result + (int) Math.pow(26, t) * map.get(curr);
            t++;
            i--;
        }

        return result;
    }

//-----------------------------------------------------------------------

    /*
    Java Solution 2
    Actually using a hashmap is not necessary here.
    */

    public int titleToNumber2(String s) {
        if(s==null || s.length() == 0){
            throw new IllegalArgumentException("Input is not valid!");
        }

        int result = 0;
        int i = s.length()-1;
        int t = 0;
        while(i >= 0){
            char curr = s.charAt(i);
            result = result + (int) Math.pow(26, t) * (curr-'A'+1);
            t++;
            i--;
        }

        return result;
    }
//-----------------------------------------------------------------------
    //My solutions in 3 languages, does any one have one line solution in Java or C++?
    class Solution3{
        public int titleToNumber2(String s) {
            int result = 0;
            for (int i = 0; i < s.length(); result = result * 26 + (s.charAt(i) - 'A' + 1), i++);
            return result;
        }
    }
//-----------------------------------------------------------------------
    //Here is my java solution
    //Here is my Java solution. Similar to the number to title.
    class Solution4{
        public int titleToNumber(String s) {
            int result = 0;
            for(int i = 0 ; i < s.length(); i++) {
                result = result * 26 + (s.charAt(i) - 'A' + 1);
            }
            return result;
        }
    }

//-----------------------------------------------------------------------
    //My 2ms JAVA solution
    public class Solution5 {
        public int titleToNumber(String s) {

            int result  = 0;
            for (int i = 0; i < s.length(); i++){
                result *= 26;
                result += ((s.charAt(i) - 'A') + 1);
            }

            return result;
        }
    }
//-----------------------------------------------------------------------
}
/*
LeetCode – Excel Sheet Column Number (Java)

Problem

Given a column title as appear in an Excel sheet, return its corresponding column number. For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
    ...
    AAA -> 703

 */