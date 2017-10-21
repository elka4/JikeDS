package _08_Bit.Ch_05_Bit_Manipulation.Q5_03_Flip_Bit_to_Win;
/*
给一个数，可以将这个数二进制形式的任意一个0转变为1。这种操作能得到的最长连续1是多长？
 */

public class QuestionD {

	public static int flipBit(int a) {
		/* If all 1s, this is already the longest sequence. */
		if (~a == 0) return Integer.BYTES * 8;
		
		int currentLength = 0;
		int previousLength = 0;
		int maxLength = 1; // We can always have a sequence of at least one 1
		while (a != 0) {
			if ((a & 1) == 1) {
				currentLength++;
			} else if ((a & 1) == 0) {
				/* Update to 0 (if next bit is 0) or currentLength (if next bit is 1). */
				previousLength = (a & 2) == 0 ? 0 : currentLength;
				currentLength = 0;
			}
			maxLength = Math.max(previousLength + currentLength + 1, maxLength);
			a >>>= 1;
		}
		return maxLength;
	}
	
	public static void main(String[] args) {
		int[][] cases = {{-1, 32}, {Integer.MAX_VALUE, 32}, {-10, 31}, {0, 1}, 
				{1, 2}, {15, 5}, {1775, 8}};
		for (int[] c : cases) {
			int x = flipBit(c[0]);
			boolean r = (c[1] == x);
			System.out.println(c[0] + ": " + x + ", " + c[1] + " " + r);
		}

	}

}

/*
5.3
Flip Bit to Win: You have an integer and you can flip exactly one bit from a 0
to a 1. Write code to find the length of the longest sequence of 1s you could create.
EXAMPLE
Input: 1775 ( o r : 1113111131111) Output: 8
 */
