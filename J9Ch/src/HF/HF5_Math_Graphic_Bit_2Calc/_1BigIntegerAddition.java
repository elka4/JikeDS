package HF.HF5_Math_Graphic_Bit_2Calc;
import org.junit.Test;

//  Big Integer Addition
//
//  415. Add Strings
//  https://leetcode.com/problems/add-strings/description/
//  2:
//  Math
//  2. Add Two Numbers - LinkedList
//  43. Multiply Strings - String
public class _1BigIntegerAddition {
//-------------------------------------------------------------------------------
    //1
    /**
     * @param num1 a non-negative integers
     * @param num2 a non-negative integers
     * @return return sum of num1 and num2
     */
    public String addStrings(String num1, String num2) {
        // Write your code here
        int i = num1.length() - 1;
        int j = num2.length() - 1;

        int carry = 0;
        String result = "";

        while (i >= 0 || j >= 0) {
            if (i >= 0) {
                carry += num1.charAt(i--) - '0';
            }
            if (j >= 0) {
                carry += num2.charAt(j--) - '0';
            }
            result = carry % 10 + result;
            carry /= 10;
        }
        return carry > 0 ? "1" + result : result;
    }

    @Test
    public void test01(){
        String num1 = "123", num2 = "45";
        System.out.println(addStrings(num1, num2));
    }

//-------------------------------------------------------------------------------
    //2
    // version: 高频题班
    /**
     * @param num1 a non-negative integers
     * @param num2 a non-negative integers
     * @return return sum of num1 and num2
     */
    public String addStrings2(String num1, String num2) {
        String ans = "";

        int carry = 0;

        for (int i = num1.length() - 1, j = num2.length() - 1;
             i >= 0 || j >= 0; i--, j--) {

            int sum = carry;
            sum += (i >= 0) ? num1.charAt(i) - '0' : 0;
            sum += (j >= 0) ? num2.charAt(j) - '0' : 0;
            ans = (sum % 10) + ans;
            carry = sum / 10;
        }

        if (carry != 0) {
            ans = carry + ans;
        }
        return ans;
    }

    @Test
    public void test02(){
        String num1 = "123", num2 = "45";
        System.out.println(addStrings2(num1, num2));
    }


//-------------------------------------------------------------------------------
}
/*
Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

 注意事项

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
样例
Given num1 = "123", num2 = "45"
return "168"
 */