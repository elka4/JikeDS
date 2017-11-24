package _08_Bit;
import java.util.*;
import org.junit.Test;

//  371. Sum of Two Integers

//  https://leetcode.com/problems/sum-of-two-integers/description/
//
public class _371_Bit_Sum_of_Two_Integers_E {
//  https://discuss.leetcode.com/topic/50315/a-summary-how-to-use-bit-manipulation-to-solve-problems-easily-and-efficiently/2

//  A summary: how to use bit manipulation to solve problems easily and efficiently


//------------------------------------------------------------------------------////

//  Java simple easy understand solution with explanation

/*    I have been confused about bit manipulation for a very long time. So I decide to do a summary about it here.

"&" AND operation, for example, 2 (0010) & 7 (0111) => 2 (0010)

            "^" XOR operation, for example, 2 (0010) ^ 7 (0111) => 5 (0101)

            "~" NOT operation, for example, ~2(0010) => -3 (1101) what??? Don't get frustrated here. It's called two's complement.

            1111 is -1, in two's complement

            1110 is -2, which is ~2 + 1, ~0010 => 1101, 1101 + 1 = 1110 => 2

            1101 is -3, which is ~3 + 1

    so if you want to get a negative number, you can simply do ~x + 1

    Reference:

    https://en.wikipedia.org/wiki/Two%27s_complement

    https://www.cs.cornell.edu/~tomf/notes/cps104/twoscomp.html

    For this, problem, for example, we have a = 1, b = 3,

    In bit representation, a = 0001, b = 0011,

    First, we can use "and"("&") operation between a and b to find a carry.

            carry = a & b, then carry = 0001

    Second, we can use "xor" ("^") operation between a and b to find the different bit, and assign it to a,

    Then, we shift carry one position left and assign it to b, b = 0010.

    Iterate until there is no carry (or b == 0)*/

    // Iterative
    public int getSum1(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;

        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }

        return a;
    }

    // Iterative
    public int getSubtract1(int a, int b) {
        while (b != 0) {
            int borrow = (~a) & b;
            a = a ^ b;
            b = borrow << 1;
        }

        return a;
    }

    // Recursive
    public int getSum2(int a, int b) {
        return (b == 0) ? a : getSum2(a ^ b, (a & b) << 1);
    }

    // Recursive
    public int getSubtract2(int a, int b) {
        return (b == 0) ? a : getSubtract2(a ^ b, (~a & b) << 1);
    }

    // Get negative number
    public int negate(int x) {
        return ~x + 1;
    }

    /*

for example, a = -1 (1111), b = 2 (0010),

i = 0, carry = 0010, a = 1101, b = 0100

i = 1, carry = 0100, a = 1001, b = 1000

i = 2, carry = 1000, a = 0001, b = 0000, stop, return a(1)
     */

/*    A better subtraction code, for if 'a' = 0, or 'b' = 0;
    I'm considering 'a' as 'minuend' and 'b' as 'subtrahend'.
    i.e. 'a-b'
    e.g. '2 - 3', this code will return answer as '-1'. Let me know if there is any mistake.*/

    public static int getSubtract3(int a, int b) {
        if(a == 0) return (~b + 1);
        if(b == 0) return a;

        int borrow = 0;
        while(b != 0) {
            borrow = (~a) & b;
            a = a ^ b;
            b = borrow << 1;
        }
        return a;
    }
//------------------------------------------------------------------------------////
//0ms AC java solution

    public int getSum4(int a, int b) {
        if(b == 0){//没有进为的时候完成运算
            return a;
        }
        int sum,carry;
        sum = a^b;//完成第一步加发的运算
        carry = (a&b)<<1;//完成第二步进位并且左移运算
        return getSum4(sum,carry);//
    }

//    not as cleaner as this one. but just for reference.

    public int getSum5(int a, int b) {
        //return (a|b)+(a&b);
        int carry = a & b;
        a = a | b;//get current bit value
        if (carry == 0) return a;
        a = a & (~carry); // if carry is 1, then it current value need to be set to 0;
        return getSum5(a, carry << 1);
    }
//------------------------------------------------------------------------------////


//------------------------------------------------------------------------------////
}
/*
Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example:
Given a = 1 and b = 2, return 3.


 */

/*

 */