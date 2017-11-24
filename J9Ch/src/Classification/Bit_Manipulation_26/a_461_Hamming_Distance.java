package Classification.Bit_Manipulation_26;
import java.util.*;

public class a_461_Hamming_Distance {
    /*
    What does come to your mind first when you see this sentence "corresponding bits
    are different"? Yes, XOR! Also, do not forget there is a decent function Java
     provided: Integer.bitCount() ~~~


     */
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

//------------------------------------------------------------------------------///////
public int hammingDistance2(int x, int y) {
    int xor = x ^ y, count = 0;
    for (int i=0;i<32;i++) count += (xor >> i) & 1;
    return count;
}
//------------------------------------------------------------------------------///////
//The problem is basically the same as counting the 1 bits in an integer,
// and the useful trick to do that is : xor & (xor - 1) will eliminate the last 1 bit in a integer.

    public int hammingDistance3(int x, int y) {
        int xor = x ^ y, count = 0;

        while (xor != 0) {
            xor &= (xor - 1);
            count++;
        }
        return count;
    }
//------------------------------------------------------------------------------///////
//------------------------------------------------------------------------------///////
//------------------------------------------------------------------------------///////
}

/*
The Hamming distance between two integers is the number of positions at which
the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note:
0 ≤ x, y < 231.

Example:

Input: x = 1, y = 4

Output: 2

Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ?   ?

The above arrows point to positions where the corresponding bits are different.
 */