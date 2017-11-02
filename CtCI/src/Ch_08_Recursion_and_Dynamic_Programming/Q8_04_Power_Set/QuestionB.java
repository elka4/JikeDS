package Ch_08_Recursion_and_Dynamic_Programming.Q8_04_Power_Set;

import CtCILibrary.AssortedMethods;

import java.util.ArrayList;

public class QuestionB {
	
	public static ArrayList<Integer> convertIntToSet(int x, ArrayList<Integer> set) {
		ArrayList<Integer> subset = new ArrayList<Integer>(); 
		int index = 0;
		for (int k = x; k > 0; k >>= 1) {
            System.out.println(AssortedMethods.toFullBinaryString(k) + " k");
            System.out.println(AssortedMethods.toFullBinaryString(k & 1) + " (k & 1)");
			if ((k & 1) == 1) {
				subset.add(set.get(index));
			}
			index++;
		}
		return subset;
	}
	
	public static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set) {
		ArrayList<ArrayList<Integer>> allsubsets = new ArrayList<ArrayList<Integer>>();
		int max = 1 << set.size(); /* Compute 2^n */
        System.out.println(set.size() + " set.size()");
        System.out.println(AssortedMethods.toFullBinaryString(max) + " max " + max);

        for (int k = 0; k < max; k++) {
			ArrayList<Integer> subset = convertIntToSet(k, set);
			allsubsets.add(subset);
		}
		return allsubsets;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {
			list.add(i);
		}
		
		ArrayList<ArrayList<Integer>> subsets2 = getSubsets(list);
		System.out.println(subsets2.toString());		
	}
/*
            3 set.size()
            00000000000000000000000000001000 max 8
            00000000000000000000000000000001 k
            00000000000000000000000000000001 (k & 1)
            00000000000000000000000000000010 k
            00000000000000000000000000000000 (k & 1)
            00000000000000000000000000000001 k
            00000000000000000000000000000001 (k & 1)
            00000000000000000000000000000011 k
            00000000000000000000000000000001 (k & 1)
            00000000000000000000000000000001 k
            00000000000000000000000000000001 (k & 1)
            00000000000000000000000000000100 k
            00000000000000000000000000000000 (k & 1)
            00000000000000000000000000000010 k
            00000000000000000000000000000000 (k & 1)
            00000000000000000000000000000001 k
            00000000000000000000000000000001 (k & 1)
            00000000000000000000000000000101 k
            00000000000000000000000000000001 (k & 1)
            00000000000000000000000000000010 k
            00000000000000000000000000000000 (k & 1)
            00000000000000000000000000000001 k
            00000000000000000000000000000001 (k & 1)
            00000000000000000000000000000110 k
            00000000000000000000000000000000 (k & 1)
            00000000000000000000000000000011 k
            00000000000000000000000000000001 (k & 1)
            00000000000000000000000000000001 k
            00000000000000000000000000000001 (k & 1)
            00000000000000000000000000000111 k
            00000000000000000000000000000001 (k & 1)
            00000000000000000000000000000011 k
            00000000000000000000000000000001 (k & 1)
            00000000000000000000000000000001 k
            00000000000000000000000000000001 (k & 1)
            [[], [0], [1], [0, 1], [2], [0, 2], [1, 2], [0, 1, 2]]
 */
}
