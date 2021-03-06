package Ch_01_Arrays_and_Strings.Q1_09_String_Rotation;

/*
String Rotation: Assume you have amethod isSubstring which checks ifone word is asubstring of another. Given two strings, 51 and 52, write code to check if 52 is a rotation of 51 using only one call to isSubstring (e.g.,"waterbottle"is a rotation of"erbottlewat").
 */

import org.junit.Test;

public class Question {
	public static boolean isSubstring(String big, String small) {
		if (big.indexOf(small) >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isRotation(String s1, String s2) {
	    int len = s1.length();

	    /* check that s1 and s2 are equal length and not empty */
	    if (len == s2.length() && len > 0) {

	    	/* concatenate s1 and s1 within new buffer */
	    	String s1s1 = s1 + s1;
	    	return isSubstring(s1s1, s2);
	    }
	    return false;
	}

	public boolean isRotationMy(String s1, String s2){
        String s1s1 = s1 + s1;
        return isSubstring(s1s1, s2);
    }

    @Test
    public void test01(){
        String[][] pairs = {{"apple", "pleap"}, {"waterbottle", "erbottlewat"},
                {"camera", "macera"},{"",""}};
        for (String[] pair : pairs) {
            String word1 = pair[0];
            String word2 = pair[1];
            boolean is_rotation = isRotation(word1, word2);
            System.out.println(word1 + ", " + word2 + ": " + is_rotation);
        }
    }
	
	public static void main(String[] args) {
		String[][] pairs = {{"apple", "pleap"}, {"waterbottle", "erbottlewat"},
                {"camera", "macera"}};
		for (String[] pair : pairs) {
			String word1 = pair[0];
			String word2 = pair[1];
			boolean is_rotation = isRotation(word1, word2);
			System.out.println(word1 + ", " + word2 + ": " + is_rotation);
		}
	}

}
