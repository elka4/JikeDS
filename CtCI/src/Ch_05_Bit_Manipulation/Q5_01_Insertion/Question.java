package Ch_05_Bit_Manipulation.Q5_01_Insertion;

import CtCILibrary.AssortedMethods;
import org.junit.Test;

public class Question {

	public static int updateBits(int n, int m, int i, int j) {
		// Validation
		if (i >= 32 || j < i) {
			return 0;
		}
		
		/* Create a mask to clear bits i through j in n
		/* EXAMPLE: i = 2, j = 4. Result should be 11100011.
		 * (Using 8 bits for this example.  This is obviously not actually 8 bits.)
		 */
		int allOnes = ~0; // allOnes = 11111111
        System.out.println(AssortedMethods.toFullBinaryString(allOnes) + " allOnes; int allOnes = ~0;");
		int left = allOnes << (j + 1); // 1s through position j, then 0s. left = 11100000
        System.out.println(AssortedMethods.toFullBinaryString(left) + " left;   int left = allOnes << (j + 1); 1s through position j");
	  	int right = ((1 << i) - 1); // 1�s after position i.  right = 00000011
        //int k = i;
        System.out.println(AssortedMethods.toFullBinaryString(1 << i) + " 1 << i");
        System.out.println(AssortedMethods.toFullBinaryString(right) + " right;   int right = ((1 << i) - 1); // 1�s after position i.");

        int mask = left | right; // All 1s, except for 0s between i and j. mask = 11100011
        System.out.println(AssortedMethods.toFullBinaryString(mask) + " mask;   int mask = left | right; // All 1s, except for 0s between i and j.");

		/* Clear i through j, then put m in there */
		int n_cleared = n & mask; // Clear bits j through i.
        System.out.println(AssortedMethods.toFullBinaryString(n_cleared) + " n_cleared; int n_cleared = n & mask; // Clear bits j through i.");

        int m_shifted = m << i; // Move m into correct position.
        System.out.println(AssortedMethods.toFullBinaryString(m_shifted) + " m_shifted; int m_shifted = m << i; // Move m into correct position.");

		/* OR them, and we're done! */
        System.out.println("========================");
        return n_cleared | m_shifted;
	}
	
	public static void main(String[] args) {
		int a = 103217;
		System.out.println(AssortedMethods.toFullBinaryString(a));
        //System.out.println(Integer.toBinaryString(a));
		int b = 13;
		System.out.println(AssortedMethods.toFullBinaryString(b));		
		int c = updateBits(a, b, 4, 12);
		System.out.println(AssortedMethods.toFullBinaryString(c));
	}

	@Test
    public void test01(){
        int a = 103217;
        System.out.println(Integer.toBinaryString(a));
    }
	@Test
    public void test02(){
        int a = Integer.MIN_VALUE;
        System.out.println(AssortedMethods.toFullBinaryString(a));
        //System.out.println(Integer.toBinaryString(a));
        System.out.println("aaaaaaaaaaaaaaa");
        int b = -1;
        System.out.println(AssortedMethods.toFullBinaryString(b));
        System.out.println("bbbbbbbbbbbbbbbbbb");

        int c = updateBits(a, b, 10, 23);
        System.out.println(AssortedMethods.toFullBinaryString(c));
        System.out.println("ccccccccccccccccccc");

    }
    @Test
    public void test03(){
        int a = Integer.MIN_VALUE;
        System.out.println(AssortedMethods.toFullBinaryString(a) + " n");
        //System.out.println(Integer.toBinaryString(a));
        System.out.println("aaaaaaaaaaaaaaa");
        int b = 13;
        System.out.println(AssortedMethods.toFullBinaryString(b) + " m");
        System.out.println("bbbbbbbbbbbbbbbbbb");
        int c = updateBits(a, b, 3, 6);
        System.out.println(AssortedMethods.toFullBinaryString(c) + " n_cleared | m_shifted");
        System.out.println("ccccccccccccccccccc");

        /*
10000000000000000000000000000000 n
aaaaaaaaaaaaaaa
00000000000000000000000000001101 m
bbbbbbbbbbbbbbbbbb
11111111111111111111111111111111 allOnes; int allOnes = ~0;
11111111111111111111111110000000 left;   int left = allOnes << (j + 1); 1s through position j
00000000000000000000000000001000 1 << i
00000000000000000000000000000111 right;   int right = ((1 << i) - 1); // 1?s after position i.
11111111111111111111111110000111 mask;   int mask = left | right; // All 1s, except for 0s between i and j.
10000000000000000000000000000000 n_cleared; int n_cleared = n & mask; // Clear bits j through i.
00000000000000000000000001101000 m_shifted; int m_shifted = m << i; // Move m into correct position.
========================
10000000000000000000000001101000 n_cleared | m_shifted
ccccccccccccccccccc
         */

    }

}
