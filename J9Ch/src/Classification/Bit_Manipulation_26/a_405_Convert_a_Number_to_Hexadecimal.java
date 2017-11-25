package Classification.Bit_Manipulation_26;
import java.util.*;

public class a_405_Convert_a_Number_to_Hexadecimal {
/*
Basic idea: each time we take a look at the last four digits of
            binary verion of the input, and maps that to a hex char
            shift the input to the right by 4 bits, do it again
            until input becomes 0.

*/

    public class Solution {

        char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        public String toHex(int num) {
            if (num == 0) return "0";
            String result = "";
            while (num != 0) {
                result = map[(num & 15)] + result;
                num = (num >>> 4);
            }
            return result;
        }


    }

//------------------------------------------------------------------------------

    //https://leetcode.com/problems/convert-a-number-to-hexadecimal/tabs/discuss

/*
[JAVA] Clean Code with Explanations and Running Time [2 Solutions]

Full Solutions and Explanations
Solution 1
public class Solution {
    public String toHex(int num) {
        long n = num & 0x00000000ffffffffL;
        char[] map = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.insert(0, map[(int) (n % 16)]);
            n = n / 16;
        }
        return num == 0 ? "0" : sb.toString();
    }
}
Complexity Analysis

Uniform cost model is used as Cost Model and `n` is the input number. `b` in this case would be `16.

Time Complexity:

Best Case `O(log_b(n))` : With respect to the input, the algorithm will always depend on the size of input.
Average Case `O(log_b(n))` : With respect to the input, the algorithm will always depend on the size of input.
Worst Case `O(log_b(n))` : With respect to the input, the algorithm will always depend on the size of input.
Auxiliary Space:

Worst Case `O(log_b(n))` : With respect to the input, the algorithm will always depend on the size of input.
Algorithm

Approach: Divide and Modding

To deal with negative number, the number is masked against long data type. This process will convert it to a positive long number. A simple while loop is then use to extract each base digit until number becomes `0`.

For Integer.MAX_VALUE or Integer.MIN_VALUE or any input with 8 Hexadecimal characters where the iterations would last the longest. For Integer.MAX_VALUE the algorithm will run for at most `ceil(log_16 (2^31 - 1) + 1) = 8` times.

Solution 2
public class Solution {
    public String toHex(int num) {
        if (num == 0) return "0";
        char[] map = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.insert(0, map[num & 0b1111]);
            num = num >>> 4;
        }
        return sb.toString();
    }
}
Complexity Analysis

Uniform cost model is used as Cost Model and `n` is the input number. `b` in this case would be `16.

Time Complexity:

Best Case `O(log_b(n))` : With respect to the input, the algorithm will always depend on the size of input.
Average Case `O(log_b(n))` : With respect to the input, the algorithm will always depend on the size of input.
Worst Case `O(log_b(n))` : With respect to the input, the algorithm will always depend on the size of input.
Auxiliary Space:

Worst Case `O(log_b(n))` : With respect to the input, the algorithm will always depend on the size of input.
Algorithm

Approach: Shifting and Masking

Number is masked against binary of 1111 each time to get the component value which is then mapped to corresponding character. >>> is used to right-shifted `4` bit positions with zero-extension. The zero-extension will naturally deal with negative number.

StringBuilder is used due to its efficiently in inserting character to existing StringBuilder object. If normal String is used then each insertion by + operation will have to copy over the immutable String object which is highly inefficient.

For Integer.MAX_VALUE or Integer.MIN_VALUE or any input with 8 Hexadecimal characters where the iterations would last the longest. For Integer.MAX_VALUE the algorithm will run for at most `ceil(log_16 (2^31 - 1) + 1) = 8` times.
 */

//------------------------------------------------------------------------------


//------------------------------------------------------------------------------


//------------------------------------------------------------------------------


//------------------------------------------------------------------------------


//------------------------------------------------------------------------------



}
//Two's complement

/*
Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.

Note:

All letters in hexadecimal (a-f) must be in lowercase.
The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
The given number is guaranteed to fit within the range of a 32-bit signed integer.
You must not use any method provided by the library which converts/formats the number to hex directly.
Example 1:

Input:
26

Output:
"1a"
Example 2:

Input:
-1

Output:
"ffffffff"
 */