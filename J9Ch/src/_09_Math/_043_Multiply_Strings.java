package _09_Math;


//  43. Multiply Strings
//  https://leetcode.com/problems/multiply-strings/description/
//  http://www.lintcode.com/zh-cn/problem/big-integer-multiplication/
//  4:1
public class _043_Multiply_Strings {
//-----------------------------------------------------------------------
    //1
    //  https://discuss.leetcode.com/topic/30508/easiest-java-solution-with-graph-explanation
    //Easiest JAVA Solution with Graph Explanation
    // `num1[i] * num2[j]` will be placed at indices `[i + j`, `i + j + 1]`
    class Solution1{
        public String multiply(String num1, String num2) {
            int m = num1.length(), n = num2.length();
            int[] pos = new int[m + n];

            for(int i = m - 1; i >= 0; i--) {
                for(int j = n - 1; j >= 0; j--) {
                    int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                    int p1 = i + j, p2 = i + j + 1;
                    int sum = mul + pos[p2];//pos[p2]放的是carry

                    pos[p1] += sum / 10;
                    pos[p2] = (sum) % 10;
                }
            }

            StringBuilder sb = new StringBuilder();
            for(int p : pos)
                if(!(sb.length() == 0 && p == 0))
                    sb.append(p);
            return sb.length() == 0 ? "0" : sb.toString();
        }
    }
//-----------------------------------------------------------------------
    //2
    //AC solution in Java with explanation
    public class Solution2 {
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
    /*
    If we break it into steps, it will have the following steps.
    1. compute products from each pair of digits from num1 and num2.
    2. carry each element over.
    3. output the solution.

    Things to note:

    1.The product of two numbers cannot exceed the sum of the two lengths.
    (e.g. 99 * 99 cannot be five digit)

    2.
    int d1 = num1.charAt(i) - '0';
    int d2 = num2.charAt(j) - '0';
    products[i + j + 1] += d1 * d2;
     */
//-----------------------------------------------------------------------
    //3
    //Clear JAVA solution without reversal
    class Solution3{
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

//-----------------------------------------------------------------------
    //4
    public class Solution {
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
    }
//-----------------------------------------------------------------------


//-----------------------------------------------------------------------
}
/*
----------------------------------------------------------------------------------------
Add to List
43. Multiply Strings
DescriptionHintsSubmissionsDiscussSolution
Discuss Pick One
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.

Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
Seen this question in a real interview before?   Yes  No
Companies
Facebook Twitter
Related Topics
Math String
Similar Questions
Add Two Numbers Plus One Add Binary Add Strings
----------------------------------------------------------------------------------------
*/

/*
656. 大整数乘法

以字符串的形式给定两个非负整数 num1 和 num2，返回 num1 和 num2 的乘积。

样例
num1 和 num2 的长度都小于110。
num1 和 num2 都只包含数字 0 - 9。
num1 和 num2 都不包含任意前导零。
您不能使用任何内置的BigInteger库内方法或直接将输入转换为整数。
标签
字符串处理 数学 脸书 推特
相关题目
困难 阶乘 16 %
中等 链表求和 II 24 %
容易 大整数加法 26 %
容易 链表求和
----------------------------------------------------------------------------------------
 */