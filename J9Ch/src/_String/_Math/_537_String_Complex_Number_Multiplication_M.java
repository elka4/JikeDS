package _String._Math;
import java.util.*;
import java.util.stream.*;
import org.junit.Test;

//  537. Complex Number Multiplication
//  https://leetcode.com/problems/complex-number-multiplication
//  Math String
//
public class _537_String_Complex_Number_Multiplication_M {
//------------------------------------------------------------------------------
//https://leetcode.com/problems/complex-number-multiplication/solution/
//------------------------------------------------------------------------------
    //1
    //Approach #1 Simple Solution[Accepted]
    public class Solution1 {
        public String complexNumberMultiply(String a, String b) {
            String x[] = a.split("\\+|i");
            String y[] = b.split("\\+|i");
            int a_real = Integer.parseInt(x[0]);
            int a_img = Integer.parseInt(x[1]);
            int b_real = Integer.parseInt(y[0]);
            int b_img = Integer.parseInt(y[1]);
            return (a_real * b_real - a_img * b_img) + "+" +
                    (a_real * b_img + a_img * b_real) + "i";

        }
    }




//------------------------------------------------------------------------------
    //2
//    Java 3-liner
//    This solution relies on the fact that (a+bi)(c+di) = (ac - bd) + (ad+bc)i.

    public String complexNumberMultiply2(String a, String b) {
        int[] coefs1 = Stream.of(a.split("\\+|i")).mapToInt(Integer::parseInt).toArray(),
            coefs2 = Stream.of(b.split("\\+|i")).mapToInt(Integer::parseInt).toArray();
        return (coefs1[0]*coefs2[0] - coefs1[1]*coefs2[1]) + "+" +
                (coefs1[0]*coefs2[1] + coefs1[1]*coefs2[0]) + "i";
    }

//------------------------------------------------------------------------------
    //3
    //Java - (a1+b1)*(a2+b2) = (a1a2 + b1b2 + (a1b2+b1a2))
    public String complexNumberMultiply3(String a, String b) {
        String result = "";
        String[] A = a.split("\\+");
        String[] B = b.split("\\+");
        int a1 = Integer.parseInt(A[0]);
        int b1 = Integer.parseInt(A[1].replace("i",""));

        int a2 = Integer.parseInt(B[0]);
        int b2 = Integer.parseInt(B[1].replace("i",""));

        int a1a2 = a1 * a2;
        int b1b2 = b1 * b2;
        int a1b2a2b1 = (a1 * b2) + (b1 * a2);

        String afinal = (a1a2 + (-1 * b1b2)) + "";
        String bfinal = a1b2a2b1 + "i";
        result = afinal+"+"+bfinal;
        return result;
    }

//------------------------------------------------------------------------------


//------------------------------------------------------------------------------


//------------------------------------------------------------------------------
}
/*
Given two strings representing two complex numbers.

You need to return a string representing their multiplication. Note i2 = -1 according to the definition.

Example 1:
Input: "1+1i", "1+1i"
Output: "0+2i"
Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
Example 2:
Input: "1+-1i", "1+-1i"
Output: "0+-2i"
Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
Note:

The input strings will not have extra blank.
The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.
Seen this question in a real interview before?   Yes  No

Companies
Amazon

Related Topics
Math String
 */