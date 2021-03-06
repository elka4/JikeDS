package Ch_01_Arrays_and_Strings.Q1_04_Palindrome_Permutation;

import org.junit.Test;

public class Common {

	public static int getCharNumber(Character c) {
		int a = Character.getNumericValue('a');
		int z = Character.getNumericValue('z');
		
		int val = Character.getNumericValue(c);
		if (a <= val && val <= z) {
			return val - a;
		}
		return -1;
	}
	
	public static int[] buildCharFrequencyTable(String phrase) {
		int[] table = new int[Character.getNumericValue('z')
				            - Character.getNumericValue('a') + 1];

		for (char c : phrase.toCharArray()) {
			int x = getCharNumber(c);
			if (x != -1) {
				table[x]++;
			}
		}
		return table;
	}

	@Test
	public void test01(){
		System.out.println(Character.getNumericValue('a'));//10
		System.out.println(Character.getNumericValue('z'));//35
		System.out.println(Character.getNumericValue('A'));//10
		System.out.println(Character.getNumericValue('Z'));//35
	}
	@Test
	public void test02(){
		System.out.println((int)('a'));//97
		System.out.println((int)('A'));//65

	}
}
