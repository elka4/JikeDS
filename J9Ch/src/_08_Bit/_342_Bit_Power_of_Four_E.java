package _08_Bit;
import java.util.*;
import org.junit.Test;
import java.util.regex.*;


//  342. Power of Four
//  https://leetcode.com/problems/power-of-four/description/
//  7:
//
public class _342_Bit_Power_of_Four_E {
//------------------------------------------------------------------------------
    //1
    //Java 1-line (cheating for the purpose of not using loops)
    public boolean isPowerOfFour(int num) {
        return num > 0 && (num&(num-1)) == 0 && (num & 0x55555555) != 0;
        //0x55555555 is to get rid of those power of 2 but not power of 4
        //so that the single 1 bit always appears at the odd position
    }
//    I got another 1 line solution, basically just change number to quaternary and then check if it starts with "10".
//------------------------------------------------------------------------------
    //2
    public boolean isPowerOfFour2(int num) {
        return Integer.toString(num, 4).matches("10*");
    }
//------------------------------------------------------------------------------
    //3
    //*Java* one-line solution using bitCount & numberOfTrailingZeros
    public boolean isPowerOfFour3(int num) {
        return num>=1 && Integer.bitCount(num) == 1 && Integer.numberOfTrailingZeros(num)%2 == 0;
    }
//------------------------------------------------------------------------------
    //4
    //One line in JAVA without loops/recursion which is extensible.
    public class Solution4 {

        public boolean isPowerOfFour(int num) {
            return (num&(num-1))==0 && num>0 && (num-1)%3==0;
        }
    }
//------------------------------------------------------------------------------
    //5
//    The first two conditions are for power of 2.
//    One additional condition: (num-1) can be divided by 3.



    //Java 1 line of code and can be extended to any radix solution
//    The idea is that numbers in quaternary system that is power of 4 will be like 10, 100, 1000 and such. Similar to binary case. And this can be extended to any radix.

    public boolean isPowerOfFour5(int num) {
        return Integer.toString(num, 4).matches("10*");
    }
//------------------------------------------------------------------------------
    //6
    //A Java regex solution
/*
    The binary string of power of 4 look like:
            "100"
            "10000"
            "1000000"

    There is always a "1" in the front, so we apply ^1 in regex.
    There is always a multiple of "00" in the rear, so we apply (00)+$.
    As a result, the total regex would look like ^1(00)+$.
*/


    public class Solution6 {
        public boolean isPowerOfFour(int num) {

            if (num == 0) {
                return false;
            }

            String bString = Integer.toBinaryString(num);
            System.out.println(bString);

            String pattern = "^1(00)+$";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(bString);
            return m.find();
        }
    }
//------------------------------------------------------------------------------
    //7
    //One line Java, No complex coding, Using only Integer class methods
    /*For a number to be power of 4, it should-

    Have all 0 bits and 1 - 1bit. eg 100,10000
    Zeros after 1 should be even. One zero mean multiple of 2. 2 zero means multiple of 4.*/
    public boolean isPowerOfFour7(int num) {
        return Integer.bitCount(num) == 1 && (Integer.toBinaryString(num).length()-1)%2==0;
    }

//------------------------------------------------------------------------------
}
/*
Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example:
Given num = 16, return true. Given num = 5, return false.

Follow up: Could you solve it without loops/recursion?
 */

