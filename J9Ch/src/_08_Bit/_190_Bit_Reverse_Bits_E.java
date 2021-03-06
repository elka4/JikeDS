package _08_Bit;
import java.util.*;
import org.junit.Test;

//  190. Reverse Bits
//  https://leetcode.com/problems/reverse-bits/description/
//  13
//
public class _190_Bit_Reverse_Bits_E {
//------------------------------------------------------------------------------
    //1
    //Java Solution and Optimization
    //The Java solution is straightforward, just bitwise operation:
    class Solution1{
        public int reverseBits(int n) {
            int result = 0;
            for (int i = 0; i < 32; i++) {
                result += n & 1;
                n >>>= 1;   // CATCH: must do unsigned shift
                if (i < 31) // CATCH: for last digit, don't shift!
                    result <<= 1;
            }
            return result;
        }
    }

//------------------------------------------------------------------------------
    //2
    //How to optimize if this function is called multiple times? We can divide an int into 4 bytes, and reverse each byte then combine into an int. For each byte, we can use cache to improve performance.
    // cache
    class Solution2{
        public int reverseBits(int n) {
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

        private final Map<Byte, Integer> cache = new HashMap<Byte, Integer>();
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
    }


//------------------------------------------------------------------------------
    //4
    //Same idea, but the running time is almost the same as before for test cases on OJ.
    public class Solution4 {
        // you need treat n as an unsigned value
        public int reverseBits(int n) {
            if(base == null) {
                base = new int[256];
                for(int i = 0; i < 256; ++i) base[i] = reverseBitsByte(i);
            }

            int result = 0;
            for(int i = 0; i < 4; ++i) {
                int low = n&0xFF;
                low = base[low];
                result <<= 8;
                result |= low;
                n = n >>> 8;
            }
            return result;
        }

        private int reverseBitsByte(int n) {
            int result = 0;
            for(int i = 0; i < 8; ++i) {
                int low = n&1;
                result <<= 1;
                result = result | low;
                n = n >>> 1;
            }
            return result;
        }

        private int[] base = null;
    }
//------------------------------------------------------------------------------
    //5
    //Great solution. Here is my version based on your solution
    public class Solution5 {
        // you need treat n as an unsigned value
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        public int reverseBits(int n) {
            int res = 0;
            for(int i = 0; i < 4; i++){
                int tmp = n & 0xFF;
                if(map.containsKey(tmp)){
                    res = (res << 8) + map.get(tmp);
                } else{
                    res = (res << 8) + reverse8Bits(tmp);
                }
                n >>= 8;
            }
            return res;
        }

        private int reverse8Bits(int n){
            int res = 0;
            int tmp = n;
            for(int i = 0; i < 8; i++){
                res = (res << 1) + (tmp & 1);
                tmp >>= 1;
            }
            map.put(n, res);
            return res;
        }
    }


//------------------------------------------------------------------------------
    //8
    // 9Ch
    // you need treat n as an unsigned value
    class Solution8{
        public int reverseBits(int n) {
            int reversed = 0;
            for (int i = 0; i < 32; i++) {
                reversed = (reversed << 1) | (n & 1);
                n = (n >> 1);
            }
            return reversed;
        }
    }


//------------------------------------------------------------------------------

    //6
    //  Sharing my 2ms Java Solution with Explanation
    //  https://discuss.leetcode.com/topic/42572/sharing-my-2ms-java-solution-with-explanation/2
    class Solution6{
        public int reverseBits5(int n) {
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

//------------------------------------------------------------------------------
    //10
    class Solution10{
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
    }

//------------------------------------------------------------------------------
    //11
   /* Java Solution and Optimization
    The Java solution is straightforward, just bitwise operation:*/
    class Solution11{
        public int reverseBits(int n) {
            int result = 0;
            for (int i = 0; i < 32; i++) {
                result += n & 1;
                n >>>= 1;   // CATCH: must do unsigned shift
                if (i < 31) // CATCH: for last digit, don't shift!
                    result <<= 1;
            }
            return result;
        }
    }

//------------------------------------------------------------------------------
}
/*------------------------------------------------------------------------------
Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).

Follow up:
If this function is called many times, how would you optimize it?
------------------------------------------------------------------------------*/

/*------------------------------------------------------------------------------
LeetCode – Reverse Bits (Java)

Problem

Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).

Follow up:
If this function is called many times, how would you optimize it?

Related problem: Reverse Integer
------------------------------------------------------------------------------*/