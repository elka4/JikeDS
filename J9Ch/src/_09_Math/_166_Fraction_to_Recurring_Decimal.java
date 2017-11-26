package _09_Math;
import java.util.*;


//  166. Fraction to Recurring Decimal
//  https://leetcode.com/problems/fraction-to-recurring-decimal/description/
//
//
public class _166_Fraction_to_Recurring_Decimal {
//-----------------------------------------------------------------------

    public int trailingZeroes(int n) {
        if (n < 0)
            return -1;

        int count = 0;
        for (long i = 5; n / i >= 1; i *= 5) {
            count += n / i;
        }

        return count;
    }
//-----------------------------------------------------------------------
/*My clean Java solution
    The important thing is to consider all edge cases while thinking this problem through, including: negative integer, possible overflow, etc.

    Use HashMap to store a remainder and its associated index while doing the division so that whenever a same remainder comes up, we know there is a repeating fractional part.

    Please comment if you see something wrong or can be improved. Cheers!*/

    public class Solution2 {
        public String fractionToDecimal(int numerator, int denominator) {
            if (numerator == 0) {
                return "0";
            }
            StringBuilder res = new StringBuilder();
            // "+" or "-"
            res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
            long num = Math.abs((long)numerator);
            long den = Math.abs((long)denominator);

            // integral part
            res.append(num / den);
            num %= den;
            if (num == 0) {
                return res.toString();
            }

            // fractional part
            res.append(".");
            HashMap<Long, Integer> map = new HashMap<Long, Integer>();
            map.put(num, res.length());
            while (num != 0) {
                num *= 10;
                res.append(num / den);
                num %= den;
                if (map.containsKey(num)) {
                    int index = map.get(num);
                    res.insert(index, "(");
                    res.append(")");
                    break;
                }
                else {
                    map.put(num, res.length());
                }
            }
            return res.toString();
        }
    }

//-----------------------------------------------------------------------
    //Short Java solution
    class Solution3 {
        public String fractionToDecimal(int numerator, int denominator) {
            StringBuilder result = new StringBuilder();
            String sign = (numerator < 0 == denominator < 0 || numerator == 0) ? "" : "-";
            long num = Math.abs((long) numerator);
            long den = Math.abs((long) denominator);
            result.append(sign);
            result.append(num / den);
            long remainder = num % den;
            if (remainder == 0)
                return result.toString();
            result.append(".");
            HashMap<Long, Integer> hashMap = new HashMap<Long, Integer>();
            while (!hashMap.containsKey(remainder)) {
                hashMap.put(remainder, result.length());
                result.append(10 * remainder / den);
                remainder = 10 * remainder % den;
            }
            int index = hashMap.get(remainder);
            result.insert(index, "(");
            result.append(")");
            return result.toString().replace("(0)", "");
        }
    }


//-----------------------------------------------------------------------
/*Accepted Clean Java Solution
    I don't understand why so many people tends to write "short" java solutions over "clear" java solution when performance stays the same.
    In order to be a good teammate, one should always write clean code instead of hacky code if performance stays the same.*/
    class Solution4 {
        public String fractionToDecimal(int numerator, int denominator) {
            boolean isNegative = (numerator < 0 && denominator > 0 || numerator > 0 && denominator < 0) ? true : false;
            long numeratorL = Math.abs((long) numerator);
            long denominatorL = Math.abs((long) denominator);
            Map<Long, Integer> previousRemains = new HashMap<Long, Integer>();
            StringBuilder sb = new StringBuilder();
            long quotian = numeratorL / denominatorL;
            sb.append(quotian);

            numeratorL %= denominatorL;

            if (numeratorL != 0) {
                sb.append(".");
            }

            int quotianIndex = 0;
            while (numeratorL != 0) {
                numeratorL *= 10;
                quotian = Math.abs(numeratorL / denominatorL);
                if (!previousRemains.containsKey(numeratorL)) {
                    sb.append(quotian);
                    previousRemains.put(numeratorL, quotianIndex++);
                } else {
                    int firstIndex = 1 + previousRemains.get(numeratorL) + sb.indexOf(".");
                    sb.insert(firstIndex, '(');
                    sb.append(")");
                    break;
                }
                numeratorL %= denominatorL;
            }

            if (isNegative) {
                sb.insert(0, "-");
            }
            return sb.toString();
        }
    }

//-----------------------------------------------------------------------
}
/*
LeetCode – Factorial Trailing Zeroes (Java)

Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.


Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".
 */