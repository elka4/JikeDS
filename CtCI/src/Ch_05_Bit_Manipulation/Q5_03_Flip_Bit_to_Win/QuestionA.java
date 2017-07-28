package Ch_05_Bit_Manipulation.Q5_03_Flip_Bit_to_Win;

import org.junit.Test;

/*
给一个数，可以将这个数二进制形式的任意一个0转变为1。这种操作能得到的最长连续1是多长？
 */

public class QuestionA {
	
	public static int SEQUENCE_LENGTH = 32;
	
	public static boolean getBit(int num, int i) {
		return ((num & (1 << i)) != 0);
	}	
	
	public static int longestSequence(int n) {
		int maxSeq = 0;
		
		for (int i = 0; i < SEQUENCE_LENGTH; i++) {
			maxSeq = Math.max(maxSeq,  longestSequenceOf1s(n, i));
		}
		
		return maxSeq;
	}
	
	public static int longestSequenceOf1s(int n, int indexToIgnore) {
		int max = 0;
		int counter = 0;
		for (int i = 0; i < SEQUENCE_LENGTH; i++) {
			if (i == indexToIgnore || getBit(n, i)) {
				counter++;
				max = Math.max(counter, max);
			} else {
				counter = 0;
			}
		}
		return max;
	}	
	
	public static void main(String[] args) {
		int original_number = Integer.MAX_VALUE;
		int new_number = longestSequence(original_number);
			
		System.out.println(Integer.toBinaryString(original_number));
		System.out.println(new_number);			
	}
    @Test
    public void test01(){
        int original_number = 1775;
        int new_number = longestSequence(original_number);

        System.out.println(Integer.toBinaryString(original_number));
        System.out.println(new_number);
    }
    @Test
    public void test02(){
        int original_number = 8;
        int new_number = longestSequence(original_number);

        System.out.println(Integer.toBinaryString(original_number));
        System.out.println(new_number);
    }

}

/*
5.3
Flip Bit to Win: You have an integer and you can flip exactly one bit from a 0
to a 1. Write code to find the length of the longest sequence of 1s you could create.
EXAMPLE
Input: 1775 ( o r : 1113111131111) Output: 8
 */
