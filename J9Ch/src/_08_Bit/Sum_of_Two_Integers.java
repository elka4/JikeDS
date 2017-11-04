package _08_Bit;

/*
LeetCode – Sum of Two Integers (Java)

Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.


Example:
Given a = 1 and b = 2, return 3.


 */
public class Sum_of_Two_Integers {

    /*
    Java Solution

Given two numbers a and b, a&b returns the number formed by '1' bits on a and b. When it is left shifted by 1 bit, it is the carry.

For example, given a=101 and b=111 (in binary), the a&b=101. a&b << 1 = 1010.

a^b is the number formed by different bits of a and b. a&b=10.
     */

    /*
    c is carry
     */
    public int getSum(int a, int b) {

        while(b!=0){
            int c = a&b;
            a=a^b;
            b=c<<1;
        }

        return a;
    }

    // Iterative
    public int getSum22(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;

        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }

        return a;
    }

//////////////////////////////////////////////////////////////////

    //A summary: how to use bit manipulation to solve problems easily and efficiently
    //https://leetcode.com/problems/sum-of-two-integers/discuss/




//////////////////////////////////////////////////////////////////

//https://leetcode.com/problems/sum-of-two-integers/discuss/

    /*
    Java simple easy understand solution with explanation
I have been confused about bit manipulation for a very long time. So I decide to do a summary about it here.

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

Iterate until there is no carry (or b == 0)


     */

    // Iterative
    public int getSum2(int a, int b) {
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
    public int getSubtract2(int a, int b) {
        while (b != 0) {
            int borrow = (~a) & b;
            a = a ^ b;
            b = borrow << 1;
        }

        return a;
    }

    // Recursive
    public int getSum3(int a, int b) {
        return (b == 0) ? a : getSum3(a ^ b, (a & b) << 1);
    }

    // Recursive
    public int getSubtract(int a, int b) {
        return (b == 0) ? a : getSubtract(a ^ b, (~a & b) << 1);
    }

    // Get negative number
    public int negate(int x) {
        return ~x + 1;
    }

//////////////////////////////////////////////////////////////////





//////////////////////////////////////////////////////////////////





//////////////////////////////////////////////////////////////////




}
