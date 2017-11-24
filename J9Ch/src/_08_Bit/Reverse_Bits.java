package _08_Bit;

import java.util.HashMap;
import java.util.Map;
/*
LeetCode – Reverse Bits (Java)

Problem

Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).

Follow up:
If this function is called many times, how would you optimize it?

Related problem: Reverse Integer
 */
public class Reverse_Bits {

    public int reverseBits44(int n) {
        if (n == 0) return 0;

        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            if ((n & 1) == 1) result++;
            n >>= 1;
        }
        return result;
    }

//---------------------------------//////////////////////

    public int reverseBits(int n) {
        for (int i = 0; i < 16; i++) {
            n = swapBits(n, i, 32 - i - 1);
        }

        return n;
    }

    public int swapBits(int n, int i, int j) {
        int a = (n >> i) & 1;
        int b = (n >> j) & 1;

        if ((a ^ b) != 0) {
            return n ^= (1 << i) | (1 << j);
        }

        return n;
    }



//---------------------------------/

   /* Java Solution and Optimization
    The Java solution is straightforward, just bitwise operation:*/

    public int reverseBits2(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result += n & 1;
            n >>>= 1;   // CATCH: must do unsigned shift
            if (i < 31) // CATCH: for last digit, don't shift!
                result <<= 1;
        }
        return result;
    }
    /*How to optimize if this function is called multiple times? We can divide an int into 4 bytes, and reverse each byte then combine into an int. For each byte, we can use cache to improve performance.*/

    // cache
    private final Map<Byte, Integer> cache = new HashMap<Byte, Integer>();
    public int reverseBits3(int n) {
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; i++) // convert int into 4 bytes
            bytes[i] = (byte)((n >>> 8*i) & 0xFF);
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result += reverseByte(bytes[i]); // reverse per byte
            if (i < 3)
                result <<= 8;
        }
        return result;
    }

    private int reverseByte(byte b) {
        Integer value = cache.get(b); // first look up from cache
        if (value != null)
            return value;
        value = 0;
        // reverse by bit
        for (int i = 0; i < 8; i++) {
            value += ((b >>> i) & 1);
            if (i < 7)
                value <<= 1;
        }
        cache.put(b, value);
        return value;
    }
//------------------------------------------------------------------------------/////

/*    Sharing my 2ms Java Solution with Explanation
"
    We first intitialize result to 0. We then iterate from
0 to 31 (an integer has 32 bits). In each iteration:
    We first shift result to the left by 1 bit.
            Then, if the last digit of input n is 1, we a4dd 1 to result. To
    find the last digit of n, we just do: (n & 1)
    Example, if n=5 (101), n&1 = 101 & 001 = 001 = 1;
    however, if n = 2 (10), n&1 = 10 & 01 = 0).
    Finally, we update n by shifting it to the right by 1 (n >>= 1)
    At the end of the iteration, we return result.

            Example, if input n = 13 (represented in binary as
0000_0000_0000_0000_0000_0000_0000_1101, the "_" is for readability),
    calling reverseBits(13) should return:
            1011_0000_0000_0000_0000_0000_0000_0000

    Here is how our algorithm would work for input n = 13:

    Initially, result = 0 = 0000_0000_0000_0000_0000_0000_0000_0000,
    n = 13 = 0000_0000_0000_0000_0000_0000_0000_1101

    Starting for loop:
    i = 0:
    result = result << 1 = 0000_0000_0000_0000_0000_0000_0000_0000.
    n&1 = 0000_0000_0000_0000_0000_0000_0000_1101 &
            0000_0000_0000_0000_0000_0000_0000_0001 =
            0000_0000_0000_0000_0000_0000_0000_0001 = 1
    therefore result = result + 1 =
            0000_0000_0000_0000_0000_0000_0000_0000 +
                    0000_0000_0000_0000_0000_0000_0000_0001 =
                    0000_0000_0000_0000_0000_0000_0000_0001 = 1
    We right shift n by 1 (n >>= 1) to get:
    n = 0000_0000_0000_0000_0000_0000_0000_0110.
    We then go to the next iteration.

            i = 1:
    result = result << 1 = 0000_0000_0000_0000_0000_0000_0000_0010;
    n&1 = 0000_0000_0000_0000_0000_0000_0000_0110 &
            0000_0000_0000_0000_0000_0000_0000_0001
            = 0000_0000_0000_0000_0000_0000_0000_0000 = 0;
    therefore we don't increment result.
    We right shift n by 1 (n >>= 1) to get:
    n = 0000_0000_0000_0000_0000_0000_0000_0011.
    We then go to the next iteration.

            i = 2:
    result = result << 1 = 0000_0000_0000_0000_0000_0000_0000_0100.
    n&1 = 0000_0000_0000_0000_0000_0000_0000_0011 &
            0000_0000_0000_0000_0000_0000_0000_0001 =
            0000_0000_0000_0000_0000_0000_0000_0001 = 1
    therefore result = result + 1 =
            0000_0000_0000_0000_0000_0000_0000_0100 +
                    0000_0000_0000_0000_0000_0000_0000_0001 =
                    result = 0000_0000_0000_0000_0000_0000_0000_0101
    We right shift n by 1 to get:
    n = 0000_0000_0000_0000_0000_0000_0000_0001.
    We then go to the next iteration.

            i = 3:
    result = result << 1 = 0000_0000_0000_0000_0000_0000_0000_1010.
    n&1 = 0000_0000_0000_0000_0000_0000_0000_0001 &
            0000_0000_0000_0000_0000_0000_0000_0001 =
            0000_0000_0000_0000_0000_0000_0000_0001 = 1
    therefore result = result + 1 =
= 0000_0000_0000_0000_0000_0000_0000_1011
    We right shift n by 1 to get:
    n = 0000_0000_0000_0000_0000_0000_0000_0000 = 0.

    Now, from here to the end of the iteration, n is 0, so (n&1)
    will always be 0 and and n >>=1 will not change n. The only change
    will be for result <<=1, i.e. shifting result to the left by 1 digit.
    Since there we have i=4 to i = 31 iterations left, this will result
    in padding 28 0's to the right of result. i.e at the end, we get
    result = 1011_0000_0000_0000_0000_0000_0000_0000

    "*/

//This is exactly what we expected to get

    public int reverseBits4(int n) {
        if (n == 0) return 0;

        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            if ((n & 1) == 1) result++;
            n >>= 1;
        }
        return result;
    }
}
