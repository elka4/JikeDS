package HF.HF_bit;

public class _05FlipBits {
    /**
     *@param a, b: Two integer
     *return: An integer
     */
    public static int bitSwapRequired(int a, int b) {
        // write your code here
        int count = 0;
        for (int c = a ^ b; c != 0; c = c >>> 1) {
            count += c & 1;
        }
        return count;
    }
//-----------------------------------------------------------///?????

    /**
     *@param a, b: Two integer
     *return: An integer
     */
    public int countOnes(int num) {
        int count = 0;
        while (num != 0) {
            num = num & (num - 1);
            count++;
        }
        return count;
    }

    public int bitSwapRequired2(int a, int b) {
        // write your code here
        return countOnes(a ^ b);
    }
}
/*
Determine the number of bits required to flip if you want to convert integer n to integer m.

 Notice

Both n and m are 32-bit integers.

Example
Given n = 31 (11111), m = 14 (01110), return 2.
 */