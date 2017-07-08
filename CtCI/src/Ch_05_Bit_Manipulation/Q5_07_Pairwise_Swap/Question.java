package Ch_05_Bit_Manipulation.Q5_07_Pairwise_Swap;

import CtCILibrary.AssortedMethods;
import org.junit.Test;

public class Question {

	public static int swapOddEvenBits(int x) { 

	    return ( ((x & 0xaaaaaaaa) >>> 1) | ((x & 0x55555555) << 1) );
	}
	
	public static void main(String[] args) {
		int a = 234321;
		System.out.println(a + ": " + AssortedMethods.toFullBinaryString(a));
		int b = swapOddEvenBits(a);
		System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
	}

	@Test
    public void test01(){
        int a = 1;
        System.out.println(a + ": " + AssortedMethods.toFullBinaryString(a));
        int b = swapOddEvenBits(a);
        System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
    }
    @Test
    public void test02(){
        int a = 2;
        System.out.println(a + ": " + AssortedMethods.toFullBinaryString(a));
        int b = swapOddEvenBits(a);
        System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
    }
    @Test
    public void test03(){
        int a = 3;
        System.out.println(a + ": " + AssortedMethods.toFullBinaryString(a));
        int b = swapOddEvenBits(a);
        System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
    }
    @Test
    public void test04(){
        int a = 4;
        System.out.println(a + ": " + AssortedMethods.toFullBinaryString(a));
        int b = swapOddEvenBits(a);
        System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
    }
    @Test
    public void test05(){
        int a = 5;
        System.out.println(a + ":  " + AssortedMethods.toFullBinaryString(a));
        int b = swapOddEvenBits(a);
        System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
    }
}
