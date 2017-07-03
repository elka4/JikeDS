package Ch_01_Arrays_and_Strings.Q1_04_Palindrome_Permutation;

import org.junit.Test;

public class QuestionC {
	/* Toggle the ith bit in the integer. */
	public static int toggle(int bitVector, int index) {
		if (index < 0) return bitVector;
		
		int mask = 1 << index;
		if ((bitVector & mask) == 0) {
			System.out.println("bitVector & mask == 0 " + index);
			System.out.println(Integer.toBinaryString(bitVector));
			System.out.println(Integer.toBinaryString(mask));

			bitVector |= mask;
			System.out.println(Integer.toBinaryString(bitVector));
		} else {
			System.out.println("bitVector & mask != 0 "+ index);
			System.out.println(Integer.toBinaryString(bitVector));
			System.out.println(Integer.toBinaryString(mask));
			bitVector &= ~mask;
			System.out.println(Integer.toBinaryString(bitVector));
		}
		return bitVector;
	}
	
	/* Create bit vector for string. For each letter with value i,
	 * toggle the ith bit. */
	public static int createBitVector(String phrase) {
		int bitVector = 0;
		for (char c : phrase.toCharArray()) {
			int x = Common.getCharNumber(c);
			System.out.println(c);
			bitVector = toggle(bitVector, x);
		}
		return bitVector;
	}
	
	/* Check that exactly one bit is set by subtracting one from the 
	 * integer and ANDing it with the original integer. */
	public static boolean checkExactlyOneBitSet(int bitVector) {
		return (bitVector & (bitVector - 1)) == 0;
	}
	
	public static boolean isPermutationOfPalindrome(String phrase) {
		int bitVector = createBitVector(phrase);
		return bitVector == 0 || checkExactlyOneBitSet(bitVector);
	}
	
	public static void main(String[] args) {
		String pali = "Rats live on no evil star";
		System.out.println(isPermutationOfPalindrome(pali));
	}
	@Test
	public void test01(){
		String pali = "az";
		System.out.println(isPermutationOfPalindrome(pali));
	}

	@Test
	public void test02(){
		String pali = "azc";
		System.out.println(isPermutationOfPalindrome(pali));
	}

}

/*
		R
		bitVector & mask == 0 17
		0
		100000000000000000
		100000000000000000
		a
		bitVector & mask == 0 0
		100000000000000000
		1
		100000000000000001
		t
		bitVector & mask == 0 19
		100000000000000001
		10000000000000000000
		10100000000000000001
		s
		bitVector & mask == 0 18
		10100000000000000001
		1000000000000000000
		11100000000000000001

		l
		bitVector & mask == 0 11
		11100000000000000001
		100000000000
		11100000100000000001
		i
		bitVector & mask == 0 8
		11100000100000000001
		100000000
		11100000100100000001
		v
		bitVector & mask == 0 21
		11100000100100000001
		1000000000000000000000
		1011100000100100000001
		e
		bitVector & mask == 0 4
		1011100000100100000001
		10000
		1011100000100100010001

		o
		bitVector & mask == 0 14
		1011100000100100010001
		100000000000000
		1011100100100100010001
		n
		bitVector & mask == 0 13
		1011100100100100010001
		10000000000000
		1011100110100100010001

		n
		bitVector & mask != 0 13
		1011100110100100010001
		10000000000000
		1011100100100100010001
		o
		bitVector & mask != 0 14
		1011100100100100010001
		100000000000000
		1011100000100100010001

		e
		bitVector & mask != 0 4
		1011100000100100010001
		10000
		1011100000100100000001
		v
		bitVector & mask != 0 21
		1011100000100100000001
		1000000000000000000000
		11100000100100000001
		i
		bitVector & mask != 0 8
		11100000100100000001
		100000000
		11100000100000000001
		l
		bitVector & mask != 0 11
		11100000100000000001
		100000000000
		11100000000000000001

		s
		bitVector & mask != 0 18
		11100000000000000001
		1000000000000000000
		10100000000000000001
		t
		bitVector & mask != 0 19
		10100000000000000001
		10000000000000000000
		100000000000000001
		a
		bitVector & mask != 0 0
		100000000000000001
		1
		100000000000000000
		r
		bitVector & mask != 0 17
		100000000000000000
		100000000000000000
		0
		true

		Process finished with exit code 0
*/
