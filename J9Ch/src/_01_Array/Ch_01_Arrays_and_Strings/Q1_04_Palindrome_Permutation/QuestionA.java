package _01_Array.Ch_01_Arrays_and_Strings.Q1_04_Palindrome_Permutation;

/*
PPalindrome Permutation: Given a string, write a function to check if it is a permutation of a palin- drome. A palindrome is a word or phrase that is the same forwards and backwards. A permutation is a rearrangement of letters.The palindrome does not need to be limited to just dictionary words.
EXAMPLE
Input: Tact Coa
Output: True (permutations: "taco cat". "atco cta". etc.)
 */

public class QuestionA {	
	public static boolean checkMaxOneOdd(int[] table) {
		boolean foundOdd = false;
		for (int count : table) {
			if (count % 2 == 1) {
				if (foundOdd) {
					return false;
				}
				foundOdd = true;
			}
		}
		return true;
	}
	
	public static boolean isPermutationOfPalindrome(String phrase) {
		int[] table = Common.buildCharFrequencyTable(phrase);
		return checkMaxOneOdd(table);
	}

////////////////////////////////////////////////////////////////

    public static boolean isPermutationOfPalindrome2(String phrase) {
        int countOdd = 0;
        int[] table = new int[Character.getNumericValue('z')
                - Character.getNumericValue('a') + 1];
        for (char c : phrase.toCharArray()) {
            int x = Common.getCharNumber(c);
            if (x != -1) {
                table[x]++;

                if (table[x] % 2 == 1) {
                    countOdd++;
                } else {
                    countOdd--;
                }
            }
        }
        return countOdd <= 1;
    }

////////////////////////////////////////////////////////////////

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

    public static boolean isPermutationOfPalindrome3(String phrase) {
        int bitVector = createBitVector(phrase);
        return bitVector == 0 || checkExactlyOneBitSet(bitVector);
    }

////////////////////////////////////////////////////////////////



	public static void main(String[] args) {
		String pali = "Rats live on no evil star";
		System.out.println(isPermutationOfPalindrome(pali));
	}


}


