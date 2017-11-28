package _String._Math;
import java.util.*;
import org.junit.Test;

//  273. Integer to English Words
//  https://leetcode.com/problems/integer-to-english-words/description/
//  Math String
//  _012_String_Integer_to_Roman_M
//  3:
//
public class _273_String_Integer_to_English_Words_M {
//------------------------------------------------------------------------------
    //1
    class Solution1{
        //My clean Java solution, very easy to understand
        private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

        public String numberToWords(int num) {
            if (num == 0) return "Zero";

            int i = 0;
            String words = "";

            while (num > 0) {
                if (num % 1000 != 0)
                    words = helper(num % 1000) +THOUSANDS[i] + " " + words;
                num /= 1000;
                i++;
            }

            return words.trim();
        }

        private String helper(int num) {
            if (num == 0)
                return "";
            else if (num < 20)
                return LESS_THAN_20[num] + " ";
            else if (num < 100)
                return TENS[num / 10] + " " + helper(num % 10);
            else
                return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
        }

    }
//------------------------------------------------------------------------------
    //2
    //Short clean Java solution
    public class Solution2 {
        private final String[] belowTen = new String[] {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        private final String[] belowTwenty = new String[] {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        private final String[] belowHundred = new String[] {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

        public String numberToWords(int num) {
            if (num == 0) return "Zero";
            return helper(num);
        }

        private String helper(int num) {
            String result = new String();
            if (num < 10) result = belowTen[num];
            else if (num < 20) result = belowTwenty[num -10];
            else if (num < 100) result = belowHundred[num/10] + " " + helper(num % 10);
            else if (num < 1000) result = helper(num/100) + " Hundred " +  helper(num % 100);
            else if (num < 1000000) result = helper(num/1000) + " Thousand " +  helper(num % 1000);
            else if (num < 1000000000) result = helper(num/1000000) + " Million " +  helper(num % 1000000);
            else result = helper(num/1000000000) + " Billion " + helper(num % 1000000000);
            return result.trim();
        }
    }


//------------------------------------------------------------------------------
    //3
    //My Java Solution
    class Solution3{
        public String numberToWords(int num) {
            if(num == 0)
                return "Zero";
            String[] bigString = new String[]{"Thousand","Million","Billion"};
            String result =  numberToWordsHelper(num%1000);
            num = num/1000;
            if(num > 0 && num%1000>0){
                result = numberToWordsHelper(num%1000) + "Thousand " + result;
            }
            num = num/1000;
            if(num > 0 && num%1000>0){
                result = numberToWordsHelper(num%1000) + "Million " + result;
            }
            num = num/1000;
            if(num > 0){
                result = numberToWordsHelper(num%1000) + "Billion " + result;
            }
            return result.trim();
        }

        public String numberToWordsHelper(int num){
            String[] digitString = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
            String[] teenString = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen","Eighteen", "Nineteen"};
            String[] tenString = new String[]{"","","Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
            String result = "";
            if(num > 99){
                result += digitString[num/100] + " Hundred ";
            }
            num = num % 100;
            if(num < 20 && num > 9){
                result += teenString[num%10]+" ";
            }else{
                if(num > 19){
                    result += tenString[num/10]+" ";
                }
                num = num % 10;
                if(num > 0)
                    result += digitString[num]+" ";
            }
            return result;
        }
    }

//------------------------------------------------------------------------------
}
/*
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Seen this question in a real interview before?   Yes  No

Companies
Facebook Microsoft

Related Topics
Math String

Similar Questions
Integer to Roman
 */