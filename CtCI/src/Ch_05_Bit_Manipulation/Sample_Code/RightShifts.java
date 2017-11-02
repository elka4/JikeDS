package Ch_05_Bit_Manipulation.Sample_Code;

import CtCILibrary.AssortedMethods;

public class RightShifts {
	public static int repeatedArithmeticShift(int x, int count) {
		for (int i = 0; i < count; i++) {
			x >>= 1; // Arithmetic shift by 1
		}
		return x;
	}
	
	public static int repeatedLogicalShift(int x, int count) {
		for (int i = 0; i < count; i++) {
			x >>>= 1; // Logical shift by 1
		}
		return x;
	}	
	
	public static void main(String[] args) {
		for (int i = 8; i >= -8; i--) {
			System.out.println(AssortedMethods.toFullBinaryString(i) + ": " + i);
		}
		
		int x = -93242;
		int resultArithmetic = repeatedArithmeticShift(x, 40);
		int resultLogical = repeatedLogicalShift(x, 40);
		System.out.println(AssortedMethods.toFullBinaryString(resultArithmetic)
                + ": " + resultArithmetic);
		System.out.println(AssortedMethods.toFullBinaryString(resultLogical)
                + ": " + resultLogical);
	}

	/*
00000000000000000000000000001000: 8
00000000000000000000000000000111: 7
00000000000000000000000000000110: 6
00000000000000000000000000000101: 5
00000000000000000000000000000100: 4
00000000000000000000000000000011: 3
00000000000000000000000000000010: 2
00000000000000000000000000000001: 1
00000000000000000000000000000000: 0
11111111111111111111111111111111: -1
11111111111111111111111111111110: -2
11111111111111111111111111111101: -3
11111111111111111111111111111100: -4
11111111111111111111111111111011: -5
11111111111111111111111111111010: -6
11111111111111111111111111111001: -7
11111111111111111111111111111000: -8
11111111111111111111111111111111: -1
00000000000000000000000000000000: 0
	 */

}
