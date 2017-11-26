package HF.HF5_Math_Graphic_Bit_2Calc;
import org.junit.Test;

//  Big Integer multiplication
//  43. Multiply Strings
//  https://leetcode.com/problems/multiply-strings/description/
//  5:
public class _4BigIntegermultiplication {
//------------------------------------------------------------------------------
    //1
    /**
     * @param num1 a non-negative integers
     * @param num2 a non-negative integers
     * @return return product of num1 and num2
     */
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return null;
        }

        int len1 = num1.length(), len2 = num2.length();
        int len3 = len1 + len2;
        int i, j, product, carry;

        int[] num3 = new int[len3];
        for (i = len1 - 1; i >= 0; i--) {
            carry = 0;
            for (j = len2 - 1; j >= 0; j--) {
                product = carry + num3[i + j + 1] +
                        Character.getNumericValue(num1.charAt(i)) *
                                Character.getNumericValue(num2.charAt(j));
                num3[i + j + 1] = product % 10;
                carry = product / 10;
            }
            num3[i + j + 1] = carry;
        }

        StringBuilder sb = new StringBuilder();
        i = 0;

        while (i < len3 - 1 && num3[i] == 0) {
            i++;
        }

        while (i < len3) {
            sb.append(num3[i++]);
        }

        return sb.toString();
    }

    @Test
    public void test01(){
        String num1 = "23",  num2 = "3";
        System.out.println(multiply(num1, num2));
    }

//-----------------------------------------------------------------------------------
    //2
    // version: 高频题班
    /**
     * @param num1 a non-negative integers
     * @param num2 a non-negative integers
     * @return return product of num1 and num2
     */
    public String multiply2(String num1, String num2) {
        int l1 = num1.length();
        int l2 = num2.length();

        int[] ans = new int[l1 + l2 + 1];
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                ans[i + j] += (num1.charAt(l1 - 1 - i) - '0')
                            * (num2.charAt(l2 - 1 - j) - '0');
            }
        }

        for (int i = 0; i < l1 + l2; i++) {
            ans[i + 1] += ans[i] / 10;
            ans[i] %= 10;
        }

        int i = l1 + l2;
        while (ans[i] == 0 && i >= 1) {
            i--;
        }
        String str = "";
        while (i >= 0) {
            str += ans[i--];
        }
        return str;
    }

//-----------------------------------------------------------------------------------
    //3
    //https://discuss.leetcode.com/topic/30508/easiest-java-solution-with-graph-explanation
    class Solution3{
        public String multiply(String num1, String num2) {
            int m = num1.length(), n = num2.length();
            int[] pos = new int[m + n];

            for(int i = m - 1; i >= 0; i--) {
                for(int j = n - 1; j >= 0; j--) {
                    int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                    int p1 = i + j, p2 = i + j + 1;
                    int sum = mul + pos[p2];

                    pos[p1] += sum / 10;
                    pos[p2] = (sum) % 10;
                }
            }

            StringBuilder sb = new StringBuilder();
            for(int p : pos) if(!(sb.length() == 0 && p == 0)) sb.append(p);
            return sb.length() == 0 ? "0" : sb.toString();
        }
    }

//-----------------------------------------------------------------------------------
    //4
    //AC solution in Java with explanation
    /*
    If we break it into steps, it will have the following steps. 1. compute products from each pair of digits from num1 and num2. 2. carry each element over. 3. output the solution.

    Things to note:

    1. The product of two numbers cannot exceed the sum of the two lengths. (e.g. 99 * 99 cannot be five digit)

    2.
    int d1 = num1.charAt(i) - '0';
    int d2 = num2.charAt(j) - '0';
    products[i + j + 1] += d1 * d2;
     */
    public class Solution4 {
        public String multiply(String num1, String num2) {
            int n1 = num1.length(), n2 = num2.length();
            int[] products = new int[n1 + n2];
            for (int i = n1 - 1; i >= 0; i--) {
                for (int j = n2 - 1; j >= 0; j--) {
                    int d1 = num1.charAt(i) - '0';
                    int d2 = num2.charAt(j) - '0';
                    products[i + j + 1] += d1 * d2;
                }
            }
            int carry = 0;
            for (int i = products.length - 1; i >= 0; i--) {
                int tmp = (products[i] + carry) % 10;
                carry = (products[i] + carry) / 10;
                products[i] = tmp;
            }
            StringBuilder sb = new StringBuilder();
            for (int num : products) sb.append(num);
            while (sb.length() != 0 && sb.charAt(0) == '0') sb.deleteCharAt(0);
            return sb.length() == 0 ? "0" : sb.toString();
        }
    }
//-----------------------------------------------------------------------------------
    //5
    //Clear JAVA solution without reversal
    class Solution5{
        public String multiply(String num1, String num2) {
            int len1 = num1.length();
            int len2 = num2.length();
            int[] product = new int[len1 + len2];
            for (int i = len1 - 1; i >= 0; i--) {
                for (int j = len2 - 1; j >= 0; j--) {
                    int index = len1 + len2 - i - j - 2;
                    product[index] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                    product[index + 1] += product[index] / 10;
                    product[index] %= 10;
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = product.length - 1; i > 0; i--) {
                if (stringBuilder.length() == 0 && product[i] == 0)
                    continue;
                stringBuilder.append(product[i]);
            }
            stringBuilder.append(product[0]);
            return stringBuilder.toString();
        }
    }

//-----------------------------------------------------------------------------------
}

/*
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2

Example
The length of both num1 and num2 is < 110.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
//------------------------------------------------------------------------------
*/


/* leetcode
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.

Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */