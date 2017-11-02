package Ch_05_Bit_Manipulation.Q5_07_Pairwise_Swap;

import CtCILibrary.AssortedMethods;
import org.junit.Test;
//翻转奇数位bit和偶数位bit，0和1，2和3。。。。。
public class Question {

	public static int swapOddEvenBits(int x) { 

	    return ( ((x & 0xaaaaaaaa) >>> 1) | ((x & 0x55555555) << 1) );
	}
	
	public static void main(String[] args) {
		int a = 234321;
		System.out.println(a + ": " + AssortedMethods.toFullBinaryString(a));
		int b = swapOddEvenBits(a);
		System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
		/*
		234321: 00000000000000111001001101010001
        222114: 00000000000000110110001110100010
		 */
	}
	/*
                10101010101010101010101010101010
                01010101010101010101010101010101
	 */
    @Test
    public void test00(){
	    //10101010101010101010101010101010
        System.out.println(Integer.toBinaryString(0xaaaaaaaa));
        //01010101010101010101010101010101
        System.out.println(Integer.toBinaryString(0x55555555));
    }
    /*
                10101010101010101010101010101010
                01010101010101010101010101010101
     */


	@Test
    public void test01(){
        int a = 1;
        System.out.println(a + ": " + AssortedMethods.toFullBinaryString(a));
        int b = swapOddEvenBits(a);
        System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
    }
    /*
                1: 00000000000000000000000000000001
                2: 00000000000000000000000000000010
     */
    @Test
    public void test02(){
        int a = 2;
        System.out.println(a + ": " + AssortedMethods.toFullBinaryString(a));
        int b = swapOddEvenBits(a);
        System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
    }
    /*
                2: 00000000000000000000000000000010
                1: 00000000000000000000000000000001
     */
    @Test
    public void test03(){
        int a = 3;
        System.out.println(a + ": " + AssortedMethods.toFullBinaryString(a));
        int b = swapOddEvenBits(a);
        System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
    }
    /*
                3: 00000000000000000000000000000011
                3: 00000000000000000000000000000011
     */
    @Test
    public void test04(){
        int a = 4;
        System.out.println(a + ": " + AssortedMethods.toFullBinaryString(a));
        int b = swapOddEvenBits(a);
        System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
    }
    /*
                4: 00000000000000000000000000000100
                8: 00000000000000000000000000001000
     */
    @Test
    public void test05(){
        int a = 5;
        System.out.println(a + ":  " + AssortedMethods.toFullBinaryString(a));
        int b = swapOddEvenBits(a);
        System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
    }
    /*
                5:  00000000000000000000000000000101
                10: 00000000000000000000000000001010
     */
}

/*
Pairwise Swap: Write a program to swap odd and even bits in an integer with as
 few instructions as possible (e.g., bit 0 and bit 1 are swapped,
 bit 2 and bit 3 are swapped, and so on).
*/
