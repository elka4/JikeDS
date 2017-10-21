package _01_Array.Ch_01_Arrays_and_Strings.Q1_05_One_Away;

/*
One Away: There are three types of edits that can be performed on strings: insert a character, remove a character, or replace a character. Given two strings, write a function to check if they are one edit (or zero edits) away.
EXAMPLE
pale, pIe -> true pales. pale -> true pale. bale -> true pale. bake -> false
 */

public class QuestionA {

	public static boolean oneEditReplace(String s1, String s2) {
	    //记住这种检查boolean的做法，先设成false，
        // 满足条件的时候设成true，再次满足的时候return false
		boolean foundDifference = false;
		for (int i = 0; i < s1.length(); i++) {             //这里是相同的
			if (s1.charAt(i) != s2.charAt(i)) {
				if (foundDifference) {
					return false;
				}
				
				foundDifference = true;
			}
		}
		return true;
	}
	
	/* Check if you can insert a character into s1 to make s2. */
	public static boolean oneEditInsert(String s1, String s2) {
		int index1 = 0;
		int index2 = 0;
		while (index2 < s2.length() && index1 < s1.length()) {
		    //两个char不同，但是index相同的时候，index2++，给一次机会。
            //再次出现这种情况的时候，return false
			if (s1.charAt(index1) != s2.charAt(index2)) {    //这里是相同的
				if (index1 != index2) {
					return false;
				}		
				index2++;
			} else {
				index1++;
				index2++;
			}
		}
		return true;
	}	
	
	public static boolean oneEditAway(String first, String second) {
		if (first.length() == second.length()) {
			return oneEditReplace(first, second);
		} else if (first.length() + 1 == second.length()) {
			return oneEditInsert(first, second);
		} else if (first.length() - 1 == second.length()) {
			return oneEditInsert(second, first);
		} 
		return false;
	}
	
	public static void main(String[] args) {
		String a = "pse";
		String b = "pale";
		boolean isOneEdit = oneEditAway(a, b);
		System.out.println(a + ", " + b + ": " + isOneEdit);
	}

}
