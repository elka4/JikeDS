package Ch_10_Sorting_and_Searching.Q10_08_Find_Duplicates;

import CtCILibrary.AssortedMethods;

public class Question {

	public static void checkDuplicates(int[] array) {
		BitSet bs = new BitSet(32000);
		for (int i = 0; i < array.length; i++) {
			int num = array[i];
			int num0 = num - 1; // bitset starts at 0, numbers start at 1
			if (bs.get(num0)) { 
				System.out.println(num);
			} else {
				bs.set(num0);				
			}
		}
	}
	
	public static void main(String[] args) {
		int[] array = AssortedMethods.randomArray(30, 1, 30);
		System.out.println(AssortedMethods.arrayToString(array));
		checkDuplicates(array);
	}
/*
22, 14, 22, 15, 11, 15, 22, 25, 13, 22, 1, 6, 9, 5, 3, 6, 27, 3, 4, 19, 19, 30, 10, 18, 26, 24, 25, 11, 16, 4,

22
15
22
22
6
3
19
25
11
4
 */
}
