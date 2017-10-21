package _08_Bit.Ch_05_Bit_Manipulation.Q5_04_Next_Number;


/*
用和当前数字同样数目的1，能组成的最大和最小的数
 */
public class QuestionA {

	public static int countOnes(int i) {
		int count = 0;
		while (i > 0) {
			if ((i & 1) == 1) {
				count++;
			}
			i = i >> 1;
		}
		return count;
	}
	
	public static int countZeros(int i) {
		return 32 - countOnes(i);
	}	
	
	public static boolean hasValidNext(int i) {
		if (i == 0) {
			return false;
		}
		int count = 0;
		while ((i & 1) == 0) {
			i >>= 1;
			count++;
		}
		while ((i & 1) == 1) {
			i >>= 1;
			count++;
		}		
		if (count == 31) {
			return false;
		}
		return true;	
	}
	
	public static boolean hasValidPrev(int i) {
		while ((i & 1) == 1) {
			i >>= 1;
		}
		if (i == 0) {
			return false;
		}
		return true;		
	}

	public static int getNextSlow(int i) {
		if (!hasValidNext(i)) {
			return -1;
		}
		int num_ones = countOnes(i);
		i++;
		while (countOnes(i) != num_ones) {
			i++;
		}
		return i;
	}

	public static int getPrevSlow(int i) {
		if (!hasValidPrev(i)) {
			return -1;
		}		
		int num_ones = countOnes(i);
		i--;
		while (countOnes(i) != num_ones) {
			i--;
		}
		return i;
	}
	
	public static void main(String[] args) {
		int i = 13948;
        System.out.println(i+": " + Integer.toBinaryString(i));
        int p1 = getPrevSlow(i);
		int n1 = getNextSlow(i);
		Tester.binPrint(p1);
		Tester.binPrint(n1);
	}

}

/*
5.4
Next Number: Given a positive integer, print the next smallest and the next
largest number that have the same number of 1 bits in their binary representation.
 */
